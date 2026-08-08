<p align="center">
    <a href="https://modrinth.com/mod/fabric-api"><img src="https://img.shields.io/badge/Requires-Fabric_API-blue?style=for-the-badge&logo=fabric" alt="Requires Fabric API"></a>
    <img src="https://img.shields.io/badge/Language-Java_25-orange?style=for-the-badge&logo=java" alt="Java 25">
    <img src="https://img.shields.io/badge/License-GPLv3-green?style=for-the-badge" alt="License">
    <img src="https://img.shields.io/badge/Minecraft-26.2+-brightgreen?style=for-the-badge" alt="Minecraft 26.2+">
</p>

# ⚒️ Agrarian Reform

> **"The world should not wait for you to watch it."**

**Agrarian Reform** is a pastoral immersion mod that transforms Minecraft's agriculture from a proximity-based machine into a living, breathing ecosystem. It ensures that your hard work persists and grows even when you aren't there to witness it, while adding depth to soil management, irrigation, and biodiversity.

Part of the **Vanilla Outsider Collection** — mods that refine the vanilla experience with modern standards.

---

## ✨ Features

### 🕰️ The Continuum (Offline Persistence)
Stop waiting for chunks to load for your crops to grow. **The Continuum** introduces true offline persistence:
- **Timestamped State**: When a chunk unloads, the exact state of every crop and plant is saved.
- **Time Delta Simulation**: Upon re-entering the area, the mod calculates exactly how much time has passed and simulates growth stages accordingly.
- **Universal Scope**: Supports standard crops (`CropBlock`), Sugar Cane, Cactus, Nether Wart, Cocoa, Vines, Sweet Berry Bushes, and Saplings.
- **Performance Optimized**: Uses a throttled, distributed update system to ensure no lag spikes upon returning to a large farm.

### 🧤 Soil Resilience (Smart Trample Logic)
Protect your fields without sacrificing immersion:
- **Soft Step**: Walking and running on crops is safe. Only jumping or falling from height onto them without protection will turn them back to dirt.
- **Boot Immunity**: Wearing **Leather Boots** or having the **Feather Falling** enchantment (any level) prevents crops from being trampled.
- **Heavy Entities**: Large beasts (Ravagers, Iron Golems) will always trample farmland, maintaining the threat of raids.
- **Total Immunity**: A dedicated GameRule `agrarian_reform:total_trample_immunity` allows admins to disable trampling entirely for a more relaxed experience.

### 💧 Hydro-Dynamics (Advanced Irrigation)
Water behaves more logically, rewarding strategic placement and natural weather:
- **Deep Reach**: Water source blocks hydrate farmland up to **8 blocks** away.
- **Source vs Flowing**: Only **Source Blocks** reach the full 8-block range. Flowing water remains at vanilla range (4 blocks).
- **Rainfall Celebration**: Natural rain hydrates all exposed farmland and provides a growth acceleration spurt.
- **Always Wet Option**: `agrarian_reform:always_wet_farmland` forces farmland to remain hydrated regardless of water proximity.

### 🍀 Polyculture (Biodiversity Bonus)
Nature thrives in variety. Planting different types of crops adjacent to each other (e.g., Wheat next to Carrots) provides a **Biodiversity Bonus** (+10% growth speed boost), rewarding organic farm designs over monoculture grids.

