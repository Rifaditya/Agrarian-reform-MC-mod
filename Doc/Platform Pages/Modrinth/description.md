<p align="center">
  <a href="https://discord.gg/EV99bgAFqb"><img src="https://img.shields.io/badge/Discord-Join_Community-5865F2?style=for-the-badge&logo=discord&logoColor=white" alt="Join Discord"></a>
  <a href="https://modrinth.com/mod/fabric-api"><img src="https://img.shields.io/badge/Requires-Fabric_API-blue?style=for-the-badge&logo=fabric" alt="Requires Fabric API"></a>
  <a href="https://modrinth.com/mod/dasik-library"><img src="https://img.shields.io/badge/Requires-Dasik_Library-purple?style=for-the-badge" alt="Requires Dasik Library"></a>
  <img src="https://img.shields.io/badge/Environment-Client_&_Server-success?style=for-the-badge" alt="Client & Server">
  <img src="https://img.shields.io/badge/Language-Java_25-orange?style=for-the-badge&logo=java" alt="Java 25">
  <img src="https://img.shields.io/badge/License-GPLv3-red?style=for-the-badge" alt="License GPLv3">
  <img src="https://img.shields.io/badge/Minecraft-26.1+-brightgreen?style=for-the-badge" alt="Minecraft 26.1+">
</p>

# 🌾 Agrarian Reform

> **"Farming that honors the rhythm of the soil."**

---

## 📖 Introduction

In vanilla Minecraft, farming quickly devolves into tedious busywork: breaking crops leaves empty holes in your fields, water source mechanics force rigid, ugly 9x9 irrigation grids, accidental jumps turn rich tilled soil into useless dirt, and crops instantly stop growing the moment you log off or walk a few chunks away.

**Agrarian Reform** completely revitalizes Minecraft's agriculture under the **Vanilla Outsider** philosophy. It introduces fluid right-click harvesting and instant replanting, realistic expanded hydrological irrigation, natural polyculture biodiversity growth bonuses, trample protection, universal bone-meal support for wild flora, and **The Continuum**—an intelligent offline persistence engine that simulates crop growth while you sleep or explore far-off dimensions.

> [!NOTE]
> **1 Jar 1 Version Policy:** I build **1 dedicated JAR for each Minecraft version** (e.g. MC 26.1, MC 26.2, MC 26.3). Please download the exact build that matches your Minecraft installation.
> 
> **Dependency Callout:**
> * **Minecraft 26.x+ (Modern Era):** Requires both **Fabric API** AND **Dasik Library** (`v1.8.15+`).
> * **Minecraft 1.20.1 / 1.21.x (Legacy Era):** Self-contained build requiring only **Fabric API**.
> * **Dedicated Server Ready:** Both client and server compatible. Vanilla clients can join servers running Agrarian Reform!

Part of the **Vanilla Outsider Collection** — mods that deepen vanilla immersion through native gameplay loops.

---

## ✨ Features

### 🌾 Right-Click Quick Harvest & Fluid Replanting
- **One-Click Replanting:** Right-click any fully mature crop (Wheat, Carrots, Potatoes, Beetroot, Nether Wart, Cocoa, Torchflowers, Pitcher Plants) to instantly harvest drops and replant the seed at age 0 in a single fluid gesture.
- **Main-Hand Debounce:** Equipped with anti-double-click protection to prevent accidental duplicate harvests or unintended seed placement.
- **Fortune & Tool Synergy:** If harvesting with a tool or hoe in hand, drops correctly calculate Fortune enchantments without consuming tool durability.

### 💧 Hydro-Dynamics & Capillary Irrigation
- **Chebyshev Ring Irrigation:** Expands the effective irrigation range of still water source blocks from vanilla's cramped 4 blocks to a spacious **8 blocks** by default (`agrarian_reform:hydration_source_range`), allowing expansive pastoral landscape farms.
- **Flowing Water Capillary Action:** Flowing water provides up to **4 blocks** of capillary hydration (`agrarian_reform:hydration_flowing_range`), making natural streams and canals viable for authentic terrace farming.
- **Organic Moisture Retention:** Farmland retains moisture significantly longer without harsh instant dry-outs.

