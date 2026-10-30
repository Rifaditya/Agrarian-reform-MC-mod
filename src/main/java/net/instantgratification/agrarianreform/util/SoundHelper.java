// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3

package net.instantgratification.agrarianreform.util;

import it.unimi.dsi.fastutil.ints.Int2LongMap;
import it.unimi.dsi.fastutil.ints.Int2LongOpenHashMap;

/**
 * SoundHelper: Cooldown & Sound Utilities
 *
 * Manages sound cooldowns dynamically for entity interactions to prevent
 * audio spam, utilizing primitive collections to avoid GC pressure and autoboxing.
 *
 * Verified against: Int2LongOpenHashMap.java (FastUtil 8.5+)
 */
public class SoundHelper {
    private static final Int2LongMap RUSTLE_COOLDOWNS = new Int2LongOpenHashMap();

    public static synchronized boolean shouldPlayRustle(int entityId, long currentTime) {
        long lastRustle = RUSTLE_COOLDOWNS.get(entityId);
        // FastUtil defaults to 0 if the key is not present
        if (lastRustle == 0L || currentTime - lastRustle > 10L) {
            RUSTLE_COOLDOWNS.put(entityId, currentTime);
            return true;
        }
        return false;
    }

    public static synchronized void purgeEntity(int entityId) {
        RUSTLE_COOLDOWNS.remove(entityId);
    }
}
