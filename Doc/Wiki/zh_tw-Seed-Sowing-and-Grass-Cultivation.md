# 🌱 播種與草方塊培植機制

**Seed Sowing & Grass Cultivation** provides a natural, pastoral method for restoring bare dirt into lush grass blocks using agricultural seeds.

---

## 📊 特性資訊框

| Property | Value |
| :--- | :--- |
| **Handler Class** | `net.instantgratification.agrarianreform.AgrarianReformFabric` |
| **Trigger Item** | Any item matching `c:seeds` tag (Wheat Seeds, Melon Seeds, Pumpkin Seeds, Beetroot Seeds) |
| **Target Block** | Dirt (`minecraft:dirt`) |
| **Result** | Consumes 1 seed, transforms dirt block into Grass Block (`minecraft:grass_block`) |
| **GameRule Toggle** | `agrarian_reform:seeds_grow_grass` (Default: `true`) |

---

## 🌾 工作流與遊戲機制

In vanilla Minecraft, spreading grass blocks across bare dirt patches requires waiting for adjacent grass blocks to slowly spread or obtaining silk touch tools.

With **Agrarian Reform**:
1. Hold any agricultural seed in your main hand or off-hand.
2. Right-click on a standard **Dirt** block.
3. The seed is consumed, and the block immediately converts to a **Grass Block** with subtle grass placement sounds and particle effects.

```
       [ Dirt Block ] + [ Wheat Seed ] ──(Right-Click)──> [ Grass Block ] (1 Seed Consumed)
```

---

## ⚙️ GameRule 規則管理

```bash
/gamerule agrarian_reform:seeds_grow_grass false
```

---

*See also: [[右鍵收穫與自動補種|zh_tw-Right-Click-Harvest-and-Replanting]] and [[通用骨粉機制|zh_tw-Universal-Bone-Meal]]*.
