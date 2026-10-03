# Architecture & Symbol Index: Agrarian Reform

## 1. Mod Metadata & Entrypoint
- **Mod ID**: `agrarian_reform`
- **Main Entrypoint**: `net.instantgratification.agrarianreform.AgrarianReformFabric` (`net.fabricmc.api.ModInitializer`)
- **Client Entrypoint**: `net.instantgratification.agrarianreform.client.AgrarianReformClient`

## 2. Bytecode Mixin Target Registry
| Target Vanilla Class | Mixin Class | Purpose |
| :--- | :--- | :--- |
| `Vanilla Class` | `net.instantgratification.agrarianreform.mixin.FarmlandBlockMixin` | Core mixin hook |
| `Vanilla Class` | `net.instantgratification.agrarianreform.mixin.CropBlockMixin` | Core mixin hook |
| `Vanilla Class` | `net.instantgratification.agrarianreform.mixin.BlockStateBaseMixin` | Core mixin hook |

## 3. Core Mechanics & Subsystems
- **Source Root**: `src/main/java/`
- **Resource Root**: `src/main/resources/`

## 4. Dynamic GameRules & Commands
- **GameRules / Commands**: Configured dynamically via namespaced keys (`agrarian_reform:*`).

## 5. Configuration & Sidedness Isolation
- **Sidedness**: Server-safe logic in main, client isolated in `src/client/java` or client entrypoint.
