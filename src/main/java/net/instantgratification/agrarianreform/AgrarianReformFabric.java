package net.instantgratification.agrarianreform;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.instantgratification.agrarianreform.continuum.ContinuumManager;
import net.dasik.social.api.gamerule.DynamicGameRuleManager;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.gameevent.GameEvent;
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
 */
public class AgrarianReformFabric implements ModInitializer {
    public static final String MOD_ID = "agrarian_reform";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Instant Gratification: Agrarian Reform Initialized");
        AgrarianGameRules.register();
        ContinuumManager.initialize();

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
