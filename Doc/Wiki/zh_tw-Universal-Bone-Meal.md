# 🦴 通用骨粉催熟機制

**Universal Bone Meal** expands Bone Meal functionality across all plant types, allowing players to fertilize non-natively bonemealable plants.

---

## 📊 特性資訊框

| Property | Value |
| :--- | :--- |
| **Handler Class** | `net.instantgratification.agrarianreform.AgrarianReformFabric` |
| **Trigger Item** | Bone Meal (`minecraft:bone_meal`) |
| **Target Plants** | Sugar Cane, Cactus, Nether Wart, Vines, Lily Pads, Cactus, Small Flowers |
| **GameRule Toggle** | `agrarian_reform:universal_bonemeal` (Default: `true`) |

---

## 🌿 擴展植物受肥範圍

In vanilla Minecraft, bone meal cannot be used on plants like Sugar Cane, Cacti, or Nether Wart. **Agrarian Reform** lifts these restrictions:

* **Sugar Cane & Cactus**: Using bone meal on a sugar cane or cactus block advances its age property by **+1 to +3 stages**, accelerating stalk growth up to the 3-block height limit.
* **Nether Wart**: Bone meal applied to nether crops advances its growth age stage towards maturity (Age 3).
* **Vines & Small Plants**: Bone meal forces downward growth or multiplication of foliage.

---

## ⚙️ GameRule 規則開關

```bash
/gamerule agrarian_reform:universal_bonemeal false
```

---

*See also: [[全域生長倍率與獨立調諧|zh_tw-Global-Growth-Multiplier]] and [[作物註冊表與通用作物|zh_tw-Plant-Registry-and-Crop-Types]]*.
