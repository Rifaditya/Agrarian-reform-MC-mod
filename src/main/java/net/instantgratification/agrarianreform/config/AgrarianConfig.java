// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3

package net.instantgratification.agrarianreform.config;

import net.dasik.social.api.config.ConfigOption;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * AgrarianConfig: Global Configuration Template
 *
 * Manages baseline template settings initialized for newly generated worlds.
 *
 * Verified against: ConfigHelper.java (DasikLibrary 1.21.11)
 */
public class AgrarianConfig {
    private static AgrarianConfig INSTANCE = new AgrarianConfig();
    private static Path CONFIG_PATH;

    public static final int VERSION = 2;
    public int configVersion = VERSION;

    // ==========================================
    // Category: General Options
    // ==========================================

    @ConfigOption(
        category = "general",
        nameKey = "gamerule.agrarian_reform.hydration_source_range",
        descriptionKey = "gamerule.agrarian_reform.hydration_source_range.description",
        min = 0,
        max = 32,
        step = 1,
        slider = true,
        order = 1,
        syncGameRule = true,
        gameRuleName = "agrarian_reform:hydration_source_range"
    )
    public int hydrationSourceRange = 8;

    @ConfigOption(
        category = "general",
        nameKey = "gamerule.agrarian_reform.hydration_flowing_range",
        descriptionKey = "gamerule.agrarian_reform.hydration_flowing_range.description",
        min = 0,
        max = 32,
        step = 1,
        slider = true,
        order = 2,
        syncGameRule = true,
        gameRuleName = "agrarian_reform:hydration_flowing_range"
    )
    public int hydrationFlowingRange = 4;

    @ConfigOption(
        category = "general",
        nameKey = "gamerule.agrarian_reform.pure_water_hydration_only",
        descriptionKey = "gamerule.agrarian_reform.pure_water_hydration_only.description",
        order = 3,
        syncGameRule = true,
        gameRuleName = "agrarian_reform:pure_water_hydration_only"
    )
    public boolean pureWaterHydrationOnly = false;

    @ConfigOption(
        category = "general",
        nameKey = "gamerule.agrarian_reform.rain_growth_acceleration",
        descriptionKey = "gamerule.agrarian_reform.rain_growth_acceleration.description",
        min = 0,
        max = 10,
        step = 1,
        slider = true,
        order = 4,
        syncGameRule = true,
        gameRuleName = "agrarian_reform:rain_growth_acceleration"
    )
    public int rainGrowthAcceleration = 1;

    @ConfigOption(
        category = "general",
        nameKey = "gamerule.agrarian_reform.growth_biodiversity_bonus",
        descriptionKey = "gamerule.agrarian_reform.growth_biodiversity_bonus.description",
        order = 5,
        syncGameRule = true,
        gameRuleName = "agrarian_reform:growth_biodiversity_bonus"
    )
    public boolean growthBiodiversityBonus = true;

    @ConfigOption(
        category = "general",
        nameKey = "gamerule.agrarian_reform.seeds_grow_grass",
        descriptionKey = "gamerule.agrarian_reform.seeds_grow_grass.description",
        order = 6,
        syncGameRule = true,
        gameRuleName = "agrarian_reform:seeds_grow_grass"
    )
    public boolean seedsGrowGrass = true;

    @ConfigOption(
        category = "general",
        nameKey = "gamerule.agrarian_reform.right_click_harvest",
        descriptionKey = "gamerule.agrarian_reform.right_click_harvest.description",
        order = 7,
        syncGameRule = true,
        gameRuleName = "agrarian_reform:right_click_harvest"
    )
    public boolean rightClickHarvest = true;

    @ConfigOption(
        category = "general",
        nameKey = "gamerule.agrarian_reform.universal_bonemeal",
        descriptionKey = "gamerule.agrarian_reform.universal_bonemeal.description",
        order = 8,
        syncGameRule = true,
        gameRuleName = "agrarian_reform:universal_bonemeal"
    )
    public boolean universalBonemeal = true;

    @ConfigOption(
        category = "general",
        nameKey = "gamerule.agrarian_reform.global_growth_multiplier",
        descriptionKey = "gamerule.agrarian_reform.global_growth_multiplier.description",
        min = 0,
        max = 10000,
        step = 1,
        slider = false,
        order = 9,
        syncGameRule = true,
        gameRuleName = "agrarian_reform:global_growth_multiplier"
    )
    public int globalGrowthMultiplier = 100;

    // ==========================================
    // Category: Immersion & Aesthetics
    // ==========================================

    @ConfigOption(
        category = "immersion",
        nameKey = "gamerule.agrarian_reform.total_trample_immunity",
        descriptionKey = "gamerule.agrarian_reform.total_trample_immunity.description",
        order = 1,
        syncGameRule = true,
        gameRuleName = "agrarian_reform:total_trample_immunity"
    )
    public boolean totalTrampleImmunity = false;

