# Continuum Improvements Design Plan

This document tracks planned improvements for **The Continuum** (unloaded chunk growth simulator) to enhance parity, mod compatibility, and detail simulation accuracy.

---

## 📋 Feature Checklist

### 1. ⚙️ Global Growth Multiplier Synchronization
Sync the offline simulator with the custom speed settings defined by players.
- [x] Retrieve `global_growth_multiplier` GameRule value during `CHUNK_LOAD` processing inside `ContinuumManager`.
- [x] Scale the calculated `timeDelta` (or scale `averageTicksPerStage`) by the multiplier value:
  - If `0`: Growth is entirely halted (no catching up).
  - If `1-99`: Slows down catch-up rate proportionally.
  - If `>100`: Speeds up catch-up rate proportionally (e.g. at 200, simulates double elapsed time).
- **Files**: `net.instantgratification.agrarianreform.continuum.ContinuumManager`

---

### 2. 🪱 Compost & Fertilizer Persistence
Integrate soil enrichment values with offline simulation.
- [ ] Record soil nutrient/fertilizer values in the NBT data of the chunk at unload.
- [ ] Consume fertilizer charges over the offline period to dynamically speed up the simulated growth ticks.
