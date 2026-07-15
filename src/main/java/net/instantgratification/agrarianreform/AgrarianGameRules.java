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

package net.instantgratification.agrarianreform;

import net.dasik.social.api.gamerule.DynamicGameRuleManager;
import net.instantgratification.agrarianreform.config.AgrarianConfig;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.gamerules.GameRule;
import net.minecraft.world.level.gamerules.GameRuleCategory;

/**
 * AgrarianGameRules: The Registry of Laws
 *
 * Manages the registration and retrieval of custom GameRules for Agrarian
 * Reform. All custom GameRules are properly namespaced to prevent clashes.
 *
 * Verified against: GameRules.java (26.2+)
 */
public class AgrarianGameRules {
    public static final GameRuleCategory AGRARIAN_REFORM = DynamicGameRuleManager
            .registerCategory(Identifier.fromNamespaceAndPath(AgrarianReformFabric.MOD_ID, "agrarian_reform"));

    // Hydro-Dynamics
    public static final GameRule<Integer> HYDRATION_SOURCE_RANGE = DynamicGameRuleManager.registerInteger(
            "agrarian_reform:hydration_source_range", AGRARIAN_REFORM, AgrarianConfig.get().hydrationSourceRange);

    public static final GameRule<Integer> HYDRATION_FLOWING_RANGE = DynamicGameRuleManager.registerInteger(
            "agrarian_reform:hydration_flowing_range", AGRARIAN_REFORM, AgrarianConfig.get().hydrationFlowingRange);

    public static final GameRule<Integer> RAIN_GROWTH_ACCELERATION = DynamicGameRuleManager.registerInteger(
            "agrarian_reform:rain_growth_acceleration", AGRARIAN_REFORM, AgrarianConfig.get().rainGrowthAcceleration);

    public static final GameRule<Boolean> BIODIVERSITY_BONUS = DynamicGameRuleManager.registerBoolean(
            "agrarian_reform:growth_biodiversity_bonus", AGRARIAN_REFORM, AgrarianConfig.get().growthBiodiversityBonus);

    // Aesthetics & Feedback
    public static final GameRule<Boolean> AMBIENT_CROP_RUSTLE = DynamicGameRuleManager.registerBoolean(
            "agrarian_reform:ambient_crop_rustle", AGRARIAN_REFORM, AgrarianConfig.get().ambientCropRustle);

    public static final GameRule<Boolean> AMBIENT_VITALITY_PARTICLES = DynamicGameRuleManager.registerBoolean(
            "agrarian_reform:ambient_vitality_particles", AGRARIAN_REFORM, AgrarianConfig.get().ambientVitalityParticles);

    // Instant Gratification Toggles
    public static final GameRule<Boolean> TOTAL_TRAMPLE_IMMUNITY = DynamicGameRuleManager.registerBoolean(
            "agrarian_reform:total_trample_immunity", AGRARIAN_REFORM, AgrarianConfig.get().totalTrampleImmunity);

    public static final GameRule<Boolean> ALWAYS_WET_FARMLAND = DynamicGameRuleManager.registerBoolean(
            "agrarian_reform:always_wet_farmland", AGRARIAN_REFORM, AgrarianConfig.get().alwaysWetFarmland);

    public static final GameRule<Boolean> SEEDS_GROW_GRASS = DynamicGameRuleManager.registerBoolean(
            "agrarian_reform:seeds_grow_grass", AGRARIAN_REFORM, AgrarianConfig.get().seedsGrowGrass);

    public static final GameRule<Boolean> RIGHT_CLICK_HARVEST = DynamicGameRuleManager.registerBoolean(
            "agrarian_reform:right_click_harvest", AGRARIAN_REFORM, AgrarianConfig.get().rightClickHarvest);

    public static final GameRule<Boolean> UNIVERSAL_BONEMEAL = DynamicGameRuleManager.registerBoolean(
            "agrarian_reform:universal_bonemeal", AGRARIAN_REFORM, AgrarianConfig.get().universalBonemeal);

    public static final GameRule<Integer> GLOBAL_GROWTH_MULTIPLIER = DynamicGameRuleManager.registerInteger(
            "agrarian_reform:global_growth_multiplier", AGRARIAN_REFORM, AgrarianConfig.get().globalGrowthMultiplier);

    public static void register() {
        AgrarianReformFabric.LOGGER.info("Registering GameRules for " + AgrarianReformFabric.MOD_ID);
    }
}
