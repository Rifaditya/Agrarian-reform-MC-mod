// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3

package net.instantgratification.agrarianreform.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * BlockColorsMixin: Client-Side Dew Visuals (MC 1.20.1)
 *
 * Verified against: BlockColors.java (1.20.1)
 */
@Environment(EnvType.CLIENT)
@Mixin(BlockColors.class)
public abstract class BlockColorsMixin {
    @Inject(method = "createDefault", at = @At("RETURN"))
    private static void agrarian_reform$registerFarmlandColor(CallbackInfoReturnable<BlockColors> cir) {
        cir.getReturnValue().register((BlockState state, net.minecraft.world.level.BlockAndTintGetter world, BlockPos pos, int tintIndex) -> {
            if (world instanceof Level level && pos != null) {
                long time = level.getDayTime() % 24000L;
                if (time >= 23000 || time <= 2000) {
                    int baseMoisture = state.getValue(FarmBlock.MOISTURE);
                    if (baseMoisture < 7) {
                        return 0x73553C;
                    }
                }
            }
            return -1;
        }, Blocks.FARMLAND);
    }
}
