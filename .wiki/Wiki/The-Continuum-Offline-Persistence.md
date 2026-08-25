# ⏳ The Continuum: Offline Crop Persistence Engine

**The Continuum** is Agrarian Reform's flagship simulation engine. In vanilla Minecraft, crop growth stops completely the instant a chunk unloads or a server shuts down. **The Continuum** bridges this gap by persisting chunk unload timestamps and simulating real-time offline growth upon chunk reload without causing server lag spikes.

---

## 📊 Feature Infobox

| Component | Specification |
| :--- | :--- |
| **Engine Class** | `net.instantgratification.agrarianreform.continuum.ContinuumManager` |
| **Data Handler** | `net.instantgratification.agrarianreform.continuum.ContinuumData` |
| **Scanner Helper**| `net.instantgratification.agrarianreform.continuum.CropScanner` |
| **Saved Data ID** | `agrarian_reform_continuum` (Dimension-scoped `SavedDataType`) |
| **Update Budget**| `CROPS_PER_TICK = 5` (Global queue throttled) |
| **Palette Pre-Filter**| `section.hasOnlyAir()` & `section.maybeHas(AgrarianCropRules::isCropBlock)` |
| **Stale Timestamp Ceiling**| 30 Real Days ($51,840,000\text{ ticks}$) auto-pruned on save |
| **Supported Plants**| Universal Crops, Sugar Cane, Cactus, Nether Wart, Cocoa, Vines, Saplings, Sweet Berries |

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
│             SUB-CHUNK PALETTE-GATED SCANNER                 │
│ CropScanner skips empty air & non-crop sections (O(1))      │
│ Queues matching plants into ConcurrentLinkedQueue            │
└──────────────────────────────┬──────────────────────────────┘
                               │
                               ▼
┌──────────────────────────────┴──────────────────────────────┐
│            THROTTLED PER-CROP TICK PROCESSOR                │
│ ServerTickEvents.END_SERVER_TICK processes 5 crops/tick     │
│ Scales delta per-crop via AgrarianCropRules & updates state │
└─────────────────────────────────────────────────────────────┘
```

---

## 📐 Mathematical Catch-Up Formulas

When a chunk loads, the raw time delta $\Delta t$ (measured in game ticks, where $20\text{ ticks} = 1\text{ second}$) is scaled individually per crop type based on its effective growth multiplier $M_{\text{crop}}$ (where $0 = \text{inherit global}$, $-1 = \text{frozen / 0%}$):

$$\Delta t_{\text{effective}} = \begin{cases} 0 & \text{if } M_{\text{crop}} \le 0 \\ \left\lfloor \frac{\Delta t \cdot M_{\text{crop}}}{100} \right\rfloor & \text{if } M_{\text{crop}} > 0 \end{cases}$$

### 1. Standard Crops (`CropBlock` & Modded Crops)
Standard crop growth speed $S$ is calculated taking into account soil hydration and polyculture biodiversity:

$$S = \text{CropScanner.getSpeed}(\text{crop}, \text{level}, \text{pos})$$

The average game ticks required for a crop stage advancement $T_{\text{stage}}$ is:

$$T_{\text{stage}} = \left( \frac{25.0}{S} + 1.0 \right) \cdot \frac{4096.0}{3.0}$$

The simulated growth stages $\Delta \text{age}$ added to the crop block are:

$$\Delta \text{age} = \left\lfloor \frac{\Delta t_{\text{effective}}}{T_{\text{stage}}} \right\rfloor$$

### 2. Sugar Cane & Cactus Column Heights
Sugar cane and cacti tick at an average rate of **1,365 game ticks per age stage** ($1/16$ chance per random tick). When simulated:

$$\Delta \text{age} = \left\lfloor \frac{\Delta t_{\text{effective}}}{1365} \right\rfloor$$

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

## ⚡ Performance Optimizations

### 1. Sub-Chunk Palette-Level Pre-Filtering
Rather than traversing every 3D block coordinate in loaded chunks ($16 \times 16 \times 384 = 98,304\text{ blocks}$), `CropScanner` executes palette-level filtering on each $16 \times 16 \times 16$ `LevelChunkSection`:
1. `section.hasOnlyAir()`: Skips completely empty sub-chunks in $0.0001\mu\text{s}$.
2. `section.maybeHas(AgrarianCropRules::isCropBlock)`: Queries the sub-chunk's palette array directly. If no crop blocks exist in the palette, the entire 4,096-block volume is skipped immediately.
This rejects **85% to 95%** of non-agricultural sub-chunk sections with zero voxel iteration overhead.

### 2. 30-Day Stale Timestamp Pruning
To prevent unbounded growth of `ContinuumData` in massive, long-running multiplayer worlds where players explore millions of chunks, `ContinuumData` enforces a 30-day retention ceiling:

$$\text{Max Timestamp Age} = 30\text{ days} \times 86,400\text{ s/day} \times 20\text{ ticks/s} = 51,840,000\text{ ticks}$$

During periodic autosaves (`ServerLifecycleEvents.BEFORE_SAVE`), entries older than 30 real-time days are automatically pruned from memory and storage.

---

## 💾 Chunk Persistence & Zero-Disk-Write Optimization

A core architectural strength of **Agrarian Reform** is that it operates in **100% harmony with Minecraft's native chunk saving optimization**.

1. **Decoupled World-Level Timestamping**:
   * Chunk unload timestamps are stored in `ContinuumData` (see [[Architecture & Mixins|Architecture-and-Mixins]]), which is a dimension-level `SavedDataType` (`data/agrarian_reform_continuum.dat`).
   * Unloading a chunk **never** touches or modifies chunk NBT, leaving the `LevelChunk.unsaved` flag as `false`.
2. **100% Read-Only Chunk Load Scanning**:
   * When a chunk loads, `CropScanner` inspects block states using `section.getBlockState(x, y, z)` via palette pre-filters.
   * These queries are strictly read-only and never mark the chunk dirty.
3. **Conditional Block Mutations**:
   * In `ContinuumManager.processCropUpdate`, `level.setBlock()` is **only invoked when a crop actually advances** ($\Delta \text{age} > 0$, or a tree grows, or cactus/sugar cane adds height).
   * If a chunk has no crops or the elapsed time delta was insufficient for a stage change, `setBlock` is never called, and Minecraft completely skips writing the chunk to disk ($0\text{ Disk I/O}$).

---

*See also: [[Performance & Queue Throttling|Performance-and-Queue-Throttling]], [[Plant Registry & Universal Crops|Plant-Registry-and-Crop-Types]], and [[Architecture & Mixins|Architecture-and-Mixins]]*.

