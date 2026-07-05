package net.instantgratification.agrarianreform.util;

import net.dasik.social.api.gamerule.DynamicGameRuleManager;
import net.instantgratification.agrarianreform.AgrarianGameRules;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class GrowthHelper {

    public static boolean handleRandomTick(Block block, BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        // Check if the block is a plant/crop
        boolean isPlant = block instanceof net.minecraft.world.level.block.CropBlock
                || block instanceof net.minecraft.world.level.block.SugarCaneBlock
                || block instanceof net.minecraft.world.level.block.CactusBlock
                || block instanceof net.minecraft.world.level.block.NetherWartBlock
                || block instanceof net.minecraft.world.level.block.CocoaBlock
                || block instanceof net.minecraft.world.level.block.VineBlock
                || block instanceof net.minecraft.world.level.block.SaplingBlock
                || block instanceof net.minecraft.world.level.block.SweetBerryBushBlock
                || block instanceof net.minecraft.world.level.block.BushBlock;

        if (!isPlant) {
            return false; // Let vanilla randomTick proceed normally
        }

        int multiplier = DynamicGameRuleManager.getInt(level, AgrarianGameRules.GLOBAL_GROWTH_MULTIPLIER);
        if (multiplier == 100) {
            return false; // Let vanilla randomTick proceed normally
        }

        // Cancel entirely if growth is disabled (0%)
        if (multiplier <= 0) {
            return true; // Cancel vanilla randomTick
        }

        // Handle slowdown (0 < multiplier < 100)
        if (multiplier < 100) {
            if (random.nextInt(100) >= multiplier) {
                return true; // Cancel vanilla randomTick
            }
            return false; // Let vanilla randomTick proceed normally
        }

        // Handle speedup (multiplier > 100)
        int extraRuns = (multiplier / 100) - 1;
        int chance = multiplier % 100;

        if (random.nextInt(100) < chance) {
            extraRuns++;
        }

        for (int i = 0; i < extraRuns; i++) {
            BlockState currentState = level.getBlockState(pos);
            if (currentState.is(block)) {
                currentState.randomTick(level, pos, random);
            }
        }

        return false; // Let vanilla run the final randomTick
    }
}
