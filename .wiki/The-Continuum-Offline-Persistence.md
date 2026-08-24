# ⏳ The Continuum: Offline Crop Persistence Engine

**The Continuum** is Agrarian Reform's flagship simulation engine. In vanilla Minecraft, crop growth stops completely the instant a chunk unloads or a server shuts down. **The Continuum** bridges this gap by persisting chunk unload timestamps and simulating real-time offline growth upon chunk reload without causing server lag spikes.

---

## 📊 Feature Infobox

| Component | Specification |
| :--- | :--- |
| **Engine Class** | `net.instantgratification.agrarianreform.continuum.ContinuumManager` |
| **Data Handler** | `net.instantgratification.agrarianreform.continuum.ContinuumData` |
| **Scanner Helper**| `net.instantgratification.agrarianreform.continuum.CropScanner` |
| **Saved Data ID** | `agrarian_reform:continuum_data` |
| **Update Budget**| `CROPS_PER_TICK = 5` (Global queue throttled) |
| **Supported Plants**| Crops, Sugar Cane, Cactus, Nether Wart, Cocoa, Vines, Saplings, Sweet Berries |

---

## ⚙️ How The Continuum Works

```
┌─────────────────────────────────────────────────────────────┐
│                     CHUNK UNLOAD EVENT                      │
│ ServerChunkEvents.CHUNK_UNLOAD records server game time pos │
│ Saved to world storage via ContinuumData (SavedDataType)    │
└──────────────────────────────┬──────────────────────────────┘
                               │
                               ▼ (Offline / World Unloaded Time Delta)
┌──────────────────────────────┴──────────────────────────────┐
│                      CHUNK LOAD EVENT                       │
│ ServerChunkEvents.CHUNK_LOAD retrieves unload timestamp     │
│ Calculates timeDelta = currentTick - unloadTick             │
└──────────────────────────────┬──────────────────────────────┘
                               │
                               ▼
┌──────────────────────────────┴──────────────────────────────┐
│                    SURFACE CROP SCANNER                     │
│ CropScanner scans WORLD_SURFACE heightmaps                  │
│ Queues matching plants into ConcurrentLinkedQueue            │
└──────────────────────────────┬──────────────────────────────┘
                               │
                               ▼
┌──────────────────────────────┴──────────────────────────────┐
│                   THROTTLED TICK PROCESSOR                  │
│ ServerTickEvents.END_SERVER_TICK processes 5 crops/tick     │
│ Calculates catch-up growth stages & updates block states     │
└─────────────────────────────────────────────────────────────┘
```

---

## 📐 Mathematical Catch-Up Formulas

When a chunk loads, the time delta $\Delta t$ (measured in game ticks, where $20\text{ ticks} = 1\text{ second}$) is scaled by the global growth multiplier $M$ (default $100\%$):

$$\Delta t_{\text{scaled}} = \frac{\Delta t \cdot M}{100}$$

### 1. Standard Crops (`CropBlock`)
Standard crop growth speed $S$ is calculated taking into account soil hydration and polyculture biodiversity:

$$S = \text{CropScanner.getSpeed}(\text{crop}, \text{level}, \text{pos})$$

The average game ticks required for a crop stage advancement $T_{\text{stage}}$ is:

$$T_{\text{stage}} = \left( \frac{25.0}{S} + 1.0 \right) \cdot \frac{4096.0}{3.0}$$

The simulated growth stages $\Delta \text{age}$ added to the crop block are:

$$\Delta \text{age} = \left\lfloor \frac{\Delta t_{\text{scaled}}}{T_{\text{stage}}} \right\rfloor$$

### 2. Sugar Cane & Cactus Column Heights
Sugar cane and cacti tick at an average rate of **1,365 game ticks per age stage** ($1/16$ chance per random tick). When simulated:

$$\Delta \text{age} = \left\lfloor \frac{\Delta t_{\text{scaled}}}{1365} \right\rfloor$$

New block columns build upward up to the vanilla height limit of **3 blocks**, resetting the apex block's age property to $0$.

### 3. Other Plant Stage Intervals

| Plant Type | Ticks per Stage | Random Tick Probability | Max Stage / Limit |
| :--- | :--- | :--- | :--- |
| **Nether Wart** | 13,650 ticks (~11.37 min) | 10% per random tick | Age 3 |
| **Cocoa Pods** | 6,825 ticks (~5.68 min) | 20% per random tick | Age 2 |
| **Sweet Berry Bush**| 6,825 ticks (~5.68 min) | 20% per random tick | Age 3 |
| **Vines** | 13,650 ticks (~11.37 min) | 10% per random tick | Downward propagation |
| **Saplings** | 95,550 ticks (~79.6 min) | 1.4% per random tick | Stage 2 (Triggers tree growth) |

---

