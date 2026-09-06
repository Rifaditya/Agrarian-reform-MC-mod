# 🌾 右键收获与自动补种机制

**Agrarian Reform** includes Instant Gratification Quality-of-Life (QoL) harvesting. Players can harvest mature crops with a single right-click without destroying the block or manually replanting seeds, fortified by a strict **6-Dimensional Interaction Guard**.

---

## 📊 右键收获特性信息框

| Property | Value |
| :--- | :--- |
| **Handler Class** | `net.instantgratification.agrarianreform.AgrarianReformFabric` |
| **Event Target** | `UseBlockCallback.EVENT` |
| **Trigger Action** | Right-click (Use) on mature crop block with Main Hand |
| **Effect** | Spawns crop drops, resets crop to Age 0, plays harvest sound |
| **Crouch Bypass** | Sneak + Right Click (`player.isSecondaryUseActive()`) bypasses harvest to allow block placement |
| **Debounce Guard**| Main-Hand only (`hand != InteractionHand.MAIN_HAND` fast-fails with `PASS`) |
| **GameRule Toggle** | `agrarian_reform:right_click_harvest` (Default: `true`) |

---

## ⚙️ 6 维交互防护决策流程

```
                        PLAYER RIGHT-CLICKS CROP BLOCK
                                       │
                                       ▼
                    1. Is Hand MAIN_HAND? (Off-Hand Debounce)
                                   ┌────┴────┐
                                YES│         │NO
                                   ▼         ▼
                    2. Is Player Sneaking? (Secondary Use Bypass)
                    (player.isSecondaryUseActive())
                                   ┌────┴────┐
                                 NO│         │YES
                                   ▼         ▼
                    3. Is right_click_harvest GameRule true?
                                   ┌────┴────┐
                                YES│         │NO
                                   ▼         ▼
                    4. Is crop at maximum age (isMaxAge)?
                                   ┌────┴────┐
                                YES│         │NO
                                   ▼         ▼
                    5. Sided Execution & Item Drops
                    - Query loot table on server side (!level.isClientSide)
                    - Spawn drops into world
                    - Reset block state to Age 0
                    - Play harvest & crop rustle sound
                    - Return InteractionResult.SUCCESS
```

---

## 🌾 支持右键收获的作物列表

* **Wheat** (`minecraft:wheat`)
* **Carrots** (`minecraft:carrots`)
* **Potatoes** (`minecraft:potatoes`)
* **Beetroots** (`minecraft:beetroots`)
* **Modded Agriculture**: Any crop extending `CropBlock` or registered with `#c:crops`.

---

## ⚙️ 服务器指令配置

```bash
/gamerule agrarian_reform:right_click_harvest false
```

---

*See also: [[播种与草方块培植|zh_cn-Seed-Sowing-and-Grass-Cultivation]], [[作物注册表与通用作物|zh_cn-Plant-Registry-and-Crop-Types]], and [[通用骨粉机制|zh_cn-Universal-Bone-Meal]]*.
