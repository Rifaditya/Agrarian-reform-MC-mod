// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3

package net.instantgratification.agrarianreform.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.instantgratification.agrarianreform.AgrarianReformFabric;

/**
 * AgrarianReformClient: Client-Side Initializer
 *
 * Handles client-only registrations and initializations.
 *
 * Verified against: ClientModInitializer.java (Fabric API)
 */
@Environment(EnvType.CLIENT)
public class AgrarianReformClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        AgrarianReformFabric.LOGGER.info("Initializing Agrarian Reform Client-Side Features");

        // Color logic is handled via BlockColorsMixin
    }
}
