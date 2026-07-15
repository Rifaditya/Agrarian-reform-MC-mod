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

package net.instantgratification.agrarianreform.continuum;

import com.mojang.serialization.Codec;
import net.minecraft.resources.Identifier;
import net.minecraft.util.datafix.DataFixTypes;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.saveddata.SavedDataType;
import net.instantgratification.agrarianreform.AgrarianReformFabric;

import java.util.HashMap;
import java.util.Map;

/**
 * ContinuumData: The Persistent Memory
 * 
 * Manages the serialization and deserialization of chunk unload timestamps.
 * This ensures that 'The Continuum' persists across server restarts.
 *
 * Verified against: SavedData.java (26.2+)
 */
public class ContinuumData extends SavedData {

    public static final SavedDataType<ContinuumData> TYPE = new SavedDataType<>(
            Identifier.fromNamespaceAndPath(AgrarianReformFabric.MOD_ID, "continuum_data"),
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
        this.chunkUnloadTimes.put(pos.pack(), time);
        this.setDirty();
    }

    public long getUnloadTime(final ChunkPos pos) {
        return this.chunkUnloadTimes.getOrDefault(pos.pack(), -1L);
    }

    public void remove(final ChunkPos pos) {
        if (this.chunkUnloadTimes.remove(pos.pack()) != null) {
            this.setDirty();
        }
    }
}
