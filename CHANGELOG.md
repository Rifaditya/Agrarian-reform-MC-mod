# Changelog - MC 1.21.11

## [1.0.5+1.21.11] - 2026-10-30

### Fixed
- **Multi-Era Bytecode Parity Alignment**: Synchronized build cadence and bytecode standards with modern anchors, maintaining zero redundant Mixin `@Unique` annotations and pure injector signatures across all mixin classes.

---

## [1.0.4+1.21.11] - 2026-10-25

### Changed
- **UniversalConfigScreen Integration**: Migrated client configuration GUI to DasikLibrary's centralized `UniversalConfigScreen`, eliminating legacy screen boilerplate while preserving full category and option fidelity.
- **Creator & Community Support**: Integrated direct Ko-fi creator support and Discord community buttons directly into the in-game configuration categories.
- **Dynamic Singleplayer GameRule Synchronization**: Adjusting settings in singleplayer worlds now synchronizes directly with active GameRules in real time.

---

## [1.0.3+1.21.11] - 2026-10-18

### Fixed
- Fixed `FarmlandBlockMixin` client bootstrap crash (`InvalidInjectionException` / `MixinApplyError`) on Minecraft 1.21.11 by adapting the `fallOn` method descriptor from `float` to `double fallDistance`.

## [1.0.2+1.21.11] - 2026-10-13

### Fixed
- **Saturated Growth Math & Multi-Thread Resilience**: Removed the `10,000%` artificial cap on growth multipliers, hardened rain growth acceleration spurt arithmetic against integer overflow, protected 8-neighbor polyculture biodiversity bonuses against `NaN`/`Infinity` propagation, guarded Continuum chunk unload pruning against early-world timestamp underflow, and synchronized dynamic crop configuration lookups.
- **Right-Click Harvest & Interaction Hand Swing Verification**: Verified `LivingEntity.swing` interaction hand-swing calls across right-click crop harvesting, sugar cane column harvesting, seed-to-grass conversion, and universal bone meal growth.

## [1.0.1+1.21.11] - 2026-10-10

### Fixed
- **CropBlock Mixin Descriptor**: Resolved Mixin injection method descriptor mismatch on `CropBlock.entityInside` for Minecraft 1.21.11 (`InsideBlockEffectApplier` parameter compatibility).

## [1.0.0+1.21.11] - 2026-09-30

### Added
- **Agrarian Reform: The Living Earth**: Full feature port to Minecraft 1.21.11 powered by `Dasik Library`.
- **Extended Hydro-Dynamics**: Farmland hydration range extended dynamically up to 8 blocks Chebyshev radius for water sources and 4 blocks for flowing water. Supports pure water only and always-wet farmland options.
- **The Continuum (Persistent Offline Growth)**: Tracks crop ages while chunks are unloaded or during offline periods, running catch-up growth simulation upon chunk reload.
- **Advanced Soil Resilience & Trample Protection**: Configurable total trample immunity or player-only trample immunity for farmland.
- **Polyculture Biodiversity Growth Bonus**: Diverse agricultural planting clusters gain natural growth speed acceleration bonuses.
- **Universal Agricultural Discovery**: Automatically detects, registers, and exposes dynamic growth multiplier GameRules for both vanilla and modded crops.
- **Agricultural Interactions**:
  - Right-click harvest and replant for mature crops.
  - Feeding seeds to dirt creates grass blocks.
  - Universal bone meal support for sugar cane, cactus, nether wart, cocoa, and vines.
- **Visual & Audio Polish**: Gentle ambient crop rustling sounds and vitality particles around thriving agricultural fields.
- **Optional Client Configuration GUI**: Full ModMenu and YetAnotherConfigLib (YACL v3) screen integration with graceful headless fallback.