### 🍃 Polyculture & Biodiversity Growth Bonuses
- **Ecosystem Vitality:** Planting diverse crops adjacently grants a **+10% growth probability bonus** (`agrarian_reform:growth_biodiversity_bonus`), encouraging natural mixed kitchen gardens and historically accurate companion planting over industrial monoculture grids.
- **Rain Growth Acceleration:** Crops exposed to open sky during rainstorms receive extra random growth ticks (`agrarian_reform:rain_growth_acceleration`).

### 🛡️ Soil Resilience & Trample Protection
- **Trample Immunity Guard:** Optional configurable immunity against farmland destruction from player jumps, sprint-slides, and falling entities (`agrarian_reform:total_trample_immunity`).
- **Targeted Protection:** Restrict trample immunity to players and tamed pets while leaving wild mobs subject to soil damage (`agrarian_reform:trample_immunity_players_only`).

### ⏳ The Continuum: Offline Growth Persistence
- **Time-Aware Simulation:** Calculates the exact offline time delta when a world or chunk reloads, advancing crops through their growth stages according to natural tick probabilities. Your fields flourish even while you explore the Nether or sleep in real life!
- **Zero Chunk Freezing:** Eliminates the frustration of returning home after hours of adventuring only to find your crops in the exact same immature state.

### 🦴 Universal Bone-Meal Cultivation
- Accelerates growth on non-natively bonemealable flora (Cactus, Sugar Cane, Nether Wart, Vines) using standard bone-meal item interactions.

### 🧩 Compatibility & HUD Integration
- **100% Server-Side Compatible:** Fully functional on dedicated servers; connecting vanilla clients experience smooth harvesting and visuals without client mods.
- **ModMenu & YACL / Cloth Config:** In-game graphical configuration screen in singleplayer to easily customize world defaults.
- **WTHIT / Jade Compatibility:** Displays accurate growth percentages and moisture states in third-party tooltips.

---

## 📊 Quick Reference & Mechanics Matrix

| Feature Dimension | Vanilla Minecraft | Agrarian Reform (`v1.2.0+`) |
| :--- | :---: | :---: |
| **Harvest Workflow** | Break block &rarr; collect drops &rarr; re-select seed &rarr; replant | **Right-click mature crop**: instant drops & auto-replant |
| **Water Source Irrigation** | 4-block radius only (rigid 9x9 box) | **8-block radius** (Configurable Chebyshev rings) |
| **Flowing Water Irrigation** | 0 blocks (Must be still source) | **4-block capillary action** (Streams hydrate soil) |
| **Monoculture vs Polyculture**| No growth difference | **+10% growth speed bonus** for mixed plantings |
| **Farmland Trampling** | Instantly breaks into dirt on jump | **Configurable trample immunity** (Total or Player-only) |
| **Offline Crop Growth** | Completely frozen when unloaded | **The Continuum simulation** catches up on reload |
| **Bone-Meal Support** | Blocked on Cactus, Sugar Cane, Nether Wart | **Universal bone-meal** applies to all cultivated flora |
| **Rainstorm Growth** | Standard random tick rate | **Accelerated growth stages** during rainfall |

---

## 🚀 In-Game Commands & Quick Start

Agrarian Reform parameters are native GameRules accessible via standard Minecraft commands:

```bash
# Query current water source irrigation radius
/gamerule agrarian_reform:hydration_source_range

# Expand water source irrigation to 12 blocks
/gamerule agrarian_reform:hydration_source_range 12

# Enable 100% player farmland trample immunity
/gamerule agrarian_reform:total_trample_immunity true
/gamerule agrarian_reform:trample_immunity_players_only true

# Set global crop growth speed to 150%
/gamerule agrarian_reform:global_growth_multiplier 150

# Freeze growth for a specific crop completely (-1)
/gamerule agrarian_reform:growth_minecraft_sugar_cane -1
```

