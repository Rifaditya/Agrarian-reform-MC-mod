# 📋 Agrarian Reform Release Queue & Backlog

This file tracks which built versions (from the central archive folder "E:\Minecraft Project\Vanilla Outsider Collections\Agrarian Reform\Archive Jar of all versions") have been manually uploaded to Modrinth/CurseForge.
Open this file in your editor and change `[ ]` to `[x]` when you publish a version.

## 🚀 Published & Backlog Queue

- [x] **`1.0.0+build.1`** (2026-03-01) - - Initial release (Bootstrap version).
- [x] **`1.0.0+build.2`** (2026-03-01) - - Major API migration to Minecraft 26.1 Snapshot 10. - - Rewrote GameRules to use native Vanilla registration.
- [x] **`1.0.0+build.3`** (2026-03-01) - - Integrated "The Farm Plot" (Instant Gratification) features. - - New GameRule: `totalTrampleImmunity` (Toggleable).
- [x] **`1.0.0+build.10`** (2026-03-01) - - **Rendering API**: Fully migrated to Snapshot 26.1 `BlockColorRegistry` and `BlockAndTintGetter`. - - **Time API**: Updated morning dew effect to use `Level.getOverworldClockTime()`.
- [x] **`1.0.0+build.11`** (2026-03-01) - - **FIX**: Pivoted GameRule registration to **DasikLibrary API**. - - **FIX**: Added namespaced IDs (`agrarian-reform:`) to all GameRules for Snapshot 26.1 UI visibility.
- [x] **`1.0.0+build.12`** (2026-03-01) - - **UX**: Implemented detailed, premium tooltips for all GameRules in the UI. - - **FIX**: Added missing translation for the custom "Agrarian Reform" GameRule category.
- [x] **`1.0.0+build.13`** (2026-03-01) - - Corrected GameRule translation keys (switched from colon to dot notation). - - Added `.description` suffix to all GameRule tooltips for Snapshot 10 compatibility.
- [x] **`1.0.0+build.14`** (2026-03-02) - - **Localization**: Implemented "Nuclear Lang Fix" with dual dot/colon translation variants. - - **Assets**: Standardised asset namespace to `agrarian-reform` (fixed folder name mismatch).
- [x] **`1.0.0+build.15`** (2026-03-02) - - **Visuals**: Implemented "Visual Refinement" for GameRule UI. - - **Visuals**: Hardcoded `Agrarian Reform: ` prefix in all translations for a premium, namespaced look.
- [x] **`1.0.0+build.16`** (2026-03-02) - - **Visuals**: Shortened GameRule prefixes from "Agrarian Reform:" to "AR:" for better UI fit and scannability.
- [x] **`1.1.0+build.17`** (2026-03-02) - - **Continuum**: Full vertical scanning support. Crops in underground farms and multi-story greenhouses now simulate offline growth correctly. - - **Continuum**: Integrated Polyculture/Biodiversity growth bonuses into offline simulation math.
- [x] **`1.1.0+build.18`** (2026-03-03) - - **Continuum**: Fixed "Not a string" serialization crash when saving world data by correctly stringifying map keys.
- [x] **`1.2.0+build.1`** (2026-04-13) - - **Hydro-Dynamics**: Implemented the `always_wet_farmland` GameRule to force farmland hydration regardless of water proximity. - - **API**: Migrated to Minecraft version `26.1.2` and Fabric Loader `0.18.4`.
- [x] **`1.2.0+build.2`** (2026-04-17) - - **CRITICAL**: Moved `BlockColorsMixin` to client-only mixin array — previously in shared array, causing `ClassNotFoundException` crash on dedicated servers. - - **Trample Logic**: Removed `agrarianreform$forceTrample` HEAD inject which bypassed vanilla's `mob_griefing` GameRule and entity size checks, incorrectly allowing small mobs to trample farmland.
- [x] **`1.2.0+build.3`** (2026-06-12) - - **Localization**: Added missing `always_wet_farmland` GameRule translation key and description in `en_us.json`. - - **Logic**: Fixed rustle sound cooldown being shared across ALL blocks of the same crop type (singleton bug). Cooldown is now tracked per-entity via static map.
- [x] **`1.2.4+A-26.1.2`** (2026-06-12) - - **Versioning**: Migrated from legacy `Major.Minor.Patch+build.N` to standard `Major.Minor.Patch+Stage-MC_Version` format.
- [x] **`1.2.5+A-26.1.2`** (2026-06-12) - - **Soil Restoration**: Added capability to grow grass on Dirt blocks by right-clicking them with seed items (supports wheat, melon, pumpkin, beetroot, etc. via `#minecraft:chicken_food` tag). - - **Configuration**: Added `seeds_grow_grass` GameRule (Boolean, default `true`) to toggle the seed-to-grass growth feature.
- [x] **`1.2.6+A-26.1.2`** (2026-06-12) - - **Right-Click Harvest**: Added capability to right-click fully grown crops to harvest and automatically replant them. - - **Modded Crops Compatibility**: Leveraged dynamic block state properties to support all vanilla and modded crops (e.g. Farmer's Delight, Croptopia).
- [x] **`2.0.0+R-26.2`** (2026-06-12) - - **Platform Upgrade**: Ported the entire mod to target **Minecraft 26.2**. - - **Dependencies**: Upgraded Fabric Loader to `0.19.1`, Fabric API to `0.150.1+26.2`, Fabric Loom to `1.15.5`, and DasikLibrary to `1.8.2`.
- [x] **`2.0.1-26.2`** (2026-07-05) - - **Dependencies**: Hardened Minecraft version dependency constraint from open wildcard `*` to `>=26.2`. - - **Versioning**: Switched to a clean, numeric-only version naming convention.
- [x] **`2.0.2-26.2`** (2026-07-05) - - **Right-Click Harvest**: Expanded harvesting support to Sugar Cane columns and modded crops.
- [x] **`2.0.3-26.2`** (2026-07-05) - - **Universal Bone Meal**: Implement universal bone meal mechanics for Sugar Cane, Cactus, Nether Wart, Cocoa, and Vines.
- [x] **`2.1.0+R-26.2`** (2026-07-15) - - Universal Growth: Implement a global growth speed multiplier setting (GameRule) for all growing plants. - - Audit Alignment: Fixed infinite recursion, memory leaks, and GC allocations. - - GUI Config: Added YetAnotherConfigLib (YACL) and ModMenu configurations.
- [x] **`2.1.1-26.2`** (2026-07-21) - - Continuum: Synchronize offline simulator catch-up calculations with global growth multiplier settings.
- [x] **`2.2.0-26.2`** (2026-07-21) - - Continuum: Add Non-Standard Growing Plants Support to simulate Sugar Cane, Cactus, Nether Wart, Cocoa, Vines, Sweet Berry Bushes, and Saplings.
- [x] **`2.2.1-26.2`** (2026-07-21) - - Maintenance: Performed codebase audit alignment, simplified GPLv3 headers, reformatted JSON files, and extracted calculations from Mixins to helper classes.
- [x] **`2.2.2+26.2`** (2026-07-22) - - Forward Compatibility: Added ModVersionGuard and open-ended `minecraft` dependency constraint (`>=26.2-`).
- [x] **`2.2.3+26.2`** (2026-07-22) - - Fix: ModVersionGuard context classloader resolution for Knot loader stability.
- [x] **`2.2.4+26.2`** (2026-08-08) - - **Data-Driven Tags**: Added `#agrarianreform:continuum_plants` block tag. - - **Automated Testing**: Integrated headless JUnit 5 math test suite.
- [x] **`2.2.5+26.2`** (2026-08-24) - - **Configuration Schema V2**: Upgraded AgrarianConfig with dynamic crop tracking schema (`forcedCrops`, `forcedGrowthMultipliers`), auto-population helpers, and thread-safe dirty state management (`saveIfDirty`).
- [ ] **`2.2.6+26.2`** (2026-08-24) - - **Dynamic Crop Rules & O(1) Cache**: Created dedicated `AgrarianCropRules` with $O(1)$ fast identity cache, universal 3-tier dynamic registry scanner, and dynamic `agrarian_reform:growth_<namespace>_<path>` GameRule generation.
- [ ] **`2.2.7+26.2`** (2026-08-24) - - **Lifecycle Integration & Persistence**: Registered `AgrarianCropRules` in `onInitialize()`, hooked cache flush on `SERVER_STARTED`, and integrated `BEFORE_SAVE` and `SERVER_STOPPING` auto-save hooks (`saveIfDirty`).
- [ ] **`2.2.8+26.2`** (2026-08-24) - - **Real-Time Ticking Integration**: Connected `AgrarianCropRules` to random tick handler with $O(1)$ fast-fail rejection, per-crop multiplier scaling, and smart maturity early-break.
- [ ] **`2.2.9+26.2`** (2026-08-24) - - **Concentric Farmland Hydration Sweep**: Refactored `customFarmlandWaterRange` to concentric Chebyshev perimeter search with $y \in [-1, 1]$ 3D support, zero allocations, and optional `pure_water_hydration_only` GameRule.
- [ ] **`2.2.10+26.2`** (2026-08-24) - - **Trample Immunity Entity Gating**: Added `trample_immunity_players_only` GameRule & config setting, restricting farmland trample immunity strictly to players and tamed pets.
- [ ] **`2.2.11+26.2`** (2026-08-24) - - **Soft-Step Equipment & Tag Optimization**: Optimized `hasSoftStep` with bare-foot fast-fail, `#agrarian_reform:soft_step_boots` and `#c:boots/soft` data tags, and boots-specific enchantment checks.
- [ ] **`2.2.12+26.2`** (2026-08-24) - - **Continuum Sub-Chunk & Palette Skipping**: Refactored `CropScanner.scanAndQueue` to skip pure-air and non-crop sub-chunks via palette pre-filter (`maybeHas`), eliminating 98%+ of block queries on chunk load.
- [ ] **`2.2.13+26.2`** (2026-08-25) - - **Continuum Timestamp Pruning**: Added 30-day (51.8M tick) stale chunk timestamp ceiling in `ContinuumData` and hooked save-time auto-pruning via `BEFORE_SAVE`.
- [ ] **`2.2.14+26.2`** (2026-08-25) - - **Continuum Crop Scaling**: Scaled offline growth simulation delta per-crop using `AgrarianCropRules.getEffectiveGrowthMultiplier` uniformly across all plant types.
- [ ] **`2.2.15+26.2`** (2026-08-25) - - **6D Interaction Guard**: Fortified `UseBlockCallback` handlers with main-hand harvest restrictions, sneak/secondary-use bypass, off-hand debounce, and client-sided success parity.
- [ ] **`2.2.16+26.2`** (2026-08-25) - - **Per-Feature Debug Logging & Diagnostics**: Added namespaced transient `agrarian_reform:debug_mode` GameRule and class-level SLF4J loggers with throttled random tick sampling and discrete event tracing.
- [ ] **`2.2.17+26.2`** (2026-08-25) - - **YACL / ModMenu GUI Configuration**: Added dedicated "Crop Multipliers" category tab with dynamic options per discovered crop and added missing GameRule UI controls (`pureWaterHydrationOnly`, `trampleImmunityPlayersOnly`).
- [ ] **`2.2.18+26.2`** (2026-08-25) - - **Headless Automated Unit Test Suite**: Added comprehensive headless test suites in `ContinuumMathTest` and `AgrarianConfigTest` validating multiplier hierarchy, frozen math, stale timestamp pruning, Chebyshev ring geometry, and dirty tracking.
- [ ] **`2.2.19+26.2`** (2026-08-25) - - **German Localization**: Added complete, idiomatic German (`de_de.json`) translation for all 15 GameRules, tooltips, warnings, and YACL ModMenu configuration screens.

