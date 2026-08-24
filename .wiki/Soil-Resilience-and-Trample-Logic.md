# 👟 Soil Resilience & Trample Protection

In vanilla Minecraft, farmland is as fragile as glass—a single light hop or sprint across crops turns tilled soil into coarse dirt and destroys the crop. **Agrarian Reform** implements **Soil Resilience**, treating farmland as a nurtured resource with realistic trample physics.

---

## 📊 Mechanics Infobox

| Property | Specification |
| :--- | :--- |
| **Mixin Handler** | `net.instantgratification.agrarianreform.mixin.FarmlandBlockMixin` |
| **Logic Helper** | `net.instantgratification.agrarianreform.util.GrowthHelper` |
| **Soft Step Items** | Leather Boots (`minecraft:leather_boots`) |
| **Soft Step Enchants**| Feather Falling (`minecraft:feather_falling`) |
| **Fall Distance Limit**| $\le 0.6\text{ meters}$ |
| **GameRule Override**| `agrarian_reform:total_trample_immunity` (Default: `false`) |

---

## 👟 Soft Step Protection Rules

When a player or entity steps on farmland, Agrarian Reform intercepts the trample check before dirt reversion occurs:

```
                      ENTITY FALLS / STEPS ON FARMLAND
                                     │
                                     ▼
                  Is totalTrampleImmunity GameRule true?
                                ┌────┴────┐
                             YES│         │NO
                                ▼         ▼
                        Cancel Trample  Does entity have Soft Step?
                        (Farmland Safe) (Leather Boots or Feather Falling)
                                          ┌───────┴───────┐
                                       YES│               │NO
                                          ▼               ▼
                                 Cancel Trample     Did fall distance
                                 (Farmland Safe)     exceed 0.6m limit?
                                                      ┌───┴───┐
                                                   YES│       │NO
                                                      ▼       ▼
                                                   Trample  Cancel
                                                   Dirt!    Trample
```

### 1. Protective Footwear & Enchantments
* **Leather Boots**: Wearing leather boots grants total trample immunity during walking, sprinting, and low-altitude jumping.
* **Feather Falling**: Having any level of Feather Falling enchantment on footwear prevents farmland destruction during ordinary movement.

### 2. Velocity & Impact Distance
Normal walking or running across farmland will **never** damage soil. However, dropping from a height greater than **0.6 meters** without Soft Step gear will trigger farmland trampling, preserving physical consequences for careless leaps.

### 3. Ravager & Heavy Beast Protection
Massive entities such as **Ravagers** bypass normal Soft Step logic during raids, ensuring that village invasions remain destructive and tactical threats.

---

## ⚙️ Instant Gratification GameRule

For players desiring absolute peace of mind, server administrators can activate total trample immunity:

```bash
/gamerule agrarian_reform:total_trample_immunity true
```

When set to `true`, farmland and crops are 100% immune to all trample destruction from any entity, regardless of armor or velocity.

---

*See also: [[GameRules|GameRules]] and [[Aesthetics & Ambient Feedback|Aesthetics-and-Ambient-Feedback]]*.
