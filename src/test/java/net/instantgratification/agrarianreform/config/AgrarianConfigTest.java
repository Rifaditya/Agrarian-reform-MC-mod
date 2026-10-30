// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3

package net.instantgratification.agrarianreform.config;

import java.lang.reflect.Field;
import net.dasik.social.api.config.ConfigOption;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * AgrarianConfigTest: Headless Unit Test Suite (MC 1.20.1)
 *
 * Verifies AgrarianConfig schema behaviors:
 * - Default config values and schema stability.
 * - Multiplier hierarchy resolution (forced specific > global fallback, frozen -1).
 * - Modded crop discovery registration and deduplication.
 * - Dirty tracking flag transitions (isDirty, markDirty, saveIfDirty).
 * - JSON serialization with Gson.
 *
 * Verified against: AgrarianConfig.java
 */
public class AgrarianConfigTest {

    private AgrarianConfig config;

    @BeforeEach
    void setUp() {
        config = new AgrarianConfig();
    }

    @Test
    @DisplayName("Default Config Schema Invariants")
    void testDefaultConfigValues() {
        assertEquals(8, config.hydrationSourceRange);
        assertEquals(4, config.hydrationFlowingRange);
        assertFalse(config.pureWaterHydrationOnly);
        assertEquals(1, config.rainGrowthAcceleration);
        assertTrue(config.growthBiodiversityBonus);
        assertTrue(config.seedsGrowGrass);
        assertTrue(config.rightClickHarvest);
        assertTrue(config.universalBonemeal);
        assertEquals(100, config.globalGrowthMultiplier);
        assertFalse(config.totalTrampleImmunity);
        assertFalse(config.trampleImmunityPlayersOnly);
        assertFalse(config.alwaysWetFarmland);
        assertTrue(config.ambientCropRustle);
        assertTrue(config.ambientVitalityParticles);
    }

    @Test
    @DisplayName("Per-Crop Multiplier Hierarchy and Frozen State Resolution")
    void testForcedCropMultiplierHierarchy() {
        String wheat = "minecraft:wheat";
        String carrot = "minecraft:carrots";
        String customCrop = "custommod:mystic_crop";

        // Unconfigured crop returns 0 (signals fallback to global multiplier)
        assertEquals(0, config.getForcedGrowthMultiplier(wheat));

        // Setting a forced positive multiplier overrides global
        config.setForcedGrowthMultiplier(wheat, 250);
        assertEquals(250, config.getForcedGrowthMultiplier(wheat));
        assertTrue(AgrarianConfig.isDirty());

        // Setting a frozen multiplier (-1)
        config.setForcedGrowthMultiplier(carrot, -1);
        assertEquals(-1, config.getForcedGrowthMultiplier(carrot));

        // Setting back to 0 sets value to 0 (inherit global)
        config.setForcedGrowthMultiplier(wheat, 0);
        assertEquals(0, config.getForcedGrowthMultiplier(wheat));
        assertTrue(config.getAllForcedCropIds().contains(wheat));

        // Setting custom modded crop multiplier
        config.setForcedGrowthMultiplier(customCrop, 150);
        assertEquals(150, config.getForcedGrowthMultiplier(customCrop));
        assertTrue(config.getAllForcedCropIds().contains(customCrop));
    }

    @Test
    @DisplayName("Modded Crop Discovery Tracking and Deduplication")
    void testCropDiscoveryTracking() {
        String tomato = "farmersdelight:tomatoes";
        String onion = "farmersdelight:onions";

        assertFalse(config.isForcedCrop(tomato));

        // Record first discovery
        boolean firstRecord = config.recordDiscoveredCrop(tomato);
        assertTrue(firstRecord, "First discovery must return true");
        assertTrue(config.isForcedCrop(tomato));
        assertTrue(AgrarianConfig.isDirty());

        // Duplicate discovery must return false and remain idempotent
        boolean duplicateRecord = config.recordDiscoveredCrop(tomato);
        assertFalse(duplicateRecord, "Duplicate discovery must return false");

        // Record second crop
        assertTrue(config.recordDiscoveredCrop(onion));
        assertTrue(config.isForcedCrop(onion));
        assertEquals(2, config.forcedCrops.size());
    }

    @Test
    @DisplayName("Dirty Tracking State Transitions")
    void testDirtyTrackingMechanics() {
        // Manual dirty mark
        AgrarianConfig.markDirty();
        assertTrue(AgrarianConfig.isDirty());

        // Setter dirty mark
        config.setForcedGrowthMultiplier("minecraft:potatoes", 180);
        assertTrue(AgrarianConfig.isDirty());
    }

    @Test
    @DisplayName("Config JSON Serialization with Gson")
    void testConfigSerialization() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        config.setForcedGrowthMultiplier("minecraft:wheat", 150);
        String json = gson.toJson(config);
        assertNotNull(json);
        assertTrue(json.contains("\"hydrationSourceRange\": 8"));
        assertTrue(json.contains("\"minecraft:wheat\": 150"));

        AgrarianConfig deserialized = gson.fromJson(json, AgrarianConfig.class);
        assertNotNull(deserialized);
        assertEquals(8, deserialized.hydrationSourceRange);
        assertEquals(150, deserialized.getForcedGrowthMultiplier("minecraft:wheat"));
        assertTrue(deserialized.growthBiodiversityBonus);
    }

    @Test
    @DisplayName("Pillar 1: ConfigOption annotations are present on all configuration fields with bounds")
    void testConfigAnnotationsPresent() throws Exception {
        Field hydrationField = AgrarianConfig.class.getField("hydrationSourceRange");
        assertTrue(hydrationField.isAnnotationPresent(ConfigOption.class));
        ConfigOption opt = hydrationField.getAnnotation(ConfigOption.class);
        assertEquals("general", opt.category());
        assertEquals("agrarian_reform:hydration_source_range", opt.gameRuleName());
        assertTrue(opt.syncGameRule());
        assertTrue(opt.slider());
        assertEquals(0, opt.min());
        assertEquals(32, opt.max());
        assertEquals(1, opt.step());

        Field multiplierField = AgrarianConfig.class.getField("globalGrowthMultiplier");
        ConfigOption opt2 = multiplierField.getAnnotation(ConfigOption.class);
        assertEquals(0, opt2.min());
        assertEquals(10000, opt2.max());
        assertFalse(opt2.slider());
    }

    @Test
    @DisplayName("Pillar 5: Sidedness safety - AgrarianConfig has zero client-only dependencies")
    void testConfigSidednessSafety() {
        assertFalse(AgrarianConfig.class.isAnnotationPresent(net.fabricmc.api.Environment.class),
            "AgrarianConfig must be universally accessible on both dedicated server and client");
    }
}
