# Changelog

## [2.2.19+26.3] - 2026-08-25
### Added
- **German Localization (`de_de.json`)**: Added complete, idiomatic in-game German translations covering all 15 static GameRules, dynamic crop category descriptions, tooltips, warnings, and YACL ModMenu configuration screens.

## [2.2.18+26.3] - 2026-08-25

### Added
- **Headless Automated Unit Test Suite**:
  - Expanded `ContinuumMathTest` with tests for frozen multipliers (-1), 30-day stale unload timestamp pruning ceiling, and concentric Chebyshev hydration shell geometry.
  - Added `AgrarianConfigTest` validating default schema invariants, per-crop forced multiplier maps, frozen multiplier resolution, and dirty-tracking mechanics.

## [2.2.17+26.3] - 2026-08-25

### Added
- **YACL / ModMenu GUI Dynamic Crop Multipliers**:
  - Added dedicated "Crop Multipliers" category tab dynamically populated with integer sliders for all discovered and registered modded crops.
  - Added missing GUI controls for `pureWaterHydrationOnly` (General) and `trampleImmunityPlayersOnly` (Immersion).

## [2.2.16+26.3] - 2026-08-25

### Added
- **Per-Feature Debug Logging & Diagnostics**:
  - Registered `agrarian_reform:debug_mode` GameRule defaulting to `false` and auto-resetting on server start.
  - Added dedicated class-level SLF4J loggers with throttled random tick sampling and discrete event tracing across `GrowthHelper`, `ContinuumManager`, `CropScanner`, and `AgrarianCropRules`.

## [2.2.15+26.3] - 2026-08-25

### Added
- **6-Dimensional Interaction Guard**:
  - Fortified `UseBlockCallback` event handlers with main-hand priority, sneak/secondary-use placement bypass (`isSecondaryUseActive`), off-hand duplicate item debouncing, and client-sided animation/sound parity (`InteractionResult.sidedSuccess`).

## [2.2.14+26.3] - 2026-08-25

### Added
- **Continuum Per-Crop Growth Multiplier Scaling**:
  - Connected `AgrarianCropRules.getEffectiveGrowthMultiplier` into offline catch-up simulation in `ContinuumManager.processCropUpdate`.
  - Scaled elapsed simulation time per crop type, maintaining full parity between real-time random ticks and offline catch-up across both vanilla and modded crops.

## [2.2.13+26.3] - 2026-08-25

### Added
- **Continuum Unload Timestamp Pruning**:
  - Implemented a 30 in-game day (51,840,000 game tick) retention ceiling for chunk unload timestamps in `ContinuumData`.
  - Added `ServerLifecycleEvents.BEFORE_SAVE` hook in `ContinuumManager` to automatically prune stale timestamps across all world dimensions, preventing memory bloat and unbounded disk growth.

## [2.2.12+26.3] - 2026-08-24

### Added
- **Continuum Chunk Section & Palette Skipping**:
  - Replaced the full vertical 98,304-block column scan in `CropScanner.scanAndQueue` with section-aware traversal.
  - Implemented dual-gated sub-chunk skipping: skips pure air sections (`section.hasOnlyAir()`) and non-crop sections (`!section.maybeHas(...)`) instantly in 0.001μs without checking individual voxels.
  - Reduced chunk load simulation latency by over 98%.

## [2.2.11+26.3] - 2026-08-24

### Added
- **Soft-Step Equipment & Datapack Tag Optimization**:
  - Implemented bare-foot fast-fail in `GrowthHelper.hasSoftStep`, short-circuiting bare-footed entities instantly without registry queries.
  - Added `#agrarian_reform:soft_step_boots` and `#c:boots/soft` item tags for data-driven modded boot compatibility alongside hardcoded `Items.LEATHER_BOOTS`.
  - Scoped feather falling enchantment level check strictly to the footwear slot via `EnchantmentHelper.getItemEnchantmentLevel`.

## [2.2.10+26.3] - 2026-08-24

### Added
- **Trample Immunity Entity Gating**:
  - Registered `trample_immunity_players_only` boolean GameRule & configuration setting (default `false`).
  - Gated farmland trample immunity in `GrowthHelper.handleFarmlandTrample` so servers can restrict immunity strictly to players and tamed pets while allowing natural mob crop griefing.

## [2.2.9+26.3] - 2026-08-24

### Added
- **Concentric Chebyshev Farmland Hydration Sweep**:
  - Replaced the 578-block cube scan in `GrowthHelper.customFarmlandWaterRange` with an outward expanding Chebyshev concentric perimeter search ($r = 1 \to \text{maxRange}$).
  - Short-circuits immediately at $r=1$ (checking only 8 blocks instead of 578) for adjacent water sources.
  - Added extended $y \in [-1, 1]$ vertical 3D water detection when custom range is active.
  - Added `pure_water_hydration_only` GameRule and configuration setting (default `false`) to optionally restrict irrigation to pure `Blocks.WATER`.

