// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3

package net.instantgratification.agrarianreform.continuum;

import com.mojang.serialization.Codec;
import net.minecraft.util.datafix.DataFixTypes;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.saveddata.SavedDataType;
import net.instantgratification.agrarianreform.AgrarianReformFabric;

import java.util.HashMap;
import java.util.Map;

/**
 * ContinuumData: The Persistent Memory (MC 1.21.11)
 * 
 * Manages the serialization and deserialization of chunk unload timestamps.
 * This ensures that 'The Continuum' persists across server restarts.
 *
 * Verified against: SavedData.java (1.21.11)
 */
public class ContinuumData extends SavedData {

    public static final String DATA_KEY = AgrarianReformFabric.MOD_ID + "_continuum";

    public static final SavedDataType<ContinuumData> TYPE = new SavedDataType<>(
            DATA_KEY,
            ContinuumData::create,
            Codec.unboundedMap(Codec.STRING, Codec.LONG).xmap(
                    encodedMap -> {
                        Map<Long, Long> times = new HashMap<>();
                        encodedMap.forEach((k, v) -> {
                            try {
                                times.put(Long.parseLong(k), v);
                            } catch (NumberFormatException e) {
                                // Skip invalid keys
                            }
                        });
                        return new ContinuumData(times);
                    },
                    data -> {
                        Map<String, Long> encodedMap = new HashMap<>();
                        data.getChunkUnloadTimes().forEach((k, v) -> encodedMap.put(k.toString(), v));
                        return encodedMap;
                    }),
            DataFixTypes.SAVED_DATA_MAP_DATA
    );

    public static final long MAX_UNLOAD_AGE_TICKS = 20L * 60 * 60 * 24 * 30; // 30 game days (51,840,000 ticks)

    private final Map<Long, Long> chunkUnloadTimes;

    public ContinuumData() {
        this(new HashMap<>());
    }

    public ContinuumData(Map<Long, Long> times) {
        this.chunkUnloadTimes = new HashMap<>(times);
    }

    public static ContinuumData create() {
        return new ContinuumData();
    }

    public Map<Long, Long> getChunkUnloadTimes() {
        return this.chunkUnloadTimes;
    }

    public void setUnloadTime(final ChunkPos pos, final long time) {
        this.chunkUnloadTimes.put(pos.toLong(), time);
        this.setDirty();
    }

    public long getUnloadTime(final ChunkPos pos) {
        return this.chunkUnloadTimes.getOrDefault(pos.toLong(), -1L);
    }

    public void remove(final ChunkPos pos) {
        if (this.chunkUnloadTimes.remove(pos.toLong()) != null) {
            this.setDirty();
        }
    }

    public void pruneStaleEntries(long currentGametime) {
        if (currentGametime <= MAX_UNLOAD_AGE_TICKS) {
            return;
        }
        long cutoff = currentGametime - MAX_UNLOAD_AGE_TICKS;
        if (cutoff <= 0) {
            return;
        }
        boolean changed = this.chunkUnloadTimes.values().removeIf(time -> time < cutoff);
        if (changed) {
            this.setDirty();
        }
    }
}
