package net.instantgratification.agrarianreform.client;

import net.fabricmc.api.ClientModInitializer;
import net.instantgratification.agrarianreform.AgrarianReformFabric;

public class AgrarianReformClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        AgrarianReformFabric.LOGGER.info("Initializing Agrarian Reform Client-Side Features (Snapshot 26.x)");

        // Logic handled via BlockColorsMixin for Snapshot 11 compatibility
    }
}
