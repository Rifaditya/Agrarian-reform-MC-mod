# 🦴 通用骨粉催熟机制

**Universal Bone Meal** expands Bone Meal functionality across all plant types, allowing players to fertilize non-natively bonemealable plants.

---

## 📊 特性信息框

| Property | Value |
| :--- | :--- |
| **Handler Class** | `net.instantgratification.agrarianreform.AgrarianReformFabric` |
| **Trigger Item** | Bone Meal (`minecraft:bone_meal`) |
| **Target Plants** | Sugar Cane, Cactus, Nether Wart, Vines, Lily Pads, Cactus, Small Flowers |
| **GameRule Toggle** | `agrarian_reform:universal_bonemeal` (Default: `true`) |

---

## 🌿 扩展植物受肥范围

In vanilla Minecraft, bone meal cannot be used on plants like Sugar Cane, Cacti, or Nether Wart. **Agrarian Reform** lifts these restrictions:

* **Sugar Cane & Cactus**: Using bone meal on a sugar cane or cactus block advances its age property by **+1 to +3 stages**, accelerating stalk growth up to the 3-block height limit.
* **Nether Wart**: Bone meal applied to nether crops advances its growth age stage towards maturity (Age 3).
* **Vines & Small Plants**: Bone meal forces downward growth or multiplication of foliage.

---

## ⚙️ GameRule 规则开关

```bash
/gamerule agrarian_reform:universal_bonemeal false
```

---

*See also: [[全局生长倍率与独立调谐|zh_cn-Global-Growth-Multiplier]] and [[作物注册表与通用作物|zh_cn-Plant-Registry-and-Crop-Types]]*.
