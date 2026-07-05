<p align="center">
    <a href="https://www.curseforge.com/minecraft/mc-mods/fabric-api"><img src="https://img.shields.io/badge/Requires-Fabric_API-blue?style=for-the-badge&logo=fabric" alt="Requires Fabric API"></a>
    <img src="https://img.shields.io/badge/Language-Java_25-orange?style=for-the-badge&logo=java" alt="Java 25">
    <img src="https://img.shields.io/badge/License-GPLv3-green?style=for-the-badge" alt="License">
    <img src="https://img.shields.io/badge/Minecraft-26.1.2%20%2F%2026.2+-brightgreen?style=for-the-badge" alt="Minecraft 26.1.2 / 26.2+">
</p>

# ⚒️ Agrarian Reform

### 🎮 Version Compatibility & Parity

This mod is active and fully supported across both major version streams:
* **Minecraft 26.2+**: Current public release — **`v2.0.0`**
* **Minecraft 26.1.2**: Current public release — **`v1.2.7`**

While both versions receive ongoing support and bug fixes, **Minecraft 26.1.2** targets Snapshot 11 and is built against Java 25.

<blockquote><strong>"The world should not wait for you to watch it."</strong></blockquote>

**Agrarian Reform** is a pastoral immersion mod that transforms Minecraft's agriculture from a proximity-based machine into a living, breathing ecosystem. It ensures that your hard work persists and grows even when you aren't there to witness it, while adding depth to soil management, irrigation, and biodiversity.

Part of the **Vanilla Outsider Collection** — mods that refine the vanilla experience with modern standards.

---

## ✨ Features

### 🕰️ The Continuum (Offline Persistence)
Stop waiting for chunks to load for your crops to grow. **The Continuum** introduces true offline persistence:
- **Timestamped State**: When a chunk unloads, the exact state of every crop is saved.
- **Time Delta Simulation**: Upon re-entering the area, the mod calculates exactly how much time has passed and simulates growth stages accordingly.
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
- **Rainfall Celebration**: Natural rain hydrates all exposed farmland and provides a growth acceleration.

### 🍀 Polyculture (Biodiversity Bonus)
Nature thrives in variety. Planting different types of crops adjacent to each other (e.g., Wheat next to Carrots) provides a **Biodiversity Bonus** (+10% growth speed boost), rewarding organic farm designs over monoculture grids.

### 🌾 Right-Click Harvesting & Seed-to-Grass
Farming QoL improvements designed to fit seamlessly into the vanilla experience:
- **Right-Click Harvest**: Right-click mature crops to harvest and automatically replant them in one smooth action.
- **Seed Planting**: Use seeds on dirt blocks to grow short grass, making landscaping and sheep grazing setups easier.

---

## ⚙️ Configuration (Native Game Rules)

No messy config files. Everything lives in the **Edit Game Rules** screen or via standard commands.

- `agrarian_reform:hydration_source_range`: How far still water hydrates farmland. (Default: 8)
- `agrarian_reform:hydration_flowing_range`: How far flowing water hydrates farmland. (Default: 4)
- `agrarian_reform:total_trample_immunity`: Toggle whether trampling is disabled entirely. (Default: false)
- `agrarian_reform:growth_biodiversity_bonus`: Adjust the speed bonus from mixed crop planting. (Default: true)
- `agrarian_reform:rain_growth_acceleration`: Adjust the growth boost during rainy weather. (Default: 1)
- `agrarian_reform:always_wet_farmland`: Forces farmland to remain hydrated regardless of water proximity. (Default: false)
- `agrarian_reform:seeds_grow_grass`: Toggles whether chicken feed seeds grow short grass on dirt. (Default: true)
- `agrarian_reform:right_click_harvest`: Toggles right-click harvesting and automatic replanting. (Default: true)

---

## ☕ Support

If you enjoy the **Vanilla Outsider** collection, consider supporting the next update!

<p align="center">
    <a href="https://ko-fi.com/dasikigaijin/tip"><img src="https://img.shields.io/badge/Ko--fi-Support%20Me-FF5E5B?style=for-the-badge&logo=ko-fi&logoColor=white" alt="Ko-fi"></a>
    <a href="https://sociabuzz.com/dasikigaijin/tribe"><img src="https://img.shields.io/badge/SocioBuzz-Local_Support-7BB32E?style=for-the-badge" alt="SocioBuzz"></a>
    <a href="https://saweria.co/DasikIgaijinn"><img src="https://img.shields.io/badge/Saweria-Local_Support-FFA500?style=for-the-badge" alt="Saweria"></a>
</p>

<blockquote>
    <strong>Indonesian Users:</strong> SocioBuzz and Saweria support local payment methods (Gopay, OVO, Dana, etc.) if you want to support me without using PayPal/Ko-fi!
</blockquote>

---

## 📦 Modpack Permissions

<blockquote><strong>Modpack Distribution Policy:</strong><br>
Since this mod is open-source (GPLv3), you are completely free to include it in any modpack! If you want to support my work and help fund future updates, downloading it directly through the official platform page (CurseForge) is highly appreciated.</blockquote>

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
