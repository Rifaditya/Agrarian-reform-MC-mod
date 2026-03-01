package net.instantgratification.agrarianreform;

import net.fabricmc.api.ModInitializer;
import net.instantgratification.agrarianreform.continuum.ContinuumManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Agrarian Reform: The Living Earth
 * 
 * Main entry point for the Agrarian Reform mod. This mod implements a
 * high-fidelity
 * agricultural simulation for Minecraft, featuring:
 * - The Continuum: Persistent offline crop growth via mathematical delta
 * simulation.
 * - Hydro-Dynamics: Advanced irrigation mechanics and rain integration.
 * - Polyculture: Biodiversity growth bonuses for mixed-crop fields.
 * - Soil Resilience: Nuanced trample logic and Instant Gratification toggles.
 * 
 * Part of the Vanilla Outsider and Instant Gratification collections.
 */
public class AgrarianReformFabric implements ModInitializer {
    public static final String MOD_ID = "agrarian-reform";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Instant Gratification: Agrarian Reform Initialized");
        AgrarianGameRules.register();
        ContinuumManager.initialize();
    }
}
