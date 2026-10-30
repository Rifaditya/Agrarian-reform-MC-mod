// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3

package net.instantgratification.agrarianreform.continuum;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.saveddata.SavedData;
import net.instantgratification.agrarianreform.AgrarianReformFabric;

import java.util.HashMap;
import java.util.Map;

/**
 * ContinuumData: The Persistent Memory (MC 1.20.1)
 * 
 * Manages the serialization and deserialization of chunk unload timestamps.
 * This ensures that 'The Continuum' persists across server restarts.
 *
 * Verified against: SavedData.java (1.20.1)
 */
public class ContinuumData extends SavedData {

    public static final String DATA_KEY = AgrarianReformFabric.MOD_ID + "_continuum";
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

    public static ContinuumData createFromNbt(CompoundTag tag) {
        Map<Long, Long> times = new HashMap<>();
        if (tag.contains("UnloadTimes", Tag.TAG_COMPOUND)) {
            CompoundTag timesTag = tag.getCompound("UnloadTimes");
            for (String key : timesTag.getAllKeys()) {
                try {
                    times.put(Long.parseLong(key), timesTag.getLong(key));
                } catch (NumberFormatException ignored) {
                }
            }
        }
        return new ContinuumData(times);
    }

    @Override
    public CompoundTag save(CompoundTag tag) {
        CompoundTag timesTag = new CompoundTag();
        for (Map.Entry<Long, Long> entry : this.chunkUnloadTimes.entrySet()) {
            timesTag.putLong(String.valueOf(entry.getKey()), entry.getValue());
        }
        tag.put("UnloadTimes", timesTag);
        return tag;
    }

    public static ContinuumData get(ServerLevel level) {
        return level.getDataStorage().computeIfAbsent(ContinuumData::createFromNbt, ContinuumData::create, DATA_KEY);
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
