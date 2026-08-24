# 📦 Minecraft 26.2 Guide

The **Minecraft 26.2** release is the primary target version for **Agrarian Reform**. Built natively on **Java 25**, **Fabric Loader 0.16.9+**, and **Fabric API 0.150.1+26.2**, this build provides full stability, seamless forward-compatibility, and zero legacy obfuscation mapping overhead.

---

## 📋 Version Metadata Infobox

| Property | Value |
| :--- | :--- |
| **Minecraft Target** | `26.2` (Stable) |
| **Mod Version** | `2.2.4+26.2` |
| **JAR File** | `agrarian-reform-2.2.4+26.2.jar` |
| **Fabric Loader** | `>=0.16.9` |
| **Fabric API** | `*` |
| **DasikLibrary** | `>=1.8.3` |
| **JDK Required** | Java 25 |
| **Dependency Constraint** | `"minecraft": ">=26.2-"` |

---

## 🚀 Installation & Setup

1. **Install Fabric Loader**: Download and install Fabric Loader version `0.16.9` or higher for Minecraft 26.2.
2. **Install Required Libraries**:
   - Place **Fabric API** (`0.150.1+26.2` or later) into your `.minecraft/mods/` directory.
   - Place **DasikLibrary** (`1.8.3` or later) into your `.minecraft/mods/` directory.
3. **Install Agrarian Reform**: Drop `agrarian-reform-2.2.4+26.2.jar` into your `.minecraft/mods/` directory.
4. **Optional Client Enhancement**: Install **ModMenu** and **YetAnotherConfigLib v3 (YACL)** for in-game configuration screens.

---

## 🔑 Key Features in 26.2

* **Offline Continuum Persistence**: Full time-delta catch-up growth for both standard crops (`CropBlock`) and non-standard agricultural plants (`SugarCane`, `Cactus`, `NetherWart`, `Cocoa`, `Vine`, `Sapling`, `SweetBerryBush`).
* **Advanced Hydro-Dynamics**: 8-block water source irrigation range, 4-block flowing water range, and sky-exposed rain hydration.
* **Soft Step Trample Protection**: Protect farmland using Leather Boots or Feather Falling enchantments.
* **Polyculture Biodiversity**: 10% growth speed bonus when planting diverse crops in adjacent blocks.
* **QoL Harvest & Sowing**: Right-click to harvest/replant mature crops; right-click dirt with seeds to grow grass.

---

## 🔒 ModVersionGuard Protection

Agrarian Reform includes a zero-dependency `ModVersionGuard` check executed during early startup (`onInitialize()`):
```java
ModVersionGuard.checkClass("Agrarian Reform", "net.minecraft.world.entity.EntityTypes");
```
If an incompatible Minecraft release missing core 26.2 classes is loaded, the game will halt immediately with a clean, human-readable error log explaining the mismatch rather than crashing silently during world gen or tick loops.

---

*See also: [[Version Compatibility|Version-Compatibility]] and [[Developer Setup & Building|Developer-Setup-and-Building]]*.
