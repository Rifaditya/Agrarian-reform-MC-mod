# Changelog - Agrarian Reform

## [2.0.0+A-26.2] - 2026-06-12
### Changed
- **Platform Upgrade**: Ported the entire mod to target **Minecraft 26.2-rc-1**.
- **Dependencies**: Upgraded Fabric Loader to `0.19.1`, Fabric API to `0.150.1+26.2`, Fabric Loom to `1.15.5`, and DasikLibrary to `1.8.2`.
- **Versioning**: Major version bump to reflect the platform migration.

## [1.2.6+A-26.1.2] - 2026-06-12
### Added
- **Right-Click Harvest**: Added capability to right-click fully grown crops to harvest and automatically replant them.
- **Modded Crops Compatibility**: Leveraged dynamic block state properties to support all vanilla and modded crops (e.g. Farmer's Delight, Croptopia).
- **Configuration**: Added `right_click_harvest` GameRule (Boolean, default `true`) to toggle the right-click harvest feature.

## [1.2.5+A-26.1.2] - 2026-06-12
### Added
- **Soil Restoration**: Added capability to grow grass on Dirt blocks by right-clicking them with seed items (supports wheat, melon, pumpkin, beetroot, etc. via `#minecraft:chicken_food` tag).
- **Configuration**: Added `seeds_grow_grass` GameRule (Boolean, default `true`) to toggle the seed-to-grass growth feature.

## [1.2.4+A-26.1.2] - 2026-06-12
### Changed
- **Versioning**: Migrated from legacy `Major.Minor.Patch+build.N` to standard `Major.Minor.Patch+Stage-MC_Version` format.

## [1.2.0+build.3] - 2026-06-12
### Fixed
- **Localization**: Added missing `always_wet_farmland` GameRule translation key and description in `en_us.json`.
- **Logic**: Fixed rustle sound cooldown being shared across ALL blocks of the same crop type (singleton bug). Cooldown is now tracked per-entity via static map.
- **Cleanup**: Removed 5 unused imports from `AgrarianReformClient.java`.

### Changed
- **Dependencies**: Upgraded Fabric API from `0.142.1+26.1` to `0.145.4+26.1.2` (fixes `GlobalAttachmentsProvider` missing class build failure).
- **Infrastructure**: Replaced hardcoded Fabric API version in `build.gradle` with `gradle.properties` variable reference.
- **Infrastructure**: Enabled `org.gradle.java.home` in `gradle.properties` pointing to JDK 25.0.3.
- **Documentation**: Corrected DasikLibrary version in README (`build.15` → `build.24`).

## [1.2.0+build.2] - 2026-04-17
### Fixed
- **CRITICAL**: Moved `BlockColorsMixin` to client-only mixin array — previously in shared array, causing `ClassNotFoundException` crash on dedicated servers.
- **Trample Logic**: Removed `agrarianreform$forceTrample` HEAD inject which bypassed vanilla's `mob_griefing` GameRule and entity size checks, incorrectly allowing small mobs to trample farmland.
- **Trample Logic**: Cleaned dead comment block inside `agrarianreform$wrapTurnToDirt` — WrapOperation is now the sole soft-step protection mechanism, correctly cooperating with vanilla's trample criteria.
- **Thread Safety**: `ContinuumManager.UPDATE_QUEUE` migrated from `ArrayDeque` (not thread-safe) to `ConcurrentLinkedQueue`.
- **Cleanup**: Removed unused `AgrarianReformFabricClient.java` stub (not referenced by `fabric.mod.json`).
- **Cleanup**: Removed unused `CompoundTag` and `HolderLookup` imports from `ContinuumData.java`.

## [1.2.0+build.1] - 2026-04-13
### Added
- **Hydro-Dynamics**: Implemented the `always_wet_farmland` GameRule to force farmland hydration regardless of water proximity.

### Changed
- **API**: Migrated to Minecraft version `26.1.2` and Fabric Loader `0.18.4`.
- **Dependencies**: Updated to DasikLibrary `1.6.9+build.15`.

## [1.1.0+build.18] - 2026-03-03
### Fixed
- **Continuum**: Fixed "Not a string" serialization crash when saving world data by correctly stringifying map keys.

## [1.1.0+build.17] - 2026-03-02
### Added
- **Continuum**: Full vertical scanning support. Crops in underground farms and multi-story greenhouses now simulate offline growth correctly.
- **Continuum**: Integrated Polyculture/Biodiversity growth bonuses into offline simulation math.

### Changed
- **Optimization**: Optimized Farmland hydration checks with a fast-fail path for vanilla ranges, improving server TPS on large farms.
- **Technical**: Renamed internal GameRule variables (removed `HYDRO_` prefix) for better code maintainability.

### Fixed
- **Logic**: Fixed Rain Growth Spurt bug where vanilla random ticks could overwrite modded growth boosts.
- **Localization**: Updated GameRule descriptions in `en_us.json` to accurately reflect data types (Int vs Boolean).


## [1.0.0+build.16] - 2026-03-02
### Fixed
- **Visuals**: Shortened GameRule prefixes from "Agrarian Reform:" to "AR:" for better UI fit and scannability.

## [1.0.0+build.15] - 2026-03-02
### Fixed
- **Visuals**: Implemented "Visual Refinement" for GameRule UI.
- **Visuals**: Hardcoded `Agrarian Reform: ` prefix in all translations for a premium, namespaced look.
- **Technical**: Renamed Mod ID to `agrarian_reform` (underscore) for cleaner technical fallbacks and standardisation.
- **Technical**: Simplified internal GameRule IDs (removed redundant `hydro_` prefix).
- **Technical**: Renamed mixin config to `agrarian_reform.mixins.json` for consistency.

## [1.0.0+build.14] - 2026-03-02
### Fixed
- **Localization**: Implemented "Nuclear Lang Fix" with dual dot/colon translation variants.
- **Assets**: Standardised asset namespace to `agrarian-reform` (fixed folder name mismatch).
- **Metadata**: Updated `fabric.mod.json` icon path to match the new namespace.

## [1.0.0+build.13] - 2026-03-01
### Fixed
- Corrected GameRule translation keys (switched from colon to dot notation).
- Added `.description` suffix to all GameRule tooltips for Snapshot 10 compatibility.
- Verified custom category key format: `gamerule.category.agrarian-reform.agrarian_reform`.

## [1.0.0+build.12] - 2026-03-01
- **UX**: Implemented detailed, premium tooltips for all GameRules in the UI.
- **FIX**: Added missing translation for the custom "Agrarian Reform" GameRule category.
- **FIX**: Corrected namespaced translation keys for 100% UI visibility in Snapshot 26.1.

## [1.0.0+build.11] - 2026-03-01
- **FIX**: Pivoted GameRule registration to **DasikLibrary API**.
- **FIX**: Added namespaced IDs (`agrarian-reform:`) to all GameRules for Snapshot 26.1 UI visibility.
- **NEW**: Custom "Agrarian Reform" category in the Edit Game Rules menu.
- **REF**: Cleaned up native Registry calls in Mixins for safe retrieval.

## [1.0.0+build.10] - 2026-03-01

### Fixed
- **Rendering API**: Fully migrated to Snapshot 26.1 `BlockColorRegistry` and `BlockAndTintGetter`.
- **Time API**: Updated morning dew effect to use `Level.getOverworldClockTime()`.
- **Data Persistence**: Fixed `SavedDataType` type mismatch in `ContinuumData`.
- **Infrastructure**: Optimized build configuration with Yarn mappings and unified sources for Snapshot compatibility.

## [1.0.0+build.3] - 2026-03-01

### Added
- Integrated "The Farm Plot" (Instant Gratification) features.
- New GameRule: `totalTrampleImmunity` (Toggleable).
- New GameRule: `hydroPolycultureBoost` (Toggleable).

## [1.0.0+build.2] - 2026-03-01

### Fixed
- Major API migration to Minecraft 26.1 Snapshot 10.
- Rewrote GameRules to use native Vanilla registration.
- Fixed `ContinuumManager` chunk load lambda signature.
- Corrected `CropBlock` and `FarmlandBlock` Mixins for Snapshot 10 compatibility.
- Fixed `GRASS_HIT` sound event naming.

### Added
- Functional `The Continuum` offline simulation.
- Polyculture growth bonuses.

## [1.0.0+build.1] - 2026-03-01

### Added
- Initial release (Bootstrap version).
