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

import net.dasik.social.api.gamerule.DynamicGameRuleManager;
import net.instantgratification.agrarianreform.AgrarianGameRules;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
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
 * This mixin handles hydration and trample logic for FarmlandBlocks.
 * - Hydration: Extends irrigation range based on GameRules.
 * - Trampling: Implements 'Soft Step' (Leather Boots/Feather Falling) and
 * 'Total Trample Immunity'.
 *
 * Verified against: FarmlandBlock.java (26.2+)
 */
@Mixin(FarmlandBlock.class)
public abstract class FarmlandBlockMixin {

    @Unique
    @Inject(method = "fallOn", at = @At("HEAD"), cancellable = true)
    private void agrarian_reform$handleTrample(Level level, BlockState state, BlockPos pos, Entity entity,
            double fallDistance, CallbackInfo ci) {
        if (!(level instanceof ServerLevel serverLevel))
            return;

        boolean immunity = DynamicGameRuleManager.getBoolean(serverLevel, AgrarianGameRules.TOTAL_TRAMPLE_IMMUNITY);
        boolean softStep = false;

        if (entity instanceof LivingEntity living) {
            softStep = agrarian_reform$hasSoftStep(living);
        }

        if (immunity || softStep) {
            // Cancel trampling logic but ensure fall damage is still applied
            entity.causeFallDamage((float) fallDistance, 1.0F, level.damageSources().fall());
            ci.cancel();
        }
    }

    @Unique
    private boolean agrarian_reform$hasSoftStep(LivingEntity entity) {
        // Leather Boots
        if (entity.getItemBySlot(EquipmentSlot.FEET).is(Items.LEATHER_BOOTS)) {
            return true;
        }
        // Feather falling
        var enchantmentRegistry = entity.level().registryAccess()
                .lookupOrThrow(net.minecraft.core.registries.Registries.ENCHANTMENT);
        var featherFallingOpt = enchantmentRegistry
                .get(net.minecraft.world.item.enchantment.Enchantments.FEATHER_FALLING);
        if (featherFallingOpt.isPresent()) {
            return EnchantmentHelper.getEnchantmentLevel(featherFallingOpt.get(), entity) > 0;
        }
        return false;
    }

    @Unique
    @Inject(method = "isNearWater", at = @At("HEAD"), cancellable = true)
    private static void agrarian_reform$customWaterRange(LevelReader level, BlockPos pos,
            CallbackInfoReturnable<Boolean> cir) {
        if (level instanceof ServerLevel serverLevel) {
            if (DynamicGameRuleManager.getBoolean(serverLevel, AgrarianGameRules.ALWAYS_WET_FARMLAND)) {
                cir.setReturnValue(true);
                return;
            }

            int sourceRange = DynamicGameRuleManager.getInt(serverLevel, AgrarianGameRules.HYDRATION_SOURCE_RANGE);
            int flowingRange = DynamicGameRuleManager.getInt(serverLevel, AgrarianGameRules.HYDRATION_FLOWING_RANGE);

            // If using default or smaller ranges, let vanilla handle it
            if (sourceRange <= 4 && flowingRange <= 0)
                return;

            int maxRange = Math.max(sourceRange, flowingRange);

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
            // Do NOT setReturnValue(false) here to allow vanilla fallback
        }
    }
}
