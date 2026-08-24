// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3

package net.instantgratification.agrarianreform;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.loader.api.FabricLoader;
import net.instantgratification.agrarianreform.config.AgrarianConfig;
import net.instantgratification.agrarianreform.continuum.ContinuumManager;
import net.instantgratification.agrarianreform.registry.AgrarianCropRules;
import net.instantgratification.agrarianreform.util.SoundHelper;
import net.dasik.social.api.gamerule.DynamicGameRuleManager;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.gamerules.GameRules;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

/**
 * Agrarian Reform: The Living Earth
 * 
 * Main entry point for the Agrarian Reform mod. This mod implements a
 * high-fidelity agricultural simulation for Minecraft.
 * 
 * Part of the Vanilla Outsider and Instant Gratification collections.
 *
 * Verified against: ModInitializer.java (Fabric API)
 */
public class AgrarianReformFabric implements ModInitializer {
    public static final String MOD_ID = "agrarian_reform";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        net.instantgratification.agrarianreform.util.ModVersionGuard.checkClass("Agrarian Reform", "net.minecraft.world.entity.EntityTypes");
        LOGGER.info("Instant Gratification: Agrarian Reform Initialized");

        // Load config baseline template first
        AgrarianConfig.load(FabricLoader.getInstance().getConfigDir());

        AgrarianGameRules.register();
        AgrarianCropRules.register();
        ContinuumManager.initialize();

        // Register entity unload listener to prevent memory leaks in SoundHelper
        ServerEntityEvents.ENTITY_UNLOAD.register((entity, level) -> {
            SoundHelper.purgeEntity(entity.getId());
        });

        // Initialize/update active limits on server starting
        ServerLifecycleEvents.SERVER_STARTED.register(server -> {
            // Reload config baseline template to fetch main-menu updates
            AgrarianConfig.load(FabricLoader.getInstance().getConfigDir());
            AgrarianCropRules.clearCropCache();

            GameRules rules = server.getGameRules();
            
            // If the world is newly created (not initialized yet), apply the baseline config template directly to the active GameRules
            if (!server.getWorldData().overworldData().isInitialized()) {
                rules.set(AgrarianGameRules.HYDRATION_SOURCE_RANGE, AgrarianConfig.get().hydrationSourceRange, server);
                rules.set(AgrarianGameRules.HYDRATION_FLOWING_RANGE, AgrarianConfig.get().hydrationFlowingRange, server);
                rules.set(AgrarianGameRules.RAIN_GROWTH_ACCELERATION, AgrarianConfig.get().rainGrowthAcceleration, server);
                rules.set(AgrarianGameRules.PURE_WATER_HYDRATION_ONLY, AgrarianConfig.get().pureWaterHydrationOnly, server);
                rules.set(AgrarianGameRules.BIODIVERSITY_BONUS, AgrarianConfig.get().growthBiodiversityBonus, server);
                rules.set(AgrarianGameRules.AMBIENT_CROP_RUSTLE, AgrarianConfig.get().ambientCropRustle, server);
                rules.set(AgrarianGameRules.AMBIENT_VITALITY_PARTICLES, AgrarianConfig.get().ambientVitalityParticles, server);
                rules.set(AgrarianGameRules.TOTAL_TRAMPLE_IMMUNITY, AgrarianConfig.get().totalTrampleImmunity, server);
                rules.set(AgrarianGameRules.TRAMPLE_IMMUNITY_PLAYERS_ONLY, AgrarianConfig.get().trampleImmunityPlayersOnly, server);
                rules.set(AgrarianGameRules.ALWAYS_WET_FARMLAND, AgrarianConfig.get().alwaysWetFarmland, server);
                rules.set(AgrarianGameRules.SEEDS_GROW_GRASS, AgrarianConfig.get().seedsGrowGrass, server);
                rules.set(AgrarianGameRules.RIGHT_CLICK_HARVEST, AgrarianConfig.get().rightClickHarvest, server);
                rules.set(AgrarianGameRules.UNIVERSAL_BONEMEAL, AgrarianConfig.get().universalBonemeal, server);
                rules.set(AgrarianGameRules.GLOBAL_GROWTH_MULTIPLIER, AgrarianConfig.get().globalGrowthMultiplier, server);

                for (net.minecraft.resources.Identifier cropId : AgrarianCropRules.DYNAMIC_CROPS) {
                    String growthRuleName = "agrarian_reform:growth_" + cropId.getNamespace() + "_" + cropId.getPath();
                    int forced = AgrarianConfig.get().getForcedGrowthMultiplier(cropId.toString());
                    if (forced != 0) {
                        @SuppressWarnings("unchecked")
                        net.minecraft.world.level.gamerules.GameRule<Integer> rule = (net.minecraft.world.level.gamerules.GameRule<Integer>) DynamicGameRuleManager.getDynamicRules().get(growthRuleName);
                        if (rule != null) {
                            rules.set(rule, forced, server);
                        }
                    }
                }
            }
        });

