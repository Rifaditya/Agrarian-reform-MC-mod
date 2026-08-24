# 🏗️ Architecture & Mixin Target Reference

This document outlines package architecture and provides an exhaustive breakdown of all Java Mixin injection targets in **Agrarian Reform**.

---

## 📦 Package Architecture Hierarchy

```
net.instantgratification.agrarianreform
├── AgrarianGameRules.java         # DynamicGameRuleManager registrations
├── AgrarianReformFabric.java      # ModInitializer entrypoint & event listeners
├── client
│   └── AgrarianReformClient.java  # ClientModInitializer & block color registrations
├── config
│   ├── AgrarianConfig.java        # Global JSON config template (VERSION 1)
│   ├── ModMenuIntegration.java    # ModMenu API integration
│   └── YaclScreenHelper.java      # Isolated YACL v3 client GUI builder
├── continuum
│   ├── ContinuumData.java         # SavedData persistence record (SavedDataType)
│   ├── ContinuumManager.java      # ServerChunkEvents listeners & 5-crops/tick queue
│   └── CropScanner.java           # Heightmap surface scanner & speed estimator
├── mixin
│   ├── BlockColorsMixin.java      # Client block color provider intercept
│   ├── BlockStateBaseMixin.java   # Random tick speedup/slowdown intercept
│   ├── CropBlockMixin.java        # Growth speed, rain spurt, rustle sound mixin
│   └── FarmlandBlockMixin.java    # Soft Step trample & custom hydration range mixin
├── util
│   ├── AgrarianTags.java          # Datapack tag keys (#agrarianreform:continuum_plants)
│   ├── GrowthHelper.java          # Core dynamic tick math & trample logic helpers
│   ├── ModVersionGuard.java       # Startup API class check guard (EntityTypes)
│   └── SoundHelper.java           # Audio debouncing & primitive Int2Long collections
```

---

## 🎯 Complete Mixin Target Table

| Mixin Class | Target Minecraft Class | Annotation & Injection Point | Purpose |
| :--- | :--- | :--- | :--- |
| `FarmlandBlockMixin` | `net.minecraft.world.level.block.FarmlandBlock` | `@Inject(method = "fallOn", at = @At("HEAD"), cancellable = true)` | Intercepts trample to apply Soft Step (Leather Boots / Feather Falling) & `total_trample_immunity`. |
| `FarmlandBlockMixin` | `net.minecraft.world.level.block.FarmlandBlock` | `@Inject(method = "isNearWater", at = @At("HEAD"), cancellable = true)` | Replaces 4-block water check with 8-block source / 4-block flowing hydro-dynamics. |
| `CropBlockMixin` | `net.minecraft.world.level.block.CropBlock` | `@ModifyReturnValue(method = "getGrowthSpeed", at = @At("RETURN"))` | Applies +10% polyculture biodiversity growth bonus for adjacent diverse crops. |
| `CropBlockMixin` | `net.minecraft.world.level.block.CropBlock` | `@Inject(method = "randomTick", at = @At("HEAD"), cancellable = true)` | Pre-tick rain spurt acceleration (advances stage during rainfall). |
| `CropBlockMixin` | `net.minecraft.world.level.block.CropBlock` | `@Inject(method = "randomTick", at = @At("RETURN"))` | Post-tick vitality Happy Villager green sparkles when stage changes. |
| `CropBlockMixin` | `net.minecraft.world.level.block.CropBlock` | `@Inject(method = "entityInside", at = @At("HEAD"))` | Triggers crop rustle sound effect with debounced entity cooldowns. |
| `BlockStateBaseMixin`| `net.minecraft.world.level.block.state.BlockBehaviour$BlockStateBase` | `@Inject(method = "randomTick", at = @At("HEAD"), cancellable = true)` | Intercepts plant random ticks to scale speed according to `global_growth_multiplier`. |
| `BlockColorsMixin` | `net.minecraft.client.color.block.BlockColors` | `@Inject(method = "createDefault", at = @At("RETURN"))` | Registers morning moisture soil tinting (ticks 23000 to 2000). |

---

## 💾 World Storage vs Chunk NBT Architecture

Agrarian Reform strictly decouples its persistent timestamp data from individual chunk files:

```
┌─────────────────────────────────────────────────────────────┐
│                      STORAGE SEPARATION                     │
├─────────────────────────────────────────────────────────────┤
│ 1. DIMENSION STORAGE (data/continuum_data.dat)              │
│    ├── ContinuumData (SavedDataType)                        │
│    └── Stores Map<ChunkPos (Long), UnloadTimestamp (Long)>  │
│    └── Zero impact on chunk NBT or chunk dirty flags        │
│                                                             │
│ 2. CHUNK STORAGE (region/r.X.Z.mca)                         │
│    ├── LevelChunk NBT (Block States, Tile Entities)         │
│    └── ChunkAccess.unsaved flag                             │
│    └── Only marked TRUE when setBlock() changes a state     │
└─────────────────────────────────────────────────────────────┘
```

---

*See also: [[The Continuum (Offline Growth)|The-Continuum-Offline-Persistence]], [[Performance & Queue Throttling|Performance-and-Queue-Throttling]], and [[Developer Setup & Building|Developer-Setup-and-Building]]*.

