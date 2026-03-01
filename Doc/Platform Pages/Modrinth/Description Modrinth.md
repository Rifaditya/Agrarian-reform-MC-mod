<div align="center">

<!-- Banner placeholder — replace URL when banner is uploaded -->
<!-- ![Agrarian Reform Banner](https://example.com/banner.jpg) -->

</div>
<p align="center">
    <a href="https://modrinth.com/mod/fabric-api"><img src="https://img.shields.io/badge/Requires-Fabric_API-blue?style=for-the-badge&logo=fabric" alt="Requires Fabric API"></a>
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
- **Persistent Growth**: When a chunk is unloaded, the mod timestamps the state of every crop.
- **Time Delta Simulation**: Upon your return, the mod calculates real-time passed and simulates growth.
- **Throttled Updates**: High-performance distributed application of growth stages.

### 2. Soil Resilience (Trample Logic)
- **Soft Step**: Leather Boots / Feather Falling protection.
- **[IG] Total Immunity**: Optional toggle for absolute trample prevention.

### 3. Hydro-Dynamics (Advanced Irrigation)
- **High Reach**: Water sources hydrate up to 8 blocks away.
- **Pluviophile Bonus**: Rain hydrates all exposed fields and boosts growth.

### 4. Polyculture (Biodiversity)
- **Synergy**: Mixed crop fields grow faster.

---

## ⚙️ Configuration (GameRules)
Admins can fine-tune the experience using native Minecraft GameRules:
- `hydroSourceRange`
- `hydroPolycultureBoost`
- `totalTrampleImmunity`
- `hydroRainGrowthSpurt`