        // Dual lifecycle auto-save hooks
        ServerLifecycleEvents.BEFORE_SAVE.register((server, flush, force) -> {
            AgrarianConfig.saveIfDirty();
        });

        ServerLifecycleEvents.SERVER_STOPPING.register(server -> {
            AgrarianConfig.saveIfDirty();
        });

        // Seed-to-grass growth interaction
        UseBlockCallback.EVENT.register((player, level, hand, hitResult) -> {
            if (!DynamicGameRuleManager.getBoolean(level, AgrarianGameRules.SEEDS_GROW_GRASS)) {
                return InteractionResult.PASS;
            }

            if (hitResult.getDirection() == Direction.DOWN) {
                return InteractionResult.PASS;
            }

            BlockPos pos = hitResult.getBlockPos();
            BlockState state = level.getBlockState(pos);

            if (!state.is(Blocks.DIRT)) {
                return InteractionResult.PASS;
            }

            ItemStack stack = player.getItemInHand(hand);
            if (!stack.is(ItemTags.CHICKEN_FOOD)) {
                return InteractionResult.PASS;
            }

            if (!level.isClientSide()) {
                level.setBlock(pos, Blocks.GRASS_BLOCK.defaultBlockState(), 11);
                level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, Blocks.GRASS_BLOCK.defaultBlockState()));
                
                // 1505 = LevelEvent.PARTICLES_AND_SOUND_PLANT_GROWTH
                level.levelEvent(1505, pos, 15);

                if (!player.getAbilities().instabuild) {
                    stack.shrink(1);
                }
            }

