// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3

package net.instantgratification.agrarianreform.config;

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
 * Verified against: ConfigHelper.java (DasikLibrary 1.8.3+)
 */
public class AgrarianConfig {
    private static AgrarianConfig INSTANCE = new AgrarianConfig();
    private static Path CONFIG_PATH;

    public static final int VERSION = 2;
    public int configVersion = VERSION;

    // Hydration
    public int hydrationSourceRange = 8;
    public int hydrationFlowingRange = 4;
    public int rainGrowthAcceleration = 1;
    public boolean pureWaterHydrationOnly = false;

    // Crop rules
    public boolean growthBiodiversityBonus = true;
    public boolean totalTrampleImmunity = false;
    public boolean alwaysWetFarmland = false;
    public boolean seedsGrowGrass = true;
    public boolean rightClickHarvest = true;
    public boolean universalBonemeal = true;
    public int globalGrowthMultiplier = 100;

    // Ambient/Aesthetics
    public boolean ambientCropRustle = true;
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

    public Set<String> getAllForcedCropIds() {
        Set<String> ids = new LinkedHashSet<>();
        if (forcedCrops != null) ids.addAll(forcedCrops);
        if (forcedGrowthMultipliers != null) ids.addAll(forcedGrowthMultipliers.keySet());
        return ids;
    }

    public int getForcedGrowthMultiplier(String cropId) {
        if (forcedGrowthMultipliers != null && forcedGrowthMultipliers.containsKey(cropId)) {
            return forcedGrowthMultipliers.get(cropId);
        }
        return 0;
    }

    public boolean isForcedCrop(String cropId) {
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
        CONFIG_PATH = configDir.resolve("agrarian-reform.json");
        INSTANCE = net.dasik.social.api.config.ConfigHelper.load(
            CONFIG_PATH, INSTANCE, AgrarianConfig.class, VERSION,
            config -> config.configVersion, (config, ver) -> {
                config.configVersion = ver;
                config.migrateFromV1();
            },
            null, org.slf4j.LoggerFactory.getLogger("AgrarianReform")
        );
        if (INSTANCE != null) {
            INSTANCE.migrateFromV1();
        }
    }

    public static synchronized void save() {
        if (CONFIG_PATH == null) return;
        net.dasik.social.api.config.ConfigHelper.save(CONFIG_PATH, INSTANCE, org.slf4j.LoggerFactory.getLogger("AgrarianReform"));
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
