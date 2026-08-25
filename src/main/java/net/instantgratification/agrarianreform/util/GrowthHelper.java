// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3

package net.instantgratification.agrarianreform.util;

import net.dasik.social.api.gamerule.DynamicGameRuleManager;
import net.instantgratification.agrarianreform.AgrarianGameRules;
import net.instantgratification.agrarianreform.registry.AgrarianCropRules;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.registries.BuiltInRegistries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * GrowthHelper: Dynamic tick calculations
 *
 * Simulates growth ticks dynamically based on current game rule configurations
 * and delegates mixin calculations to keep adapters thin.
 *
 * Verified against: BlockBehaviour.java (26.2+)
 */
public class GrowthHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(GrowthHelper.class);

    public static boolean isDebug(Level level) {
        if (level instanceof ServerLevel serverLevel) {
            return DynamicGameRuleManager.getBoolean(serverLevel, AgrarianGameRules.DEBUG_MODE);
        }
        return LOGGER.isDebugEnabled();
    }

    public static final TagKey<Item> SOFT_STEP_BOOTS = TagKey.create(Registries.ITEM,
            Identifier.fromNamespaceAndPath("agrarian_reform", "soft_step_boots"));
    public static final TagKey<Item> CONVENTIONAL_SOFT_BOOTS = TagKey.create(Registries.ITEM,
            Identifier.fromNamespaceAndPath("c", "boots/soft"));

    private static final ThreadLocal<Boolean> IN_GROWTH_TICK = ThreadLocal.withInitial(() -> false);

    public static boolean handleRandomTick(Block block, BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (IN_GROWTH_TICK.get()) {
            return false; // Let inner random ticks run vanilla logic directly without recursion
        }

        // Fast O(1) fail-fast rejection for non-crop blocks
        if (!AgrarianCropRules.isCropBlock(block)) {
            return false; // Let vanilla randomTick proceed normally
        }

        int multiplier = AgrarianCropRules.getEffectiveGrowthMultiplier(level, block);
        if (multiplier == 100) {
            return false; // Let vanilla randomTick proceed normally
        }

        // Cancel entirely if growth is disabled (0% / frozen)
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

        if (extraRuns > 0) {
            IN_GROWTH_TICK.set(true);
            try {
                for (int i = 0; i < extraRuns; i++) {
                    BlockState currentState = level.getBlockState(pos);
                    if (!currentState.is(block)) {
                        break; // State changed, terminate early
                    }
                    if (block instanceof CropBlock crop && crop.isMaxAge(currentState)) {
                        break; // Reached max growth stage, terminate early
                    }
                    currentState.randomTick(level, pos, random);
                }
            } finally {
                IN_GROWTH_TICK.set(false);
            }
        }

        if (isDebug(level) && random.nextInt(50) == 0) {
            LOGGER.debug("[AgrarianReform:GrowthHelper] Crop {} at {} evaluated with multiplier {}% (extra runs: {})",
                    BuiltInRegistries.BLOCK.getKey(block), pos, multiplier, extraRuns);
        }

        return false; // Let vanilla run the final randomTick
    }

    public static float modifyCropGrowthSpeed(float original, Block block, BlockGetter level, BlockPos pos) {
        float speed = original;
        if (level instanceof ServerLevel serverLevel) {
            if (DynamicGameRuleManager.getBoolean(serverLevel, AgrarianGameRules.BIODIVERSITY_BONUS)) {
                boolean hasDifferentCrop = false;
                Direction[] directions = { Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST };
                for (Direction dir : directions) {
                    BlockState neighborState = level.getBlockState(pos.relative(dir));
                    if (neighborState.is(BlockTags.CROPS) && neighborState.getBlock() != block) {
                        hasDifferentCrop = true;
                        break;
                    }
                }
                if (hasDifferentCrop) {
                    speed += 0.10f; // 10% biodiversity bonus
                }
            }
        }
        return speed;
    }

    public static boolean preCropRandomTick(BlockState state, ServerLevel level, BlockPos pos, CropBlock cropBlock) {
        if (!cropBlock.isMaxAge(state)) {
            if (level.isRainingAt(pos.above()) && level.canSeeSky(pos.above())) {
                int spurtAmount = DynamicGameRuleManager.getInt(level, AgrarianGameRules.RAIN_GROWTH_ACCELERATION);
                if (spurtAmount > 0) {
                    int currentAge = cropBlock.getAge(state);
                    int maxAge = cropBlock.getMaxAge();
                    int newAge = Math.min(maxAge, currentAge + spurtAmount);

                    if (newAge > currentAge) {
                        level.setBlock(pos, cropBlock.getStateForAge(newAge), Block.UPDATE_ALL);
                        if (DynamicGameRuleManager.getBoolean(level, AgrarianGameRules.AMBIENT_VITALITY_PARTICLES)) {
                            level.sendParticles(ParticleTypes.HAPPY_VILLAGER, pos.getX() + 0.5D, pos.getY() + 0.5D,
                                    pos.getZ() + 0.5D, 3, 0.25D, 0.25D, 0.25D, 0.0D);
                        }
                        return true; // Cancel tick
                    }
                }
            }
        }
        return false;
    }

    public static void postCropRandomTick(BlockState oldState, ServerLevel level, BlockPos pos, Block cropBlock) {
        BlockState newState = level.getBlockState(pos);
        if (newState.getBlock() == cropBlock && !newState.equals(oldState)) {
            if (DynamicGameRuleManager.getBoolean(level, AgrarianGameRules.AMBIENT_VITALITY_PARTICLES)) {
                level.sendParticles(ParticleTypes.HAPPY_VILLAGER, pos.getX() + 0.5D, pos.getY() + 0.5D,
                        pos.getZ() + 0.5D, 3, 0.25D, 0.25D, 0.25D, 0.0D);
            }
        }
    }

    public static void playCropRustleSound(BlockState state, Level level, BlockPos pos, Entity entity, boolean isMaxAge) {
        if (!(level instanceof ServerLevel serverLevel)) {
            return;
        }
        if (isMaxAge && DynamicGameRuleManager.getBoolean(serverLevel, AgrarianGameRules.AMBIENT_CROP_RUSTLE)) {
            long time = serverLevel.getGameTime();
            int entityId = entity.getId();
            if (SoundHelper.shouldPlayRustle(entityId, time)) {
                double motion = entity.getDeltaMovement().horizontalDistance();
                if (motion > 0.01D) {
                    float pitch = 0.8F + serverLevel.getRandom().nextFloat() * 0.4F;
                    float volume = 0.2F + serverLevel.getRandom().nextFloat() * 0.1F;
                    serverLevel.playSound(null, pos, SoundEvents.GRASS_HIT, SoundSource.BLOCKS, volume, pitch);
                }
            }
        }
    }

    public static boolean handleFarmlandTrample(Level level, BlockState state, BlockPos pos, Entity entity, double fallDistance) {
        if (!(level instanceof ServerLevel serverLevel)) {
            return false;
        }

        boolean totalImmunity = DynamicGameRuleManager.getBoolean(serverLevel, AgrarianGameRules.TOTAL_TRAMPLE_IMMUNITY);
        boolean playersOnly = DynamicGameRuleManager.getBoolean(serverLevel, AgrarianGameRules.TRAMPLE_IMMUNITY_PLAYERS_ONLY);
        boolean immune = false;

        if (totalImmunity) {
            if (playersOnly) {
                immune = entity instanceof net.minecraft.world.entity.player.Player
                        || (entity instanceof net.minecraft.world.entity.TamableAnimal pet && pet.isTame());
            } else {
                immune = true;
            }
        }

        boolean softStep = false;
        if (!immune && entity instanceof LivingEntity living) {
            softStep = hasSoftStep(living);
        }

        if (immune || softStep) {
            if (isDebug(serverLevel)) {
                LOGGER.debug("[AgrarianReform:GrowthHelper] Farmland trample at {} by {}: immune={}, softStep={}",
                        pos, entity.getType().toShortString(), immune, softStep);
            }
            entity.causeFallDamage((float) fallDistance, 1.0F, level.damageSources().fall());
            return true; // Cancel trample
        }
        return false;
    }

    private static boolean hasSoftStep(LivingEntity entity) {
        ItemStack feet = entity.getItemBySlot(EquipmentSlot.FEET);
        if (feet.isEmpty()) {
            return false;
        }
        if (feet.is(Items.LEATHER_BOOTS) || feet.is(SOFT_STEP_BOOTS) || feet.is(CONVENTIONAL_SOFT_BOOTS)) {
            return true;
        }
        var enchantmentRegistry = entity.level().registryAccess()
                .lookupOrThrow(Registries.ENCHANTMENT);
        var featherFallingOpt = enchantmentRegistry
                .get(net.minecraft.world.item.enchantment.Enchantments.FEATHER_FALLING);
        if (featherFallingOpt.isPresent()) {
            return EnchantmentHelper.getItemEnchantmentLevel(featherFallingOpt.get(), feet) > 0;
        }
        return false;
    }

    public static Boolean customFarmlandWaterRange(LevelReader level, BlockPos pos) {
        if (level instanceof ServerLevel serverLevel) {
            if (DynamicGameRuleManager.getBoolean(serverLevel, AgrarianGameRules.ALWAYS_WET_FARMLAND)) {
                return true;
            }

            int sourceRange = DynamicGameRuleManager.getInt(serverLevel, AgrarianGameRules.HYDRATION_SOURCE_RANGE);
            int flowingRange = DynamicGameRuleManager.getInt(serverLevel, AgrarianGameRules.HYDRATION_FLOWING_RANGE);
            boolean pureWaterOnly = DynamicGameRuleManager.getBoolean(serverLevel, AgrarianGameRules.PURE_WATER_HYDRATION_ONLY);

            if (sourceRange <= 4 && flowingRange <= 0 && !pureWaterOnly) {
                return null; // Fall back to vanilla range calculation
            }

            int maxRange = Math.max(sourceRange, flowingRange);
            BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
            int px = pos.getX();
            int py = pos.getY();
            int pz = pos.getZ();

            // Concentric Chebyshev ring search: terminates in O(1) for nearby water
            for (int r = 1; r <= maxRange; r++) {
                for (int y = -1; y <= 1; y++) {
                    int checkY = py + y;
                    // Sweep North & South perimeter edges at radius r
                    for (int dx = -r; dx <= r; dx++) {
                        if (checkWater(level, mutable.set(px + dx, checkY, pz - r), r, sourceRange, flowingRange, pureWaterOnly)
                                || checkWater(level, mutable.set(px + dx, checkY, pz + r), r, sourceRange, flowingRange, pureWaterOnly)) {
                            return true;
                        }
                    }
                    // Sweep East & West perimeter edges at radius r (excluding already checked corners)
                    for (int dz = -r + 1; dz <= r - 1; dz++) {
                        if (checkWater(level, mutable.set(px - r, checkY, pz + dz), r, sourceRange, flowingRange, pureWaterOnly)
                                || checkWater(level, mutable.set(px + r, checkY, pz + dz), r, sourceRange, flowingRange, pureWaterOnly)) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }
        return null;
    }

    private static boolean checkWater(LevelReader level, BlockPos pos, int dist, int sourceRange, int flowingRange, boolean pureWaterOnly) {
        if (pureWaterOnly && !level.getBlockState(pos).is(net.minecraft.world.level.block.Blocks.WATER)) {
            return false;
        }
        var fluid = level.getFluidState(pos);
        if (fluid.is(FluidTags.WATER)) {
            if (fluid.isSource()) {
                return dist <= sourceRange;
            } else {
                return dist <= flowingRange;
            }
        }
        return false;
    }
}
