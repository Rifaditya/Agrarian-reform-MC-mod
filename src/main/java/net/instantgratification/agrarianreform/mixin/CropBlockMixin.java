// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3

package net.instantgratification.agrarianreform.mixin;

import net.instantgratification.agrarianreform.util.GrowthHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * CropBlockMixin: Thin adapter for CropBlock enhancements (MC 1.20.1)
 * 
 * Delegates polyculture, rain acceleration, particles, and rustle sound logic
 * to GrowthHelper.
 * 
 * Verified against: CropBlock.java (1.20.1)
 */
@Mixin(CropBlock.class)
public abstract class CropBlockMixin {

    @Shadow
    public abstract boolean isMaxAge(BlockState state);
    @Inject(method = "getGrowthSpeed", at = @At("RETURN"), cancellable = true)
    private static void agrarian_reform$modifyGrowthSpeed(Block block, BlockGetter level, BlockPos pos, CallbackInfoReturnable<Float> cir) {
        cir.setReturnValue(GrowthHelper.modifyCropGrowthSpeed(cir.getReturnValueF(), block, level, pos));
    }
    @Inject(method = "randomTick", at = @At("HEAD"), cancellable = true)
    private void agrarian_reform$preRandomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random, CallbackInfo ci) {
        if (GrowthHelper.preCropRandomTick(state, level, pos, (CropBlock) (Object) this)) {
            ci.cancel();
        }
    }
    @Inject(method = "randomTick", at = @At("RETURN"))
    private void agrarian_reform$postRandomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random, CallbackInfo ci) {
        GrowthHelper.postCropRandomTick(state, level, pos, (Block) (Object) this);
    }
    @Inject(method = "entityInside", at = @At("HEAD"))
    private void agrarian_reform$playRustleSound(BlockState state, Level level, BlockPos pos, Entity entity, CallbackInfo ci) {
        GrowthHelper.playCropRustleSound(state, level, pos, entity, this.isMaxAge(state));
    }
}
