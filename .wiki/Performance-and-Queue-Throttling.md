# ⚡ Performance & Queue Throttling Engine

**Agrarian Reform** is engineered for high-performance servers, handling massive agricultural farms without inducing lag spikes, watchdog timeouts, or tick drops.

---

## 📊 Performance Infobox

| Property | Value |
| :--- | :--- |
| **Global Throttling Budget** | `CROPS_PER_TICK = 5` |
| **Queue Implementation** | `java.util.concurrent.ConcurrentLinkedQueue` |
| **Time Delta Scanner** | `Heightmap.Types.WORLD_SURFACE` fast surface scan |
| **Scan Overhead** | $O(1)$ calculation per crop, $O(n)$ distributed queue application |

---

## 🧠 Architectural Design

```
┌─────────────────────────────────────────────────────────────┐
│                    CHUNK LOAD SCAN EVENT                    │
│ Scans heightmap surface for crop blocks                      │
│ Wraps coordinates & timeDelta into CropUpdateTask records   │
└──────────────────────────────┬──────────────────────────────┘
                               │
                               ▼
┌──────────────────────────────┴──────────────────────────────┐
│             ConcurrentLinkedQueue<CropUpdateTask>           │
│ Lock-free thread-safe task buffer                           │
└──────────────────────────────┬──────────────────────────────┘
                               │
                               ▼
┌──────────────────────────────┴──────────────────────────────┐
│                  ServerTickEvents.END_SERVER_TICK           │
│ Polls at most 5 tasks per tick                              │
│ Updates block states smoothly across consecutive game ticks │
└─────────────────────────────────────────────────────────────┘
```

### Why 5 Crops Per Tick?
If a player unloads a farm containing 10,000 crops for 24 hours, applying 10,000 instant block updates in a single tick would trigger a severe server freeze (1000+ ms tick latency).

By distributing updates at a lock-free **5 crops per tick**:
* The server maintains a solid **20 TPS** during chunk loading.
* The 10,000 crops update smoothly across 2,000 ticks (~100 seconds) in the background without affecting gameplay.

---

## 💾 Zero-Disk-Write Guarantees & Dirty State Preservation

Beyond tick CPU budgets, Agrarian Reform protects disk I/O bandwidth and SSD storage wear:

1. **Read-Only Chunk Surface Sweeps**:
   * The initial chunk load scan (`CropScanner.scanAndQueue`) uses `Heightmap.Types.WORLD_SURFACE` and `chunk.getBlockState(pos)`.
   * These calls are 100% read-only and never call `LevelChunk.setBlockState()`, keeping the chunk's internal `unsaved` flag as `false`.
2. **Selective Block Mutation**:
   * In `ContinuumManager.processCropUpdate`, `level.setBlock()` is **only** executed when the calculated stage advancement $\Delta \text{age} > 0$.
   * Chunks where no crops advanced (e.g. fully grown crops, empty fields, or insufficient offline time) **never execute a block update**.
3. **Vanilla Fast-Fail Save Protection**:
   * Because untouched chunks remain `unsaved == false`, Minecraft's internal `ChunkMap.save()` fast-fails and skips NBT serialization, zlib compression, and `.mca` sector writes completely.

---

*See also: [[The Continuum (Offline Growth)|The-Continuum-Offline-Persistence]] and [[Architecture & Mixins|Architecture-and-Mixins]]*.

