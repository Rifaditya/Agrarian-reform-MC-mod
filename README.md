<div align="center">

# 🌾 VO: Agrarian Reform: The Living Earth

> **"The world should not freeze just because you walked away."**

[![Requires Fabric API](https://img.shields.io/badge/Requires-Fabric_API-blue?style=for-the-badge&logo=fabric)](https://modrinth.com/mod/fabric-api)
[![Requires Dasik Library](https://img.shields.io/badge/Requires-Dasik_Library-purple?style=for-the-badge)](https://modrinth.com/mod/dasik-library)
![Language Java 25](https://img.shields.io/badge/Language-Java_25-orange?style=for-the-badge&logo=java)
![License GPLv3](https://img.shields.io/badge/License-GPLv3-green?style=for-the-badge)
![Minecraft 26.2+](https://img.shields.io/badge/Minecraft-26.2+-brightgreen?style=for-the-badge)

</div>

**Agrarian Reform** is a pastoral immersion mod that transforms Minecraft's agriculture from a proximity-based machine into a persistent, living ecosystem. It ensures that your hard work persists and grows even when you aren't there to witness it, while adding depth to soil management, irrigation, and biodiversity.

Part of the **Vanilla Outsider Collection** — mods that refine vanilla mechanics with modern engineering standards.

---

## 🌾 Core Mechanics

### 1. 🕰️ The Continuum (True Offline Crop Persistence)
In vanilla Minecraft, crops only grow in loaded chunks. **Agrarian Reform** introduces **The Continuum**, a high-precision simulation engine that bridges the gap between gameplay sessions.
- **Persistent Growth**: When a chunk is unloaded, the mod saves the exact timestamp and state of all crops and plants.
- **Universal Scope**: Automatically simulates standard crops (`CropBlock`), Sugar Cane, Cactus, Nether Wart, Cocoa, Vines, Sweet Berry Bushes, and Saplings.
- **Time-Delta Simulation**: Upon your return, the mod calculates elapsed real-world time (scaled dynamically with the global growth multiplier) and simulates growth stages accordingly.
- **Throttled Updates**: To prevent lag spikes upon loading massive multi-thousand-block farms, updates are applied via a smooth, throttled background queue.

### 2. 🏷️ Data-Driven Custom Crops (`#agrarianreform:continuum_plants`)
Modpack and datapack creators can register custom or modded plant blocks to `#agrarianreform:continuum_plants` (`data/agrarianreform/tags/block/continuum_plants.json`) to participate in the Continuum offline growth engine without writing code.

### 3. 🧤 Soil Resilience (Smart Trample Logic)
Farmland is treated as a nurtured resource rather than fragile glass.
- **Soft-Step Movement**: Sprinting and walking across crops and farmland is 100% safe.
- **Protective Footwear**: Wearing **Leather Boots** or having the **Feather Falling** enchantment (any level) completely prevents farmland from being trampled when falling or jumping.
- **Heavy Mob Threat**: Large beasts (Ravagers, Iron Golems) still crush farmland underfoot, preserving the tension of village raids.
- **Total Immunity Toggle**: Enable `agrarian_reform:total_trample_immunity` for an absolute zero-trample peaceful farming experience.

### 4. 💧 Hydro-Dynamics (Advanced Irrigation)
Water reach is influenced by its state and spatial layout.
- **8-Block Source Reach**: Water **source blocks** irrigate a massive **8-block radius** (4x more coverage per water source than vanilla).
- **Flowing Water**: Flowing water streams maintain the standard **4-block radius**, encouraging the construction of proper irrigation canals and wells.
- **Rainfall Hydration**: During natural rain, all sky-exposed farmland acts as hydrated and receives active growth spurts.
- **Always Wet Option**: Toggle `agrarian_reform:always_wet_farmland` to keep all farmland permanently hydrated regardless of water proximity.

### 5. 🍀 Polyculture (Biodiversity Bonus)
Organic farming practices are naturally rewarded over repetitive monoculture grids.
- **Biodiversity Bonus**: Crops planted adjacent to *different* species (e.g., Wheat adjacent to Carrots) receive a **+10% growth speed boost**.
- **Aesthetic Reward**: Incentivizes vibrant, natural farm designs over industrialized monoculture grids.

### 6. 🌾 Right-Click Harvesting & Seed-to-Grass Landscaping
- **Right-Click Harvest**: Right-click fully matured crops to harvest yields and instantly replant the seed in one fluid action. Supports vanilla crops, modded crops (Farmer's Delight, Croptopia), and Sugar Cane columns (harvests upper stalks while keeping the root base alive).
- **Seed-to-Grass**: Right-click dirt or coarse dirt with any seed item (`#minecraft:chicken_food` tag) to sprout short grass, making pasture restoration effortless.

### 7. 🦴 Universal Bone Meal
- **Sugar Cane & Cactus**: Fertilizing instantly grows the vertical column up by 1 block (up to 3 blocks max).
- **Nether Wart & Cocoa**: Advances growth by 1 stage per application.
- **Vines**: Grows the vine downward by 1 block, matching wall attachments.
- **Fair Consumption**: Consumes exactly 1 bone meal item from hand (creative mode bypassed) with authentic particles and sounds.

### 8. ⚡ Global Growth Multiplier
- Scale crop growth speeds globally from `0%` (frozen) to `100%` (standard vanilla) up to `500%` (ultra-fast growth).
- Offline Continuum catch-up calculations automatically scale proportionally with the multiplier.

---

## 🎨 Aesthetics & Sensory Feedback
- **Crop Rustling**: Walking through fully grown crops produces subtle foliage rustling sounds with per-entity cooldowns.
- **Vitality Particles**: Successful growth stages emit soft green vitality sparkles.
- **HUD & Tooltip Support**: Full compatibility with **Jade** and **WTHIT** to inspect growth stages, hydration, and polyculture bonuses.
- **GUI Settings**: In-game visual configuration via **ModMenu** and **YetAnotherConfigLib (YACL)**.

---

## ⚙️ Configuration (GameRules Reference)

All settings are customized in-game via the **Edit Game Rules** UI screen or standard Brigadier `/gamerule` commands:

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

## 🛠️ Technical Specifications
- **Target**: Minecraft 26.2+ (Forward-Compatible)
- **Mod Version**: `2.2.4+26.2`
- **Loader**: Fabric Loader `>=0.16.9`
- **Dependencies**: Fabric API, DasikLibrary `>=1.8.2`
- **Java**: Version 25 (Strict)
- **Environment**: Client & Server (100% server-side compatible with vanilla clients)

---

## ☕ Support

If you enjoy **Agrarian Reform** and the **Vanilla Outsider** philosophy, consider fueling the next update!

<p align="center">
    <a href="https://ko-fi.com/dasikigaijin/tip"><img src="https://img.shields.io/badge/Ko--fi-Support%20Me-FF5E5B?style=for-the-badge&logo=ko-fi&logoColor=white" alt="Ko-fi"></a>
    <a href="https://sociabuzz.com/dasikigaijin/tribe"><img src="https://img.shields.io/badge/SocioBuzz-Local_Support-7BB32E?style=for-the-badge" alt="SocioBuzz"></a>
    <a href="https://saweria.co/DasikIgaijinn"><img src="https://img.shields.io/badge/Saweria-Local_Support-FFA500?style=for-the-badge" alt="Saweria"></a>
</p>

---

## 📜 Credits & License

| Role | Author |
| :--- | :--- |
| **Creator** | **Dasik** (Rifaditya) |
| **Collection** | Vanilla Outsider |
| **License** | GNU GPLv3 |

Developed as part of the **Vanilla Outsider Collection**. Licensed under the **GNU General Public License v3.0**.
