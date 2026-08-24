# Changelog History

## [2.2.4+26.3] - 2026-08-24
- **Platform Expansion (Minecraft 26.3)**:
  - Scaffolded dedicated subproject for **Minecraft 26.3** targeting open-ended bounds (`>=26.3-`), Fabric Loom 1.15+, and modern snapshot dependencies.
  - Configured automated release archiving to `Archive Jar of all versions/MC 26.3/`.

## [2.2.1-26.2] - 2026-07-21
- **Codebase Audit Alignment**:
  - Simplified GPLv3 license headers to single-line in all 15 Java source files to comply with repository rules.
  - Reformatted GSON JSON configs to 2-space indentation.
  - Cleaned up unused and wildcard imports.
  - Refactored `CropBlockMixin` and `FarmlandBlockMixin` to delegate all calculations to helper methods in `GrowthHelper` (complying with thin Mixin adapter policies).
  - Fixed pre-release version reference keywords to target stable `26.2`.

## [2.2.0-26.2] - 2026-07-21
- **Non-Standard Growing Plants Support**:
  - Extended the offline simulator ("The Continuum") to detect and simulate the growth of non-standard crops when chunks reload.
  - Added specialized O(1) growth simulation math and support for:
    - **Sugar Cane & Cactus**: Dynamically grows vertical columns up to the limit of 3 blocks and increments their block age state properties.
    - **Nether Wart**: Grows age stages 0 to 3.
    - **Cocoa Pods & Sweet Berry Bushes**: Grows age stages 0 to 2 and 0 to 3.
    - **Vines**: Simulates downward growth into empty blocks underneath.
    - **Saplings**: Simulates stages 0 to 1 and generates tree structures when growth cycles complete.

## [2.1.1-26.2] - 2026-07-21
- **Continuum Acceleration Synchronization**:
  - Synchronized the offline growth simulator ("The Continuum") catch-up time delta with the active `global_growth_multiplier` GameRule configuration.
  - Offline growth is now slowed down, accelerated, or disabled proportionally to active gameplay rules when chunks reload.

## [2.1.0+R-26.2] - 2026-07-15
- **Universal Growth Multiplier**:
  - Implemented the `global_growth_multiplier` GameRule (Integer, default `100`), allowing server administrators and players to globally scale the growth rate of all plants.
  - Ticks are modified proportionally without altering the actual server tick rate (TPS).
  - Broadly targets vanilla and modded crops, Sugar Cane, Cactus, Nether Wart, Cocoa, Vines, Saplings, and Sweet Berry Bushes.
- **Ticking, Recursion & Performance Optimization**:
  - Implemented a `ThreadLocal` recursion guard in the accelerated ticking loop to prevent infinite recursion and stack overflow crashes when the multiplier is set to high rates.
  - Extracted crop rustling cooldown logic into a primitive FastUtil map in `SoundHelper`, integrated with `ServerEntityEvents.ENTITY_UNLOAD` to prevent memory leaks.
  - Replaced object allocations with `BlockPos.MutableBlockPos` inside `CropScanner` coordinates loops, reducing heap allocations during chunk loading by ~30k objects.
  - Integrated namespaced dynamic GameRules registry with standard prefixing conventions.
- **Configuration Screen (YACL & ModMenu)**:
  - Added full configuration support with dynamic main-menu configuration GUI using YetAnotherConfigLib (YACL) and ModMenu.
  - Implemented client-only mixin separation to prevent crashes when executing on dedicated servers.
  - Added warning tooltips for extreme growth multipliers.

## [2.0.3-26.2] - 2026-07-05
- **Universal Bone Meal**:
  - Implemented custom Bone Meal right-click interactions for plants that are not natively bonemealable in vanilla:
    - **Sugar Cane & Cactus**: Grows the vertical column up by 1 block (up to the vanilla height limit of 3 blocks) if the space above is empty.
    - **Nether Wart**: Advances the age property by 1 stage per use (up to max age 3).
    - **Cocoa**: Advances the growth stage by 1 (up to max age 2).
    - **Vines**: Grows the vine downwards by 1 block, copying the horizontal wall attachment property state from the vine block above it.
  - Plays the green vanilla bone meal particle and sound event (`1505`) at the growth position.
  - Consumes exactly 1 Bone Meal item from the player's hand (bypassed in Creative mode) and swings the player's hand.
- **Configuration**:
  - Registered the boolean GameRule `universal_bonemeal` (default `true`) in `AgrarianGameRules.java` to toggle the feature, with full descriptions added to `en_us.json`.

## [2.0.2-26.2] - 2026-07-05
- **Right-Click Harvest**:
  - Expanded Right-Click Harvest to support Sugar Cane columns: right-clicking any block in the column harvests all blocks above the base block, leaving the bottom-most block intact so it can continue to grow.
  - Expanded crop detection to include modded plants (detecting any block inheriting from `BushBlock` that has an `"age"` property, excluding stems).
  - Enhanced the replanting algorithm to scan for items inheriting from `BlockItem` that place the harvested block, making seed consumption fully compatible with custom modded seeds.
- **Changelog Consolidation**:
  - Deleted the root-level `CHANGELOG.md` file. All change records are now centralized in `Doc/Develop/Changelogs/History.md` to conform to standard collection conventions.

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
