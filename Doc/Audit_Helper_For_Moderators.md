# Moderator Audit Helper: Agrarian Reform

**Mod Name:** Vanilla Outsider: Agrarian Reform
**Mod ID:** `agrarian_reform` (Fabric)
**Version:** 2.2.0
**Creator:** Dasik (Rifaditya)

## 🛡️ Safety & Compliance Statement

To assist Platform Moderators (Modrinth/CurseForge) in auditing this project, I certify the following:

1. **No External Network Connections**: This mod runs entirely offline within the Minecraft game loop. It does **NOT** make any HTTP/Web requests or analytics connections.
2. **No Data Collection**: No telemetry, analytics, or user-data tracking.
3. **No Binary Execution**: No OS-level commands or external binary execution.
4. **Filesystem Hygiene**: Only writes to standard level directory via standard Minecraft SavedData api (storing unload chunk timestamps under `data/agrarian_reform_continuum.dat` using official NBT codecs) and registered GameRules.

## 📂 Codebase Overview for Reviewers

| Feature | Source File | Description |
| :--- | :--- | :--- |
| **Data Persistence** | `continuum.ContinuumData` | Stores chunk unload game-time timestamps mapped to ChunkPos. |
| **Offline Simulation** | `continuum.ContinuumManager` | Calculates time delta during chunk load events and approximates random ticks. |
| **Block Updates** | `continuum.CropScanner` | Scans crop blocks inside the loaded chunk and queues growth updates. |
| **Growth Mixin** | `mixin.CropBlockMixin` | Intercepts random tick growth speed logic to apply polyculture adjacency bonuses. |
| **Farmland Mixin** | `mixin.FarmlandBlockMixin` | Injects custom water irrigation range checks and weather/rain hydration logic. |
| **Registry** | `AgrarianGameRules` | Registers all 10 agricultural custom GameRules via native Minecraft systems. |

## 🔍 Advanced Logic & Safety

- **O(1) Growth Simulation**: Instead of ticking block updates iteratively (which would cause massive lag spikes when loading chunks unloaded for days), the mod calculates a deterministic growth delta using the chunk's unloaded time delta (scaled dynamically by the global growth multiplier) and crop-specific baseline speed.
- **Throttled Update Queue**: Block state updates are queued and applied slowly (e.g. 5 blocks per tick) to distribute the processing load across multiple game frames, keeping client rendering and server TPS perfectly smooth.
- **Memory Management**: Timestamps are purged from `ContinuumData` immediately upon chunk load to prevent memory leaks and coordinate map bloating.

## 🛠️ Build & Dependencies

- **Loader**: Fabric Loader (>=0.19.1)
- **Toolchain**: JDK 25, Gradle 9.3+
- **External Libs**: `dasik-library` (Required, >= 1.8.2). Optionally supports `cloth-config` and `modmenu` for configuration GUI.
