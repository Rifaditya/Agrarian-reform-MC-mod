# 🌾 Agrarian Reform: The Living Earth

> **"The world should not wait for you to watch it."**

Welcome to the official **Agrarian Reform** wiki. **Agrarian Reform** is an agricultural simulation and immersion mod that transforms Minecraft farming from a proximity-based machine into a living, persistent ecosystem. It introduces **The Continuum** (sub-chunk palette-optimized offline growth catch-up), **Universal Crop Auto-Population** (automatic discovery and per-crop multiplier tuning for any modded crop), deep soil management with selective player/pet trample protection, realistic concentric hydro-dynamics, and polyculture biodiversity bonuses.

> 📌 **Repository Source Disclaimer**: The documentation in this Wiki reflects the **current source code state in the repository**, which may include recent unreleased commits or developmental features ahead of public release builds on CurseForge and Modrinth.

---

## 🛠️ Overview & Quick Specifications

| Parameter | Specification |
| :--- | :--- |
| **Mod Identifier** | `agrarian_reform` |
| **Supported Game Versions** | Minecraft 26.2 (`>=26.2-`), Minecraft 26.3 (`>=26.3-` / `26.3-snapshot-6`) |
| **Current Mod Version** | `2.2.18+26.2` / `2.2.18+26.3` |
| **Mod Loader** | Fabric Loader (`>=0.16.9`) |
| **Language Target** | Java 25 (Strict) |
| **Primary Dependencies** | Fabric API (`*`), DasikLibrary (`>=1.8.3`) |
| **Optional Integrations**| ModMenu (`*`), YetAnotherConfigLib v3 (`*`) |
| **Author & Lead** | **Dasik (Rifaditya)** |
| **License** | GNU General Public License v3.0 (GPLv3) |

---

## 📦 Minecraft Versions Directory

* [[MC 26.2 & 26.3 Guide|Minecraft-26.2-Guide]] — Comprehensive guide for modern Minecraft 26.2 and 26.3 release builds.
* [[Version Compatibility|Version-Compatibility]] — Version support matrix, open-ended lower bounds, and Knot classloader safety guards.

---

## 🎮 Player & Feature Guides Directory

* [[The Continuum (Offline Growth)|The-Continuum-Offline-Persistence]] — Sub-chunk palette filtering, unload timestamping, 30-day stale pruning ceiling, queued simulation, and zero-disk-write chunk preservation.
* [[Plant Registry & Universal Crops|Plant-Registry-and-Crop-Types]] — O(1) dynamic crop discovery engine, property inspection, `#c:crops` tag indexing, and per-crop multiplier scaling.
* [[Hydro-Dynamics & Irrigation|Hydro-Dynamics-and-Irrigation]] — Concentric Chebyshev square ring capillary hydration (8 source, 4 flowing), 3D elevation ($y \in [-1, 1]$), and pure water gating.
* [[Soil Resilience & Trample Logic|Soil-Resilience-and-Trample-Logic]] — Soft Step protection with Leather Boots/Feather Falling, bare-foot fast-fail, and player/pet selective entity gating.
* [[Right-Click Harvest & Replant|Right-Click-Harvest-and-Replanting]] — 6-Dimensional interaction guard, main-hand debouncing, sneak placement bypass, and surplus item drops.
* [[Polyculture & Biodiversity|Polyculture-and-Biodiversity]] — Mixed crop planting incentives and 10% growth probability acceleration.
* [[Seed Sowing & Grass Cultivation|Seed-Sowing-and-Grass-Cultivation]] — Sowing seeds on dirt blocks to restore natural grass blocks.
* [[Universal Bone Meal|Universal-Bone-Meal]] — Accelerating growth on non-standard and non-natively bonemealable plants (Cactus, Sugar Cane, Nether Wart, Vines).
* [[Global Growth Multiplier|Global-Growth-Multiplier]] — Server-wide and per-crop speed tuning from 0% (disabled), -1 (frozen), 100% (vanilla), to accelerated (>100%).
* [[Performance & Queue Throttling|Performance-and-Queue-Throttling]] — Server tick budget preservation (`CROPS_PER_TICK = 5`), palette pre-filtering, and lock-free task queues.
* [[GameRules|GameRules]] — Complete listing of 15 namespaced `agrarian_reform:*` rules, dynamic crop rules, and transient debug toggles.
* [[Commands|Commands]] — Brigadier command administration and dynamic configuration overrides.
* [[Configuration|Configuration]] — Schema v2 JSON template configuration, dirty-tracking auto-save, and 3-tab YACL GUI integration.
* [[Aesthetics & Ambient Feedback|Aesthetics-and-Ambient-Feedback]] — Crop rustle audio dynamics, vitality particle sparkles, and morning moisture.
* [[Advancements & Progression|Advancements]] — Native integration with vanilla Husbandry advancements.

---

## 💻 Developer Reference Directory

* [[Developer Setup & Building|Developer-Setup-and-Building]] — Building with JDK 25, Gradle 9.3+, Loom 1.15+, and headless JUnit 5 unit tests (`ContinuumMathTest`, `AgrarianConfigTest`).
* [[Architecture & Mixins|Architecture-and-Mixins]] — Package hierarchy trees and Mixin target class breakdown tables.
* [[API & Addon Integration|API-and-Addon-Integration]] — DasikLibrary API integration, custom datapack tags (`#agrarian_reform:soft_step_boots`), and extension hooks.

---

<p align="center">
  <em>Agrarian Reform is developed as part of the Instant Gratification and Vanilla Outsider Collections.</em>
</p>
