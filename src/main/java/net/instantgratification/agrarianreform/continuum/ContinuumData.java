package net.instantgratification.agrarianreform.continuum;

import com.mojang.serialization.Codec;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
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
 * This ensures that 'The Continuum' persists across server restarts and
 * long periods of downtime.
 */
public class ContinuumData extends SavedData {

    public static final SavedDataType<ContinuumData> TYPE = new SavedDataType<>(
            Identifier.fromNamespaceAndPath(AgrarianReformFabric.MOD_ID, "continuum_data"),
            ContinuumData::create,
            Codec.unboundedMap(Codec.LONG, Codec.LONG).xmap(
                    ContinuumData::new,
                    ContinuumData::getChunkUnloadTimes),
            DataFixTypes.SAVED_DATA_MAP_DATA // Use an existing data fix type to avoid crashes if we don't have a custom
                                             // one
    );

    private final Map<Long, Long> chunkUnloadTimes;

    public ContinuumData() {
        this(new HashMap<>());
    }

    public ContinuumData(Map<Long, Long> times) {
        // Ensure mutable map
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
