<p align="center">
    <a href="https://modrinth.com/mod/fabric-api"><img src="https://img.shields.io/badge/Requires-Fabric_API-blue?style=for-the-badge&logo=fabric" alt="Requires Fabric API"></a>
    <a href="https://modrinth.com/mod/dasik-library"><img src="https://img.shields.io/badge/Requires-Dasik_Library-purple?style=for-the-badge" alt="Requires Dasik Library"></a>
    <img src="https://img.shields.io/badge/Language-Java_25-orange?style=for-the-badge&logo=java" alt="Java 25">
    <img src="https://img.shields.io/badge/License-GPLv3-green?style=for-the-badge" alt="License">
    <img src="https://img.shields.io/badge/Minecraft-26.2+-brightgreen?style=for-the-badge" alt="Minecraft 26.2+">
</p>

# 🌾 VO: Agrarian Reform

> **"The world should not freeze just because you walked away."**

**The Vanilla Frustration:** In vanilla Minecraft, agriculture is an artificial, proximity-based simulation. You plant a sprawling field of wheat or pumpkin patches, venture out on an epic mining trip or nether raid, and return home hours later only to find your crops completely frozen in the exact same state you left them. Worse, accidental jumps trample tilled soil into ugly dirt, water channels only reach a measly 4 blocks, and bonemealing stubborn crops like sugar cane or cactus is completely impossible.

**Agrarian Reform** transforms Minecraft farming into a persistent, living ecosystem. With **The Continuum** offline growth simulation engine, crops calculate elapsed real-world time and grow organically while chunks are unloaded. Combined with smart trample protection (wearing Leather Boots or Feather Falling prevents crop damage), realistic 8-block source hydration, polyculture biodiversity growth bonuses, universal bone meal mechanics, and right-click harvesting, farming finally feels immersive, rewarding, and seamless.

Part of the **Vanilla Outsider Collection** — mods that refine vanilla mechanics with modern engineering standards.

---

## ✨ Features

### 🕰️ The Continuum (True Offline Crop Persistence)
Never sit around waiting for chunks to tick just so your harvest matures:
- **Timestamped Chunk State**: When you travel away and chunks unload, the mod records the exact game timestamp and state of every plant.
- **Time-Delta Catch-Up**: Upon re-entering the area, the simulation calculates the exact elapsed time and advances crop stages accordingly.
- **Universal Scope**: Automatically simulates standard crops (`CropBlock`), Sugar Cane, Cactus, Nether Wart, Cocoa, Vines, Sweet Berry Bushes, and Saplings.
- **Lag-Free Throttled Execution**: Growth calculations are distributed smoothly across background catch-up ticks to guarantee zero frame drops or lag spikes even when returning to massive multi-thousand block automated farms.

### 🏷️ Data-Driven Modded Crop Tag (`#agrarianreform:continuum_plants`)
Complete compatibility for modpack and datapack creators:
- Register any modded or custom plant block to the `#agrarianreform:continuum_plants` block tag.
- Custom crops are instantly recognized by the Continuum engine and participate in offline growth simulations without writing a single line of Java code.

### 🧤 Soil Resilience (Smart Trample Logic)
Work inside your fields freely without fear of destroying hours of tilling:
- **Soft-Step Walking & Running**: Sprinting and walking across crops and farmland is 100% safe.
- **Protective Footwear**: Wearing **Leather Boots** or having the **Feather Falling** enchantment (any level) completely prevents farmland from being trampled when falling or jumping.
- **Heavy Mob Threat Preservation**: Massive raid beasts (Ravagers, Iron Golems) still crush farmland underfoot, preserving the environmental tension of village sieges.
- **Total Immunity Toggle**: Enable `agrarian_reform:total_trample_immunity` for an absolute zero-trample peaceful farming experience.

