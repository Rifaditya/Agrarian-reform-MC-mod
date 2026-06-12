package net.instantgratification.agrarianreform;

import net.dasik.social.api.gamerule.DynamicGameRuleManager;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.gamerules.GameRule;
import net.minecraft.world.level.gamerules.GameRuleCategory;

/**
 * AgrarianGameRules: The Registry of Laws
 *
 * Manages the registration and retrieval of custom GameRules for Agrarian
 * Reform. Uses the native Mojang registry system introduced in Snapshot 26.1
 * (Snapshot 10 API).
 *
 * IMPORTANT: All registry IDs MUST use snake_case (a-z0-9/._- only).
 * camelCase characters are illegal in Minecraft Identifiers and will throw
 * IdentifierException at class initialisation time, crashing the game.
 *
 * Available Rules:
 * - hydration_source_range:    Irrigation radius for source blocks.
 * - hydration_flowing_range:   Irrigation radius for flowing water.
 * - rain_growth_acceleration:  Extra growth ticks during rain.
 * - growth_biodiversity_bonus: Toggle for biodiversity growth bonuses.
 * - ambient_crop_rustle:       Toggle for crop rustle sounds.
 * - ambient_vitality_particles: Toggle for crop water particles.
 * - total_trample_immunity:    Emergency toggle for absolute crop protection (IG Mode).
 * - always_wet_farmland:       Forces farmland to remain hydrated regardless of water proximity.
 */
public class AgrarianGameRules {
        public static final GameRuleCategory AGRARIAN_REFORM = DynamicGameRuleManager
                        .registerCategory(Identifier.fromNamespaceAndPath(AgrarianReformFabric.MOD_ID, "agrarian_reform"));

        // Hydro-Dynamics
        public static final GameRule<Integer> HYDRATION_SOURCE_RANGE = DynamicGameRuleManager.registerInteger(
                        "hydration_source_range", AGRARIAN_REFORM, 8);

        public static final GameRule<Integer> HYDRATION_FLOWING_RANGE = DynamicGameRuleManager.registerInteger(
                        "hydration_flowing_range", AGRARIAN_REFORM, 4);

        public static final GameRule<Integer> RAIN_GROWTH_ACCELERATION = DynamicGameRuleManager.registerInteger(
                        "rain_growth_acceleration", AGRARIAN_REFORM, 1);

        public static final GameRule<Boolean> BIODIVERSITY_BONUS = DynamicGameRuleManager.registerBoolean(
                        "growth_biodiversity_bonus", AGRARIAN_REFORM, true);

        // Aesthetics & Feedback
        public static final GameRule<Boolean> AMBIENT_CROP_RUSTLE = DynamicGameRuleManager.registerBoolean(
                        "ambient_crop_rustle", AGRARIAN_REFORM, true);

        public static final GameRule<Boolean> AMBIENT_VITALITY_PARTICLES = DynamicGameRuleManager.registerBoolean(
                        "ambient_vitality_particles", AGRARIAN_REFORM, true);

        // Instant Gratification Toggles
        public static final GameRule<Boolean> TOTAL_TRAMPLE_IMMUNITY = DynamicGameRuleManager.registerBoolean(
                        "total_trample_immunity", AGRARIAN_REFORM, false);

        public static final GameRule<Boolean> ALWAYS_WET_FARMLAND = DynamicGameRuleManager.registerBoolean(
                        "always_wet_farmland", AGRARIAN_REFORM, false);

        public static final GameRule<Boolean> SEEDS_GROW_GRASS = DynamicGameRuleManager.registerBoolean(
                        "seeds_grow_grass", AGRARIAN_REFORM, true);

        public static final GameRule<Boolean> RIGHT_CLICK_HARVEST = DynamicGameRuleManager.registerBoolean(
                        "right_click_harvest", AGRARIAN_REFORM, true);

        public static void register() {
                AgrarianReformFabric.LOGGER.info("Registering GameRules for " + AgrarianReformFabric.MOD_ID);
        }
}
