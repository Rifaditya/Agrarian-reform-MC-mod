package net.instantgratification.agrarianreform;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.level.gamerules.GameRule;
import net.minecraft.world.level.gamerules.GameRuleCategory;
import net.minecraft.world.level.gamerules.GameRuleType;
import net.minecraft.world.level.gamerules.GameRules;

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
 * - hydro_source_range:      Irrigation radius for source blocks.
 * - hydro_flowing_range:     Irrigation radius for flowing water.
 * - hydro_rain_growth_spurt: Extra growth ticks during rain.
 * - hydro_polyculture_boost: Toggle for biodiversity growth bonuses.
 * - hydro_crop_rustle:       Toggle for crop rustle sounds.
 * - hydro_crop_particles:    Toggle for crop water particles.
 * - total_trample_immunity:  Emergency toggle for absolute crop protection (IG Mode).
 */
public class AgrarianGameRules {

        // Hydro-Dynamics
        public static final GameRule<Integer> HYDRO_SOURCE_RANGE = register(
                        "hydro_source_range", GameRuleCategory.UPDATES, GameRuleType.INT,
                        IntegerArgumentType.integer(0, 16), Codec.intRange(0, 16),
                        8, (visitor, rule) -> visitor.visitInteger(rule), i -> i);

        public static final GameRule<Integer> HYDRO_FLOWING_RANGE = register(
                        "hydro_flowing_range", GameRuleCategory.UPDATES, GameRuleType.INT,
                        IntegerArgumentType.integer(0, 16), Codec.intRange(0, 16),
                        4, (visitor, rule) -> visitor.visitInteger(rule), i -> i);

        public static final GameRule<Integer> HYDRO_RAIN_GROWTH_SPURT = register(
                        "hydro_rain_growth_spurt", GameRuleCategory.UPDATES, GameRuleType.INT,
                        IntegerArgumentType.integer(0, 7), Codec.intRange(0, 7),
                        1, (visitor, rule) -> visitor.visitInteger(rule), i -> i);

        public static final GameRule<Boolean> HYDRO_POLYCULTURE_BOOST = register(
                        "hydro_polyculture_boost", GameRuleCategory.UPDATES, GameRuleType.BOOL,
                        BoolArgumentType.bool(), Codec.BOOL,
                        true, (visitor, rule) -> visitor.visitBoolean(rule), b -> b ? 1 : 0);

        // Aesthetics & Feedback
        public static final GameRule<Boolean> HYDRO_CROP_RUSTLE = register(
                        "hydro_crop_rustle", GameRuleCategory.PLAYER, GameRuleType.BOOL,
                        BoolArgumentType.bool(), Codec.BOOL,
                        true, (visitor, rule) -> visitor.visitBoolean(rule), b -> b ? 1 : 0);

        public static final GameRule<Boolean> HYDRO_CROP_PARTICLES = register(
                        "hydro_crop_particles", GameRuleCategory.PLAYER, GameRuleType.BOOL,
                        BoolArgumentType.bool(), Codec.BOOL,
                        true, (visitor, rule) -> visitor.visitBoolean(rule), b -> b ? 1 : 0);

        // Instant Gratification Toggles
        public static final GameRule<Boolean> TOTAL_TRAMPLE_IMMUNITY = register(
                        "total_trample_immunity", GameRuleCategory.MOBS, GameRuleType.BOOL,
                        BoolArgumentType.bool(), Codec.BOOL,
                        false, (visitor, rule) -> visitor.visitBoolean(rule), b -> b ? 1 : 0);

        private static <T> GameRule<T> register(
                        String id,
                        GameRuleCategory category,
                        GameRuleType typeHint,
                        com.mojang.brigadier.arguments.ArgumentType<T> argumentType,
                        Codec<T> codec,
                        T defaultValue,
                        GameRules.VisitorCaller<T> visitorCaller,
                        java.util.function.ToIntFunction<T> commandResultFunction) {
                return Registry.register(
                                BuiltInRegistries.GAME_RULE,
                                id,
                                new GameRule<>(category, typeHint, argumentType, visitorCaller, codec,
                                                commandResultFunction, defaultValue, FeatureFlagSet.of()));
        }

        public static void register() {
                AgrarianReformFabric.LOGGER.info("Registering GameRules for " + AgrarianReformFabric.MOD_ID);
        }
}
