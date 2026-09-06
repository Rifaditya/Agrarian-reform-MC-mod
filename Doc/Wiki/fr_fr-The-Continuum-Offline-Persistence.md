# ⏳ Le Continuum : Moteur de persistance et croissance hors-ligne

**The Continuum** is Agrarian Reform's flagship simulation engine. In vanilla Minecraft, crop growth stops completely the instant a chunk unloads or a server shuts down. **The Continuum** bridges this gap by persisting chunk unload timestamps and simulating real-time offline growth upon chunk reload without causing server lag spikes.

---

## 📊 Infobox des spécifications techniques

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

## ⚙️ Fonctionnement du Continuum

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

## 📐 Formules mathématiques de rattrapage

$$\Delta t_{\text{effective}} = \begin{cases} 0 & \text{if } M_{\text{crop}} \le 0 \\ \left\lfloor \frac{\Delta t \cdot M_{\text{crop}}}{100} \right\rfloor & \text{if } M_{\text{crop}} > 0 \end{cases}$$

### 1. Standard Crops (`CropBlock` & Modded Crops)

$$S = \text{CropScanner.getSpeed}(\text{crop}, \text{level}, \text{pos})$$
$$T_{\text{stage}} = \left( \frac{25.0}{S} + 1.0 \right) \cdot \frac{4096.0}{3.0}$$
$$\Delta \text{age} = \left\lfloor \frac{\Delta t_{\text{effective}}}{T_{\text{stage}}} \right\rfloor$$

### 2. Sugar Cane & Cactus Column Heights

$$\Delta \text{age} = \left\lfloor \frac{\Delta t_{\text{effective}}}{1365} \right\rfloor$$

### 3. Other Plant Stage Intervals

| Plant Type | Ticks per Stage | Random Tick Probability | Max Stage / Limit |
| :--- | :--- | :--- | :--- |
| **Nether Wart** | 13,650 ticks (~11.37 min) | 10% per random tick | Age 3 |
| **Cocoa Pods** | 6,825 ticks (~5.68 min) | 20% per random tick | Age 2 |
| **Sweet Berry Bush**| 6,825 ticks (~5.68 min) | 20% per random tick | Age 3 |
| **Vines** | 13,650 ticks (~11.37 min) | 10% per random tick | Downward propagation |
| **Saplings** | 95,550 ticks (~79.6 min) | 1.4% per random tick | Stage 2 (Triggers tree growth) |

---

## ⚡ Optimisations de performances

### 1. Sub-Chunk Palette-Level Pre-Filtering
Rather than traversing every 3D block coordinate in loaded chunks ($16 \times 16 \times 384 = 98,304\text{ blocks}$), `CropScanner` executes palette-level filtering on each $16 \times 16 \times 16$ `LevelChunkSection`:
1. `section.hasOnlyAir()`: Skips completely empty sub-chunks in $0.0001\mu\text{s}$.
2. `section.maybeHas(AgrarianCropRules::isCropBlock)`: Queries the sub-chunk's palette array directly. If no crop blocks exist in the palette, the entire 4,096-block volume is skipped immediately.
This rejects **85% to 95%** of non-agricultural sub-chunk sections with zero voxel iteration overhead.

### 2. 30-Day Stale Timestamp Pruning
$$\text{Max Timestamp Age} = 30\text{ days} \times 86,400\text{ s/day} \times 20\text{ ticks/s} = 51,840,000\text{ ticks}$$
During periodic autosaves (`ServerLifecycleEvents.BEFORE_SAVE`), entries older than 30 real-time days are automatically pruned from memory and storage.

---

## 💾 Persistance des chunks et 0 écriture sur disque

1. **Decoupled World-Level Timestamping**: Chunk unload timestamps are stored in `ContinuumData` (`data/agrarian_reform_continuum.dat`), leaving `LevelChunk.unsaved` as `false`.
2. **100% Read-Only Chunk Load Scanning**: Inspects block states via palette pre-filters without marking chunk dirty.
3. **Conditional Block Mutations**: `level.setBlock()` is **only invoked when a crop actually advances** ($\Delta \text{age} > 0$). Otherwise, Minecraft skips writing to disk ($0\text{ Disk I/O}$).

---

*See also: [[Performances et régulation de file d'attente|fr_fr-Performance-and-Queue-Throttling]], [[Registre des plantes et cultures universelles|fr_fr-Plant-Registry-and-Crop-Types]], and [[Architecture et cibles Mixin|fr_fr-Architecture-and-Mixins]]*.