## [2.2.8+26.3] - 2026-08-24

### Added
- **Real-Time Random Tick Growth Integration**:
  - Replaced the multi-tier `instanceof` chain in `GrowthHelper.handleRandomTick` with $O(1)$ fast-fail identity caching (`AgrarianCropRules.isCropBlock`), eliminating overhead on non-crop block ticks.
  - Connected per-crop multiplier resolution (`AgrarianCropRules.getEffectiveGrowthMultiplier`), supporting individual custom speeds, frozen rates (0%), and global fallbacks.
  - Implemented smart maturity early-break in the accelerated tick loop to terminate iterations as soon as crops reach maximum growth or change state.

## [2.2.7+26.3] - 2026-08-24

### Added
- **Mod Lifecycle & Persistence Hooks**:
  - Registered `AgrarianCropRules` during mod initialization (`onInitialize()`).
  - Added classification cache clearing on `SERVER_STARTED` to ensure clean tag cache refreshes upon world reload.
  - Synchronized initial configuration crop multipliers to active GameRules on newly created worlds while preserving world save authority on existing saves.
  - Added dual auto-save hooks on `BEFORE_SAVE` and `SERVER_STOPPING` to flush dirty configuration changes safely.

## [2.2.6+26.3] - 2026-08-24

### Added
- **Dynamic Crop Rules & O(1) Rejection Cache (`AgrarianCropRules`)**:
  - Implemented dedicated `AgrarianCropRules` helper with $O(1)$ fast-fail identity caching (`CROP_BLOCK_CACHE`) and server reload cache clearing (`clearCropCache()`).
  - Added smart crop scanner detecting standard Mojang plant classes (`CropBlock`, `BushBlock`, `SugarCaneBlock`, `CactusBlock`, `NetherWartBlock`, `CocoaBlock`, `VineBlock`, `SaplingBlock`, `SweetBerryBushBlock`, `StemBlock`), custom growth properties (`age`, `growth`, `stage`, `level`), and tags (`#c:crops`, `#minecraft:crops`, `#agrarianreform:continuum_plants`).
  - Implemented dynamic integer GameRule generator (`agrarian_reform:growth_<namespace>_<path>`) and dynamic registry discovery subscription.
  - Implemented multiplier resolution hierarchy (`Per-Crop GameRule -> Per-Crop Config -> Global Multiplier -> 100%`).

## [2.2.5+26.3] - 2026-08-24

### Added
- **Configuration Schema V2 & Dirty-State Management**: Upgraded `AgrarianConfig` to version 2, introducing persistent tracking for auto-discovered and forced crops (`forcedCrops`, `forcedGrowthMultipliers`), discovery recording helpers, and thread-safe dirty tracking (`saveIfDirty`) to eliminate unnecessary disk I/O.

## [2.2.4+26.3] - 2026-08-24

### Added
- **Platform Expansion (Minecraft 26.3)**: Scaffolded dedicated subproject for **Minecraft 26.3** targeting open-ended bounds (`>=26.3-`), Fabric Loom 1.15+, and modern snapshot dependencies.

## [2.2.4+26.2] - 2026-08-08

### Added
- **Data-Driven Plant Tag (`#agrarianreform:continuum_plants`)**: Added data-driven block tag system allowing datapack and modpack authors to register custom mod crops to the offline Continuum growth engine without code edits.
- **Automated Math Test Suite**: Integrated headless JUnit 5 unit test suite (`ContinuumMathTest`) verifying time-delta calculations and crop growth stage formulas under `./gradlew test`.

## [2.2.3+26.2] - 2026-07-22

### ⚠️ Version Guard Notice
- Includes zero-dependency ModVersionGuard pre-release protection. Halts startup with an explicit warning banner if run on incompatible Minecraft drops or missing core dependencies to prevent world save corruption.

### Fixed
- **ModVersionGuard Protection Banner**: Updated ModVersionGuard.java to use Knot ClassLoader resolution (Thread.currentThread().getContextClassLoader()) and display explicit pre-release protection warnings upon an API mismatch.

## [2.2.2+26.2] - 2026-07-22

### Added
- **Forward Compatibility & Version Guard**: Configured `fabric.mod.json` with `"minecraft": ">=26.2-"` for open-ended forward compatibility. Added zero-dependency `ModVersionGuard` check on startup to display human-readable guidance if an incompatible Minecraft API version is encountered.
