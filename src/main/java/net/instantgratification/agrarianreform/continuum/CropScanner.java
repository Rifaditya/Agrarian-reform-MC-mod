// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3

package net.instantgratification.agrarianreform.continuum;

import net.dasik.social.api.gamerule.DynamicGameRuleManager;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.levelgen.Heightmap;

import net.instantgratification.agrarianreform.util.AgrarianTags;

/**
 * CropScanner: The Chunk Auditor
 * 
 * Provides utility methods to scan loaded chunks for agricultural blocks and
 * perform high-precision growth speed approximations.
 *
 * Verified against: Heightmap.java (26.2+)
 */
public class CropScanner {

    public static void scanAndQueue(ServerLevel level, LevelChunk chunk, long timeDelta) {
        ChunkPos chunkPos = chunk.getPos();
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

        // Scan the surface of the chunk
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                int worldX = chunkPos.getMinBlockX() + x;
                int worldZ = chunkPos.getMinBlockZ() + z;

                int yMax = chunk.getHeight(Heightmap.Types.WORLD_SURFACE, x, z);
                for (int dy = yMax; dy >= level.getMinY(); dy--) {
                    pos.set(worldX, dy, worldZ);
                    BlockState state = chunk.getBlockState(pos);

                    Block block = state.getBlock();
                    boolean isContinuumPlant = state.is(AgrarianTags.CONTINUUM_PLANTS)
                            || block instanceof CropBlock
                            || block instanceof net.minecraft.world.level.block.SugarCaneBlock
                            || block instanceof net.minecraft.world.level.block.CactusBlock
                            || block instanceof net.minecraft.world.level.block.NetherWartBlock
                            || block instanceof net.minecraft.world.level.block.CocoaBlock
                            || block instanceof net.minecraft.world.level.block.VineBlock
                            || block instanceof net.minecraft.world.level.block.SaplingBlock
                            || block instanceof net.minecraft.world.level.block.SweetBerryBushBlock;

                    if (isContinuumPlant) {
                        // Found a crop! Queue it using an immutable copy to prevent queue corruption
                        ContinuumManager.UPDATE_QUEUE.offer(new ContinuumManager.CropUpdateTask(level, pos.immutable(), timeDelta));
                    }
                }
            }
        }
    }

    // Helper to estimate growth speed without invoking the full random mixin intercept
    public static float getSpeed(CropBlock cropBlock, ServerLevel level, BlockPos pos) {
        float speed = 1.0F;
        BlockPos below = pos.below();

        for (int xx = -1; xx <= 1; xx++) {
            for (int zz = -1; zz <= 1; zz++) {
                float blockSpeed = 0.0F;
                BlockState blockState = level.getBlockState(below.offset(xx, 0, zz));
                // We use a simple checks for farmland and moisture
                if (blockState.is(net.minecraft.world.level.block.Blocks.FARMLAND)) {
                    blockSpeed = 1.0F;
                    if (blockState.getValue(net.minecraft.world.level.block.FarmlandBlock.MOISTURE) > 0) {
                        blockSpeed = 3.0F;
                    }
                }

                if (xx != 0 || zz != 0) {
                    blockSpeed /= 4.0F;
                }

                speed += blockSpeed;
            }
        }

        BlockPos north = pos.north();
        BlockPos south = pos.south();
        BlockPos west = pos.west();
        BlockPos east = pos.east();
        boolean horizontal = level.getBlockState(west).is(cropBlock) || level.getBlockState(east).is(cropBlock);
        boolean vertical = level.getBlockState(north).is(cropBlock) || level.getBlockState(south).is(cropBlock);
        if (horizontal && vertical) {
            speed /= 2.0F;
        } else {
            boolean diagonal = level.getBlockState(west.north()).is(cropBlock)
                    || level.getBlockState(east.north()).is(cropBlock)
                    || level.getBlockState(east.south()).is(cropBlock)
                    || level.getBlockState(west.south()).is(cropBlock);
            if (diagonal) {
                speed /= 2.0F;
            }
        }

        // --- Agrarian Reform Feature Catch-up ---
        // 1. Biodiversity Bonus
        if (DynamicGameRuleManager.getBoolean(level, net.instantgratification.agrarianreform.AgrarianGameRules.BIODIVERSITY_BONUS)) {
            boolean hasDifferentCrop = false;
            net.minecraft.core.Direction[] directions = { net.minecraft.core.Direction.NORTH, net.minecraft.core.Direction.SOUTH, net.minecraft.core.Direction.EAST, net.minecraft.core.Direction.WEST };
            for (net.minecraft.core.Direction dir : directions) {
                BlockState neighborState = level.getBlockState(pos.relative(dir));
                if (neighborState.is(net.minecraft.tags.BlockTags.CROPS) && !neighborState.is(cropBlock)) {
                    hasDifferentCrop = true;
                    break;
                }
            }
            if (hasDifferentCrop) {
                speed += 0.10f;
            }
        }

        return speed;
    }
}