### 🌾 Right-Click Harvesting & Seed-to-Grass
Farming QoL improvements designed to fit seamlessly into the vanilla experience:
- **Right-Click Harvest**: Right-click mature crops to harvest and automatically replant them in one smooth action. Fully supports vanilla crops, modded crop blocks (e.g. Farmer's Delight, Croptopia), and Sugar Cane columns (harvests all blocks above the base, allowing the column to automatically regrow).
- **Seed Planting**: Use seeds on dirt blocks to grow short grass, making landscaping and sheep grazing setups easier.

### 🦴 Universal Bone Meal
Grow plants that are not natively bonemealable in vanilla:
- **Sugar Cane & Cactus**: Grows the vertical column up by 1 block (up to the standard limit of 3 blocks).
- **Nether Wart & Cocoa**: Advances their growth by 1 stage per use.
- **Vines**: Grows the vine downwards by 1 block, matching the horizontal attachment properties of the vine above.
- **Fair Consumption**: Consumes exactly 1 bone meal item from your hand (unless in Creative mode) and triggers standard particles and sounds.

### ⚡ Global Growth Multiplier
Control the growth speed of your crops globally without modifying actual server game ticks:
- **Proportional Ticking**: Speeds up or slows down the random tick rate of crops.
- **Ticking Math**: `0` stops growth entirely, `50` halves growth speed, and values above `100` speed it up proportionally (e.g. `200` runs growth ticks twice as fast).
- **Target Scope**: Targets vanilla and modded crops, sugar cane, cactus, nether wart, cocoa, vines, saplings, and sweet berry bushes.

---

## ⚙️ Configuration (Native Game Rules)

> [!IMPORTANT]
> **Config vs. In-Game GameRules:**
> The global configuration file only defines **default values for new worlds** at creation time.
> If you have **already created/opened a world**, changing the config file will have no effect. You must change the settings in-game using the **Edit Game Rules** UI screen or the `/gamerule` command.

No messy config files. Everything lives in the **Edit Game Rules** screen or via standard commands:

- `agrarian_reform:hydration_source_range`: How far still water hydrates farmland. (Default: 8)
- `agrarian_reform:hydration_flowing_range`: How far flowing water hydrates farmland. (Default: 4)
- `agrarian_reform:rain_growth_acceleration`: Growth stages jumped during rain events. (Default: 1)
- `agrarian_reform:growth_biodiversity_bonus`: Toggles speed bonus from mixed crop planting. (Default: true)
- `agrarian_reform:ambient_crop_rustle`: Enables sound effects when walking through crops. (Default: true)
- `agrarian_reform:ambient_vitality_particles`: Enables vitality particles on growth events. (Default: true)
- `agrarian_reform:total_trample_immunity`: Toggle whether trampling is disabled entirely. (Default: false)
- `agrarian_reform:always_wet_farmland`: Forces farmland to remain hydrated regardless of water. (Default: false)
- `agrarian_reform:seeds_grow_grass`: Toggles whether seeds grow short grass on dirt. (Default: true)
- `agrarian_reform:right_click_harvest`: Toggles right-click harvesting and automatic replanting. (Default: true)
- `agrarian_reform:universal_bonemeal`: Enables using bone meal on non-bonemealable plants. (Default: true)
- `agrarian_reform:global_growth_multiplier`: Scale growth speeds globally (0 = disabled, 100 = vanilla, 200 = 2x speed). (Default: 100)

---

## ☕ Support

If you enjoy the **Vanilla Outsider** collection, consider supporting the next update!

<p align="center">
    <a href="https://ko-fi.com/dasikigaijin/tip"><img src="https://img.shields.io/badge/Ko--fi-Support%20Me-FF5E5B?style=for-the-badge&logo=ko-fi&logoColor=white" alt="Ko-fi"></a>
    <a href="https://sociabuzz.com/dasikigaijin/tribe"><img src="https://img.shields.io/badge/SocioBuzz-Local_Support-7BB32E?style=for-the-badge" alt="SocioBuzz"></a>
    <a href="https://saweria.co/DasikIgaijinn"><img src="https://img.shields.io/badge/Saweria-Local_Support-FFA500?style=for-the-badge" alt="Saweria"></a>
</p>

> [!NOTE]
> **Indonesian Users:** SocioBuzz and Saweria support local payment methods (Gopay, OVO, Dana, etc.) if you want to support me without using PayPal/Ko-fi!

---

## 📦 Modpack Permissions

> **Modpack Distribution Policy:**
> You are free to include this mod in any modpack, provided that the modpack is hosted on the same platform where you obtained this mod (e.g. CurseForge modpacks on CurseForge, Modrinth modpacks on Modrinth). Cross-platform redistribution is strictly prohibited to support the creator and ensure legitimate downloads.

---

## 📜 Credits

| Role | Author |
| :--- | :--- |
| **Creator** | **Dasik** (Rifaditya) |
| **Collection** | Vanilla Outsider |
| **License** | GPLv3 |

---

<div align="center">

**Made with ❤️ for the Minecraft community**

*Part of the Vanilla Outsider Collection*

</div>
