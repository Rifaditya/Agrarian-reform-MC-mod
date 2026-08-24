# ⚖️ Namespaced GameRules Reference

All configuration options in **Agrarian Reform** are registered as native Minecraft GameRules under the namespaced category **Agrarian Reform** (`agrarian_reform:*`), managed via DasikLibrary's `DynamicGameRuleManager`.

---

## 📊 Complete GameRules Reference Table

| GameRule Key | Type | Default | Description |
| :--- | :--- | :--- | :--- |
| `agrarian_reform:hydration_source_range` | Integer | `8` | Distance (in blocks) a water source block provides hydration. |
| `agrarian_reform:hydration_flowing_range` | Integer | `4` | Distance (in blocks) flowing water provides hydration. |
| `agrarian_reform:rain_growth_acceleration` | Integer | `1` | Extra growth stages awarded to sky-exposed crops during rain. |
| `agrarian_reform:growth_biodiversity_bonus`| Boolean | `true` | Enables +10% growth speed bonus for mixed adjacent crops. |
| `agrarian_reform:ambient_crop_rustle` | Boolean | `true` | Enables rustle sound effects when walking through crops. |
| `agrarian_reform:ambient_vitality_particles`| Boolean | `true` | Enables Happy Villager sparkles on growth events. |
| `agrarian_reform:total_trample_immunity` | Boolean | `false` | Grants 100% immunity to all farmland trampling. |
| `agrarian_reform:always_wet_farmland` | Boolean | `false` | Forces farmland to stay hydrated regardless of water proximity. |
| `agrarian_reform:seeds_grow_grass` | Boolean | `true` | Allows right-clicking dirt with seeds to grow grass blocks. |
| `agrarian_reform:right_click_harvest` | Boolean | `true` | Enables right-click harvesting and automatic replanting. |
| `agrarian_reform:universal_bonemeal` | Boolean | `true` | Allows bone meal on non-natively bonemealable plants. |
| `agrarian_reform:global_growth_multiplier` | Integer | `100` | Global plant growth speed percentage (0 = off, 100 = default). |

---

## 🛠️ In-Game Administration Commands

```bash
# Query current water source range
/gamerule agrarian_reform:hydration_source_range

# Set water source range to 12 blocks
/gamerule agrarian_reform:hydration_source_range 12

# Enable total trample immunity
/gamerule agrarian_reform:total_trample_immunity true
```

---

*See also: [[Commands|Commands]] and [[Configuration|Configuration]]*.
