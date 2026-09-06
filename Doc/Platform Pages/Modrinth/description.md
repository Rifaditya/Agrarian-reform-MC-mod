<p align="center">
  <a href="https://modrinth.com/mod/fabric-api"><img src="https://img.shields.io/badge/Requires-Fabric_API-blue?style=for-the-badge&logo=fabric" alt="Requires Fabric API"></a>
  <a href="https://modrinth.com/mod/dasik-library"><img src="https://img.shields.io/badge/Requires-Dasik_Library-8A2BE2?style=for-the-badge" alt="Requires Dasik Library"></a>
  <img src="https://img.shields.io/badge/Language-Java_25-orange?style=for-the-badge&logo=java" alt="Java 25">
  <img src="https://img.shields.io/badge/License-GPLv3-green?style=for-the-badge" alt="License GPLv3">
  <img src="https://img.shields.io/badge/Minecraft-26.2+-brightgreen?style=for-the-badge" alt="Minecraft 26.2+">
</p>

# 🌾 Agrarian Reform

> **"The world should not freeze just because you walked away."**

> [!NOTE]
> **1 Jar 1 Version Policy:** I build **1 dedicated JAR for each Minecraft version** (e.g. MC 26.2, MC 26.3). Please download the exact build that matches your Minecraft installation.
> <br><br>
> **Dependency Requirement:** For modern Minecraft 26.x releases (26.1.2, 26.2, 26.3+), this mod requires both **Fabric API** and **Dasik Library** (`v1.8.3+`). Legacy builds (1.20.1, 1.21.x) are self-contained and only require Fabric API.

In vanilla Minecraft, crop farming is a fragile, proximity-based simulation. You till farmland, sow sprawling fields of wheat or vegetables, venture off on an epic mining expedition or Nether trek, and return home hours later only to find your crops completely frozen in the exact same growth stage you left them. Worse, an accidental hop instantly tramples tilled soil into coarse dirt, water canals only reach 4 blocks away, and stubborn crops like sugar cane or cactus completely ignore bone meal.

**Agrarian Reform** transforms agriculture into an organic, persistent ecosystem. Powered by **The Continuum** offline growth simulation engine, crops calculate elapsed game time and grow organically while chunks are unloaded. Combined with smart trample protection (Leather Boots and Feather Falling save farmland), deep-reach 8-block source irrigation, companion polyculture growth bonuses, universal bone meal, and right-click harvesting, farming finally feels immersive, rewarding, and seamless.

Part of the **Vanilla Outsider Collection** — mods that refine vanilla mechanics with modern engineering standards.

---

## ✨ Features

### 🕰️ The Continuum: True Offline Crop Persistence
Never sit around waiting for chunks to tick just so your harvest matures:
- **Timestamped Chunk State**: When you travel away and chunks unload, the mod records the exact game timestamp of the chunk.
- **Time-Delta Catch-Up**: Upon re-entering the area, the simulation calculates the elapsed game time and advances plant growth stages accordingly.
- **Universal Plant Scope**: Automatically simulates standard crops (`CropBlock`), Sugar Cane, Cactus, Nether Wart, Cocoa, Vines, Sweet Berry Bushes, and Saplings.
- **Throttled Lag-Free Execution**: Growth calculations are processed smoothly at 5 crop blocks per tick globally, guaranteeing zero frame stutter or TPS spikes even when returning to massive multi-thousand block farms.

### 🧤 Soil Resilience: Smart Trample Protection
Tend your fields freely without fear of ruining hours of careful tilling:
- **Soft-Step Safe Movement**: Walking, running, and sprinting across crops and farmland is 100% safe.
- **Protective Footwear**: Wearing **Leather Boots** (`#c:boots/soft`, `#agrarian_reform:soft_step_boots`) or any level of the **Feather Falling** enchantment completely prevents farmland from being trampled when jumping or falling.
- **Heavy Mob Threat Preservation**: Massive raid beasts (Ravagers, Iron Golems) still crush farmland underfoot, preserving the environmental tension of village sieges.
- **Total Immunity Toggles**: Enable `agrarian_reform:total_trample_immunity` for an absolute zero-trample experience, or `trample_immunity_players_only` to restrict immunity to players and tamed pets.

