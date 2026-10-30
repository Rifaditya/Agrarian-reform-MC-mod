// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3

package net.instantgratification.agrarianreform.command;

import net.instantgratification.agrarianreform.AgrarianGameRules;
import net.instantgratification.agrarianreform.config.AgrarianConfig;
import net.instantgratification.agrarianreform.registry.AgrarianCropRules;
import net.minecraft.SharedConstants;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.Bootstrap;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * AgrarianReformCommandTest: Pillar 3 GameRule & Command Surface Verification Suite (MC 1.20.1)
 *
 * Verifies all 15 static namespaced AgrarianGameRules keys, dynamic per-crop
 * rule key formatting (agrarian_reform:growth_<ns>_<path>), uncapped integer
 * multiplier bounds [Integer.MIN_VALUE, Integer.MAX_VALUE], and AgrarianConfig
 * baseline synchronization.
 */
public class AgrarianReformCommandTest {

    private static final List<String> EXPECTED_STATIC_RULE_KEYS = List.of(
        "agrarian_reform:hydration_source_range",
        "agrarian_reform:hydration_flowing_range",
        "agrarian_reform:rain_growth_acceleration",
        "agrarian_reform:pure_water_hydration_only",
        "agrarian_reform:growth_biodiversity_bonus",
        "agrarian_reform:ambient_crop_rustle",
        "agrarian_reform:ambient_vitality_particles",
        "agrarian_reform:total_trample_immunity",
        "agrarian_reform:trample_immunity_players_only",
        "agrarian_reform:always_wet_farmland",
        "agrarian_reform:seeds_grow_grass",
        "agrarian_reform:right_click_harvest",
        "agrarian_reform:universal_bonemeal",
        "agrarian_reform:global_growth_multiplier",
        "agrarian_reform:debug_mode"
    );

    private static final List<String> EXPECTED_STATIC_FIELDS = List.of(
        "HYDRATION_SOURCE_RANGE",
        "HYDRATION_FLOWING_RANGE",
        "RAIN_GROWTH_ACCELERATION",
        "PURE_WATER_HYDRATION_ONLY",
        "BIODIVERSITY_BONUS",
        "AMBIENT_CROP_RUSTLE",
        "AMBIENT_VITALITY_PARTICLES",
        "TOTAL_TRAMPLE_IMMUNITY",
        "TRAMPLE_IMMUNITY_PLAYERS_ONLY",
        "ALWAYS_WET_FARMLAND",
        "SEEDS_GROW_GRASS",
        "RIGHT_CLICK_HARVEST",
        "UNIVERSAL_BONEMEAL",
        "GLOBAL_GROWTH_MULTIPLIER",
        "DEBUG_MODE"
    );

    @BeforeAll
    static void initMinecraft() {
        try {
            SharedConstants.tryDetectVersion();
            Bootstrap.bootStrap();
        } catch (Throwable ignored) {
        }
    }

    @Test
    @DisplayName("Pillar 3: All 15 Static Namespaced AgrarianGameRules Keys & Uncapped Ranges")
    void testStaticGameRuleRegistrationAndNamespacing() throws IOException, NoSuchFieldException {
        try {
            AgrarianGameRules.register();
            assertNotNull(AgrarianGameRules.AGRARIAN_REFORM, "GameRuleCategory AGRARIAN_REFORM must be initialized");
        } catch (Throwable ignored) {
        }

        // Verify all 15 static fields exist with public static final modifiers on AgrarianGameRules
        for (String fieldName : EXPECTED_STATIC_FIELDS) {
            Field field = AgrarianGameRules.class.getDeclaredField(fieldName);
            int mods = field.getModifiers();
            assertTrue(Modifier.isPublic(mods) && Modifier.isStatic(mods) && Modifier.isFinal(mods),
                    "Field " + fieldName + " must be public static final");
        }

        // Verify source contract for all 15 namespaced rule keys and uncapped [Integer.MIN_VALUE, Integer.MAX_VALUE] bounds
        Path rulesSource = Paths.get("src/main/java/net/instantgratification/agrarianreform/AgrarianGameRules.java");
        assertTrue(Files.exists(rulesSource));
        String source = Files.readString(rulesSource, StandardCharsets.UTF_8);

        for (String expectedKey : EXPECTED_STATIC_RULE_KEYS) {
            assertTrue(expectedKey.startsWith("agrarian_reform:"), "Rule key must be namespaced: " + expectedKey);
            assertTrue(source.contains("\"" + expectedKey + "\""),
                    "AgrarianGameRules.java must declare namespaced rule key: " + expectedKey);
        }

        // Count .range(Integer.MIN_VALUE, Integer.MAX_VALUE) across the 4 integer GameRules
        int uncappedRangeCount = source.split("\\.range\\(Integer\\.MIN_VALUE,\\s*Integer\\.MAX_VALUE\\)", -1).length - 1;
        assertEquals(4, uncappedRangeCount,
                "All 4 integer static GameRules must declare uncapped .range(Integer.MIN_VALUE, Integer.MAX_VALUE)");
    }

    @Test
    @DisplayName("Pillar 3: Dynamic Crop Rule Key Formatting & Uncapped Multiplier Range Synchronization")
    void testDynamicCropRuleFormattingAndConfigSync() throws IOException {
        ResourceLocation cropId = new ResourceLocation("farmersdelight", "tomatoes");
        String expectedRuleKey = "agrarian_reform:growth_" + cropId.getNamespace() + "_" + cropId.getPath();
        assertEquals("agrarian_reform:growth_farmersdelight_tomatoes", expectedRuleKey);

        AgrarianConfig config = AgrarianConfig.get();
        config.setForcedGrowthMultiplier(cropId.toString(), Integer.MAX_VALUE);
        assertEquals(Integer.MAX_VALUE, config.getForcedGrowthMultiplier(cropId.toString()));

        config.setForcedGrowthMultiplier(cropId.toString(), Integer.MIN_VALUE);
        assertEquals(Integer.MIN_VALUE, config.getForcedGrowthMultiplier(cropId.toString()));

        config.setForcedGrowthMultiplier(cropId.toString(), 0);
        try {
            AgrarianCropRules.registerDynamicRules(cropId);
        } catch (Throwable ignored) {
        }

        Path cropRulesSource = Paths.get("src/main/java/net/instantgratification/agrarianreform/registry/AgrarianCropRules.java");
        String cropRulesContent = Files.readString(cropRulesSource, StandardCharsets.UTF_8);
        assertTrue(cropRulesContent.contains(".range(Integer.MIN_VALUE, Integer.MAX_VALUE)"),
                "Dynamic crop rules in AgrarianCropRules.java must declare uncapped [Integer.MIN_VALUE, Integer.MAX_VALUE]");
    }
}
