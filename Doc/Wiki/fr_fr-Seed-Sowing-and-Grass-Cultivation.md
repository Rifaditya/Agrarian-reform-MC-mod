# 🌱 Semis de graines et culture d'herbe

**Seed Sowing & Grass Cultivation** provides a natural, pastoral method for restoring bare dirt into lush grass blocks using agricultural seeds.

---

## 📊 Infobox de la fonctionnalité

| Property | Value |
| :--- | :--- |
| **Handler Class** | `net.instantgratification.agrarianreform.AgrarianReformFabric` |
| **Trigger Item** | Any item matching `c:seeds` tag (Wheat Seeds, Melon Seeds, Pumpkin Seeds, Beetroot Seeds) |
| **Target Block** | Dirt (`minecraft:dirt`) |
| **Result** | Consumes 1 seed, transforms dirt block into Grass Block (`minecraft:grass_block`) |
| **GameRule Toggle** | `agrarian_reform:seeds_grow_grass` (Default: `true`) |

---

## 🌾 Fonctionnement et mécanique de jeu

In vanilla Minecraft, spreading grass blocks across bare dirt patches requires waiting for adjacent grass blocks to slowly spread or obtaining silk touch tools.

With **Agrarian Reform**:
1. Hold any agricultural seed in your main hand or off-hand.
2. Right-click on a standard **Dirt** block.
3. The seed is consumed, and the block immediately converts to a **Grass Block** with subtle grass placement sounds and particle effects.

```
       [ Dirt Block ] + [ Wheat Seed ] ──(Right-Click)──> [ Grass Block ] (1 Seed Consumed)
```

---

## ⚙️ Gestion par GameRule

```bash
/gamerule agrarian_reform:seeds_grow_grass false
```

---

*See also: [[Récolte par clic droit et replantation|fr_fr-Right-Click-Harvest-and-Replanting]] and [[Poudre d'os universelle|fr_fr-Universal-Bone-Meal]]*.