## 💾 Chunk Persistence & Zero-Disk-Write Optimization

A core architectural strength of **Agrarian Reform** is that it operates in **100% harmony with Minecraft's native chunk saving optimization**.

```
┌─────────────────────────────────────────────────────────────┐
│               VANILLA CHUNK SAVING OPTIMIZATION             │
├─────────────────────────────────────────────────────────────┤
│ 1. Chunk is loaded from disk into memory                    │
│    └── ChunkAccess.unsaved initialized to FALSE             │
│                                                             │
│ 2. World Simulation / Player Activity                       │
│    ├── Block change / Crop growth -> LevelChunk.setBlock()  │
│    │   └── Marks chunk as DIRTY (unsaved = TRUE)            │
│    └── Unchanged chunk / Idle terrain                       │
│        └── Chunk remains CLEAN (unsaved = FALSE)            │
│                                                             │
│ 3. Chunk Unload / Auto-Save Event                           │
│    ├── If unsaved == TRUE: Serializes NBT & saves to .mca   │
│    └── If unsaved == FALSE: FAST-FAILS (0 Disk I/O!)        │
└─────────────────────────────────────────────────────────────┘
```

### 1. How Vanilla Minecraft Chunk Saving Works
In vanilla Minecraft, every chunk implements `ChunkAccess` (and `LevelChunk`), which tracks an internal boolean flag:
```java
private volatile boolean unsaved;
```

When a chunk unloads, during periodic 5-minute autosaves, or during `/save-all`, Minecraft executes `ChunkMap.save(ChunkAccess chunk)`:
```java
if (!chunk.isUnsaved()) {
    return false; // Fast-fail! Immediately skips saving.
}
```
* **Clean Chunks (`unsaved == false`)**: If no block state or tile entity in the chunk was modified, Minecraft **aborts saving immediately**. Zero NBT serialization occurs, zero CPU compression is spent, and zero sector writes hit the `.mca` RegionFile on disk.
* **Dirty Chunks (`unsaved == true`)**: If a block state changes—such as dirt spreading into a grass block, sugar cane advancing its internal `age` property ($0 \rightarrow 15$), or a crop advancing a growth stage—`LevelChunk.setBlockState()` sets `unsaved = true`. Minecraft then permanently serializes and writes the updated chunk to disk.

### 2. How Agrarian Reform Preserves This Optimization
Agrarian Reform is specifically engineered to avoid causing artificial chunk dirtiness:

1. **Decoupled World-Level Timestamping**:
   * Chunk unload timestamps are stored in `ContinuumData` (see [[Architecture & Mixins|Architecture-and-Mixins]]), which is a dimension-level `SavedDataType` (`data/continuum_data.dat`).
   * Unloading a chunk **never** touches or modifies chunk NBT, leaving the `LevelChunk.unsaved` flag as `false`.
2. **100% Read-Only Chunk Load Scanning**:
   * When a chunk loads, `CropScanner` inspects block states using `chunk.getBlockState(pos)` and `chunk.getHeight(...)`.
   * These queries are strictly read-only and never trigger `setUnsaved(true)`.
3. **Conditional Block Mutations**:
   * In `ContinuumManager.processCropUpdate`, `level.setBlock()` is **only invoked when a crop actually advances** ($\Delta \text{age} > 0$, or a tree grows, or cactus/sugar cane adds height).
   * If a player briefly enters a chunk with no crops, or if the offline time delta was too brief for any stage advancement, `setBlock` is never called. The chunk remains `unsaved == false` and Minecraft completely skips writing it to disk.

### 3. Persistence Comparison Matrix

| Scenario / Event | Vanilla setBlockState? | unsaved Flag | Saved to Disk (.mca)? | Agrarian Reform Impact |
| :--- | :---: | :---: | :---: | :--- |
| **Dirt spreads into Grass Block** | Yes | `true` | **YES** | Preserved (Vanilla mechanic) |
| **Sugar cane ages / grows height** | Yes | `true` | **YES** | Preserved (Vanilla & Continuum) |
| **Crop advances growth stage** | Yes | `true` | **YES** | Preserved (Offline catch-up & Vanilla) |
| **Chunk loaded with mature crops (no changes)** | No | `false` | **NO (0 Disk I/O)** | Preserved (Read-only scan skips saves) |
| **Player walks across farmland with Soft Step** | No | `false` | **NO (0 Disk I/O)** | Prevents unnecessary trample dirt saves |
| **Empty or non-agricultural chunk loads/unloads** | No | `false` | **NO (0 Disk I/O)** | Completely skipped by save engine |

---

*See also: [[Performance & Queue Throttling|Performance-and-Queue-Throttling]], [[Plant Registry & Crop Types|Plant-Registry-and-Crop-Types]], and [[Architecture & Mixins|Architecture-and-Mixins]]*.

