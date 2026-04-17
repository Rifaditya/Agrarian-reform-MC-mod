package net.instantgratification.agrarianreform.continuum;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerChunkEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.instantgratification.agrarianreform.AgrarianReformFabric;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.saveddata.SavedData;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * ContinuumManager: The Offline Simulator
 * 
 * Orchestrates the persistence and catch-up growth of crops in unloaded chunks.
 * - Persistence: Listens for CHUNK_UNLOAD events to timestamp chunk state.
 * - Catch-up: Listens for CHUNK_LOAD to calculate time deltas and trigger
 * scans.
 * - Performance: Implements a throttled UPDATE_QUEUE that processes only a
 * small
 * number of crop blocks per tick to prevent server-side lag spikes.
 */
public class ContinuumManager {
    private static final String DATA_KEY = AgrarianReformFabric.MOD_ID + "_continuum";
    // Throttling config: Update only 5 crop blocks per tick globally.
    private static final int CROPS_PER_TICK = 5;

    public static final Queue<CropUpdateTask> UPDATE_QUEUE = new ConcurrentLinkedQueue<>();

    public static void initialize() {
        ServerChunkEvents.CHUNK_UNLOAD.register((serverLevel, chunk) -> {
            ContinuumData data = getOrCreateData(serverLevel);
            data.setUnloadTime(chunk.getPos(), serverLevel.getGameTime());
        });

        ServerChunkEvents.CHUNK_LOAD.register(ContinuumManager::onChunkLoad);

        ServerTickEvents.END_SERVER_TICK.register(server -> {
            int updates = 0;
            while (updates < CROPS_PER_TICK && !UPDATE_QUEUE.isEmpty()) {
                CropUpdateTask task = UPDATE_QUEUE.poll();
                if (task != null) {
                    processCropUpdate(task);
                    updates++;
                }
            }
        });
    }

    private static ContinuumData getOrCreateData(ServerLevel level) {
        return level.getDataStorage().computeIfAbsent(ContinuumData.TYPE);
    }

    private static void onChunkLoad(ServerLevel serverLevel, LevelChunk chunk, boolean isNew) {
        ContinuumData data = getOrCreateData(serverLevel);
        long unloadTime = data.getUnloadTime(chunk.getPos());
        if (unloadTime != -1L) {
            long timeDelta = serverLevel.getGameTime() - unloadTime;
            data.remove(chunk.getPos());
            if (timeDelta > 0) {
                CropScanner.scanAndQueue(serverLevel, chunk, timeDelta);
            }
        }
    }

    private static void processCropUpdate(CropUpdateTask task) {
        if (task.level.isLoaded(task.pos)) {
            BlockState currentState = task.level.getBlockState(task.pos);
            if (currentState.getBlock() instanceof CropBlock cropBlock) {
                // Determine how many random ticks would have occurred
                // Random tick speed default is 3 per chunk (16x16x16) per tick.
                // A crop only grows if random tick hits it and crop growth speed allows.
                // Simplified O(1) Math:
                float growthSpeed = CropScanner.getSpeed(cropBlock, task.level, task.pos);
                // The vanilla chance of growing is: random.nextInt((int)(25.0F / growthSpeed) +
                // 1) == 0
                float chancePerTick = 1.0f / ((25.0f / growthSpeed) + 1.0f);

                // Frequency of this particular block getting hit by a random tick across a
                // whole chunk space
                // is extremely low. Approximated average ticks to grow 1 stage = (25 / speed) *
                // 4096 (blocks in section) / 3 (random ticks)
                // Actually random ticks are per section (16x16x16)
                long averageTicksPerStage = (long) (((25.0f / growthSpeed) + 1.0f) * 4096.0f / 3.0f);

                int stagesToGrow = (int) (task.timeDelta / averageTicksPerStage);

                if (stagesToGrow > 0) {
                    int currentAge = cropBlock.getAge(currentState);
                    int maxAge = cropBlock.getMaxAge();
                    int newAge = Math.min(maxAge, currentAge + stagesToGrow);
                    if (newAge > currentAge) {
                        task.level.setBlock(task.pos, cropBlock.getStateForAge(newAge), Block.UPDATE_ALL);
                    }
                }
            }
        }
    }

    public record CropUpdateTask(ServerLevel level, BlockPos pos, long timeDelta) {
    }
}