    @ConfigOption(
        category = "immersion",
        nameKey = "gamerule.agrarian_reform.trample_immunity_players_only",
        descriptionKey = "gamerule.agrarian_reform.trample_immunity_players_only.description",
        order = 2,
        syncGameRule = true,
        gameRuleName = "agrarian_reform:trample_immunity_players_only"
    )
    public boolean trampleImmunityPlayersOnly = false;

    @ConfigOption(
        category = "immersion",
        nameKey = "gamerule.agrarian_reform.always_wet_farmland",
        descriptionKey = "gamerule.agrarian_reform.always_wet_farmland.description",
        order = 3,
        syncGameRule = true,
        gameRuleName = "agrarian_reform:always_wet_farmland"
    )
    public boolean alwaysWetFarmland = false;

    @ConfigOption(
        category = "immersion",
        nameKey = "gamerule.agrarian_reform.ambient_crop_rustle",
        descriptionKey = "gamerule.agrarian_reform.ambient_crop_rustle.description",
        order = 4,
        syncGameRule = true,
        gameRuleName = "agrarian_reform:ambient_crop_rustle"
    )
    public boolean ambientCropRustle = true;

    @ConfigOption(
        category = "immersion",
        nameKey = "gamerule.agrarian_reform.ambient_vitality_particles",
        descriptionKey = "gamerule.agrarian_reform.ambient_vitality_particles.description",
        order = 5,
        syncGameRule = true,
        gameRuleName = "agrarian_reform:ambient_vitality_particles"
    )
    public boolean ambientVitalityParticles = true;

    // Forced & Dynamic Modded/Universal Crops
    public List<String> forcedCrops = new ArrayList<>();
    public Map<String, Integer> forcedGrowthMultipliers = new HashMap<>();

    private static volatile boolean dirty = false;

    public static void markDirty() {
        dirty = true;
    }

    public static boolean isDirty() {
        return dirty;
    }

    public synchronized Set<String> getAllForcedCropIds() {
        Set<String> ids = new LinkedHashSet<>();
        if (forcedCrops != null) ids.addAll(forcedCrops);
        if (forcedGrowthMultipliers != null) ids.addAll(forcedGrowthMultipliers.keySet());
        return ids;
    }

    public synchronized int getForcedGrowthMultiplier(String cropId) {
        if (forcedGrowthMultipliers != null && forcedGrowthMultipliers.containsKey(cropId)) {
            return forcedGrowthMultipliers.get(cropId);
        }
        return 0;
    }

    public synchronized boolean isForcedCrop(String cropId) {
        return getAllForcedCropIds().contains(cropId);
    }

    public synchronized boolean recordDiscoveredCrop(String cropId) {
        if (cropId == null || cropId.isEmpty()) return false;
        boolean modified = false;
        if (forcedCrops == null) {
            forcedCrops = new ArrayList<>();
            modified = true;
        }
        if (!forcedCrops.contains(cropId)) {
            forcedCrops.add(cropId);
            modified = true;
        }
        if (forcedGrowthMultipliers == null) {
            forcedGrowthMultipliers = new HashMap<>();
            modified = true;
        }
        if (!forcedGrowthMultipliers.containsKey(cropId)) {
            forcedGrowthMultipliers.put(cropId, 0);
            modified = true;
        }
        if (modified) {
            dirty = true;
        }
        return modified;
    }

    public synchronized void setForcedGrowthMultiplier(String cropId, int multiplier) {
        if (cropId == null || cropId.isEmpty()) return;
        if (forcedGrowthMultipliers == null) forcedGrowthMultipliers = new HashMap<>();
        if (forcedCrops == null) forcedCrops = new ArrayList<>();
        forcedGrowthMultipliers.put(cropId, multiplier);
        if (!forcedCrops.contains(cropId)) forcedCrops.add(cropId);
        dirty = true;
    }

    public void migrateFromV1() {
        if (forcedCrops == null) {
            forcedCrops = new ArrayList<>();
        }
        if (forcedGrowthMultipliers == null) {
            forcedGrowthMultipliers = new HashMap<>();
        }
    }

    public static synchronized void load(Path configDir) {
        Path legacyPath = configDir.resolve("agrarian-reform.json");
        Path standardPath = configDir.resolve("agrarian_reform").resolve("config.json");
        if (Files.exists(standardPath)) {
            CONFIG_PATH = standardPath;
        } else if (Files.exists(legacyPath)) {
            CONFIG_PATH = legacyPath;
        } else {
            CONFIG_PATH = standardPath;
        }
        INSTANCE = net.dasik.social.api.config.ConfigHelper.loadOrCreate(
            CONFIG_PATH,
            AgrarianConfig.class,
            AgrarianConfig::new
        );
        if (INSTANCE != null) {
            INSTANCE.migrateFromV1();
        }
    }

    public static synchronized void save() {
        if (CONFIG_PATH == null) return;
        net.dasik.social.api.config.ConfigHelper.save(CONFIG_PATH, INSTANCE);
    }

    public static synchronized void saveIfDirty() {
        if (dirty) {
            save();
            dirty = false;
        }
    }

    public static AgrarianConfig get() {
        return INSTANCE;
    }
}
