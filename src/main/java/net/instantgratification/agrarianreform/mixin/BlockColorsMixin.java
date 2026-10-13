// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3

package net.instantgratification.agrarianreform.mixin;

import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.color.block.BlockTintSource;
import net.minecraft.client.renderer.block.BlockAndTintGetter;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FarmlandBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import java.util.List;

/**
 * BlockColorsMixin: Client-Side Dew Visuals
 *
 * Verified against: BlockColors.java (26.2+)
 */
@Environment(EnvType.CLIENT)
@Mixin(BlockColors.class)
public abstract class BlockColorsMixin {

    @Unique
    @Inject(method = "createDefault", at = @At("RETURN"))
    private static void agrarian_reform$registerFarmlandColor(CallbackInfoReturnable<BlockColors> cir) {
        cir.getReturnValue().register(List.of(new BlockTintSource() {
            @Override
            public int color(BlockState state) {
                return -1;
            }

            @Override
            public int colorInWorld(BlockState state, BlockAndTintGetter world, BlockPos pos) {
                if (world instanceof Level level && pos != null) {
                    // Time based tint: Morning Dew (between 23000 and 2000 ticks)
                    long time = level.getOverworldClockTime() % 24000L;
                    if (time >= 23000 || time <= 2000) {
                        // Darken the farmland slightly to simulate moisture.
                        int baseMoisture = state.getValue(FarmlandBlock.MOISTURE);
                        if (baseMoisture < 7) {
                            return 0x73553C; // Damp soil tint.
                        }
                    }
                }
                return -1;
            }
        }), Blocks.FARMLAND);
    }
}
