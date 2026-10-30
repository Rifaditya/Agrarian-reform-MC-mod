# Architecture & Symbol Index: Agrarian Reform (MC 1.21.11)

## 1. Mod Metadata & Entrypoints
- **Mod ID**: `agrarian_reform`
- **Target Minecraft**: `1.21.11` (Java 21, Mojang Official Mappings)
- **Main Entrypoint**: `net.instantgratification.agrarianreform.AgrarianReformFabric` (`ModInitializer`)
- **Client Entrypoint**: `net.instantgratification.agrarianreform.client.AgrarianReformClient` (`ClientModInitializer`)
- **ModMenu Entrypoint**: `net.instantgratification.agrarianreform.client.ModMenuIntegration` (`ModMenuApi`)

## 2. Bytecode Mixin Target Registry
| Target Vanilla Class | Mixin Class | Side | Purpose |
| :--- | :--- | :--- | :--- |
| `net.minecraft.world.level.block.FarmBlock` | `FarmlandBlockMixin` | Common | Concentric Chebyshev hydration range & trample immunity |
| `net.minecraft.world.level.block.CropBlock` | `CropBlockMixin` | Common | Soft-step collision slowing & polyculture biodiversity bonus |
| `net.minecraft.world.level.block.state.BlockBehaviour$BlockStateBase` | `BlockStateBaseMixin` | Common | Real-time random tick acceleration & rain growth spurt hook |
| `net.minecraft.client.color.block.BlockColors` | `client.BlockColorsMixin` | Client | Wet farmland visual soil color tinting |

## 3. Core Mechanics & Subsystems
- **Growth & Hydration Engine**: `growth.GrowthHelper` — Concentric Chebyshev water search, saturated rain spurt math, polyculture biodiversity scanning, and soft-step boot detection.
- **Dynamic Crop Discovery**: `config.AgrarianCropRules` — O(1) crop identity cache, registry scanning, and dynamic per-crop GameRule registration (`agrarian_reform:growth_<ns>_<path>`).
- **The Continuum (Offline Catch-Up)**: `continuum.ContinuumManager`, `ContinuumData`, `CropScanner` — SavedData chunk unload timestamps with 30-day pruning and section-palette-aware catch-up simulation.
- **6D Interaction Guard**: `interaction.HarvestInteractionHelper` — Right-click harvest/replant, sugar cane column harvesting, seed-to-grass conversion, and universal bone meal support.

## 4. Dynamic GameRules & Configuration
- **GameRules**: `config.AgrarianGameRules` — 16 static rules (`agrarian_reform:*`) + dynamic crop rules via `DasikLibrary` `DynamicGameRuleManager`.
- **Configuration**: `config.AgrarianConfig` — Thread-safe V2 JSON schema (`config/agrarian_reform.json`) with dirty-state persistence.

## 5. Sidedness Isolation & Verification
- **Sidedness**: Zero `net.minecraft.client.*` imports in common packages; `@Environment(EnvType.CLIENT)` enforced on `AgrarianReformClient`, `ModMenuIntegration`, `YaclScreenHelper`, and `BlockColorsMixin`.
- **5-Pillar Test Suite**: 9 JUnit 5 test classes (`GrowthHelperTest`, `HarvestInteractionTest`, `ContinuumMathTest`, `AgrarianConfigTest`, `AgrarianReformStressFuzzTest`, `AgrarianReformConcurrencyLoadTest`, `AgrarianReformCommandTest`, `AgrarianReformGameTest`, `AgrarianReformSidednessContractTest`).
