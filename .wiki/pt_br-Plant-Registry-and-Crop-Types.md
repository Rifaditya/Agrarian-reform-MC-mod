# 🌿 Registro de plantas e culturas universais

**Agrarian Reform** features a zero-configuration **Universal Crop Auto-Population Engine** (`AgrarianCropRules.java`) powered by an $O(1)$ fast-fail identity cache. Any vanilla or third-party modded crop is automatically discovered, registered for offline Continuum simulation, and granted dedicated GameRule multiplier tuning without requiring manual datapack configuration.

---

## 🔍 Motor de descoberta dinâmica e classificação

```
                               BLOCK INSPECTION
                                      │
                     ┌────────────────┴────────────────┐
                     ▼                                 ▼
          Known Cached Crop (O(1))            Uncached Block Type
                     │                                 │
                     ▼                                 ▼
            Return Multiplier              3-Tier Discovery Pipeline
                                           1. Class Hierarchy: CropBlock, SaplingBlock, BonemealableBlock
                                           2. Tag Membership: #c:crops, #minecraft:crops, #farmersdelight:wild_crops
                                           3. Property Inspection: BlockState with "age" IntegerProperty
                                                       │
                                                       ▼ (If Match Found)
                                           DYNAMIC REGISTRATION
                                           - Cache in DYNAMIC_CROPS Set
                                           - Register GameRule: agrarian_reform:crop_growth_multiplier_<id>
                                           - Mark AgrarianConfig dirty = true
```

---

## 📊 Índice de plantas suportadas e estágios de crescimento

| Category | Java Class Target / Tag | Vanilla Examples | Average Ticks / Stage | Continuum Catch-up | Universal Bone Meal |
| :--- | :--- | :--- | :--- | :--- | :--- |
| **Standard Crops** | `CropBlock`, `#c:crops` | Wheat, Carrots, Potatoes, Beetroots | $\sim 6,241\text{ ticks}$ | ✅ Yes (Throttled Queue) | Vanilla Native |
| **Tall Stalks** | `SugarCaneBlock`, `CactusBlock` | Sugar Cane, Cactus | $1,365\text{ ticks}$ | ✅ Yes (Height capped 3) | ✅ Extended Support |
| **Nether Flora** | `NetherWartBlock` | Nether Wart | $13,650\text{ ticks}$ | ✅ Yes (Stage 0 $\to$ 3) | ✅ Extended Support |
| **Stem & Pod Crops**| `CocoaBlock`, `SweetBerryBushBlock`| Cocoa Beans, Sweet Berries | $6,825\text{ ticks}$ | ✅ Yes (Stage 0 $\to$ 2/3) | ✅ Extended Support |
| **Vines & Foliage** | `VineBlock` | Vines | Variable | ✅ Yes (Downward spread) | ✅ Extended Support |
| **Trees & Saplings**| `SaplingBlock` | Oak, Spruce, Birch, Cherry Saplings | $95,550\text{ ticks}$ | ✅ Yes (Tree advance) | Vanilla Native |
| **Modded Agriculture**| Any `#c:crops` or `AgeBlock` | Farmer's Delight, Mystical Agriculture | Configurable | ✅ Yes (Scaled delta) | Dynamic / Extended |

---

## 🌾 Hierarquia de resolução de multiplicadores

```java
public static int getEffectiveGrowthMultiplier(Level level, Block block) {
    Identifier id = BuiltInRegistries.BLOCK.getKey(block);
    // 1. Check world-specific GameRule
    GameRule<Integer> dynamicRule = DYNAMIC_GAMERULES.get(id);
    if (dynamicRule != null && level instanceof ServerLevel serverLevel) {
        int val = DynamicGameRuleManager.getInt(serverLevel, dynamicRule);
        if (val > 0) return val;       // Positive override
        if (val == -1) return 0;       // -1 = Frozen (0% growth)
    }
    // 2. Fall back to Global Multiplier
    if (level instanceof ServerLevel serverLevel) {
        return DynamicGameRuleManager.getInt(serverLevel, AgrarianGameRules.GLOBAL_GROWTH_MULTIPLIER);
    }
    return 100;
}
```

### 🔄 Runtime Discovery & Mod Removal Resilience

1. **Mid-Game Dynamic Discovery**: `AgrarianCropRules` hooks into random ticks, Continuum chunk loading sweeps, and block interaction events. When a previously unregistered modded crop is loaded or interacted with mid-session, it is discovered on-the-fly, registered into the $O(1)$ block cache, and given a dynamic GameRule with zero reload latency.
2. **Mod Removal & Zero-Crash Safety**: Dynamic crop rules and caches rely strictly on standard Minecraft `Identifier` strings (`namespace:path`) rather than direct compiled class types. When a crop mod is uninstalled, `AgrarianCropRules.isCropBlock` safely ignores missing blocks, producing **0 `ClassNotFoundException` or `NoClassDefFoundError` crashes**.

---

## 🏷️ Integração de tags de datapack (`AgrarianTags`)

```json
{
  "replace": false,
  "values": [
    "#minecraft:crops",
    "#c:crops",
    "farmersdelight:cabbage",
    "farmersdelight:tomatoes",
    "farmersdelight:onions"
  ]
}
```

---

*See also: [[O Continuum (Crescimento offline)|pt_br-The-Continuum-Offline-Persistence]], [[Multiplicador global de crescimento|pt_br-Global-Growth-Multiplier]], and [[Configuração em dois níveis|pt_br-Configuration]]*.