            return InteractionResult.SUCCESS;
        });

        // Right-click harvest and replant interaction
        UseBlockCallback.EVENT.register((player, level, hand, hitResult) -> {
            if (!DynamicGameRuleManager.getBoolean(level, AgrarianGameRules.RIGHT_CLICK_HARVEST)) {
                return InteractionResult.PASS;
            }

            BlockPos pos = hitResult.getBlockPos();
            BlockState state = level.getBlockState(pos);

            // Sugar Cane handling
            if (state.getBlock() instanceof net.minecraft.world.level.block.SugarCaneBlock) {
                BlockPos basePos = pos;
                while (level.getBlockState(basePos.below()).getBlock() instanceof net.minecraft.world.level.block.SugarCaneBlock) {
                    basePos = basePos.below();
                }

                java.util.List<BlockPos> harvestPosList = new java.util.ArrayList<>();
                BlockPos currentPos = basePos.above();
                while (level.getBlockState(currentPos).getBlock() instanceof net.minecraft.world.level.block.SugarCaneBlock) {
                    harvestPosList.add(currentPos);
                    currentPos = currentPos.above();
                }

                if (harvestPosList.isEmpty()) {
                    return InteractionResult.PASS;
                }

                if (!level.isClientSide() && level instanceof ServerLevel serverLevel) {
                    for (BlockPos harvestPos : harvestPosList) {
                        BlockState harvestState = level.getBlockState(harvestPos);
                        List<ItemStack> drops = Block.getDrops(harvestState, serverLevel, harvestPos, null, player, player.getItemInHand(hand));
                        level.setBlock(harvestPos, Blocks.AIR.defaultBlockState(), 11);
                        level.gameEvent(GameEvent.BLOCK_CHANGE, harvestPos, GameEvent.Context.of(player, Blocks.AIR.defaultBlockState()));
                        for (ItemStack drop : drops) {
                            if (!drop.isEmpty()) {
                                Block.popResource(level, pos, drop);
                            }
                        }
                    }

                    SoundType soundType = state.getSoundType();
                    level.playSound(null, pos, soundType.getBreakSound(), SoundSource.BLOCKS, (soundType.getVolume() + 1.0f) / 2.0f, soundType.getPitch() * 0.8f);
                    player.swing(hand, true);
                }

                return InteractionResult.SUCCESS;
            }

            IntegerProperty ageProp = agrarianreform$getAgeProperty(state);
            boolean isCrop = state.is(BlockTags.CROPS)
                    || state.getBlock() instanceof CropBlock
                    || state.getBlock() instanceof net.minecraft.world.level.block.NetherWartBlock
                    || state.getBlock() instanceof net.minecraft.world.level.block.CocoaBlock
                    || (state.getBlock() instanceof net.minecraft.world.level.block.BushBlock && ageProp != null && !(state.getBlock() instanceof net.minecraft.world.level.block.StemBlock));

            if (!isCrop || ageProp == null) {
                return InteractionResult.PASS;
            }

            int currentAge = state.getValue(ageProp);
            int maxAge = agrarianreform$getMaxAge(ageProp);

            if (currentAge < maxAge) {
                return InteractionResult.PASS;
            }

            if (!level.isClientSide() && level instanceof ServerLevel serverLevel) {
                // Get drops using Block.getDrops
                List<ItemStack> drops = Block.getDrops(state, serverLevel, pos, null, player, player.getItemInHand(hand));

                // Replant the crop (set age to 0)
                level.setBlock(pos, state.setValue(ageProp, 0), 11);
                level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, state.setValue(ageProp, 0)));

                // Identify and consume 1 seed/crop item from the drops to simulate replanting
                for (ItemStack drop : drops) {
                    if (drop.is(ItemTags.CHICKEN_FOOD) 
                            || drop.is(ItemTags.VILLAGER_PLANTABLE_SEEDS) 
                            || drop.getItem() == state.getBlock().asItem()
                            || (drop.getItem() instanceof net.minecraft.world.item.BlockItem blockItem && blockItem.getBlock() == state.getBlock())) {
                        drop.shrink(1);
                        break;
                    }
                }

                // Spawn the remaining drops
                for (ItemStack drop : drops) {
                    if (!drop.isEmpty()) {
                        Block.popResource(level, pos, drop);
                    }
                }

                // Play crop harvest break sound
                SoundType soundType = state.getSoundType();
                level.playSound(null, pos, soundType.getBreakSound(), SoundSource.BLOCKS, (soundType.getVolume() + 1.0f) / 2.0f, soundType.getPitch() * 0.8f);

                // Swing player hand
                player.swing(hand, true);
            }

            return InteractionResult.SUCCESS;
        });

        // Universal Bone Meal interaction
        UseBlockCallback.EVENT.register((player, level, hand, hitResult) -> {
            if (!DynamicGameRuleManager.getBoolean(level, AgrarianGameRules.UNIVERSAL_BONEMEAL)) {
                return InteractionResult.PASS;
            }

            ItemStack stack = player.getItemInHand(hand);
            if (!stack.is(Items.BONE_MEAL)) {
                return InteractionResult.PASS;
            }

            BlockPos pos = hitResult.getBlockPos();
            BlockState state = level.getBlockState(pos);
            Block block = state.getBlock();
            boolean grew = false;

            if (block instanceof net.minecraft.world.level.block.SugarCaneBlock) {
                // Find base and calculate column height
                BlockPos basePos = pos;
                while (level.getBlockState(basePos.below()).is(block)) {
                    basePos = basePos.below();
                }

                // Count going up
                int height = 1;
                BlockPos topPos = basePos;
                while (level.getBlockState(topPos.above()).is(block)) {
                    topPos = topPos.above();
                    height++;
                }

                if (height < 3 && level.isEmptyBlock(topPos.above())) {
                    if (!level.isClientSide()) {
                        level.setBlockAndUpdate(topPos.above(), block.defaultBlockState());
                        level.levelEvent(1505, topPos.above(), 15);
                    }
                    grew = true;
                }
            } else if (block instanceof net.minecraft.world.level.block.CactusBlock) {
                // Find base and calculate column height
                BlockPos basePos = pos;
                while (level.getBlockState(basePos.below()).is(block)) {
                    basePos = basePos.below();
                }

                // Count going up
                int height = 1;
                BlockPos topPos = basePos;
                while (level.getBlockState(topPos.above()).is(block)) {
                    topPos = topPos.above();
                    height++;
                }

                if (height < 3 && level.isEmptyBlock(topPos.above())) {
                    if (!level.isClientSide()) {
                        level.setBlockAndUpdate(topPos.above(), block.defaultBlockState());
                        level.levelEvent(1505, topPos.above(), 15);
                    }
                    grew = true;
                }
            } else if (block instanceof net.minecraft.world.level.block.NetherWartBlock) {
                IntegerProperty ageProp = net.minecraft.world.level.block.state.properties.BlockStateProperties.AGE_3;
                if (state.hasProperty(ageProp)) {
                    int age = state.getValue(ageProp);
                    if (age < 3) {
                        if (!level.isClientSide()) {
                            level.setBlock(pos, state.setValue(ageProp, age + 1), 11);
                            level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, state.setValue(ageProp, age + 1)));
                            level.levelEvent(1505, pos, 15);
                        }
                        grew = true;
                    }
                }
            } else if (block instanceof net.minecraft.world.level.block.CocoaBlock) {
                IntegerProperty ageProp = net.minecraft.world.level.block.state.properties.BlockStateProperties.AGE_2;
                if (state.hasProperty(ageProp)) {
                    int age = state.getValue(ageProp);
                    if (age < 2) {
                        if (!level.isClientSide()) {
                            level.setBlock(pos, state.setValue(ageProp, age + 1), 11);
                            level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, state.setValue(ageProp, age + 1)));
                            level.levelEvent(1505, pos, 15);
                        }
                        grew = true;
                    }
                }
            } else if (block instanceof net.minecraft.world.level.block.VineBlock) {
                // Find bottom vine in column
                BlockPos bottomPos = pos;
                while (level.getBlockState(bottomPos.below()).is(block)) {
                    bottomPos = bottomPos.below();
                }

                BlockPos growPos = bottomPos.below();
                if (level.isEmptyBlock(growPos)) {
                    BlockState bottomState = level.getBlockState(bottomPos);
                    if (bottomState.canSurvive(level, growPos)) {
                        if (!level.isClientSide()) {
                            level.setBlockAndUpdate(growPos, bottomState);
                            level.levelEvent(1505, growPos, 15);
                        }
                        grew = true;
                    }
                }
            }

            if (grew) {
                if (!level.isClientSide()) {
                    if (!player.getAbilities().instabuild) {
                        stack.shrink(1);
                    }
                    player.swing(hand, true);
                }
                return InteractionResult.SUCCESS;
            }

            return InteractionResult.PASS;
        });
    }

    private static IntegerProperty agrarianreform$getAgeProperty(BlockState state) {
        for (Property<?> prop : state.getProperties()) {
            if (prop instanceof IntegerProperty intProp && intProp.getName().equals("age")) {
                return intProp;
            }
        }
        return null;
    }

    private static int agrarianreform$getMaxAge(IntegerProperty ageProp) {
        int max = 0;
        for (int val : ageProp.getPossibleValues()) {
            if (val > max) {
                max = val;
            }
        }
        return max;
    }
}