### 💧 Hydro-Dynamics (Deep Reach Irrigation)
Water flows and hydrates soil with realistic spatial dynamics:
- **8-Block Source Reach**: Still water source blocks hydrate farmland up to **8 blocks away** (4x more coverage per water source than vanilla).
- **Flowing Water Fallback**: Flowing water streams retain standard 4-block hydration distance, rewarding planned irrigation canal layouts.
- **Natural Rain Hydration**: Natural rain events instantly hydrate all exposed farmland and grant active growth spurts.
- **Permanent Hydration Option**: Toggle `agrarian_reform:always_wet_farmland` to keep all farmland hydrated regardless of water proximity.

### 🍀 Polyculture (Biodiversity Growth Bonus)
Organic farming practices are naturally rewarded over repetitive monoculture grids:
- Planting diverse adjacent crops (e.g. alternating rows or checkerboards of Wheat, Carrots, Potatoes, and Beetroots) grants a **+10% Biodiversity Growth Bonus**.
- Discourages unnatural monoculture grid spam while honoring authentic companion planting.

### 🌾 Right-Click Harvesting & Seed-to-Grass Landscaping
Streamlined field labor that eliminates tedious manual replanting:
- **Right-Click Harvest**: Right-click fully matured crops to harvest yields and instantly replant the seed in one fluid gesture. Works with vanilla crops, modded agricultural crops (Farmer's Delight, Croptopia), and Sugar Cane columns (harvests upper stalks while keeping the root base alive).
- **Seed-to-Grass Landscaping**: Right-click dirt or coarse dirt with any seed item (`#minecraft:chicken_food` tag) to sprout short grass, making pasture restoration and landscape detailing effortless.

### 🦴 Universal Bone Meal
Apply natural fertilizers to stubborn plants that vanilla ignores:
- **Sugar Cane & Cactus**: Fertilizing instantly grows the vertical column up by 1 block (up to the vanilla 3-block height limit).
- **Nether Wart & Cocoa**: Advances growth by 1 stage per application.
- **Vines**: Grows the vine downwards by 1 block, automatically matching wall attachments.
- **Fair Consumption**: Consumes exactly 1 bone meal item from your hand (creative mode bypassed) with authentic green vitality particles and audio.

### ⚡ Global Growth Multiplier
Fine-tune agricultural pacing across your entire world:
- **Proportional Scaling**: Scale crop growth speeds from `0%` (growth frozen) to `100%` (standard vanilla) up to `500%` (ultra-fast growth).
- **Integrated Continuum Math**: Offline catch-up calculations scale proportionally with the global growth multiplier setting.

### 🌿 Ambient Aesthetics & Sensory Feedback
- **Crop Rustling Audio**: Brushing through dense, tall wheat fields triggers subtle, organic foliage rustle sounds.
- **Vitality Growth Particles**: Natural growth events and bonemeal surges emit soft green vitality sparkles.

### 🧩 Compatibility & HUD Integration
- **Jade / WTHIT Tooltips**: Seamlessly inspect crop growth percentages, hydration states, and polyculture bonuses in real-time.
- **ModMenu & YACL Integration**: Optional graphical configuration screen accessible directly from the title screen or pause menu.
- **100% Server-Side Compatible**: Vanilla clients can connect to servers running Agrarian Reform without needing the mod installed locally.

---

## 📊 Mechanics & Operational Matrix

| Farming System | Trigger / Requirement | Vanilla Behavior | Agrarian Reform Behavior |
| :--- | :--- | :--- | :--- |
| **Offline Persistence** | Chunk unload / travel | Growth completely frozen | **Continuum simulation** advances growth stages upon return |
| **Walking / Sprinting** | Moving over crops | Safe | **100% Safe** with ambient foliage rustle sounds |
| **Jumping / Falling** | Leather Boots / Feather Falling | Tramples farmland into dirt | **Zero damage** (protected by footwear / enchantment) |
| **Water Source Irrigation** | Still Water block | 4 blocks max reach | **8 blocks reach** (quadruple coverage area) |
| **Flowing Water Irrigation** | Flowing water stream | 4 blocks reach | **4 blocks reach** (rewards still water canals) |
| **Crop Diversity** | Adjacent different crops | No effect | **+10% Polyculture Growth Bonus** |
| **Right-Click Harvest** | Right-click mature crop | Opens hand / punches | **Harvests yields + auto-replants in 1 click** |
| **Bone Meal on Sugar Cane** | Right-click with Bone Meal | Particle effect only (no growth) | **Grows column upward +1 block** (up to 3 max) |
| **Bone Meal on Vines** | Right-click with Bone Meal | No effect | **Grows vine downward +1 block** |
| **Seed on Dirt** | Right-click with any seed | No effect | **Sprouts short grass block** |

---

## 🛠️ In-Game Configuration & Controls

All agricultural settings can be customized in-game via the **Edit Game Rules** screen or using standard Brigadier `/gamerule` commands. If [ModMenu](https://modrinth.com/mod/modmenu) and [YetAnotherConfigLib (YACL)](https://modrinth.com/mod/yacl) are installed, a dedicated visual settings GUI is available in the ModMenu list.

```sql
/gamerule agrarian_reform:hydration_source_range <blocks>     → Set still water hydration radius (Default: 8)
/gamerule agrarian_reform:hydration_flowing_range <blocks>    → Set flowing water hydration radius (Default: 4)
/gamerule agrarian_reform:growth_biodiversity_bonus <true|false> → Toggle +10% companion crop boost (Default: true)
/gamerule agrarian_reform:total_trample_immunity <true|false>  → Toggle absolute trample immunity (Default: false)
/gamerule agrarian_reform:always_wet_farmland <true|false>     → Force farmland to stay wet everywhere (Default: false)
/gamerule agrarian_reform:seeds_grow_grass <true|false>        → Toggle seed-to-grass landscaping (Default: true)
/gamerule agrarian_reform:right_click_harvest <true|false>     → Toggle 1-click harvest and replant (Default: true)
/gamerule agrarian_reform:universal_bonemeal <true|false>      → Toggle bone meal on cane/cactus/vines (Default: true)
/gamerule agrarian_reform:global_growth_multiplier <0-500>     → Set global growth speed (Default: 100)
```

---

## ⚙️ Native GameRules Reference

> [!IMPORTANT]
> **💡 Config vs. In-Game GameRules:** The global configuration file (`config/agrarian-reform.json`) only defines default template values for newly created worlds. In existing worlds, adjust settings in-game via the **Edit Game Rules** UI screen or the `/gamerule` command.

| GameRule Identifier | Type | Default | Description |
| :--- | :--- | :--- | :--- |
| `agrarian_reform:hydration_source_range` | Integer | `8` | Distance in blocks that still water source blocks hydrate surrounding farmland. |
| `agrarian_reform:hydration_flowing_range` | Integer | `4` | Distance in blocks that flowing water streams hydrate surrounding farmland. |
| `agrarian_reform:rain_growth_acceleration` | Integer | `1` | Growth stages advanced during natural rainfall events. |
| `agrarian_reform:growth_biodiversity_bonus` | Boolean | `true` | Enables +10% growth speed bonus when crops are planted next to different species. |
| `agrarian_reform:ambient_crop_rustle` | Boolean | `true` | Plays ambient foliage rustling sound effects when walking through crops. |
| `agrarian_reform:ambient_vitality_particles` | Boolean | `true` | Emits subtle green vitality sparkle particles upon successful crop growth events. |
| `agrarian_reform:total_trample_immunity` | Boolean | `false` | When enabled, disables farmland trampling entirely for all entities. |
| `agrarian_reform:always_wet_farmland` | Boolean | `false` | When enabled, keeps all farmland permanently hydrated regardless of water proximity. |
| `agrarian_reform:seeds_grow_grass` | Boolean | `true` | Allows players to right-click dirt with any seed item to sprout short grass. |
| `agrarian_reform:right_click_harvest` | Boolean | `true` | Enables right-clicking fully grown crops to harvest drops and automatically replant. |
| `agrarian_reform:universal_bonemeal` | Boolean | `true` | Enables using bone meal on Sugar Cane, Cactus, Nether Wart, Cocoa, and Vines. |
| `agrarian_reform:global_growth_multiplier` | Integer | `100` | Global crop growth speed scaling percentage (`0` = frozen, `100` = 1x vanilla, `200` = 2x). |

---

## 📖 In-Depth How-To & Gameplay Playbook

### 1. Laying Out High-Efficiency Farmland
* **8-Block Irrigation Grid**: Place a single water source block in the center of a $17 \times 17$ plot. Because source water now reaches 8 blocks in all cardinal directions, one water block hydrates an entire field of **288 farmland blocks**.
* **Polyculture Planting**: Plant alternating stripes or checkerboards of Wheat, Carrots, Potatoes, and Beetroots. Adjacent different crop types trigger the **Biodiversity Bonus**, speeding up growth cycles by +10%.

### 2. Protective Footwear & Field Navigation
* **Daily Field Work**: Sprint and jump freely across your fields by equipping **Leather Boots** or any footwear enchanted with **Feather Falling** (Level I–IV). Your farmland will never degrade into dirt.
* **Livestock Grazing**: Use spare wheat, beetroot, or melon seeds on dirt paths to quickly restore lush green grass for sheep pens and pasture aesthetics.

### 3. Vertical Harvesting & Bone Meal Expansion
* **Right-Click Gathering**: Walk through mature fields right-clicking crops. Drops are deposited directly into your inventory while seed bases remain planted.
* **Sugar Cane & Cactus Columns**: Aim bone meal at the bottom or middle stalk of a Sugar Cane or Cactus column to instantly grow another stalk on top.
* **Vine Farming**: Bone meal hanging vines to force them to extend downward without climbing ladders.

### 4. Exploring the World with The Continuum
* **Embarking on Expeditions**: Plant your fields and set off on long mining expeditions or nether fortress runs. There is no need to stay AFK near your base.
* **Returning Home**: Upon returning to your homestead chunk, the Continuum engine calculates elapsed world time and catches up all crops to their mature stages.

### 5. Datapack & Modded Crop Registration
* To add custom crops from other mods to the Continuum offline engine, create a datapack containing `data/agrarianreform/tags/block/continuum_plants.json`:
```json
{
  "replace": false,
  "values": [
    "farmersdelight:cabbages",
    "farmersdelight:tomatoes",
    "farmersdelight:onions",
    "croptopia:artichoke_crop"
  ]
}
```

---

## ☕ Support

If you enjoy **Agrarian Reform** and the **Vanilla Outsider** philosophy, consider fueling the next update!

<p align="center">
    <a href="https://ko-fi.com/dasikigaijin/tip"><img src="https://img.shields.io/badge/Ko--fi-Support%20Me-FF5E5B?style=for-the-badge&logo=ko-fi&logoColor=white" alt="Ko-fi"></a>
    <a href="https://sociabuzz.com/dasikigaijin/tribe"><img src="https://img.shields.io/badge/SocioBuzz-Local_Support-7BB32E?style=for-the-badge" alt="SocioBuzz"></a>
    <a href="https://saweria.co/DasikIgaijinn"><img src="https://img.shields.io/badge/Saweria-Local_Support-FFA500?style=for-the-badge" alt="Saweria"></a>
</p>

> [!NOTE]
> **🇮🇩 Indonesian Users:** SocioBuzz and Saweria support local payment methods (Gopay, OVO, Dana, etc.) if you want to support me without using PayPal/Ko-fi!

---

## 📜 Credits & Permissions

| Role | Author |
| :--- | :--- |
| **Creator** | **Dasik** (Rifaditya) |
| **Collection** | Vanilla Outsider |
| **License** | GNU GPLv3 |

> [!IMPORTANT]
> **📦 Modpack Permissions & Distribution:**<br>
> You are free to include this mod in any modpack on any platform. However, the mod itself must be downloaded from its official distribution pages on **Modrinth** or **CurseForge**. Re-uploading or redistributing official JAR files to third-party sites is strictly prohibited unless explicitly permitted by the creator.
> <br><br>
> **License & Forks:**<br>
> Since the source code is licensed under **GNU GPLv3**, you are fully permitted to fork the repository, make modifications, build your own versions, and distribute them under the terms of the GPLv3.

---

<div align="center">

**Made with ❤️ for the Minecraft community**

*Part of the Vanilla Outsider Collection*

</div>
