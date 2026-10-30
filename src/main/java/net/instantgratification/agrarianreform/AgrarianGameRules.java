// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3

package net.instantgratification.agrarianreform;

import net.dasik.social.api.gamerule.DynamicGameRuleManager;
import net.fabricmc.fabric.api.gamerule.v1.CustomGameRuleCategory;
import net.instantgratification.agrarianreform.config.AgrarianConfig;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.GameRules;

/**
 * AgrarianGameRules: The Registry of Laws (MC 1.21.1)
 *
 * Manages the registration and retrieval of custom GameRules for Agrarian
 * Reform. All custom GameRules are properly namespaced to prevent clashes.
 *
 * Verified against: GameRules.java (1.21.1)
 */
public class AgrarianGameRules {
    public static final CustomGameRuleCategory AGRARIAN_REFORM = DynamicGameRuleManager
            .registerCategory(ResourceLocation.fromNamespaceAndPath(AgrarianReformFabric.MOD_ID, "agrarian_reform"),
                    Component.translatable("gamerule.category.agrarian_reform"));

    // Hydro-Dynamics
    public static final GameRules.Key<GameRules.IntegerValue> HYDRATION_SOURCE_RANGE = DynamicGameRuleManager
            .integerRule("agrarian_reform:hydration_source_range", AGRARIAN_REFORM, AgrarianConfig.get().hydrationSourceRange)
            .range(Integer.MIN_VALUE, Integer.MAX_VALUE)
            .register();

    public static final GameRules.Key<GameRules.IntegerValue> HYDRATION_FLOWING_RANGE = DynamicGameRuleManager
            .integerRule("agrarian_reform:hydration_flowing_range", AGRARIAN_REFORM, AgrarianConfig.get().hydrationFlowingRange)
            .range(Integer.MIN_VALUE, Integer.MAX_VALUE)
            .register();

    public static final GameRules.Key<GameRules.IntegerValue> RAIN_GROWTH_ACCELERATION = DynamicGameRuleManager
            .integerRule("agrarian_reform:rain_growth_acceleration", AGRARIAN_REFORM, AgrarianConfig.get().rainGrowthAcceleration)
            .range(Integer.MIN_VALUE, Integer.MAX_VALUE)
            .register();

    public static final GameRules.Key<GameRules.BooleanValue> PURE_WATER_HYDRATION_ONLY = DynamicGameRuleManager
            .booleanRule("agrarian_reform:pure_water_hydration_only", AGRARIAN_REFORM, AgrarianConfig.get().pureWaterHydrationOnly)
            .register();

    public static final GameRules.Key<GameRules.BooleanValue> BIODIVERSITY_BONUS = DynamicGameRuleManager
            .booleanRule("agrarian_reform:growth_biodiversity_bonus", AGRARIAN_REFORM, AgrarianConfig.get().growthBiodiversityBonus)
            .register();

    // Aesthetics & Feedback
    public static final GameRules.Key<GameRules.BooleanValue> AMBIENT_CROP_RUSTLE = DynamicGameRuleManager
            .booleanRule("agrarian_reform:ambient_crop_rustle", AGRARIAN_REFORM, AgrarianConfig.get().ambientCropRustle)
            .register();

    public static final GameRules.Key<GameRules.BooleanValue> AMBIENT_VITALITY_PARTICLES = DynamicGameRuleManager
            .booleanRule("agrarian_reform:ambient_vitality_particles", AGRARIAN_REFORM, AgrarianConfig.get().ambientVitalityParticles)
            .register();

    // Instant Gratification Toggles
    public static final GameRules.Key<GameRules.BooleanValue> TOTAL_TRAMPLE_IMMUNITY = DynamicGameRuleManager
            .booleanRule("agrarian_reform:total_trample_immunity", AGRARIAN_REFORM, AgrarianConfig.get().totalTrampleImmunity)
            .register();

    public static final GameRules.Key<GameRules.BooleanValue> TRAMPLE_IMMUNITY_PLAYERS_ONLY = DynamicGameRuleManager
            .booleanRule("agrarian_reform:trample_immunity_players_only", AGRARIAN_REFORM, AgrarianConfig.get().trampleImmunityPlayersOnly)
            .register();

    public static final GameRules.Key<GameRules.BooleanValue> ALWAYS_WET_FARMLAND = DynamicGameRuleManager
            .booleanRule("agrarian_reform:always_wet_farmland", AGRARIAN_REFORM, AgrarianConfig.get().alwaysWetFarmland)
            .register();

    public static final GameRules.Key<GameRules.BooleanValue> SEEDS_GROW_GRASS = DynamicGameRuleManager
            .booleanRule("agrarian_reform:seeds_grow_grass", AGRARIAN_REFORM, AgrarianConfig.get().seedsGrowGrass)
            .register();

    public static final GameRules.Key<GameRules.BooleanValue> RIGHT_CLICK_HARVEST = DynamicGameRuleManager
            .booleanRule("agrarian_reform:right_click_harvest", AGRARIAN_REFORM, AgrarianConfig.get().rightClickHarvest)
            .register();

    public static final GameRules.Key<GameRules.BooleanValue> UNIVERSAL_BONEMEAL = DynamicGameRuleManager
            .booleanRule("agrarian_reform:universal_bonemeal", AGRARIAN_REFORM, AgrarianConfig.get().universalBonemeal)
            .register();

    public static final GameRules.Key<GameRules.IntegerValue> GLOBAL_GROWTH_MULTIPLIER = DynamicGameRuleManager
            .integerRule("agrarian_reform:global_growth_multiplier", AGRARIAN_REFORM, AgrarianConfig.get().globalGrowthMultiplier)
            .range(Integer.MIN_VALUE, Integer.MAX_VALUE)
            .register();

    // Diagnostics
    public static final GameRules.Key<GameRules.BooleanValue> DEBUG_MODE = DynamicGameRuleManager
            .booleanRule("agrarian_reform:debug_mode", AGRARIAN_REFORM, false)
            .register();

    public static void register() {
        AgrarianReformFabric.LOGGER.info("Registering GameRules for " + AgrarianReformFabric.MOD_ID);
    }
}
