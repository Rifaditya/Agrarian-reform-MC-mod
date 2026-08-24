# 🌾 Agrarian Reform: The Living Earth

> **"The world should not wait for you to watch it."**

Welcome to the official **Agrarian Reform** wiki. **Agrarian Reform** is a pastoral immersion mod that transforms Minecraft's agriculture from a proximity-based machine into a living, breathing ecosystem. It ensures that your crops persist and grow even when chunks are unloaded or during offline server time, while introducing deep soil management, realistic hydro-dynamics, and polyculture biodiversity bonuses.

> 📌 **Repository Source Disclaimer**: The documentation in this Wiki reflects the **current source code state in the repository**, which may include recent unreleased commits or developmental features ahead of public release builds on CurseForge and Modrinth.

---

## 🛠️ Overview & Quick Specifications

| Parameter | Specification |
| :--- | :--- |
| **Mod Identifier** | `agrarian_reform` |
| **Current Target** | Minecraft 26.2 (Stable) |
| **Mod Version** | `2.2.4+26.2` |
| **Mod Loader** | Fabric Loader (`>=0.16.9`) |
| **Language Target** | Java 25 (Strict) |
| **Primary Dependencies** | Fabric API (`*`), DasikLibrary (`>=1.8.3`) |
| **Optional Integrations**| ModMenu (`*`), YetAnotherConfigLib v3 (`*`) |
| **Author & Lead** | **Dasik (Rifaditya)** |
| **License** | GNU General Public License v3.0 (GPLv3) |

---

## 📦 Minecraft Versions Directory

* [[MC 26.2 Guide|Minecraft-26.2-Guide]] — Official guide for stable Minecraft 26.2 release builds.
* [[Version Compatibility|Version-Compatibility]] — Version support matrix, open-ended lower bounds, and version guards.

---

## 🎮 Player & Feature Guides Directory

* [[The Continuum (Offline Growth)|The-Continuum-Offline-Persistence]] — Chunk unload timestamping, time delta catch-up calculation, queued simulation, and zero-disk-write chunk preservation.
* [[Soil Resilience & Trample Logic|Soil-Resilience-and-Trample-Logic]] — Soft Step protection with Leather Boots/Feather Falling, velocity limits, and Ravager impact.
* [[Hydro-Dynamics & Irrigation|Hydro-Dynamics-and-Irrigation]] — 8-block water source capillary irrigation, 4-block flowing water, and pluviophile rain hydration.
* [[Polyculture & Biodiversity|Polyculture-and-Biodiversity]] — Mixed crop planting incentives and 10% growth probability acceleration.
* [[Right-Click Harvest & Replant|Right-Click-Harvest-and-Replanting]] — One-click harvesting, age-0 automatic replanting, and surplus item drops.
* [[Seed Sowing & Grass Cultivation|Seed-Sowing-and-Grass-Cultivation]] — Sowing seeds on dirt to restore grass blocks naturally.
* [[Universal Bone Meal|Universal-Bone-Meal]] — Accelerating growth on non-standard and non-natively bonemealable plants.
* [[Global Growth Multiplier|Global-Growth-Multiplier]] — Server-wide growth speed tuning from 0% to >100%.
* [[Plant Registry & Crop Types|Plant-Registry-and-Crop-Types]] — Index of supported crops, saplings, vines, and berries.
* [[Performance & Queue Throttling|Performance-and-Queue-Throttling]] — Server tick budget preservation (`CROPS_PER_TICK = 5`), lock-free task queues, and zero-disk-write guarantees.
* [[Advancements & Progression|Advancements]] — Native integration with vanilla Husbandry advancements.
* [[GameRules|GameRules]] — Complete listing of namespaced `agrarian_reform:*` rules and default parameters.
* [[Commands|Commands]] — Brigadier command administration and dynamic configuration overrides.
* [[Configuration|Configuration]] — Global JSON template configuration and client GUI integration.
* [[Aesthetics & Ambient Feedback|Aesthetics-and-Ambient-Feedback]] — Crop rustle audio dynamics, vitality particle sparkles, and morning moisture.

---

## 💻 Developer Reference Directory

* [[Developer Setup & Building|Developer-Setup-and-Building]] — Building with JDK 25, Gradle 9.3+, Loom 1.15+, and GameTest verification.
* [[Architecture & Mixins|Architecture-and-Mixins]] — Package hierarchy trees and Mixin target class breakdown tables.
* [[API & Addon Integration|API-and-Addon-Integration]] — DasikLibrary API integration, custom tags, and extension hooks.

---

<p align="center">
  <em>Agrarian Reform is developed as part of the Instant Gratification and Vanilla Outsider Collections.</em>
</p>
