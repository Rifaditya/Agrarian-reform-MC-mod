# 🔌 API & Addon Integration

**Agrarian Reform** is designed for deep library integration with **DasikLibrary** and open extension by third-party mod developers.

---

## 📚 DasikLibrary API Dependencies

Agrarian Reform relies on **DasikLibrary** (`>=1.8.3`) for core infrastructure:

| DasikLibrary API Module | Used In Agrarian Reform | Purpose |
| :--- | :--- | :--- |
| `DynamicGameRuleManager` | `AgrarianGameRules.java` | Registers namespaced dynamic GameRules cleanly with GSON sync. |
| `ConfigHelper` | `AgrarianConfig.java` | Loads and saves versioned JSON global configuration templates. |

---

## 🏷️ Custom Datapack Tag Integration

To register third-party mod crops with Agrarian Reform's offline growth simulator, add your custom crop blocks to the `#agrarianreform:continuum_plants` tag inside your mod's datapack resources:

`data/agrarianreform/tags/block/continuum_plants.json`:
```json
{
  "replace": false,
  "values": [
    "mymod:custom_corn_crop",
    "mymod:custom_tomato_bush"
  ]
}
```

Once tagged, **The Continuum** will automatically scan, queue, and calculate catch-up growth for your mod's crops when chunks reload!

---

*See also: [[Plant Registry & Crop Types|Plant-Registry-and-Crop-Types]] and [[Architecture & Mixins|Architecture-and-Mixins]]*.
