# ⚖️ Referência completa de regras de jogo GameRules

All configuration parameters in **Agrarian Reform** are registered as native Minecraft GameRules under the namespaced category **Agrarian Reform** (`agrarian_reform:*`), managed via DasikLibrary's `DynamicGameRuleManager`. World-specific settings are permanently saved within each world's `level.dat` and can be edited in-game or through server commands.

---

## 📊 Tabela de referência de GameRules estáticas

| GameRule Key | Type | Default | Valid Range | Sided Execution | Description |
| :--- | :--- | :--- | :--- | :--- | :--- |
| `agrarian_reform:hydration_source_range` | Integer | `8` | `0` to `32` | Server & Client | Radius (in blocks) a still water source block hydrates farmland via Chebyshev rings. |
| `agrarian_reform:hydration_flowing_range` | Integer | `4` | `0` to `32` | Server & Client | Radius (in blocks) flowing water provides capillary hydration to farmland. |
| `agrarian_reform:pure_water_hydration_only` | Boolean | `false` | `true` / `false` | Server & Client | When enabled, waterlogged blocks and fluid cauldrons are ignored; only pure water blocks hydrate farmland. |
| `agrarian_reform:rain_growth_acceleration` | Integer | `1` | `0` to `10` | Server | Extra growth stages awarded to sky-exposed crops during rain storms per random tick. |
| `agrarian_reform:growth_biodiversity_bonus`| Boolean | `true` | `true` / `false` | Server | Grants a +10% growth probability bonus when different crop types are planted adjacently. |
| `agrarian_reform:total_trample_immunity` | Boolean | `false` | `true` / `false` | Server | Grants 100% immunity against farmland trample destruction from falling/jumping entities. |
| `agrarian_reform:trample_immunity_players_only` | Boolean | `false` | `true` / `false` | Server | When enabled alongside Total Trample Immunity, only players and tamed pets receive protection; hostile/wild mobs still trample soil. |
| `agrarian_reform:always_wet_farmland` | Boolean | `false` | `true` / `false` | Server & Client | Forces all farmland blocks to maintain moisture level 7 permanently without water proximity. |
| `agrarian_reform:seeds_grow_grass` | Boolean | `true` | `true` / `false` | Server & Client | Right-clicking a dirt block with agricultural seeds consumes the seed and converts the block to grass. |
| `agrarian_reform:right_click_harvest` | Boolean | `true` | `true` / `false` | Server & Client | Right-clicking a mature crop harvests its drops and automatically replants it at age 0 with main-hand debouncing. |
| `agrarian_reform:universal_bonemeal` | Boolean | `true` | `true` / `false` | Server & Client | Accelerates growth on non-natively bonemealable plants (Cactus, Sugar Cane, Nether Wart, Vines). |
| `agrarian_reform:global_growth_multiplier` | Integer | `100` | `0` to `2147483647` | Server | Global speed multiplier percentage for all crop random ticks and Continuum offline growth (0 = off, 100 = 1x vanilla). |
| `agrarian_reform:ambient_crop_rustle` | Boolean | `true` | `true` / `false` | Client | Plays ambient rustling audio feedback when moving through crops or upon growth stage progression. |
| `agrarian_reform:ambient_vitality_particles`| Boolean | `true` | `true` / `false` | Client | Emits subtle Happy Villager vitality sparkle particles upon successful growth stage transitions. |
| `agrarian_reform:debug_mode` | Boolean | `false` | `true` / `false` | Server | Enables verbose diagnostic logging in server logs for crop ticks and continuum catch-up. **Transient: resets to `false` on restart.** |

---

## 🌾 GameRules dinâmicas de multiplicador por cultura

Whenever a new modded or vanilla crop is discovered in the world or registered via data packs, **Agrarian Reform** dynamically registers a dedicated GameRule formatted as:

$$\text{GameRule Key} = \text{agrarian\_reform:growth\_}\langle \text{namespace} \rangle\text{\_}\langle \text{path} \rangle$$

### Resolution Hierarchy:
1. **Specific Crop Override ($> 0$)**: If set to a positive integer (e.g. `200`), the crop grows at that exact rate (200% = 2x speed), bypassing the global multiplier.
2. **Frozen / Disabled State ($-1$)**: If set to `-1`, growth ticks for that specific crop are completely frozen (0% speed), producing zero extra ticks and zero Continuum catch-up delta.
3. **Global Multiplier Fallback ($0$)**: If set to `0` (default), the crop automatically inherits the global `agrarian_reform:global_growth_multiplier`.

```bash
# Set wheat to grow at 3x speed
/gamerule agrarian_reform:growth_minecraft_wheat 300

# Freeze sugar cane growth completely
/gamerule agrarian_reform:growth_minecraft_sugar_cane -1

# Reset carrots to inherit the global multiplier
/gamerule agrarian_reform:growth_minecraft_carrots 0
```

### ⚡ Mid-Game Dynamic Registration (Zero Restart Required)
* **On-Demand Instantiation**: When a player explores a new area, encounters a newly loaded modded chunk, or plants a modded seed mid-game, `AgrarianCropRules` detects the crop and instantiates its `GameRule` immediately on-the-fly.
* **Instant Command Availability**: The new GameRule is instantly queryable and editable via `/gamerule` with full tab-completion without requiring a server reboot or game restart.
* **Datapack Reload Parity**: Executing `/reload` mid-game triggers `DynamicRegistryScanner` to re-scan `BuiltInRegistries.BLOCK` and register any newly added datapack crop tags automatically.

### 🛡️ Mod Removal & Zero-Crash Dormancy Lifecycle
* **100% Crash-Proof Decoupling**: Dynamic GameRules are identified purely using standard Strings and Vanilla `Identifier` keys (`"farmersdelight:tomatoes"`), with zero hardcoded class dependencies.
* **Dormant / Passive State**: If a mod is uninstalled, Minecraft removes its in-world blocks. The corresponding GameRule becomes completely **dormant (idle)**—it executes zero queries, consumes zero CPU cycles, and will **never crash the game or corrupt chunk data**.
* **Seamless Re-Installation Recovery**: If the player re-installs the mod later, the saved GameRule multiplier in `level.dat` and `config/agrarian-reform.json` is instantly recognized and restored.

---

## 🛠️ Exemplos de comandos de administração

```bash
# Query current water source range
/gamerule agrarian_reform:hydration_source_range

# Set water source range to 12 blocks
/gamerule agrarian_reform:hydration_source_range 12

# Enable player-only trample immunity
/gamerule agrarian_reform:total_trample_immunity true
/gamerule agrarian_reform:trample_immunity_players_only true

# Temporarily enable diagnostic debug logging
/gamerule agrarian_reform:debug_mode true
```

---

*See also: [[Comandos Brigadier e administração|pt_br-Commands]], [[Configuração em dois níveis|pt_br-Configuration]], and [[Registro de plantas e culturas universais|pt_br-Plant-Registry-and-Crop-Types]]*.
