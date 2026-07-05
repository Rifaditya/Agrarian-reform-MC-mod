# 📋 Agrarian Reform Release Queue & Backlog

This file tracks which built versions (from `/Archive/builds/`) have been manually uploaded to Modrinth/CurseForge.
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
- [x] **`2.0.0+R-26.2`** (2026-06-12) - - **Platform Upgrade**: Ported the entire mod to target **Minecraft 26.2-rc-1**. - - **Dependencies**: Upgraded Fabric Loader to `0.19.1`, Fabric API to `0.150.1+26.2`, Fabric Loom to `1.15.5`, and DasikLibrary to `1.8.2`.
- [x] **`2.0.1-26.2`** (2026-07-05) - - **Dependencies**: Hardened Minecraft version dependency constraint from open wildcard `*` to `>=26.2`. - - **Versioning**: Switched to a clean, numeric-only version naming convention.
- [x] **`2.0.2-26.2`** (2026-07-05) - - **Right-Click Harvest**: Expanded harvesting support to Sugar Cane columns and modded crops.
- [ ] **`2.0.3-26.2`** (2026-07-05) - - **Universal Bone Meal**: Implement universal bone meal mechanics for Sugar Cane, Cactus, Nether Wart, Cocoa, and Vines.
