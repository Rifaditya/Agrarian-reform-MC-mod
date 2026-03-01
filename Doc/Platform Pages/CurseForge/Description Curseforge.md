<div align="center">

<!-- Banner placeholder — replace URL when banner is uploaded -->
<!-- ![Agrarian Reform Banner](https://example.com/banner.jpg) -->

</div>
<p align="center">
    <a href="https://www.curseforge.com/minecraft/mc-mods/fabric-api"><img src="https://img.shields.io/badge/Requires-Fabric_API-blue?style=for-the-badge&logo=fabric" alt="Requires Fabric API"></a>
    <img src="https://img.shields.io/badge/Language-Java-orange?style=for-the-badge&logo=java" alt="Java">
    <img src="https://img.shields.io/badge/License-GPLv3-green?style=for-the-badge" alt="License">
    <img src="https://img.shields.io/badge/Minecraft-26.1+-brightgreen?style=for-the-badge" alt="Minecraft 26.1+">
</p>

# ⚒️ Agrarian Reform: The Living Earth

**"The world should not wait for you to watch it."**

**Agrarian Reform** is a pastoral immersion mod that transforms Minecraft's agriculture from a proximity-based machine into a living, breathing ecosystem. It ensures that your hard work persists and grows even when you aren't there to witness it, while adding depth to soil management, irrigation, and biodiversity.

---

## 🌾 Core Mechanics

### 1. The Continuum (Offline Persistence)
In vanilla Minecraft, crops only grow in loaded chunks. **Agrarian Reform** introduces **The Continuum**, a high-precision simulation engine that bridges the gap between gameplay sessions.
- **Persistent Growth**: When a chunk is unloaded, the mod timestamps the state of every crop.
- **Time Delta Simulation**: Upon your return, the mod calculates exactly how much real-time has passed and simulates the random ticks that *would* have occurred.
- **Throttled Updates**: Updates are applied via a smooth, throttled queue (O(1) calculation with O(n) distributed application).

### 2. Soil Resilience (Trample Logic)
Farmland is treated as a nurtured resource rather than fragile glass.
- **Soft Step**: Players wearing **Leather Boots** or possessing **Feather Falling** are immune to trampling crops during normal movement.
- **[IG] Total Immunity**: A GameRule `totalTrampleImmunity` allows for absolute trample prevention for players seeking a more relaxed experience.

### 3. Hydro-Dynamics (Advanced Irrigation)
- **Capillary Range**: Water **source blocks** irrigate a massive **8-block radius** (Vanilla is 4).
- **Rainfall Celebration**: During rain, all sky-exposed farmland acts as hydrated, and crops receive growth spurts.

### 4. Polyculture (Biodiversity)
- **Biodiversity Bonus**: Crops planted next to *different* species (e.g., Wheat adjacent to Carrots) receive a growth probability boost.

---

## ⚙️ Configuration (GameRules)
Admins can fine-tune the experience using native Minecraft GameRules:
- `hydroSourceRange` (Int, default 8)
- `hydroFlowingRange` (Int, default 4)
- `totalTrampleImmunity` (Bool, default false)
- `hydroPolycultureBoost` (Bool, default true)
