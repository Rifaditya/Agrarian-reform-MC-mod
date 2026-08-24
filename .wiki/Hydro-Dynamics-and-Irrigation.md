# 💧 Hydro-Dynamics & Advanced Irrigation

**Agrarian Reform** overhauls Minecraft's binary water hydration system into realistic **Hydro-Dynamics**, rewarding thoughtful canal architecture and leveraging natural rain precipitation to nourish crops.

---

## 📊 Irrigation Infobox

| Property | Default Value | GameRule Key |
| :--- | :--- | :--- |
| **Water Source Radius** | 8 Blocks | `agrarian_reform:hydration_source_range` |
| **Flowing Water Radius**| 4 Blocks | `agrarian_reform:hydration_flowing_range` |
| **Rain Growth Spurt** | +1 Stage / Rain Tick | `agrarian_reform:rain_growth_acceleration` |
| **Always Wet Override** | `false` | `agrarian_reform:always_wet_farmland` |

---

## 🌊 Water State & Capillary Hydration

In vanilla Minecraft, all water hydrates farmland within a static 4-block square. Agrarian Reform differentiates between still water sources and flowing water:

### 1. Water Source Blocks (8-Block Radius)
Still water source blocks (`minecraft:water`) provide deep capillary irrigation, hydrating farmland up to an **8-block horizontal radius** in all directions ($17 \times 17$ farmland grid per single water source block).

### 2. Flowing Water (4-Block Radius)
Flowing water currents maintain a standard **4-block horizontal radius**, encouraging players to build dedicated main reservoirs, aqueducts, and irrigation ditches rather than relying on endless flowing streams.

```
       FLOWING WATER CANAL (4-Blk Radius)              WATER SOURCE RESERVOIR (8-Blk Radius)
     ┌───┬───┬───┬───┬───┬───┬───┐              ┌───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┐
     │ F │ F │ F │ W │ F │ F │ F │              │ F │ F │ F │ F │ F │ F │ F │ F │ F │ F │ F │
     └───┴───┴───┴───┴───┴───┴───┘              ├───┼───┼───┼───┼───┼───┼───┼───┼───┼───┼───┤
     W = Flowing Water (Hydrates 4 Blks)        │ F │ F │ F │ F │ S │ F │ F │ F │ F │ F │ F │
     F = Hydrated Farmland                      └───┴───┴───┴───┴───┴───┴───┴───┴───┴───┴───┘
                                                S = Water Source Block (Hydrates 8 Blks)
```

---

## 🌧️ Pluviophile Rain Hydration & Acceleration

Rain is no longer just a visual storm background—it actively accelerates crop maturity:

1. **Global Rain Exposure**: During rain, any sky-exposed farmland acts as fully hydrated regardless of water proximity.
2. **Rain Growth Spurts**: When random ticks strike sky-exposed crops during rain, the crop advances **+1 extra growth stage** instantly (`rain_growth_acceleration`), accompanied by **Happy Villager** green sparkles.

---

## ⚙️ GameRule Overrides

Server admins can customize irrigation distance or force farmland moisture:
* Adjust water source range: `/gamerule agrarian_reform:hydration_source_range 12`
* Force all farmland to stay wet: `/gamerule agrarian_reform:always_wet_farmland true`

---

*See also: [[Polyculture & Biodiversity|Polyculture-and-Biodiversity]] and [[GameRules|GameRules]]*.
