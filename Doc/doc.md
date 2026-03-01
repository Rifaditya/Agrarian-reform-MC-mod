# Agrarian Reform: Technical Documentation

## Overview
**Agrarian Reform** is a specialized Minecraft mod for the Fabric loader (Snapshot 26.1). It focuses on high-fidelity agricultural simulation, ensuring that environment interactions (growth, hydration, trampling) are both realistic and respectful of the player's time.

---

## 🏗️ Architecture

### 1. The Continuum (Offline Growth)
The core of the mod's simulation logic. It allows crops to "catch up" on missed random ticks while the chunk was unloaded.
- **Data Persistence**: Uses `ContinuumData` (World State) to store a `Long2LongOpenHashMap` mapping `ChunkPos` to `GameTime` (the moment of unload).
- **Calculation Engine**: Upon `CHUNK_LOAD`, the `ContinuumManager` calculates the time delta. `CropScanner` then iterates through the chunk's `SectionStorage`.
- **Growth Algorithm**:
  - Instead of expensive iterative loops, we use a single-pass O(1) mathematical simulation per crop block.
  - Calculated as: `AgeDelta = (TimeDelta / (AverageGrowthPeriod * 20))`.
  - Factors in block-specific growth speeds (hydration, neighbors) via `CropBlock.getGrowthSpeed()`.

### 2. Hydro-Dynamics (Dynamic Irrigation)
Extends and refines the vanilla irrigation system.
- **Range Logic**: Interdicts the `isNearWater` check in `FarmlandBlock`.
- **State-Dependent Radius**:
  - **Source Blocks**: 8 blocks.
  - **Flowing Water**: 4 blocks (Vanilla default).
- **Rain Integration**: Listens for rainfall events. When active, it forces a hydration state on all farmland with sky access, effectively treating the entire farm as "Wet" without local water placement.

### 3. Polyculture (Adjacency Bonus)
Uses the `BlockTags.CROPS` tag to identify neighbors.
- **Inter-Species Synergy**: If a crop is surrounded by different types of crops, it gains a +10% growth speed modifier.
- **Optimization**: This check is performed only during `getGrowthSpeed` calculation, ensuring zero impact on server TPS.

---

## 🛠️ Configuration & Toggles
All features are exposed via the native `GameRules` system, requiring no external configuration libraries.
- `totalTrampleImmunity`: When enabled, bypasses all trample checks for an "Instant Gratification" experience.
- `hydroPolycultureBoost`: Allows server admins to disable the biodiversity bonus.
- `hydroRainGrowthSpurt`: Defines how many age stages a crop can jump during a rain tick.

---

## ⚠️ Known Constraints & Maintenance
- **Snapshot 26.1 Compatibility**: The mod heavily relies on the native `GameRule<T>` registry introduced in Snapshot 10.
- **Loom/Gradle**: Requires Java 25.
- **Memory**: The `ContinuumData` map is purged on chunk load, keeping memory usage constant and preventing map bloating.

---

## 📅 Maintenance Protocol
- **Version Bumping**: Strict build-number incrementing is required for every code change.
- **Archival**: Compiled JARs must be moved to `/Archive/builds/` before overwriting.
- **Doc Sync**: Any logic change must be reflected in this document immediately.
