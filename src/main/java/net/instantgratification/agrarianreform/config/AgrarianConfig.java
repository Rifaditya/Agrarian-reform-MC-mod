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

package net.instantgratification.agrarianreform.config;

import java.nio.file.Path;

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

    public static final int VERSION = 1;
    public int configVersion = VERSION;

    // Hydration
    public int hydrationSourceRange = 8;
    public int hydrationFlowingRange = 4;
    public int rainGrowthAcceleration = 1;

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

    public static synchronized void load(Path configDir) {
        CONFIG_PATH = configDir.resolve("agrarian-reform.json");
        INSTANCE = net.dasik.social.api.config.ConfigHelper.load(
            CONFIG_PATH, INSTANCE, AgrarianConfig.class, VERSION,
            config -> config.configVersion, (config, ver) -> config.configVersion = ver,
            null, org.slf4j.LoggerFactory.getLogger("AgrarianReform")
        );
    }

    public static synchronized void save() {
        if (CONFIG_PATH == null) return;
        net.dasik.social.api.config.ConfigHelper.save(CONFIG_PATH, INSTANCE, org.slf4j.LoggerFactory.getLogger("AgrarianReform"));
    }

    public static AgrarianConfig get() {
        return INSTANCE;
    }
}
