# 🦴 Poudre d'os universelle

**Universal Bone Meal** expands Bone Meal functionality across all plant types, allowing players to fertilize non-natively bonemealable plants.

---

## 📊 Infobox de la fonctionnalité

| Property | Value |
| :--- | :--- |
| **Handler Class** | `net.instantgratification.agrarianreform.AgrarianReformFabric` |
| **Trigger Item** | Bone Meal (`minecraft:bone_meal`) |
| **Target Plants** | Sugar Cane, Cactus, Nether Wart, Vines, Lily Pads, Cactus, Small Flowers |
| **GameRule Toggle** | `agrarian_reform:universal_bonemeal` (Default: `true`) |

---

## 🌿 Fertilisation étendue des plantes

In vanilla Minecraft, bone meal cannot be used on plants like Sugar Cane, Cacti, or Nether Wart. **Agrarian Reform** lifts these restrictions:

* **Sugar Cane & Cactus**: Using bone meal on a sugar cane or cactus block advances its age property by **+1 to +3 stages**, accelerating stalk growth up to the 3-block height limit.
* **Nether Wart**: Bone meal applied to nether crops advances its growth age stage towards maturity (Age 3).
* **Vines & Small Plants**: Bone meal forces downward growth or multiplication of foliage.

---

## ⚙️ Commutateur GameRule

```bash
/gamerule agrarian_reform:universal_bonemeal false
```

---

*See also: [[Multiplicateur global de croissance|fr_fr-Global-Growth-Multiplier]] and [[Registre des plantes et cultures universelles|fr_fr-Plant-Registry-and-Crop-Types]]*.
