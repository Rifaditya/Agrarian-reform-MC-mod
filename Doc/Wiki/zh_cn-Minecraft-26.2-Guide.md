# 📦 Minecraft 26.2 与 26.3 现代化指南

The **Minecraft 26.2** and **Minecraft 26.3** releases are the primary target versions for **Agrarian Reform**. Built natively on **Java 25**, **Fabric Loader 0.16.9+**, and **Fabric API**, these builds provide full stability, universal modded crop auto-population, and zero legacy obfuscation mapping overhead.

---

## 📋 版本元数据信息框

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

## 🚀 安装与环境配置

1. **安装 Fabric Loader**：下载并安装适用于 Minecraft 26.2 或 26.3 的 Fabric Loader（`0.16.9` 或更高版本）。
2. **安装核心前置库**：
   - 将 **Fabric API** 放入 `.minecraft/mods/` 目录。
   - 将 **DasikLibrary** (`1.8.3` 或更高版本) 放入 `.minecraft/mods/` 目录。
3. **安装 Agrarian Reform**：将 `agrarian-reform-2.2.18+26.2.jar`（或 `+26.3.jar`）放入 `.minecraft/mods/` 目录。
4. **可选客户端增强**：安装 **ModMenu** 和 **YetAnotherConfigLib v3 (YACL)** 以获得游戏内 3 标签页图形配置界面。

---

## 🔑 26.2 与 26.3 核心特色

* **通用作物全自动识别注册**：零配置自动识别任何模组作物 (`#c:crops`)，动态注册命名空间 GameRule 并提供 GUI 独立倍率调节。
* **时空连续体 (The Continuum)**：次区块调色板快速预过滤离线生长模拟，具备 30 天超期时间戳自动修剪。
* **切比雪夫同心水动力学**：水源方块 8 格毛细湿润半径，按同心方环外扩排查，支持可选纯水模式。
* **耕地韧性与选择性防护**：皮革靴子/摔落保护柔步保护，支持玩家/驯服宠物防踩保护开关 (`trample_immunity_players_only`)。
* **6 维交互收获守卫**：主手防抖与潜行放置穿透的右键收获并自动补种。
* **混耕生物多样性**：在相邻方块种植不同种类作物时享有 +10% 生长加速加成。

---

## 🔒 ModVersionGuard 版本防崩防护

Agrarian Reform 在模组早期初始化 (`onInitialize()`) 期间执行零依赖的 `ModVersionGuard` 类检查：
```java
ModVersionGuard.checkClass("Agrarian Reform", "net.minecraft.world.entity.EntityTypes");
```
如果加载了缺失核心类的不兼容 Minecraft 版本，Knot 类加载器会立即捕获异常，并在日志中输出清晰易懂的错误诊断信息，而不是在世界生成或 Tick 循环中隐蔽崩溃。

---

*参见：[[版本兼容性与支持周期|zh_cn-Version-Compatibility]] 与 [[开发者环境与编译指南|zh_cn-Developer-Setup-and-Building]]*。
