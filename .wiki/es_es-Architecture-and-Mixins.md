# 🏗️ Arquitectura y tabla de inyecciones Mixin

This document outlines package architecture, test suites, and provides an exhaustive breakdown of all Java Mixin injection targets in **Agrarian Reform**.

---

## 📦 Jerarquía de paquetes del código

```
net.instantgratification.agrarianreform
├── AgrarianGameRules.java         # 15 static GameRules & transient debug mode
├── AgrarianReformFabric.java      # ModInitializer entrypoint, lifecycle hooks, 6D use-block
├── client
│   └── AgrarianReformClient.java  # ClientModInitializer & morning moisture tint
├── config
│   ├── AgrarianConfig.java        # Global JSON config template (Schema v2) & dirty-tracking
│   ├── ModMenuIntegration.java    # ModMenu API integration
│   └── YaclScreenHelper.java      # Isolated 3-tab YACL v3 client GUI builder
├── continuum
│   ├── ContinuumData.java         # SavedDataType dimension persistence & 30-day pruning
│   ├── ContinuumManager.java      # ServerChunkEvents listeners & 5-crops/tick queue
│   └── CropScanner.java           # Sub-chunk palette pre-filtering & speed estimator
├── crop
│   └── AgrarianCropRules.java     # O(1) universal crop auto-population & dynamic GameRules
├── mixin
│   ├── BlockColorsMixin.java      # Client block color provider intercept
│   ├── BlockStateBaseMixin.java   # Random tick speedup/slowdown & max-age early-break
│   ├── CropBlockMixin.java        # Growth speed, rain spurt, rustle sound mixin
│   └── FarmlandBlockMixin.java    # Soft Step trample & Chebyshev concentric hydration
├── util
│   ├── AgrarianTags.java          # Datapack tag keys (#c:crops, #soft_step_boots)
│   ├── GrowthHelper.java          # Concentric Chebyshev math, bare-foot trample logic
│   ├── ModVersionGuard.java       # Startup API class check guard (Knot resolution)
│   └── SoundHelper.java           # Audio debouncing & primitive Int2Long collections
```

### Headless Test Architecture (`src/test/java`)

```
net.instantgratification.agrarianreform
├── config
│   └── AgrarianConfigTest.java    # Schema stability, multiplier hierarchy, dirty tracking
└── continuum
    └── ContinuumMathTest.java     # Delta math, frozen scaling, 30-day pruning, Chebyshev geometry
```

---

## 🎯 Tabla completa de inyecciones Mixin

| Mixin Class | Target Minecraft Class | Annotation & Injection Point | Purpose |
| :--- | :--- | :--- | :--- |
| `FarmlandBlockMixin` | `net.minecraft.world.level.block.FarmlandBlock` | `@Inject(method = "fallOn", at = @At("HEAD"), cancellable = true)` | Intercepts trample with bare-foot fast-fail, Soft Step, and `trample_immunity_players_only` gating. |
| `FarmlandBlockMixin` | `net.minecraft.world.level.block.FarmlandBlock` | `@Inject(method = "isNearWater", at = @At("HEAD"), cancellable = true)` | Replaces 4-block water check with expanding Concentric Chebyshev shells ($r=1 \to \text{maxRange}$, 3D $y \in [-1, 1]$) and pure water gating. |
| `CropBlockMixin` | `net.minecraft.world.level.block.CropBlock` | `@ModifyReturnValue(method = "getGrowthSpeed", at = @At("RETURN"))` | Applies +10% polyculture biodiversity growth bonus for adjacent diverse crops. |
| `CropBlockMixin` | `net.minecraft.world.level.block.CropBlock` | `@Inject(method = "randomTick", at = @At("HEAD"), cancellable = true)` | Pre-tick rain spurt acceleration (advances stage during rainfall). |
| `CropBlockMixin` | `net.minecraft.world.level.block.CropBlock` | `@Inject(method = "randomTick", at = @At("RETURN"))` | Post-tick vitality Happy Villager green sparkles when stage changes. |
| `CropBlockMixin` | `net.minecraft.world.level.block.CropBlock` | `@Inject(method = "entityInside", at = @At("HEAD"))` | Triggers crop rustle sound effect with debounced entity cooldowns. |
| `BlockStateBaseMixin`| `net.minecraft.world.level.block.state.BlockBehaviour$BlockStateBase` | `@Inject(method = "randomTick", at = @At("HEAD"), cancellable = true)` | Intercepts plant random ticks to scale speed per-crop and breaks early upon reaching max age. |
| `BlockColorsMixin` | `net.minecraft.client.color.block.BlockColors` | `@Inject(method = "createDefault", at = @At("RETURN"))` | Registers morning moisture soil tinting (ticks 23000 to 2000). |

---

## 💾 Almacenamiento del mundo vs NBT de chunks

Agrarian Reform strictly decouples its persistent timestamp data from individual chunk files:

```
┌─────────────────────────────────────────────────────────────┐
│                      STORAGE SEPARATION                     │
├─────────────────────────────────────────────────────────────┤
│ 1. DIMENSION STORAGE (data/agrarian_reform_continuum.dat)   │
│    ├── ContinuumData (SavedDataType)                        │
│    └── Stores Map<ChunkPos (Long), UnloadTimestamp (Long)>  │
│    └── Auto-prunes entries older than 30 days on save       │
│    └── Zero impact on chunk NBT or chunk dirty flags        │
│                                                             │
│ 2. CHUNK STORAGE (region/r.X.Z.mca)                         │
│    ├── LevelChunk NBT (Block States, Tile Entities)         │
│    └── ChunkAccess.unsaved flag                             │
│    └── Only marked TRUE when setBlock() changes a state     │
└─────────────────────────────────────────────────────────────┘
```

---

*See also: [[El Continuo (Crecimiento offline)|es_es-The-Continuum-Offline-Persistence]], [[Rendimiento y control de colas|es_es-Performance-and-Queue-Throttling]], and [[Configuración de desarrollo y compilación|es_es-Developer-Setup-and-Building]]*.
