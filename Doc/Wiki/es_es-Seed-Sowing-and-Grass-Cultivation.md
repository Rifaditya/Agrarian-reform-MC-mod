# 🌱 Siembra de semillas y cultivo de hierba

**Seed Sowing & Grass Cultivation** provides a natural, pastoral method for restoring bare dirt into lush grass blocks using agricultural seeds.

---

## 📊 Infobox de la característica

| Property | Value |
| :--- | :--- |
| **Handler Class** | `net.instantgratification.agrarianreform.AgrarianReformFabric` |
| **Trigger Item** | Any item matching `c:seeds` tag (Wheat Seeds, Melon Seeds, Pumpkin Seeds, Beetroot Seeds) |
| **Target Block** | Dirt (`minecraft:dirt`) |
| **Result** | Consumes 1 seed, transforms dirt block into Grass Block (`minecraft:grass_block`) |
| **GameRule Toggle** | `agrarian_reform:seeds_grow_grass` (Default: `true`) |

---

## 🌾 Flujo de trabajo y mecánicas

In vanilla Minecraft, spreading grass blocks across bare dirt patches requires waiting for adjacent grass blocks to slowly spread or obtaining silk touch tools.

With **Agrarian Reform**:
1. Hold any agricultural seed in your main hand or off-hand.
2. Right-click on a standard **Dirt** block.
3. The seed is consumed, and the block immediately converts to a **Grass Block** with subtle grass placement sounds and particle effects.

```
       [ Dirt Block ] + [ Wheat Seed ] ──(Right-Click)──> [ Grass Block ] (1 Seed Consumed)
```

---

## ⚙️ Administración de GameRule

```bash
/gamerule agrarian_reform:seeds_grow_grass false
```

---

*See also: [[Cosecha con clic derecho y resiembra|es_es-Right-Click-Harvest-and-Replanting]] and [[Polvo de hueso universal|es_es-Universal-Bone-Meal]]*.
