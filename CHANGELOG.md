# Changelog

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