### 💧 Hydro-Dynamics: Deep Irrigation & Rain Spurts
Water hydrates soil with realistic spatial reach:
- **8-Block Source Reach**: Still water source blocks hydrate farmland up to **8 blocks away** (quadrupling the hydration surface area compared to vanilla's 4 blocks).
- **Flowing Water Canal Fallback**: Flowing water streams retain standard 4-block hydration distance, rewarding dedicated still-water irrigation canals.
- **Natural Rain Hydration**: Natural rainfall hydrates exposed open-sky farmland and grants active growth spurts with happy green vitality particles.
- **Permanent Wetness Option**: Toggle `agrarian_reform:always_wet_farmland` to keep all farmland permanently hydrated regardless of water proximity.

### 🍀 Polyculture: Biodiversity Growth Bonus
Organic farming practices are naturally rewarded over sterile monoculture grids:
- Planting diverse adjacent crops (e.g. alternating rows or checkerboards of Wheat, Carrots, Potatoes, and Beetroots) grants an automatic **+10% Biodiversity Growth Bonus**.
- Discourages unnatural monoculture grid spam while honoring authentic companion planting.

### 🌾 Right-Click Harvesting & Seed-to-Grass Detailing
Streamlined field labor that eliminates tedious manual replanting:
- **Right-Click Harvest**: Right-click fully matured crops to harvest yields and instantly replant the seed in one fluid gesture. Works seamlessly with vanilla crops, cocoa, nether wart, and sugar cane columns (harvests upper stalks while keeping the root base intact).
- **Seed-to-Grass Landscaping**: Right-click dirt or coarse dirt with any seed item (`#minecraft:chicken_food` tag) to sprout a vibrant grass block, making pasture restoration effortless.

### 🦴 Universal Bone Meal
Apply natural fertilizers to stubborn plants that vanilla ignores:
- **Sugar Cane & Cactus**: Fertilizing instantly grows the vertical column up by 1 block (up to the vanilla 3-block height ceiling).
- **Nether Wart & Cocoa**: Advances growth by 1 stage per application.
- **Vines**: Grows the vine downwards by 1 block, automatically matching wall attachments.
- **Fair Consumption**: Consumes exactly 1 bone meal item from your hand (creative mode bypassed) accompanied by authentic green vitality particles and audio cues.

### ⚡ Global Growth Tuning
Fine-tune agricultural pacing across your entire world:
- **Proportional Scaling**: Scale crop growth speeds from `0%` (growth frozen) to `100%` (standard vanilla) up to `500%` (ultra-fast growth) via `agrarian_reform:global_growth_multiplier`.
- **Integrated Continuum Math**: Offline catch-up calculations scale proportionally with the global growth multiplier setting.

### 🌿 Ambient Sensory Feedback
- **Crop Rustling Audio**: Brushing through dense, mature wheat fields triggers subtle, organic foliage rustle sounds.
- **Vitality Growth Particles**: Natural growth events and bonemeal surges emit soft green vitality sparkles.

### 🧩 Compatibility & HUD Integration
- **Jade / WTHIT Tooltips**: Seamlessly inspect crop growth percentages, hydration states, and polyculture bonuses in real-time.
- **ModMenu & YACL Integration**: Optional graphical configuration screen accessible directly from the title screen or pause menu.
- **100% Server-Side Compatible**: Vanilla clients can connect to servers running Agrarian Reform without needing any client-side mod installed.

---

## 📊 Mechanics & Operational Matrix

| Farming Mechanic | Vanilla Minecraft | Agrarian Reform (Modern 26.2+) |
| :--- | :---: | :---: |
| **Offline Persistence** | Growth completely frozen in unloaded chunks | **The Continuum engine** simulates and catches up growth on chunk load |
| **Walking / Sprinting** | Safe | **100% Safe** with ambient foliage rustle sounds |
| **Jumping / Falling** | Tramples farmland into coarse dirt | **Zero damage** when wearing Leather Boots or Feather Falling |
| **Water Source Reach** | 4 blocks max reach | **8 blocks reach** (4x more coverage per water source) |
| **Flowing Water Reach** | 4 blocks reach | **4 blocks reach** (rewards still water canals) |
| **Crop Intercropping** | No effect | **+10% Polyculture Growth Bonus** for adjacent diverse crops |
| **Harvesting Mature Crops** | Punches/destroys block, requires replanting | **Right-click harvests & auto-replants in 1 click** |
| **Bone Meal on Sugar Cane** | Green particles only (zero growth) | **Grows stalk upward +1 block** (up to 3 blocks max) |
| **Bone Meal on Cactus** | Green particles only (zero growth) | **Grows stalk upward +1 block** (up to 3 blocks max) |
| **Bone Meal on Vines** | No effect | **Grows vine downward +1 block** |
| **Seeds on Dirt Blocks** | No effect | **Sprouts short grass block** |

---

## 🚀 In-Game Commands & Quick Start

Adjust agricultural simulation parameters live in your active world without restarting using standard Minecraft `/gamerule` commands:

```text
/gamerule agrarian_reform:hydration_source_range 8
/gamerule agrarian_reform:growth_biodiversity_bonus true
/gamerule agrarian_reform:right_click_harvest true
/gamerule agrarian_reform:universal_bonemeal true
/gamerule agrarian_reform:global_growth_multiplier 100
```

All modifications made via `/gamerule` take effect immediately and synchronize across all connected players.

---

## ⚙️ Configuration (Native GameRules)

> [!IMPORTANT]
> **💡 Config vs. In-Game GameRules:** The global configuration file (`config/agrarian-reform.json`) only defines default values for newly created worlds. In existing worlds, change settings in-game via the **Edit Game Rules** UI screen or the `/gamerule` command.

| GameRule Name | Type | Default | Valid Range | Description |
| :--- | :---: | :---: | :---: | :--- |
| `agrarian_reform:hydration_source_range` | `Integer` | `8` | `1` to `32` | Maximum block distance a still water source hydrates farmland. |
| `agrarian_reform:hydration_flowing_range` | `Integer` | `4` | `0` to `16` | Maximum block distance flowing water hydrates farmland. |
| `agrarian_reform:rain_growth_acceleration` | `Integer` | `1` | `0` to `5` | Extra growth stages granted to exposed open-sky crops during rainfall. |
| `agrarian_reform:pure_water_hydration_only` | `Boolean` | `false` | `true / false` | When true, only pure water source blocks hydrate farmland. |
| `agrarian_reform:growth_biodiversity_bonus` | `Boolean` | `true` | `true / false` | Grants a +10% growth speed bonus when different crop types are planted adjacently. |
| `agrarian_reform:ambient_crop_rustle` | `Boolean` | `true` | `true / false` | Plays subtle foliage rustle sounds when walking or sprinting through mature crops. |
| `agrarian_reform:ambient_vitality_particles` | `Boolean` | `true` | `true / false` | Emits green vitality particles when crops advance in growth stage. |
| `agrarian_reform:total_trample_immunity` | `Boolean` | `false` | `true / false` | Completely prevents all farmland trampling by all entities. |
| `agrarian_reform:trample_immunity_players_only` | `Boolean` | `false` | `true / false` | Restricts trample immunity to players and tamed pets, letting mobs still trample soil. |
| `agrarian_reform:always_wet_farmland` | `Boolean` | `false` | `true / false` | Keeps all farmland permanently hydrated regardless of water proximity. |
| `agrarian_reform:seeds_grow_grass` | `Boolean` | `true` | `true / false` | Right-clicking dirt or coarse dirt with chicken food/seeds sprouts a grass block. |
| `agrarian_reform:right_click_harvest` | `Boolean` | `true` | `true / false` | Right-clicking mature crops harvests drops and replants automatically. |
| `agrarian_reform:universal_bonemeal` | `Boolean` | `true` | `true / false` | Allows bone meal to fertilize sugar cane, cactus, nether wart, cocoa, and vines. |
| `agrarian_reform:global_growth_multiplier` | `Integer` | `100` | `0` to `500` | Global crop growth rate percentage (100 = vanilla speed, 0 = frozen, 200 = 2x speed). |
| `agrarian_reform:debug_mode` | `Boolean` | `false` | `true / false` | Enables detailed diagnostic logging for crop ticks. Resets to false on restart. |

---

## 📖 In-Depth How-To & Operational Playbook

### 1. Drop-In Setup & Baseline Initialization
1. Place `agrarian-reform-*.jar` along with **Fabric API** and **Dasik Library** into your `mods` folder.
2. Launch Minecraft. The mod will automatically generate `config/agrarian-reform.json` populated with recommended agricultural defaults.

### 2. High-Efficiency Irrigation Layouts (8-Block Chebyshev Radii)
- Because still water sources hydrate farmland up to **8 blocks away**, you only need a single water source block in the center of a **17x17 plot** of farmland (289 blocks of farmland from 1 bucket of water!).
- For aesthetics, build irrigation canals with flowing water streams: flowing water hydrates 4 blocks out, allowing realistic river channels across your fields.

### 3. Maximizing Polyculture Companion Planting (+10% Bonus)
- Avoid massive single-crop monoculture fields. Instead, plant alternating rows: Wheat &rarr; Carrots &rarr; Potatoes &rarr; Beetroots.
- Every crop that has at least one neighboring crop of a different type receives an automatic **+10% growth speed bonus**.

### 4. Safe Farmland Navigation & Trample Prevention
- Equip any pair of **Leather Boots** or armor enchanted with **Feather Falling** (even Level I). You can now sprint and jump across your fields without converting farmland back into dirt.
- Wear soft boots during harvest season to tend crops quickly without worrying about movement errors.

### 5. Fluid Right-Click Harvesting & Perennial Sugar Cane Care
- When crops reach maturity, simply right-click with an empty hand or tool. The harvest drops are placed into your inventory or ground, and the crop automatically resets to stage 0.
- When right-clicking a 3-block tall Sugar Cane column, the mod harvests the top 2 stalks while keeping the base root plant untouched, allowing continuous passive growth.

### 6. Universal Bone Meal Application
- Right-click Sugar Cane or Cactus with Bone Meal in hand to force an immediate vertical growth spurt (up to 3 blocks high).
- Right-click Nether Wart or Cocoa to advance their maturity stages instantly.
- Right-click Vines to grow them downwards by 1 block along walls.

### 7. Pasture Landscaping with Seeds
- Hold any seed item (`Wheat Seeds`, `Beetroot Seeds`, `Melon Seeds`, `Pumpkin Seeds`) and right-click on bare dirt or coarse dirt. The block instantly transforms into a lush green grass block with green growth particles and audio feedback.

---

## 🧩 Recommended Sister Mods

If you enjoy **Agrarian Reform**, these companion mods from the **Vanilla Outsider Collection** plug in seamlessly:

* 🐾 [**Natural Reproduction**](https://modrinth.com/mod/vo-natural-reproduction): Organic autonomous livestock breeding, herd dynamics, and genetic traits that bring your pastures to life.
* 🛏️ [**True Sleep**](https://modrinth.com/mod/vanilla-outsider-true-sleep): Accelerates furnace smelting, brewing, crop growth, and tile entities during sleep instead of instantly skipping the night.
* 🦇 [**Better Bats**](https://modrinth.com/mod/vo-better-bats): Dynamic bat swarms that roost upside down, fertilize farmland with guano, and hunt crop pests.

> 🌟 *Explore the full [**Vanilla Outsider Collection**](https://modrinth.com/collection/vanilla-outsider) for more vanilla enhancements.*

---

## ☕ Support

If you enjoy the **Vanilla Outsider Collection**, consider fueling future development!

<p align="center">
  <a href="https://ko-fi.com/dasikigaijin/tip"><img src="https://img.shields.io/badge/Ko--fi-Support%20Me-FF5E5B?style=for-the-badge&logo=ko-fi&logoColor=white" alt="Ko-fi"></a>
  <a href="https://sociabuzz.com/dasikigaijin/tribe"><img src="https://img.shields.io/badge/SocioBuzz-Local_Support-7BB32E?style=for-the-badge" alt="SocioBuzz"></a>
  <a href="https://saweria.co/DasikIgaijinn"><img src="https://img.shields.io/badge/Saweria-Local_Support-FFA500?style=for-the-badge" alt="Saweria"></a>
</p>

> [!NOTE]
> **🇮🇩 Indonesian Users:** SocioBuzz and Saweria support local payment methods (Gopay, OVO, Dana, etc.) if you want to support me without using PayPal/Ko-fi!

> [!TIP]
> **Dedicated Server Hosting Partner:**
> Looking for a reliable server to play with friends? Check out **BisectHosting** for 1-click modpack installations, automated backups, and 24/7 dedicated customer support.

---

## 📜 Credits & Modpack Permissions

| Property | Information |
| :--- | :--- |
| **Creator / Author** | **Dasik** (Rifaditya) |
| **Collection** | Vanilla Outsider Collection |
| **License** | [GNU General Public License v3.0 (GPLv3)](https://www.gnu.org/licenses/gpl-3.0.html) |
| **Source Code** | [GitHub - Rifaditya/Agrarian-reform-MC-mod](https://github.com/Rifaditya/Agrarian-reform-MC-mod) |
| **Issue Tracker** | [GitHub Issues](https://github.com/Rifaditya/Agrarian-reform-MC-mod/issues) |
| **Documentation / Wiki** | [GitHub Wiki](https://github.com/Rifaditya/Agrarian-reform-MC-mod/wiki) |

> [!IMPORTANT]
> **📦 Modpack Permissions & Distribution:**<br>
> You are fully welcome to include this mod in any modpack on any platform! However, the mod file must be downloaded directly through official distribution channels (**Modrinth** or **CurseForge**). Re-uploading, mirroring, or redistributing the original mod JAR to third-party mirror sites, scraper portals, or unauthorized launchers is strictly prohibited.
> <br><br>
> **⚖️ License & Fork Guidelines (No Zero-Change Re-uploads):**<br>
> This project is open-source under the **GNU GPLv3**. You are fully encouraged to inspect the code, learn from it, and fork the repository to create genuine modifications, substantial feature expansions, or community ports—provided your project remains open-source under GPLv3 with proper attribution.<br>
> **However, straight 1:1 re-uploads, clone forks with no meaningful functional changes, or re-publishing identical builds under different project names (e.g. to farm downloads or rewards) are strictly forbidden.**

---

<p align="center">
  <strong>Made with ❤️ for the Minecraft community</strong><br>
  <em>Part of the Vanilla Outsider Collection</em>
</p>
