/*
 * Copyright (C) 2026 Dasik (Rifaditya)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package net.instantgratification.agrarianreform.continuum;

import net.dasik.social.api.gamerule.DynamicGameRuleManager;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerChunkEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.instantgratification.agrarianreform.AgrarianGameRules;
import net.instantgratification.agrarianreform.AgrarianReformFabric;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.SugarCaneBlock;
import net.minecraft.world.level.block.CactusBlock;
import net.minecraft.world.level.block.NetherWartBlock;
import net.minecraft.world.level.block.CocoaBlock;
import net.minecraft.world.level.block.VineBlock;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.chunk.LevelChunk;

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
 * small number of crop blocks per tick to prevent server-side lag spikes.
 *
 * Verified against: ServerChunkEvents.java (Fabric API)
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
                int multiplier = DynamicGameRuleManager.getInt(serverLevel, AgrarianGameRules.GLOBAL_GROWTH_MULTIPLIER);
                if (multiplier <= 0) {
                    return; // Dynamic growth is disabled, skip simulation
                }
                long scaledTimeDelta = (timeDelta * multiplier) / 100L;
                if (scaledTimeDelta > 0) {
                    CropScanner.scanAndQueue(serverLevel, chunk, scaledTimeDelta);
                }
            }
        }
    }

    private static void processCropUpdate(CropUpdateTask task) {
        if (!task.level.isLoaded(task.pos)) {
            return;
        }

        BlockState currentState = task.level.getBlockState(task.pos);
        Block block = currentState.getBlock();

        if (block instanceof CropBlock cropBlock) {
            float growthSpeed = CropScanner.getSpeed(cropBlock, task.level, task.pos);
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
        } else if (block instanceof SugarCaneBlock || block instanceof CactusBlock) {
            // Find base of the column to determine height
            BlockPos basePos = task.pos;
            while (task.level.getBlockState(basePos.below()).is(block)) {
                basePos = basePos.below();
            }

            // Find current height going up
            int currentHeight = 1;
            BlockPos topPos = basePos;
            while (task.level.getBlockState(topPos.above()).is(block)) {
                topPos = topPos.above();
                currentHeight++;
            }

            // If the pos is not the top block, do not grow it to avoid duplicate calculations in columns
            if (!task.pos.equals(topPos)) {
                return;
            }

            if (currentHeight >= 3) {
                return; // Height limit reached
            }

            int currentAge = currentState.getValue(BlockStateProperties.AGE_15);
            // Sugar cane/cactus ticks: average 1,365 game ticks per age stage (1/16 chance per random tick)
            int stages = (int) (task.timeDelta / 1365L);
            if (stages > 0) {
                int newAge = currentAge + stages;
                int blocksToAdd = newAge / 16;
                int finalAge = newAge % 16;
                int finalHeight = Math.min(3, currentHeight + blocksToAdd);

                if (finalHeight > currentHeight) {
                    BlockPos buildPos = topPos;
                    for (int i = 0; i < finalHeight - currentHeight; i++) {
                        buildPos = buildPos.above();
                        if (task.level.isEmptyBlock(buildPos)) {
                            task.level.setBlockAndUpdate(buildPos, block.defaultBlockState().setValue(BlockStateProperties.AGE_15, finalAge));
                        }
                    }
                    // Reset the old top block's age to 0 when grown
                    task.level.setBlockAndUpdate(topPos, currentState.setValue(BlockStateProperties.AGE_15, 0));
                } else {
                    task.level.setBlockAndUpdate(topPos, currentState.setValue(BlockStateProperties.AGE_15, finalAge));
                }
            }
        } else if (block instanceof NetherWartBlock) {
            int currentAge = currentState.getValue(BlockStateProperties.AGE_3);
            // Nether wart ticks: average 13,650 game ticks per age stage (10% chance per random tick)
            int stages = (int) (task.timeDelta / 13650L);
            if (stages > 0) {
                int newAge = Math.min(3, currentAge + stages);
                if (newAge > currentAge) {
                    task.level.setBlock(task.pos, currentState.setValue(BlockStateProperties.AGE_3, newAge), Block.UPDATE_ALL);
                }
            }
        } else if (block instanceof CocoaBlock) {
            int currentAge = currentState.getValue(BlockStateProperties.AGE_2);
            // Cocoa ticks: average 6,825 game ticks per age stage (20% chance per random tick)
            int stages = (int) (task.timeDelta / 6825L);
            if (stages > 0) {
                int newAge = Math.min(2, currentAge + stages);
                if (newAge > currentAge) {
                    task.level.setBlock(task.pos, currentState.setValue(BlockStateProperties.AGE_2, newAge), Block.UPDATE_ALL);
                }
            }
        } else if (block instanceof SweetBerryBushBlock) {
            int currentAge = currentState.getValue(BlockStateProperties.AGE_3);
            // Sweet berry ticks: average 6,825 game ticks per age stage (20% chance per random tick)
            int stages = (int) (task.timeDelta / 6825L);
            if (stages > 0) {
                int newAge = Math.min(3, currentAge + stages);
                if (newAge > currentAge) {
                    task.level.setBlock(task.pos, currentState.setValue(BlockStateProperties.AGE_3, newAge), Block.UPDATE_ALL);
                }
            }
        } else if (block instanceof VineBlock) {
            // Vines grow downwards. Ticks: average 13,650 game ticks (10% chance)
            if (task.timeDelta >= 13650L) {
                BlockPos belowPos = task.pos.below();
                if (task.level.isEmptyBlock(belowPos)) {
                    // Place vine below, copying the direction properties of the current vine
                    BlockState belowState = block.defaultBlockState();
                    boolean hasSupport = false;
                    for (net.minecraft.core.Direction dir : net.minecraft.core.Direction.Plane.HORIZONTAL) {
                        var prop = VineBlock.getPropertyForFace(dir);
                        if (currentState.getValue(prop)) {
                            // Check if there is block support below in that direction
                            if (VineBlock.isAcceptableNeighbour(task.level, belowPos.relative(dir), dir)) {
                                belowState = belowState.setValue(prop, true);
                                hasSupport = true;
                            }
                        }
                    }
                    if (hasSupport) {
                        task.level.setBlockAndUpdate(belowPos, belowState);
                    }
                }
            }
        } else if (block instanceof SaplingBlock saplingBlock) {
            int currentStage = currentState.getValue(BlockStateProperties.STAGE);
            // Sapling stages: average 95,550 game ticks per stage (about 1.4% chance per random tick)
            int stages = (int) (task.timeDelta / 95550L);
            if (stages > 0) {
                int newStage = currentStage + stages;
                if (newStage >= 2) {
                    // Grow the tree structure!
                    saplingBlock.advanceTree(task.level, task.pos, currentState, task.level.getRandom());
                } else if (newStage == 1 && currentStage == 0) {
                    task.level.setBlock(task.pos, currentState.setValue(BlockStateProperties.STAGE, 1), 260);
                }
            }
        }
    }

    public static class CropUpdateTask {
        public final ServerLevel level;
        public final BlockPos pos;
        public final long timeDelta;

        public CropUpdateTask(ServerLevel level, BlockPos pos, long timeDelta) {
            this.level = level;
            this.pos = pos;
            this.timeDelta = timeDelta;
        }
    }
}
