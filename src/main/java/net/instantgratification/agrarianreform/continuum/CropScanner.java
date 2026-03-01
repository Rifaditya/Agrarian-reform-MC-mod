package net.instantgratification.agrarianreform.continuum;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.levelgen.Heightmap;

/**
 * CropScanner: The Chunk Auditor
 * 
 * Provides utility methods to scan loaded chunks for agricultural blocks and
 * perform high-precision growth speed approximations.
 * - Scan & Queue: Iterates through chunk surface columns to find crops.
 * - Mathematical Approximation: Recreates vanilla growth probability logic
 * in a static context to calculate theoretical 'ticks per stage'.
 */
public class CropScanner {

    public static void scanAndQueue(ServerLevel level, LevelChunk chunk, long timeDelta) {
        ChunkPos chunkPos = chunk.getPos();

        // Scan the surface of the chunk
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                int worldX = chunkPos.getMinBlockX() + x;
                int worldZ = chunkPos.getMinBlockZ() + z;

                // We use WORLD_SURFACE to find the highest block
                int y = chunk.getHeight(Heightmap.Types.WORLD_SURFACE, x, z);

                // The block *at* WORLD_SURFACE is usually air or the non-solid block itself.
                // We will check downwards until we hit something solid or we're confident no
                // crops exist.
                // A quick check 3 blocks down from the surface is usually sufficient for crops.
                for (int dy = y; dy > y - 3 && dy >= level.getMinY(); dy--) {
                    BlockPos pos = new BlockPos(worldX, dy, worldZ);
                    BlockState state = chunk.getBlockState(pos);

                    if (state.getBlock() instanceof CropBlock) {
                        // Found a crop! Queue it for update.
                        ContinuumManager.UPDATE_QUEUE.offer(new ContinuumManager.CropUpdateTask(level, pos, timeDelta));
                        break; // Move to next X/Z column
                    }
                }
            }
        }
    }

    // Helper to estimate growth speed without invoking the full random mixin
    // intercept
    public static float getSpeed(CropBlock cropBlock, ServerLevel level, BlockPos pos) {
        // Vanilla speed calc has protected access but we can invoke or recreate it
        // carefully.
        // It's technically protected in CropBlock. Let's create a proxy accessor via
        // Mixin,
        // or just recreate the logic here safely to avoid Invoker mixins for
        // simplicity.
        // For Zenith limits, rebuilding the mathematical probability is acceptable.

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

        return speed;
    }
}
