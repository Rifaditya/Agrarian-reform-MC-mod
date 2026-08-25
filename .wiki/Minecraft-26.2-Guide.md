# 📦 Minecraft 26.2 & 26.3 Modern Guide

The **Minecraft 26.2** and **Minecraft 26.3** releases are the primary target versions for **Agrarian Reform**. Built natively on **Java 25**, **Fabric Loader 0.16.9+**, and **Fabric API**, these builds provide full stability, universal modded crop auto-population, and zero legacy obfuscation mapping overhead.

---

## 📋 Version Metadata Infobox

| Property | Minecraft 26.2 Build | Minecraft 26.3 Build |
| :--- | :--- | :--- |
| **Minecraft Target** | `26.2` (Stable) | `26.3` (Snapshot 6+) |
| **Mod Version** | `2.2.18+26.2` | `2.2.18+26.3` |
| **JAR File** | `agrarian-reform-2.2.18+26.2.jar` | `agrarian-reform-2.2.18+26.3.jar` |
| **Fabric Loader** | `>=0.16.9` | `>=0.16.9` |
| **Fabric API** | `*` | `*` |
| **DasikLibrary** | `>=1.8.3` | `>=1.8.3` |
| **JDK Required** | Java 25 (`release = 25`) | Java 25 (`release = 25`) |
| **Dependency Constraint** | `"minecraft": ">=26.2-"` | `"minecraft": ">=26.3-"` |

---

## 🚀 Installation & Setup

1. **Install Fabric Loader**: Download and install Fabric Loader version `0.16.9` or higher for Minecraft 26.2 or 26.3.
2. **Install Required Libraries**:
   - Place **Fabric API** into your `.minecraft/mods/` directory.
   - Place **DasikLibrary** (`1.8.3` or later) into your `.minecraft/mods/` directory.
3. **Install Agrarian Reform**: Drop `agrarian-reform-2.2.18+26.2.jar` (or `+26.3.jar`) into your `.minecraft/mods/` directory.
4. **Optional Client Enhancement**: Install **ModMenu** and **YetAnotherConfigLib v3 (YACL)** for the in-game 3-tab configuration screen.

---

## 🔑 Key Features in 26.2 & 26.3

* **Universal Crop Auto-Population**: Zero-config auto-discovery of any modded crop (`#c:crops`), registering dynamic namespaced GameRules and GUI multiplier controls.
* **The Continuum**: Sub-chunk palette-filtered offline growth catch-up with 30-day stale timestamp auto-pruning.
* **Concentric Chebyshev Hydro-Dynamics**: 8-block water source irrigation range with concentric square ring evaluation and optional pure water gating.
* **Soil Resilience & Selective Gating**: Soft Step protection (Leather Boots/Feather Falling) with optional player/tamed pet entity restriction (`trample_immunity_players_only`).
* **6D Harvest Guard**: Right-click harvest/replanting with main-hand debouncing and sneak placement bypass.
* **Polyculture Biodiversity**: 10% growth speed bonus when planting diverse crops in adjacent blocks.

---

## 🔒 ModVersionGuard Protection

Agrarian Reform includes a zero-dependency `ModVersionGuard` check executed during early startup (`onInitialize()`):
```java
ModVersionGuard.checkClass("Agrarian Reform", "net.minecraft.world.entity.EntityTypes");
```
If an incompatible Minecraft release missing core classes is loaded, Knot classloader resolution detects the discrepancy immediately, producing a clean, human-readable error log explaining the mismatch rather than crashing silently during world gen or tick loops.

---

*See also: [[Version Compatibility|Version-Compatibility]] and [[Developer Setup & Building|Developer-Setup-and-Building]]*.
