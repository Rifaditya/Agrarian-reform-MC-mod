# 🌿 Plant Registry & Crop Types Index

This page lists all plant categories, block classes, and tags supported by **Agrarian Reform**'s offline persistence and growth acceleration mechanics.

---

## 📊 Supported Plant Index

| Category | Java Class Target | Vanilla Examples | Continuum Catch-up | Universal Bone Meal |
| :--- | :--- | :--- | :--- | :--- |
| **Standard Crops** | `CropBlock` | Wheat, Carrots, Potatoes, Beetroots | ✅ Yes (Throttled Queue) | Vanilla Native |
| **Tall Stalks** | `SugarCaneBlock`, `CactusBlock` | Sugar Cane, Cactus | ✅ Yes (Height capped 3) | ✅ Extended Support |
| **Nether Flora** | `NetherWartBlock` | Nether Wart | ✅ Yes (13,650 ticks/stage) | ✅ Extended Support |
| **Stem & Pod Crops**| `CocoaBlock`, `SweetBerryBushBlock`| Cocoa Beans, Sweet Berries | ✅ Yes (6,825 ticks/stage) | ✅ Extended Support |
| **Vines & Foliage** | `VineBlock` | Vines | ✅ Yes (Downward growth) | ✅ Extended Support |
| **Trees & Saplings**| `SaplingBlock` | Oak, Spruce, Birch, Jungle Saplings | ✅ Yes (Tree advance) | Vanilla Native |

---

## 🏷️ Datapack Tag Integration (`AgrarianTags`)

Agrarian Reform registers custom tags for seamless third-party mod crop support (`AgrarianTags.CONTINUUM_PLANTS`):

```json
{
  "replace": false,
  "values": [
    "#minecraft:crops",
    "minecraft:sugar_cane",
    "minecraft:cactus",
    "minecraft:nether_wart",
    "minecraft:cocoa",
    "minecraft:sweet_berry_bush",
    "minecraft:vine"
  ]
}
```

Third-party mod developers can add their custom crops to `#agrarianreform:continuum_plants` via datapacks to automatically register them with The Continuum offline growth simulator!

---

*See also: [[The Continuum (Offline Growth)|The-Continuum-Offline-Persistence]] and [[API & Addon Integration|API-and-Addon-Integration]]*.
