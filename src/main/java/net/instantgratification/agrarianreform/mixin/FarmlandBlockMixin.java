// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3

package net.instantgratification.agrarianreform.mixin;

import net.instantgratification.agrarianreform.util.GrowthHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * FarmlandBlockMixin: Thin adapter for FarmBlock enhancements (MC 1.20.1)
 * 
 * Delegates trample, leather boots check, and custom hydration range logic
 * to GrowthHelper.
 *
 * Verified against: FarmBlock.java (1.20.1)
 */
@Mixin(FarmBlock.class)
public abstract class FarmlandBlockMixin {
    @Inject(method = "fallOn", at = @At("HEAD"), cancellable = true)
    private void agrarian_reform$handleTrample(Level level, BlockState state, BlockPos pos, Entity entity,
            float fallDistance, CallbackInfo ci) {
        if (GrowthHelper.handleFarmlandTrample(level, state, pos, entity, fallDistance)) {
            ci.cancel();
        }
    }
    @Inject(method = "isNearWater", at = @At("HEAD"), cancellable = true)
    private static void agrarian_reform$customWaterRange(LevelReader level, BlockPos pos,
            CallbackInfoReturnable<Boolean> cir) {
        Boolean result = GrowthHelper.customFarmlandWaterRange(level, pos);
        if (result != null) {
            cir.setReturnValue(result);
        }
    }
}
