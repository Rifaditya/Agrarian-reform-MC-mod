# 👟 耕地耐久性と踏み荒らし保護メカニクス

In vanilla Minecraft, farmland is as fragile as glass—a single light hop or sprint across crops turns tilled soil into coarse dirt and destroys the crop. **Agrarian Reform** implements **Soil Resilience**, treating farmland as a nurtured resource with realistic trample physics, bare-foot fast-fails, and selective player/pet entity gating.

---

## 📊 踏み荒らし保護インフォボックス

| Property | Specification |
| :--- | :--- |
| **Mixin Handler** | `net.instantgratification.agrarianreform.mixin.FarmlandBlockMixin` |
| **Logic Helper** | `net.instantgratification.agrarianreform.util.GrowthHelper` |
| **Soft Step Tags** | `#agrarian_reform:soft_step_boots`, `#c:boots/soft`, `minecraft:leather_boots` |
| **Soft Step Enchants**| Feather Falling (`minecraft:feather_falling`) on feet slot |
| **Fall Distance Limit**| $\le 0.6\text{ meters}$ |
| **Entity Gating Rule**| `agrarian_reform:trample_immunity_players_only` (Default: `false`) |
| **Total Immunity Rule**| `agrarian_reform:total_trample_immunity` (Default: `false`) |

---

## 👟 ソフトステップ保護ルール

```
                       ENTITY FALLS / STEPS ON FARMLAND
                                       │
                                       ▼
                    Is totalTrampleImmunity GameRule true?
                                  ┌────┴────┐
                               YES│         │NO
                                  ▼         ▼
                  Is trampleImmunityPlayersOnly true?  Bare-foot Fast-Fail Check
                                  ┌────┴────┐          (Feet slot empty -> skip enchant lookup)
                               YES│         │NO                     │
                                  ▼         ▼                       ▼
                         Is Player / Tamed? Cancel Trample   Does entity have Soft Step?
                         ┌────────┴────────┐(Farmland Safe)  (#soft_step_boots or Feather Falling)
                      YES│                 │NO                      ┌───────┴───────┐
                         ▼                 ▼                     YES│               │NO
                   Cancel Trample     Normal Trample Logic          ▼               ▼
                   (Farmland Safe)    (Checks Soft Step/Fall) Cancel Trample  Did fall distance
                                                              (Farmland Safe) exceed 0.6m limit?
                                                                                ┌───┴───┐
                                                                             YES│       │NO
                                                                                ▼       ▼
                                                                             Trample  Cancel
                                                                             Dirt!    Trample
```

### 1. Protective Footwear & Enchantments
* **Leather Boots & Tagged Boots**: Wearing leather boots or any boots tagged in `#agrarian_reform:soft_step_boots` or `#c:boots/soft` grants total trample immunity during walking, sprinting, and low-altitude jumping.
* **Feather Falling**: Having any level of Feather Falling enchantment on footwear prevents farmland destruction during ordinary movement.
* **Bare-Foot Fast-Fail**: If an entity has an empty feet slot (`getItemBySlot(EquipmentSlot.FEET).isEmpty()`), expensive enchantment iteration is bypassed immediately in $0.0001\mu\text{s}$.

### 2. Velocity & Impact Distance
Normal walking or running across farmland will **never** damage soil. However, dropping from a height greater than **0.6 meters** without Soft Step gear will trigger farmland trampling, preserving physical consequences for careless leaps.

### 3. Player & Tamed Pet Entity Gating
When `agrarian_reform:trample_immunity_players_only` is activated alongside `total_trample_immunity`:
* **Players and Tamed Pets** (`TamableAnimal.isTame()` like wolves and cats) are granted 100% trample immunity.
* **Hostile & Wild Mobs** (Zombies, Skeletons, Villagers, Wild Animals, Ravagers) still trample and destroy crops upon falling, preserving tactical farm defense mechanics.

---

## ⚙️ GameRule コマンド操作

```bash
# Enable total trample immunity for all entities
/gamerule agrarian_reform:total_trample_immunity true

# Restrict trample immunity strictly to players and tamed pets
/gamerule agrarian_reform:total_trample_immunity true
/gamerule agrarian_reform:trample_immunity_players_only true
```

---

*See also: [[ゲームルール (GameRules) リファレンス|ja_jp-GameRules]], [[演出と環境フィードバック|ja_jp-Aesthetics-and-Ambient-Feedback]], and [[API とアドオン統合|ja_jp-API-and-Addon-Integration]]*.
