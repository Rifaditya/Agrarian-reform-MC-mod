# Changelog History

## [2.1.0-26.2] - 2026-07-05
- **Universal Growth Multiplier**: Added `global_growth_multiplier` GameRule (Integer, default `100`) to scale growth speeds of all vanilla and modded plants.
- **Ticking Optimization**: Structured ticking logic inside a dedicated `GrowthHelper` class to satisfy Mixin isolation guidelines.

## [2.0.3-26.2] - 2026-07-05
- **Universal Bone Meal**: Added support for using bone meal on non-bonemealable blocks (Sugar Cane, Cactus, Nether Wart, Cocoa, and Vines).
- **Configuration**: Added `universal_bonemeal` GameRule (Boolean, default `true`) to toggle this feature.

## [2.0.2-26.2] - 2026-07-05
- **Right-Click Harvest**: Expanded harvesting support to Sugar Cane columns and modded crops.
- **Changelog Consolidation**: Deleted root-level `CHANGELOG.md` file to centralize version tracking in `History.md`.

## [2.0.1-26.2] - 2026-07-05
- **Dependencies**: Hardened Minecraft version dependency constraint from open wildcard `*` to `>=26.2`.
- **Versioning**: Switched to a clean, numeric-only version naming convention (removing stage-specific alphabetical markers).

## [2.0.0+R-26.2] - 2026-06-12
- **Platform Upgrade**: Ported the entire mod to target **Minecraft 26.2-rc-1**.
- **Dependencies**: Upgraded Fabric Loader to `0.19.1`, Fabric API to `0.150.1+26.2`, Fabric Loom to `1.15.5`, and DasikLibrary to `1.8.2`.
- **Versioning**: Major version bump to reflect the platform migration.

## [1.2.6+A-26.1.2] - 2026-06-12
- **Right-Click Harvest**: Added capability to right-click fully grown crops to harvest and automatically replant them.
- **Modded Crops Compatibility**: Leveraged dynamic block state properties to support all vanilla and modded crops (e.g. Farmer's Delight, Croptopia).
- **Configuration**: Added `right_click_harvest` GameRule (Boolean, default `true`) to toggle the right-click harvest feature.

## [1.2.5+A-26.1.2] - 2026-06-12
- **Soil Restoration**: Added capability to grow grass on Dirt blocks by right-clicking them with seed items (supports wheat, melon, pumpkin, beetroot, etc. via `#minecraft:chicken_food` tag).
- **Configuration**: Added `seeds_grow_grass` GameRule (Boolean, default `true`) to toggle the seed-to-grass growth feature.

## [1.2.4+A-26.1.2] - 2026-06-12
- **Versioning**: Migrated from legacy `Major.Minor.Patch+build.N` to standard `Major.Minor.Patch+Stage-MC_Version` format.

## [1.2.0+build.3] - 2026-06-12
- **Localization**: Added missing `always_wet_farmland` GameRule translation key and description in `en_us.json`.
- **Logic**: Fixed rustle sound cooldown being shared across ALL blocks of the same crop type (singleton bug). Cooldown is now tracked per-entity via static map.
- **Cleanup**: Removed 5 unused imports from `AgrarianReformClient.java`.
- **Dependencies**: Upgraded Fabric API from `0.142.1+26.1` to `0.145.4+26.1.2` (fixes `GlobalAttachmentsProvider` missing class build failure).
- **Infrastructure**: Replaced hardcoded Fabric API version in `build.gradle` with `gradle.properties` variable reference.
- **Infrastructure**: Enabled `org.gradle.java.home` in `gradle.properties` pointing to JDK 25.0.3.
- **Documentation**: Corrected DasikLibrary version in README (`build.15` → `build.24`).

## [1.2.0+build.2] - 2026-04-17
- **Critical Fix**: Resolved server crash by moving `BlockColorsMixin` to client-only array.
- **Logic Fix**: Corrected trample criteria to respect vanilla mob_griefing and entity size.
- **Stability**: Migrated throttled queue to `ConcurrentLinkedQueue` for thread safety.

## [1.2.0+build.1] - 2026-04-13
- **Hydro-Dynamics**: Added `always_wet_farmland` GameRule for forced hydration.
- **Technical**: Migrated to Snapshot 26.1.2 (Snapshot 11 API).
- **Dependencies**: Updated to DasikLibrary `1.6.9+build.15`.

## [1.1.0+build.18] - 2026-03-03
- **Continuum**: Fixed serialization crash by stringifying map keys.

## [1.1.0+build.17] - 2026-03-02
- **Continuum**: Full vertical scanning for underground farms.
- **Optimization**: Fast-fail path for vanilla range hydration.

## [1.0.0+build.16] - 2026-03-02
- **Visuals**: Shortened GameRule prefixes from "Agrarian Reform:" to "AR:" for better UI fit and scannability.

## [1.0.0+build.15] - 2026-03-02
- **Visual Refinement**: Hardcoded "Agrarian Reform: " prefix in translations to match "Aggressive" mod branding.
- **Namespace Change**: Migrated Mod ID and assets to `agrarian_reform` (underscore).
- **Simplified IDs**: Shortened GameRule IDs for better legibility and maintenance.

## [1.0.0+build.14] - 2026-03-02
- **Nuclear Lang Fix**: Implemented dual-format translation keys (dot/colon) for 100% UI visibility.
- **Asset Standardisation**: Migrated assets folder to `agrarian-reform` namespace.
- **Bugfix**: Resolved "missing lang" labels in Edit Game Rules screen.

## [1.0.0+build.13] - 2026-03-01
- Fixed GameRule translation key separators (switched to dot notation).
- Added `.description` suffix to all tooltips for Snapshot 10 / Fabric compatibility.

## [1.0.0+build.11] - 2026-03-01
- **API Pivot**: Migrated GameRule registration to **DasikLibrary DynamicGameRuleManager**.
- Implemented namespaced IDs (`agrarian-reform:`) for all rules.

## [1.0.0+build.10] - 2026-03-01
- Migrated to Snapshot 26.1 `BlockColorRegistry` and `BlockAndTintGetter`.
- Updated Time API usage for `getOverworldClockTime()`.

## [1.0.0+build.3] - 2026-03-01
- Integrated "The Farm Plot" features.
- Added `totalTrampleImmunity` and `hydroPolycultureBoost` GameRules.

## [1.0.0+build.2] - 2026-03-01
- Migrated to Minecraft 26.1 Snapshot 10.
- Implemented `The Continuum` mathematical growth simulation.
- Redesigned GameRules using native snapshot API.

## [1.0.0+build.1] - 2026-03-01
- Initial release.
- The Living Earth conceptual framework.
