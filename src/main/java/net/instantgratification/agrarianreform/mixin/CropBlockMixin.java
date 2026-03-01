package net.instantgratification.agrarianreform.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.instantgratification.agrarianreform.AgrarianGameRules;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * CropBlockMixin: Growth & Biodiversity
 * 
 * Enhances the growth logic for all blocks extending CropBlock.
 * - Polyculture: Calculates growth speed modifiers based on adjacent differing
 * crop types.
 * - Visual Feedback: Injects particles (happy villager) and sounds (rustle)
 * upon growth
 * or player interaction (brushing against stalks).
 */
@Mixin(CropBlock.class)
public abstract class CropBlockMixin {

    @Shadow
    public abstract boolean isMaxAge(BlockState state);

    @Unique
    private long agrarianreform$lastRustleTime = 0;

    // Phase 4: Polyculture & Phase 3: Rain Boost
    @ModifyReturnValue(method = "getGrowthSpeed", at = @At("RETURN"))
    private static float agrarianreform$modifyGrowthSpeed(float original, Block block, BlockGetter level,
            BlockPos pos) {
        float speed = original;

        // Phase 4: Polyculture (Biodiversity)
        if (level instanceof ServerLevel serverLevel) {
            if (serverLevel.getGameRules().get(AgrarianGameRules.HYDRO_POLYCULTURE_BOOST)) {
                // Check N/S/E/W neighbors.
                boolean hasDifferentCrop = false;
                Direction[] directions = { Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST };
                for (Direction dir : directions) {
                    BlockState neighborState = level.getBlockState(pos.relative(dir));
                    if (neighborState.is(BlockTags.CROPS) && neighborState.getBlock() != block) {
                        hasDifferentCrop = true;
                        break;
                    }
                }

                if (hasDifferentCrop) {
                    speed += 0.10f; // 10% base boost conceptually
                }
            }
        }

        return speed;
    }

    // Phase 3 & 5: Rain Growth Jump & Particle Feedback
    @Inject(method = "randomTick", at = @At("HEAD"))
    private void agrarianreform$preRandomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random,
            CallbackInfo ci) {
        if (!this.isMaxAge(state)) {
            // Check Rain Growth Spurt before the standard random tick logic executes
            if (level.isRainingAt(pos.above()) && level.canSeeSky(pos.above())) {
                int spurtAmount = level.getGameRules().get(AgrarianGameRules.HYDRO_RAIN_GROWTH_SPURT);
                if (spurtAmount > 0) {
                    CropBlock self = (CropBlock) (Object) this;
                    int currentAge = self.getAge(state);
                    int maxAge = self.getMaxAge();
                    int newAge = Math.min(maxAge, currentAge + spurtAmount);

                    if (newAge > currentAge) {
                        level.setBlock(pos, self.getStateForAge(newAge), Block.UPDATE_ALL);
                        // Send particle feedback manually since we jumped the age here
                        if (level.getGameRules().get(AgrarianGameRules.HYDRO_CROP_PARTICLES)) {
                            level.sendParticles(ParticleTypes.HAPPY_VILLAGER, pos.getX() + 0.5D, pos.getY() + 0.5D,
                                    pos.getZ() + 0.5D, 3, 0.25D, 0.25D, 0.25D, 0.0D);
                        }
                    }
                }
            }
        }
    }

    @Inject(method = "randomTick", at = @At("RETURN"))
    private void agrarianreform$postRandomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random,
            CallbackInfo ci) {
        // Did the block state change due to standard random ticking?
        BlockState newState = level.getBlockState(pos);
        if (newState.getBlock() == (Object) this && !newState.equals(state)) {
            if (level.getGameRules().get(AgrarianGameRules.HYDRO_CROP_PARTICLES)) {
                level.sendParticles(ParticleTypes.HAPPY_VILLAGER, pos.getX() + 0.5D, pos.getY() + 0.5D,
                        pos.getZ() + 0.5D, 3, 0.25D, 0.25D, 0.25D, 0.0D);
            }
        }
    }

    // Phase 5: Aesthetic Rustling Audio
    @Inject(method = "entityInside", at = @At("HEAD"))
    private void agrarianreform$playRustleSound(BlockState state, Level level, BlockPos pos, Entity entity,
            InsideBlockEffectApplier effectApplier, boolean isPrecise, CallbackInfo ci) {
        if (!(level instanceof ServerLevel serverLevel))
            return;
        if (this.isMaxAge(state) && serverLevel.getGameRules().get(AgrarianGameRules.HYDRO_CROP_RUSTLE)) {
            long time = serverLevel.getGameTime();
            // 10 tick cooldown to prevent audio spam
            if (time - this.agrarianreform$lastRustleTime > 10L) {
                // Determine if entity is moving sufficiently
                double motion = entity.getDeltaMovement().horizontalDistance();
                if (motion > 0.01D) {
                    this.agrarianreform$lastRustleTime = time;

                    // Randomize pitch
                    float pitch = 0.8F + serverLevel.getRandom().nextFloat() * 0.4F; // 0.8 to 1.2
                    float volume = 0.2F + serverLevel.getRandom().nextFloat() * 0.1F; // 0.2 to 0.3

                    serverLevel.playSound(null, pos, SoundEvents.GRASS_HIT, SoundSource.BLOCKS, volume, pitch);
                }
            }
        }
    }
}
