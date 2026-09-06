# 🌱 種蒔きと草ブロック育成メカニクス

**Seed Sowing & Grass Cultivation** provides a natural, pastoral method for restoring bare dirt into lush grass blocks using agricultural seeds.

---

## 📊 機能インフォボックス

| Property | Value |
| :--- | :--- |
| **Handler Class** | `net.instantgratification.agrarianreform.AgrarianReformFabric` |
| **Trigger Item** | Any item matching `c:seeds` tag (Wheat Seeds, Melon Seeds, Pumpkin Seeds, Beetroot Seeds) |
| **Target Block** | Dirt (`minecraft:dirt`) |
| **Result** | Consumes 1 seed, transforms dirt block into Grass Block (`minecraft:grass_block`) |
| **GameRule Toggle** | `agrarian_reform:seeds_grow_grass` (Default: `true`) |

---

## 🌾 育成フローとゲーム内メカニクス

In vanilla Minecraft, spreading grass blocks across bare dirt patches requires waiting for adjacent grass blocks to slowly spread or obtaining silk touch tools.

With **Agrarian Reform**:
1. Hold any agricultural seed in your main hand or off-hand.
2. Right-click on a standard **Dirt** block.
3. The seed is consumed, and the block immediately converts to a **Grass Block** with subtle grass placement sounds and particle effects.

```
       [ Dirt Block ] + [ Wheat Seed ] ──(Right-Click)──> [ Grass Block ] (1 Seed Consumed)
```

---

## ⚙️ GameRule による管理

```bash
/gamerule agrarian_reform:seeds_grow_grass false
```

---

*See also: [[右クリック収穫と自動再植|ja_jp-Right-Click-Harvest-and-Replanting]] and [[汎用骨粉システム|ja_jp-Universal-Bone-Meal]]*.