All modifications made via `/gamerule` take effect immediately without requiring a game restart.

---

## ⚙️ Configuration (Native GameRules)

> [!IMPORTANT]
> **💡 Config vs. In-Game GameRules:** The global configuration file (`config/agrarian-reform.json`) only defines default values for newly created worlds. In existing worlds, change settings in-game via the **Edit Game Rules** UI screen or the `/gamerule` command.

| GameRule Name | Type | Default | Valid Range | Description |
| :--- | :---: | :---: | :---: | :--- |
| `agrarian_reform:hydration_source_range` | `Integer` | `8` | `0` to `32` | Block radius a still water source hydrates farmland. |
| `agrarian_reform:hydration_flowing_range` | `Integer` | `4` | `0` to `32` | Block radius flowing water provides capillary hydration. |
| `agrarian_reform:growth_biodiversity_bonus` | `Boolean` | `true` | `true / false` | Grants +10% growth speed bonus when different crops are planted adjacently. |
| `agrarian_reform:rain_growth_acceleration` | `Integer` | `1` | `0` to `10` | Extra growth stages awarded to sky-exposed crops during rainstorms. |
| `agrarian_reform:total_trample_immunity` | `Boolean` | `false` | `true / false` | 100% protection against farmland trample destruction. |
| `agrarian_reform:trample_immunity_players_only`| `Boolean` | `false` | `true / false` | Restricts trample protection to players and tamed pets. |
| `agrarian_reform:always_wet_farmland` | `Boolean` | `false` | `true / false` | Forces all farmland to maintain moisture level 7 without water. |
| `agrarian_reform:right_click_harvest` | `Boolean` | `true` | `true / false` | Enables one-click harvest and auto-replant on mature crops. |
| `agrarian_reform:universal_bonemeal` | `Boolean` | `true` | `true / false` | Allows bone-meal acceleration on Cactus, Sugar Cane, and Nether Wart. |
| `agrarian_reform:global_growth_multiplier` | `Integer` | `100` | `0` to `2147483647` | Global crop growth speed percentage (100 = 1x vanilla). |
| `agrarian_reform:ambient_crop_rustle` | `Boolean` | `true` | `true / false` | Subtle ambient audio feedback when walking through crops. |
| `agrarian_reform:ambient_vitality_particles` | `Boolean` | `true` | `true / false` | Emits green sparkle particles when crops advance a growth stage. |

---

## 📖 In-Depth How-To & Gameplay Playbook

### 1. Drop-In Setup & Baseline Initialization
1. Place `agrarian-reform-*.jar` along with **Fabric API** and **Dasik Library** into your `mods` folder.
2. Launch Minecraft. The mod will automatically generate `config/agrarian-reform.json` populated with recommended pastoral defaults.

### 2. Crafting Expansive Natural Terrace Farms
- Because water source blocks hydrate up to **8 blocks away**, you no longer need a water hole every 4 blocks. Place a single central pond or water well to hydrate an expansive **17x17 field**!
- Use winding streams and canals: flowing water hydrates 4 blocks outwards, allowing picturesque hillside terraces with zero artificial water grids.

### 3. Mastering Polyculture & Companion Planting
- Alternate your crop rows (e.g. Wheat &rarr; Carrot &rarr; Potato &rarr; Beetroot). Adjacent different crop types trigger the **+10% biodiversity growth bonus**.
- Watch for gentle green vitality sparkles (`ambient_vitality_particles`): these confirm your crops are thriving and benefiting from companion planting.

### 4. Right-Click Harvesting & Field Management
- Simply right-click any fully grown crop with an empty hand or tool. Drops will scatter naturally around your feet, and the crop will automatically reset to seed stage at age 0.
- Hold a Fortune III tool while right-clicking to safely multiply potato, carrot, or seed yields without taking tool wear.

