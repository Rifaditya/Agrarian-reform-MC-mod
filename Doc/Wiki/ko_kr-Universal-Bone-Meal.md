# 🦴 범용 골분 시비 시스템

**Universal Bone Meal** expands Bone Meal functionality across all plant types, allowing players to fertilize non-natively bonemealable plants.

---

## 📊 기능 인포박스

| Property | Value |
| :--- | :--- |
| **Handler Class** | `net.instantgratification.agrarianreform.AgrarianReformFabric` |
| **Trigger Item** | Bone Meal (`minecraft:bone_meal`) |
| **Target Plants** | Sugar Cane, Cactus, Nether Wart, Vines, Lily Pads, Cactus, Small Flowers |
| **GameRule Toggle** | `agrarian_reform:universal_bonemeal` (Default: `true`) |

---

## 🌿 비료 적용 대상 식물 확대

In vanilla Minecraft, bone meal cannot be used on plants like Sugar Cane, Cacti, or Nether Wart. **Agrarian Reform** lifts these restrictions:

* **Sugar Cane & Cactus**: Using bone meal on a sugar cane or cactus block advances its age property by **+1 to +3 stages**, accelerating stalk growth up to the 3-block height limit.
* **Nether Wart**: Bone meal applied to nether crops advances its growth age stage towards maturity (Age 3).
* **Vines & Small Plants**: Bone meal forces downward growth or multiplication of foliage.

---

## ⚙️ GameRule 토글 설정

```bash
/gamerule agrarian_reform:universal_bonemeal false
```

---

*See also: [[글로벌 성장 배율 및 개별 조정|ko_kr-Global-Growth-Multiplier]] and [[작물 레지스트리 및 범용 작물|ko_kr-Plant-Registry-and-Crop-Types]]*.
