# ⚡ 性能优化与队列节流引擎

**Agrarian Reform** is engineered for high-performance servers, handling massive agricultural mega-farms without inducing lag spikes, watchdog timeouts, or tick drops.

---

## 📊 性能技术指标信息框

| Property | Value |
| :--- | :--- |
| **Global Throttling Budget** | `CROPS_PER_TICK = 5` |
| **Queue Implementation** | `java.util.concurrent.ConcurrentLinkedQueue` |
| **Sub-Chunk Palette Filter** | `hasOnlyAir()` & `maybeHas(AgrarianCropRules::isCropBlock)` |
| **Hydration Search** | Concentric Chebyshev shells ($r=1 \to \text{maxRange}$, fast-fail at nearest water) |
| **Footwear Optimization** | Bare-foot fast-fail ($0.0001\mu\text{s}$ before enchantment query) |
| **Diagnostics Logging** | Static SLF4J loggers gated behind transient `agrarian_reform:debug_mode` GameRule |

---

## 🧠 架构设计与快速跳过流水线

```
┌─────────────────────────────────────────────────────────────┐
│                    CHUNK LOAD SCAN EVENT                    │
│ Palette Pre-Filter: section.hasOnlyAir() & maybeHas()       │
│ Rejects 85%+ empty non-crop sub-chunks in O(1) time         │
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
* The server maintains a rock-solid **20 TPS** during chunk loading.
* The 10,000 crops update smoothly across 2,000 ticks (~100 seconds) in the background without affecting gameplay or causing network lag spikes.

---

## 🏎️ 核心快速跳过与算法优化

1. **Sub-Chunk Palette Gating**: Instead of scanning $98,304$ block coordinates per chunk, `CropScanner` queries sub-chunk palettes directly. If a 4,096-block sub-chunk contains no agricultural blocks, the entire section is skipped in $0.0001\mu\text{s}$.
2. **Concentric Chebyshev Hydration Sweeps**: Farmland hydration evaluates outward square shells ($r = 1 \to \text{maxRange}$). Since most farmland has water immediately adjacent ($r=1$), the check fast-fails immediately on the first shell instead of evaluating all $17 \times 17$ positions.
3. **Bare-Foot Trample Fast-Fail**: When an entity steps on farmland, `GrowthHelper.hasSoftStep()` first verifies if the feet equipment slot is empty. If empty, expensive NBT enchantment iterations are completely bypassed.
4. **Max-Age Early-Break**: During high-multiplier growth loops, ticks immediately break the moment a crop reaches maximum age, eliminating wasted block state mutations.

---

## 💾 零磁盘写入保障与脏状态维护

1. **Read-Only Chunk Surface Sweeps**: Initial chunk load scans are 100% read-only and never call `LevelChunk.setBlockState()`, keeping the chunk's internal `unsaved` flag as `false`.
2. **Selective Block Mutation**: In `ContinuumManager.processCropUpdate`, `level.setBlock()` is **only** executed when the calculated stage advancement $\Delta \text{age} > 0$.
3. **Vanilla Fast-Fail Save Protection**: Because untouched chunks remain `unsaved == false`, Minecraft's internal `ChunkMap.save()` fast-fails and skips NBT serialization, zlib compression, and `.mca` sector writes completely ($0\text{ Disk I/O}$).

---

*See also: [[时空连续体 (离线生长模拟)|zh_cn-The-Continuum-Offline-Persistence]], [[水动力学与灌溉系统|zh_cn-Hydro-Dynamics-and-Irrigation]], and [[架构设计与 Mixin 注入参考|zh_cn-Architecture-and-Mixins]]*.
