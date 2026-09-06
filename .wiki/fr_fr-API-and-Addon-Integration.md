# 🔌 Guide d'intégration API et création d'addons

**Agrarian Reform** is designed for deep integration with **DasikLibrary** and open extension by third-party mod developers and datapack creators.

---

## 📚 Dépendances de l'API DasikLibrary

Agrarian Reform relies on **DasikLibrary** (`>=1.8.3`) for core infrastructure:

| DasikLibrary API Module | Used In Agrarian Reform | Purpose |
| :--- | :--- | :--- |
| `DynamicGameRuleManager` | `AgrarianGameRules.java`, `AgrarianCropRules.java` | Registers namespaced dynamic GameRules cleanly with GSON sync and live world storage. |
| `ConfigHelper` | `AgrarianConfig.java` | Loads and saves versioned JSON global configuration templates with schema migrations. |

---

## 🌾 Façade publique AgrarianCropRules

Third-party mods can programmatically query Agrarian Reform's crop cache and growth math:

```java
// Check if a block is recognized as an agricultural crop
boolean isCrop = AgrarianCropRules.isCropBlock(myBlock);

// Query effective growth speed multiplier (respects frozen -1, positive override, global fallback)
int effectiveMultiplier = AgrarianCropRules.getEffectiveGrowthMultiplier(level, myBlock);

// Check if a block state is at its maximum harvestable age
boolean maxAge = AgrarianCropRules.isMaxAge(blockState);
```

---

## 🏷️ Intégrations de tags de datapacks personnalisés

### 1. Soft Step Footwear Tag (`#agrarian_reform:soft_step_boots`)
Add custom armor items or boots to protect farmland from trampling:

`data/agrarian_reform/tags/item/soft_step_boots.json`:
```json
{
  "replace": false,
  "values": [
    "mymod:padded_leather_boots",
    "mymod:farmer_shoes"
  ]
}
```

### 2. Continuum Plants Tag (`#agrarian_reform:continuum_plants` & `#c:crops`)
Explicitly register custom plants with The Continuum offline growth simulator:

`data/agrarian_reform/tags/block/continuum_plants.json`:
```json
{
  "replace": false,
  "values": [
    "mymod:custom_corn_crop",
    "mymod:custom_tomato_bush"
  ]
}
```

---

*See also: [[Registre des plantes et cultures universelles|fr_fr-Plant-Registry-and-Crop-Types]] and [[Architecture et cibles Mixin|fr_fr-Architecture-and-Mixins]]*.
