package net.instantgratification.agrarianreform.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.dasik.social.api.gamerule.DynamicGameRuleManager;
import net.instantgratification.agrarianreform.AgrarianGameRules;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Ravager;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.FarmlandBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * FarmlandBlockMixin: Soil Resilience & Hydro-Dynamics
 * 
 * This mixin interdicts the core hydration and trample logic of FarmlandBlocks.
 * - Hydration: Extends irrigation range to 8 blocks for source water.
 * - Trampling: Implements the 'Soft Step' logic (Leather Boots/Feather Falling)
 * and respects the 'totalTrampleImmunity' GameRule for IG mode.
 */
@Mixin(FarmlandBlock.class)
public abstract class FarmlandBlockMixin {

    @Inject(method = "fallOn", at = @At("HEAD"), cancellable = true)
    private void agrarianreform$handleTrample(Level level, BlockState state, BlockPos pos, Entity entity,
            double fallDistance, CallbackInfo ci) {
        if (!(level instanceof ServerLevel serverLevel))
            return;

        // 1. Total Immunity (IG Toggle)
        if (DynamicGameRuleManager.getBoolean(serverLevel, AgrarianGameRules.TOTAL_TRAMPLE_IMMUNITY)) {
            ci.cancel();
            return;
        }

        // 2. Nuanced Logic (VO Phase 2) - Soft Step
        if (entity instanceof LivingEntity livingEntity) {
            if (agrarianreform$hasSoftStep(livingEntity)) {
                // Soft step: cancel trampling but still apply fall damage manually
                entity.causeFallDamage((float) fallDistance, 1.0F, level.damageSources().fall());
                ci.cancel();
            }
        }
    }

    @WrapOperation(method = "fallOn", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/FarmlandBlock;turnToDirt(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;)V"))
    private void agrarianreform$wrapTurnToDirt(Entity sourceEntity, BlockState state, Level level, BlockPos pos,
            Operation<Void> original) {
        if (sourceEntity instanceof Ravager) {
            original.call(sourceEntity, state, level, pos);
            return;
        }

        if (sourceEntity instanceof LivingEntity living) {
            if (agrarianreform$hasSoftStep(living)) {
                return; // Soft step protects the soil
            }
        }

        original.call(sourceEntity, state, level, pos);
    }

    @Unique
    private boolean agrarianreform$hasSoftStep(LivingEntity entity) {
        // Leather Boots
        if (entity.getItemBySlot(EquipmentSlot.FEET).is(Items.LEATHER_BOOTS)) {
            return true;
        }
        // Feather falling
        // In 26.1, enchantments are data-driven, so we check using EnchantmentHelper
        // and RegistryAccess
        var enchantmentRegistry = entity.level().registryAccess()
                .lookupOrThrow(net.minecraft.core.registries.Registries.ENCHANTMENT);
        var featherFallingOpt = enchantmentRegistry
                .get(net.minecraft.world.item.enchantment.Enchantments.FEATHER_FALLING);
        if (featherFallingOpt.isPresent()) {
            return EnchantmentHelper.getEnchantmentLevel(featherFallingOpt.get(), entity) > 0;
        }
        return false;
    }

    @Inject(method = "isNearWater", at = @At("HEAD"), cancellable = true)
    private static void agrarianreform$customWaterRange(LevelReader level, BlockPos pos,
            CallbackInfoReturnable<Boolean> cir) {
        if (level instanceof ServerLevel serverLevel) {
            if (DynamicGameRuleManager.getBoolean(serverLevel, AgrarianGameRules.ALWAYS_WET_FARMLAND)) {
                cir.setReturnValue(true);
                return;
            }

            int sourceRange = DynamicGameRuleManager.getInt(serverLevel, AgrarianGameRules.HYDRATION_SOURCE_RANGE);
            int flowingRange = DynamicGameRuleManager.getInt(serverLevel, AgrarianGameRules.HYDRATION_FLOWING_RANGE);

            if (sourceRange <= 4 && flowingRange <= 0)
                return; // Let vanilla handle default/smaller ranges

            int maxRange = Math.max(sourceRange, flowingRange);

            // Optimization: Fast-fail if no water in the general vicinity using block
            // polling is still O(N^2),
            // but we only check the Y layer of the farmland and one above.
            for (BlockPos blockPos : BlockPos.betweenClosed(pos.offset(-maxRange, 0, -maxRange),
                    pos.offset(maxRange, 1, maxRange))) {
                if (level.getFluidState(blockPos).is(FluidTags.WATER)) {
                    int dx = Math.abs(blockPos.getX() - pos.getX());
                    int dz = Math.abs(blockPos.getZ() - pos.getZ());
                    int dist = Math.max(dx, dz);

                    if (level.getFluidState(blockPos).isSource()) {
                        if (dist <= sourceRange) {
                            cir.setReturnValue(true);
                            return;
                        }
                    } else {
                        if (dist <= flowingRange) {
                            cir.setReturnValue(true);
                            return;
                        }
                    }
                }
            }
            cir.setReturnValue(false);
        }
    }
}
