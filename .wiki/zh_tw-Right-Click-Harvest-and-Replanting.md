# 🌾 右鍵收穫與自動補種機制

**Agrarian Reform** includes Instant Gratification Quality-of-Life (QoL) harvesting. Players can harvest mature crops with a single right-click without destroying the block or manually replanting seeds, fortified by a strict **6-Dimensional Interaction Guard**.

---

## 📊 右鍵收穫特性資訊框

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

## ⚙️ 6 維互動防護決策流程

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

## 🌾 支援右鍵收穫的作物列表

* **Wheat** (`minecraft:wheat`)
* **Carrots** (`minecraft:carrots`)
* **Potatoes** (`minecraft:potatoes`)
* **Beetroots** (`minecraft:beetroots`)
* **Modded Agriculture**: Any crop extending `CropBlock` or registered with `#c:crops`.

---

## ⚙️ 伺服器指令設定

```bash
/gamerule agrarian_reform:right_click_harvest false
```

---

*See also: [[播種與草方塊培植|zh_tw-Seed-Sowing-and-Grass-Cultivation]], [[作物註冊表與通用作物|zh_tw-Plant-Registry-and-Crop-Types]], and [[通用骨粉機制|zh_tw-Universal-Bone-Meal]]*.
