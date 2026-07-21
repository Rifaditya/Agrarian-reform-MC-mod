// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3

package net.instantgratification.agrarianreform.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.instantgratification.agrarianreform.util.GrowthHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
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
 * CropBlockMixin: Thin adapter for CropBlock enhancements
 * 
 * Delegates polyculture, rain acceleration, particles, and rustle sound logic
 * to GrowthHelper.
 * 
 * Verified against: CropBlock.java (26.2+)
 */
@Mixin(CropBlock.class)
public abstract class CropBlockMixin {

    @Shadow
    public abstract boolean isMaxAge(BlockState state);

    @Unique
    @ModifyReturnValue(method = "getGrowthSpeed", at = @At("RETURN"))
    private static float agrarian_reform$modifyGrowthSpeed(float original, Block block, BlockGetter level, BlockPos pos) {
        return GrowthHelper.modifyCropGrowthSpeed(original, block, level, pos);
    }

    @Unique
    @Inject(method = "randomTick", at = @At("HEAD"), cancellable = true)
    private void agrarian_reform$preRandomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random, CallbackInfo ci) {
        if (GrowthHelper.preCropRandomTick(state, level, pos, (CropBlock) (Object) this)) {
            ci.cancel();
        }
    }

    @Unique
    @Inject(method = "randomTick", at = @At("RETURN"))
    private void agrarian_reform$postRandomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random, CallbackInfo ci) {
        GrowthHelper.postCropRandomTick(state, level, pos, (Block) (Object) this);
    }

    @Unique
    @Inject(method = "entityInside", at = @At("HEAD"))
    private void agrarian_reform$playRustleSound(BlockState state, Level level, BlockPos pos, Entity entity,
            InsideBlockEffectApplier effectApplier, boolean isPrecise, CallbackInfo ci) {
        GrowthHelper.playCropRustleSound(state, level, pos, entity, this.isMaxAge(state));
    }
}
