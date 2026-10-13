# Architecture & Symbol Index: Agrarian Reform (MC 26.1.2)

- **Target Environment**: `MC 26.1.2 | Java 25 | Fabric Loom 1.15-SNAPSHOT (Official Mojang Mappings)`

## 1. Mod Metadata & Entrypoint
- **Mod ID**: `agrarian_reform`
- **Main Entrypoint**: `net.instantgratification.agrarianreform.AgrarianReformFabric` (`net.fabricmc.api.ModInitializer`)
- **Client Entrypoint**: `net.instantgratification.agrarianreform.client.AgrarianReformClient`
- **ModMenu Entrypoint**: `net.instantgratification.agrarianreform.config.ModMenuIntegration`

## 2. Bytecode Mixin Target Registry
| Target Vanilla Class | Mixin Class | Purpose |
| :--- | :--- | :--- |
| `net.minecraft.world.level.block.FarmBlock` | `net.instantgratification.agrarianreform.mixin.FarmlandBlockMixin` | Custom hydration range & trample immunity |
| `net.minecraft.world.level.block.CropBlock` | `net.instantgratification.agrarianreform.mixin.CropBlockMixin` | Rain growth spurt, biodiversity bonus & rustle sounds |
| `net.minecraft.world.level.block.state.BlockBehaviour$BlockStateBase` | `net.instantgratification.agrarianreform.mixin.BlockStateBaseMixin` | Global & per-crop random tick growth multiplier |
| `net.minecraft.client.color.block.BlockColors` | `net.instantgratification.agrarianreform.mixin.BlockColorsMixin` | Client-only morning dew tinting |

## 3. Core Mechanics & Subsystems
- **Source Root**: `src/main/java/net/instantgratification/agrarianreform/`
- **Resource Root**: `src/main/resources/`

## 4. Dynamic GameRules & Commands
- **GameRules / Commands**: Configured dynamically via namespaced keys (`agrarian_reform:*`) and `DynamicGameRuleManager`.

## 5. Configuration & Sidedness Isolation
- **Sidedness**: Server-safe logic in main; client isolated via `@Environment(EnvType.CLIENT)` and `agrarian_reform.client.mixins.json`.
