/*
 * Copyright (C) 2026 Dasik (Rifaditya)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package net.instantgratification.agrarianreform.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.dasik.social.api.gamerule.DynamicGameRuleManager;
import net.instantgratification.agrarianreform.AgrarianGameRules;
import net.instantgratification.agrarianreform.util.SoundHelper;
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
 * - Polyculture: Calculates growth speed modifiers based on adjacent differing crop types.
 * - Visual Feedback: Injects particles (happy villager) and sounds (rustle)
 *   upon growth or player interaction (brushing against stalks).
 * 
 * Verified against: CropBlock.java (26.2+)
 */
@Mixin(CropBlock.class)
public abstract class CropBlockMixin {

    @Shadow
    public abstract boolean isMaxAge(BlockState state);

    // Phase 4: Polyculture & Phase 3: Rain Boost
    @Unique
    @ModifyReturnValue(method = "getGrowthSpeed", at = @At("RETURN"))
    private static float agrarian_reform$modifyGrowthSpeed(float original, Block block, BlockGetter level, BlockPos pos) {
        float speed = original;

        // Phase 4: Polyculture (Biodiversity)
        if (level instanceof ServerLevel serverLevel) {
            if (DynamicGameRuleManager.getBoolean(serverLevel, AgrarianGameRules.BIODIVERSITY_BONUS)) {
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
    @Unique
    @Inject(method = "randomTick", at = @At("HEAD"), cancellable = true)
    private void agrarian_reform$preRandomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random, CallbackInfo ci) {
        if (!this.isMaxAge(state)) {
            // Check Rain Growth Spurt before the standard random tick logic executes
            if (level.isRainingAt(pos.above()) && level.canSeeSky(pos.above())) {
                int spurtAmount = DynamicGameRuleManager.getInt(level, AgrarianGameRules.RAIN_GROWTH_ACCELERATION);
                if (spurtAmount > 0) {
                    CropBlock self = (CropBlock) (Object) this;
                    int currentAge = self.getAge(state);
                    int maxAge = self.getMaxAge();
                    int newAge = Math.min(maxAge, currentAge + spurtAmount);

                    if (newAge > currentAge) {
                        level.setBlock(pos, self.getStateForAge(newAge), Block.UPDATE_ALL);
                        // Send particle feedback manually since we jumped the age here
                        if (DynamicGameRuleManager.getBoolean(level, AgrarianGameRules.AMBIENT_VITALITY_PARTICLES)) {
                            level.sendParticles(ParticleTypes.HAPPY_VILLAGER, pos.getX() + 0.5D, pos.getY() + 0.5D,
                                    pos.getZ() + 0.5D, 3, 0.25D, 0.25D, 0.25D, 0.0D);
                        }
                        // CANCEL the rest of the tick to prevent vanilla from overwriting our boost!
                        ci.cancel();
                    }
                }
            }
        }
    }

    @Unique
    @Inject(method = "randomTick", at = @At("RETURN"))
    private void agrarian_reform$postRandomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random, CallbackInfo ci) {
        // Did the block state change due to standard random ticking?
        BlockState newState = level.getBlockState(pos);
        if (newState.getBlock() == (Object) this && !newState.equals(state)) {
            if (DynamicGameRuleManager.getBoolean(level, AgrarianGameRules.AMBIENT_VITALITY_PARTICLES)) {
                level.sendParticles(ParticleTypes.HAPPY_VILLAGER, pos.getX() + 0.5D, pos.getY() + 0.5D,
                        pos.getZ() + 0.5D, 3, 0.25D, 0.25D, 0.25D, 0.0D);
            }
        }
    }

    // Phase 5: Aesthetic Rustling Audio
    @Unique
    @Inject(method = "entityInside", at = @At("HEAD"))
    private void agrarian_reform$playRustleSound(BlockState state, Level level, BlockPos pos, Entity entity,
            InsideBlockEffectApplier effectApplier, boolean isPrecise, CallbackInfo ci) {
        if (!(level instanceof ServerLevel serverLevel))
            return;
        if (this.isMaxAge(state) && DynamicGameRuleManager.getBoolean(serverLevel, AgrarianGameRules.AMBIENT_CROP_RUSTLE)) {
            long time = serverLevel.getGameTime();
            int entityId = entity.getId();
            // Use primitive cooldown check in SoundHelper
            if (SoundHelper.shouldPlayRustle(entityId, time)) {
                // Determine if entity is moving sufficiently
                double motion = entity.getDeltaMovement().horizontalDistance();
                if (motion > 0.01D) {
                    // Randomize pitch and volume
                    float pitch = 0.8F + serverLevel.getRandom().nextFloat() * 0.4F; // 0.8 to 1.2
                    float volume = 0.2F + serverLevel.getRandom().nextFloat() * 0.1F; // 0.2 to 0.3
                    serverLevel.playSound(null, pos, SoundEvents.GRASS_HIT, SoundSource.BLOCKS, volume, pitch);
                }
            }
        }
    }
}
