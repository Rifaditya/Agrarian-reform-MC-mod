<div align="center">

# ⚒️ Agrarian Reform: The Living Earth

**"The world should not wait for you to watch it."**

[![Requires Fabric API](https://img.shields.io/badge/Requires-Fabric_API-blue?style=for-the-badge&logo=fabric)](https://modrinth.com/mod/fabric-api)
![Java 25](https://img.shields.io/badge/Language-Java_25-orange?style=for-the-badge&logo=java)
![Minecraft 26.1+](https://img.shields.io/badge/Minecraft-26.1+-brightgreen?style=for-the-badge)
![License GPLv3](https://img.shields.io/badge/License-GPLv3-green?style=for-the-badge)

</div>

**Agrarian Reform** is a pastoral immersion mod that transforms Minecraft's agriculture from a proximity-based machine into a living, breathing ecosystem. It ensures that your hard work persists and grows even when you aren't there to witness it, while adding depth to soil management, irrigation, and biodiversity.

---

## 🌾 Core Mechanics

### 1. The Continuum (Offline Persistence)
In vanilla Minecraft, crops only grow in loaded chunks. **Agrarian Reform** introduces **The Continuum**, a high-precision simulation engine that bridges the gap between gameplay sessions.
- **Persistent Growth**: When a chunk is unloaded, the mod timestamps the state of every crop.
- **Time Delta Simulation**: Upon your return, the mod calculates exactly how much real-time has passed and simulates the random ticks that *would* have occurred.
- **Throttled Updates**: To prevent lag spikes upon loading a massive farm, updates are applied via a smooth, throttled queue (O(1) calculation with O(n) distributed application).

### 2. Soil Resilience (Trample Logic)
Farmland is treated as a nurtured resource rather than fragile glass.
- **Soft Step**: Players wearing **Leather Boots** or possessing the **Feather Falling** enchantment are immune to trampling crops during normal movement.
- **Mass & Velocity**: Walking is safe; however, high-velocity impacts (falls > 0.6m) or jumping without protection will still trample the soil.
- **Ravager Protection**: Large beasts like Ravagers remain a threat and will always trample crops, maintaining the stakes of village raids.
- **[IG] Total Immunity**: A GameRule `totalTrampleImmunity` allows for absolute trample prevention for players seeking a more relaxed experience.

### 3. Hydro-Dynamics (Advanced Irrigation)
Water is no longer a binary toggle for hydration; its reach is influenced by its state.
- **Capillary Range**: Water **source blocks** irrigate a massive **8-block radius** (Vanilla is 4).
- **Flow Logic**: Flowing water maintains the vanilla **4-block radius**, encouraging the construction of proper irrigation canals and wells.
- **Pluviophile Bonus**: During rain, all sky-exposed farmland acts as if hydrated, and crops receive a significant growth spurt celebrated with visual particles.

### 4. Polyculture (Biodiversity)
Monocultures are discouraged in favor of beautiful, varied garden patches.
- **Biodiversity Bonus**: Crops planted next to *different* species (e.g., Wheat adjacent to Carrots) receive a **10% growth probability boost**.
- **Aesthetic Reward**: This mechanic incentivizes organic, "chaotic" farm designs that look more natural and vibrant than industrialized grids.

---

## 🎨 Aesthetics & Feedback
- **Crop Rustling**: Walking through fully grown crops produces a satisfying brushing sound with randomized pitch.
- **Growth Sparkles**: Successful growth stages occasionally trigger a subtle green particle effect, making a busy field feel alive.
- **Morning Moisture**: Farmland appears slightly darker at sunrise (visual only) to simulate morning dew.

---

## ⚙️ Configuration (GameRules)
Admins can fine-tune the experience using native Minecraft GameRules:

| GameRule | Type | Default | Description |
| :--- | :--- | :--- | :--- |
| `hydroSourceRange` | Integer | 8 | The irrigation radius of water source blocks. |
| `hydroFlowingRange` | Integer | 4 | The irrigation radius of flowing water. |
| `hydroRainGrowthSpurt`| Integer | 1 | Growth stages jumped during rain events. |
| `hydroPolycultureBoost`| Boolean | true | Whether mixed crops receive a growth bonus. |
| `totalTrampleImmunity` | Boolean | false | Absolute immunity to all trampling (IG Mode). |
| `hydroCropRustle` | Boolean | true | Enables sound effects when walking through crops. |
| `hydroCropParticles` | Boolean | true | Enables 'happy villager' particles on growth. |

---

## 🛠️ Technical Specs
- **Target**: Minecraft 26.1 (Snapshot 8/10)
- **Loader**: Fabric
- **Dependencies**: Fabric API, DasikLibrary (Standalone)
- **Java**: Version 25 (Strict)

---

## 📜 Credits
Developed as part of the **Vanilla Outsider** and **Instant Gratification** collections.
