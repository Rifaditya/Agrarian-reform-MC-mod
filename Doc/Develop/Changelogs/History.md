# Changelog History

## [1.2.7+A-26.1.2] - 2026-06-12
- **Backport**: Backported all Phase 5 capabilities to Minecraft 26.1.2.
- **Right-Click Harvest**: Fully grown crops can be right-clicked to harvest and automatically replanted. Supports all vanilla and modded crops. Gated by `right_click_harvest` GameRule (Boolean, default `true`).
- **Soil Restoration**: Right-clicking dirt blocks with seeds converts them to grass. Gated by `seeds_grow_grass` GameRule (Boolean, default `true`).
- **Dependencies**: Updated DasikLibrary dependency to `1.6.9+build.24` for full API capability.

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
