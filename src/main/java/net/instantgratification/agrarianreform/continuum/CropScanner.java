// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3

package net.instantgratification.agrarianreform.continuum;

import net.dasik.social.api.gamerule.DynamicGameRuleManager;
import net.instantgratification.agrarianreform.AgrarianGameRules;
import net.instantgratification.agrarianreform.registry.AgrarianCropRules;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.chunk.LevelChunkSection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * CropScanner: The Chunk Auditor (MC 1.20.1)
 * 
 * Provides utility methods to scan loaded chunks for agricultural blocks and
 * perform high-precision growth speed approximations.
 *
 * Verified against: LevelChunkSection.java (1.20.1)
 */
public class CropScanner {
    private static final Logger LOGGER = LoggerFactory.getLogger(CropScanner.class);

    public static boolean isDebug(Level level) {
        if (level instanceof ServerLevel serverLevel) {
            return DynamicGameRuleManager.getBoolean(serverLevel, AgrarianGameRules.DEBUG_MODE);
        }
        return LOGGER.isDebugEnabled();
    }

    public static void scanAndQueue(ServerLevel level, LevelChunk chunk, long timeDelta) {
        ChunkPos chunkPos = chunk.getPos();
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
        LevelChunkSection[] sections = chunk.getSections();

        int minX = chunkPos.getMinBlockX();
        int minZ = chunkPos.getMinBlockZ();
        int skippedSections = 0;
        int queuedCrops = 0;

        for (int sectionIndex = 0; sectionIndex < sections.length; sectionIndex++) {
            LevelChunkSection section = sections[sectionIndex];
            if (section == null || section.hasOnlyAir() || !section.maybeHas(state -> AgrarianCropRules.isCropBlock(state.getBlock()))) {
                skippedSections++;
                continue; // Skip entire empty or non-crop sub-chunk via palette pre-filter
            }

            int sectionBaseY = chunk.getSectionYFromSectionIndex(sectionIndex) * 16;

            for (int x = 0; x < 16; x++) {
                for (int z = 0; z < 16; z++) {
                    for (int y = 0; y < 16; y++) {
                        BlockState state = section.getBlockState(x, y, z);
                        if (AgrarianCropRules.isCropBlock(state.getBlock())) {
                            pos.set(minX + x, sectionBaseY + y, minZ + z);
                            ContinuumManager.UPDATE_QUEUE.offer(
                                    new ContinuumManager.CropUpdateTask(level, pos.immutable(), timeDelta)
                            );
                            queuedCrops++;
                        }
                    }
                }
            }
        }

        if (isDebug(level)) {
            LOGGER.debug("[AgrarianReform:CropScanner] Scanned chunk {}: skipped {}/{} sections, queued {} crops",
                    chunkPos, skippedSections, sections.length, queuedCrops);
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
                // We use simple checks for farmland and moisture
                if (blockState.is(Blocks.FARMLAND)) {
                    blockSpeed = 1.0F;
                    if (blockState.hasProperty(BlockStateProperties.MOISTURE) && blockState.getValue(BlockStateProperties.MOISTURE) > 0) {
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
        if (DynamicGameRuleManager.getBoolean(level, AgrarianGameRules.BIODIVERSITY_BONUS)) {
            boolean hasDifferentCrop = false;
            Direction[] directions = { Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST };
            for (Direction dir : directions) {
                BlockState neighborState = level.getBlockState(pos.relative(dir));
                if (neighborState.is(BlockTags.CROPS) && !neighborState.is(cropBlock)) {
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
