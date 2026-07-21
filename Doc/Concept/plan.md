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

### 2. 🌾 Non-Standard Growing Plants Support
Extend the offline simulator beyond standard vanilla `CropBlock` types.
- [x] Update `CropScanner` to detect and record state for:
  - **Sugar Cane & Cactus**: Track column height and age properties.
  - **Nether Wart & Cocoa**: Track age/growth stages.
  - **Vines & Sweet Berry Bushes**: Track vertical growth and berries.
  - **Saplings**: Track stage and trigger tree generation.
- [x] Implement simulation calculations for each plant type upon reload (e.g. adding columns for Sugar Cane, advancing age caps for Nether Wart).
- **Files**: `net.instantgratification.agrarianreform.continuum.CropScanner`, `ContinuumManager`

---

### 3. 🪱 Compost & Fertilizer Persistence
Integrate soil enrichment values with offline simulation.
- [ ] Record soil nutrient/fertilizer values in the NBT data of the chunk at unload.
- [ ] Consume fertilizer charges over the offline period to dynamically speed up the simulated growth ticks.
