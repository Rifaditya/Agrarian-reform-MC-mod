# 🌾 右クリック収穫と自動再植システム

**Agrarian Reform** includes Instant Gratification Quality-of-Life (QoL) harvesting. Players can harvest mature crops with a single right-click without destroying the block or manually replanting seeds, fortified by a strict **6-Dimensional Interaction Guard**.

---

## 📊 右クリック収穫インフォボックス

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

## ⚙️ 6次元インタラクション判定フロー

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

## 🌾 対応作物一覧

* **Wheat** (`minecraft:wheat`)
* **Carrots** (`minecraft:carrots`)
* **Potatoes** (`minecraft:potatoes`)
* **Beetroots** (`minecraft:beetroots`)
* **Modded Agriculture**: Any crop extending `CropBlock` or registered with `#c:crops`.

---

## ⚙️ サーバーコマンド設定

```bash
/gamerule agrarian_reform:right_click_harvest false
```

---

*See also: [[種蒔きと草ブロック育成|ja_jp-Seed-Sowing-and-Grass-Cultivation]], [[作物レジストリと汎用作物|ja_jp-Plant-Registry-and-Crop-Types]], and [[汎用骨粉システム|ja_jp-Universal-Bone-Meal]]*.