### 5. Managing Trample & Animal Grazing
- If you frequently walk across your fields or build farmsteads near villages, run `/gamerule agrarian_reform:total_trample_immunity true` to keep your soil permanently tilled.
- For balanced immersion, enable `trample_immunity_players_only true` so you can jump on your own crops while wild animals are still discouraged from trampling them.

---

## ☕ Support

If you enjoy **Agrarian Reform** and the **Vanilla Outsider Collection**, consider fueling future updates!

<p align="center">
  <a href="https://ko-fi.com/dasikigaijin/tip"><img src="https://img.shields.io/badge/Ko--fi-Support%20Me-FF5E5B?style=for-the-badge&logo=ko-fi&logoColor=white" alt="Ko-fi"></a>
  <a href="https://sociabuzz.com/dasikigaijin/tribe"><img src="https://img.shields.io/badge/SocioBuzz-Local_Support-7BB32E?style=for-the-badge" alt="SocioBuzz"></a>
  <a href="https://saweria.co/DasikIgaijinn"><img src="https://img.shields.io/badge/Saweria-Local_Support-FFA500?style=for-the-badge" alt="Saweria"></a>
</p>

> [!NOTE]
> **🇮🇩 Indonesian Users:** SocioBuzz and Saweria support local payment methods (Gopay, OVO, Dana, etc.) if you want to support me without using PayPal/Ko-fi!

### 💬 Join the Community & Get Support
Looking for help, want to test early beta builds, or vote on upcoming features? Join our official Discord community!

<p align="center">
  <a href="https://discord.gg/EV99bgAFqb">
    <img src="https://img.shields.io/badge/💬_Discord-Join_Community-5865F2?style=for-the-badge&logo=discord&logoColor=white" alt="Join Official Discord">
  </a>
</p>

---

## 📜 Credits & Modpack Permissions

| Role / Property | Author / Link |
| :--- | :--- |
| **Creator / Author** | **Dasik** (Rifaditya) |
| **Community** | [Official Discord](https://discord.gg/EV99bgAFqb) |
| **Collection** | Vanilla Outsider Collection |
| **License** | [GNU General Public License v3.0 (GPLv3)](https://www.gnu.org/licenses/gpl-3.0.html) |
| **Source Code** | [GitHub - Rifaditya/vanilla-outsider-agrarian-reform](https://github.com/Rifaditya/vanilla-outsider-agrarian-reform) |
| **Issue Tracker** | [GitHub Issues](https://github.com/Rifaditya/vanilla-outsider-agrarian-reform/issues) |
| **Documentation / Wiki** | [Web Documentation Portal](https://mod-portal.pages.dev/vanilla-outsider-agrarian-reform/) |

> [!IMPORTANT]
> **📦 Modpack Permissions & Distribution:**<br>
> You are fully welcome to include this mod in any modpack on any platform! However, the mod file must be downloaded directly through official distribution channels (**Modrinth** or **CurseForge**). Re-uploading, mirroring, or redistributing the original mod JAR to third-party mirror sites, scraper portals, or unauthorized launchers is strictly prohibited.
> <br><br>
> **⚖️ License & Fork Guidelines (No Zero-Change Re-uploads):**<br>
> This project is open-source under the **GNU GPLv3**. You are fully encouraged to inspect the code, learn from it, and fork the repository to create genuine modifications, substantial feature expansions, or community ports—provided your project remains open-source under GPLv3 with proper attribution.<br>
> **However, straight 1:1 re-uploads, clone forks with no meaningful functional changes, or re-publishing identical builds under different project names (e.g. to farm downloads or rewards) are strictly forbidden.**
---

<div align="center">

**Made with ❤️ for the Minecraft community**

*Part of the Vanilla Outsider Collection*

</div>
