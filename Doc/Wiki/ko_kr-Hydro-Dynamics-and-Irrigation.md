# 💧 유체역학 및 고급 관개 시스템

**Agrarian Reform** overhauls Minecraft's binary water hydration system into realistic **Hydro-Dynamics**, rewarding thoughtful canal architecture, expanding concentric hydration shells, and leveraging natural rain precipitation to nourish crops.

---

## 📊 관개 인포박스

| Property | Default Value | GameRule Key |
| :--- | :--- | :--- |
| **Water Source Radius** | 8 Blocks | `agrarian_reform:hydration_source_range` |
| **Flowing Water Radius**| 4 Blocks | `agrarian_reform:hydration_flowing_range` |
| **Pure Water Gating** | `false` | `agrarian_reform:pure_water_hydration_only` |
| **Rain Growth Spurt** | +1 Stage / Rain Tick | `agrarian_reform:rain_growth_acceleration` |
| **Always Wet Override** | `false` | `agrarian_reform:always_wet_farmland` |

---

## 🌊 체비쇼프 동심 정사각형 관개 고리

$$\text{Chebyshev Distance: } r = \max(|dx|, |dz|)$$
$$\text{Block Count per Shell: } \text{Shell}(r) = 8r$$

Because the search evaluates the closest concentric shells first, water sources immediately adjacent to farmland fast-fail within $1\text{ block}$ ($r=1$), executing in $\sim 0.0001\mu\text{s}$ before traversing outer rings.

### 1. Water Source Blocks (8-Block Radius)
Still water source blocks (`minecraft:water`) provide deep capillary irrigation, hydrating farmland up to an **8-block horizontal radius** in all directions ($17 \times 17$ farmland grid per single water source block).

### 2. Flowing Water (4-Block Radius)
Flowing water currents maintain a standard **4-block horizontal radius**, encouraging players to build dedicated main reservoirs, aqueducts, and irrigation ditches rather than relying on endless flowing streams.

### 3. Pure Water Hydration Gating
When `agrarian_reform:pure_water_hydration_only` is enabled (`true`), waterlogged blocks (leaves, slabs, stairs) and fluid cauldrons are ignored, requiring pure water fluid blocks for farmland hydration.

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

## 🌧️ 강우 수분 공급 및 성장 가속

1. **Global Rain Exposure**: During rain, any sky-exposed farmland acts as fully hydrated regardless of water proximity.
2. **Rain Growth Spurts**: When random ticks strike sky-exposed crops during rain, the crop advances **+1 extra growth stage** instantly (`rain_growth_acceleration`), accompanied by **Happy Villager** green sparkles.

---

## ⚙ GameRule 설정 재정의

* Adjust water source range: `/gamerule agrarian_reform:hydration_source_range 12`
* Require pure water only: `/gamerule agrarian_reform:pure_water_hydration_only true`
* Force all farmland to stay wet: `/gamerule agrarian_reform:always_wet_farmland true`

---

*See also: [[다모작 및 생물다양성 보너스|ko_kr-Polyculture-and-Biodiversity]] and [[게임 규칙 (GameRules) 레퍼런스|ko_kr-GameRules]]*.
