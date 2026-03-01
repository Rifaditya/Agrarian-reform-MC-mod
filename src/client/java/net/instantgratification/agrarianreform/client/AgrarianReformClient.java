package net.instantgratification.agrarianreform.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.instantgratification.agrarianreform.AgrarianReformFabric;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockAndTintGetter;

public class AgrarianReformClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        AgrarianReformFabric.LOGGER.info("Initializing Agrarian Reform Client-Side Features");

        // Morning Dew Visual on Farmland
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> {
            if (world != null && pos != null) {
                // Time based tint: Morning Dew (between 23000 and 2000 ticks)
                long time = world.getLevelData().getDayTime() % 24000L;
                if (time >= 23000 || time <= 2000) {
                    // Darken the farmland slightly to simulate moisture.
                    // Vanilla dry farmland is default. Moist farmland is darker.
                    // We can return a slightly darker tint for the "Dew" effect if it isn't already
                    // fully moist.
                    int baseMoisture = state.getValue(net.minecraft.world.level.block.FarmlandBlock.MOISTURE);
                    if (baseMoisture < 7) {
                        // Return the standard damp color or a specialized dew tint.
                        // Standard farmland tint isn't strictly driven by BlockColors in vanilla (it
                        // uses different textures),
                        // but if we apply a tint, we can overlay a damp hue.
                        // For a dirt-like color darkened:
                        return 0x73553C; // Example damp soil tint.
                    }
                }
            }
            return -1; // Default
        }, Blocks.FARMLAND);
    }
}
