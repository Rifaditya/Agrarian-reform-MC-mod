# 🌾 Right-Click Harvest & Auto-Replanting

**Agrarian Reform** includes Instant Gratification Quality-of-Life (QoL) harvesting. Players can harvest mature crops with a single right-click without destroying the block or manually replanting seeds.

---

## 📊 Feature Infobox

| Property | Value |
| :--- | :--- |
| **Handler Class** | `net.instantgratification.agrarianreform.AgrarianReformFabric` |
| **Trigger Action** | Right-click (Use) on mature crop block |
| **Effect** | Spawns crop drops, resets crop to Age 0, plays harvest sound |
| **GameRule Toggle** | `agrarian_reform:right_click_harvest` (Default: `true`) |

---

## ⚙️ Harvest Workflow

```
                  PLAYER RIGHT-CLICKS CROP BLOCK
                                │
                                ▼
               Is right_click_harvest GameRule true?
                              ┌─┴─┐
                           YES│   │NO
                              ▼   ▼
               Is crop block at maximum age (isMaxAge)?
                              ┌─┴─┐
                           YES│   │NO
                              ▼   ▼
               1. Calculate crop drops via Loot Table
               2. Spawn items into world
               3. Reset block state to Age 0
               4. Play harvest & rustle sound
```

---

## 🌾 Supported Crops for Right-Click Harvest

Right-click harvesting automatically supports all standard crops:
* **Wheat** (`minecraft:wheat`)
* **Carrots** (`minecraft:carrots`)
* **Potatoes** (`minecraft:potatoes`)
* **Beetroots** (`minecraft:beetroots`)
* Custom third-party mod crops extending `CropBlock`.

---

## ⚙️ Server Command Toggle

Disable right-click harvesting across your world:

```bash
/gamerule agrarian_reform:right_click_harvest false
```

---

*See also: [[Seed Sowing & Grass Cultivation|Seed-Sowing-and-Grass-Cultivation]] and [[Universal Bone Meal|Universal-Bone-Meal]]*.
