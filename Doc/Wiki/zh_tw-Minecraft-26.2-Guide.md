# 📦 Minecraft 26.2 與 26.3 現代化指南

The **Minecraft 26.2** and **Minecraft 26.3** releases are the primary target versions for **Agrarian Reform**. Built natively on **Java 25**, **Fabric Loader 0.16.9+**, and **Fabric API**, these builds provide full stability, universal modded crop auto-population, and zero legacy obfuscation mapping overhead.

---

## 📋 版本元資料資訊框

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

## 🚀 安裝與環境設定

1. **安裝 Fabric Loader**：下載並安裝適用於 Minecraft 26.2 或 26.3 的 Fabric Loader（`0.16.9` 或更高版本）。
2. **安裝核心前置庫**：
   - 將 **Fabric API** 放入 `.minecraft/mods/` 目錄。
   - 將 **DasikLibrary** (`1.8.3` 或更高版本) 放入 `.minecraft/mods/` 目錄。
3. **安裝 Agrarian Reform**：將 `agrarian-reform-2.2.18+26.2.jar`（或 `+26.3.jar`）放入 `.minecraft/mods/` 目錄。
4. **可選客戶端增強**：安裝 **ModMenu** 和 **YetAnotherConfigLib v3 (YACL)** 以獲得遊戲內 3 分頁圖形設定介面。

---

## 🔑 26.2 與 26.3 核心特色

* **通用作物全自動識別註冊**：零設定自動識別任何模組作物 (`#c:crops`)，動態註冊命名空間 GameRule 並提供 GUI 獨立倍率調節。
* **時空連續體 (The Continuum)**：次區塊調色盤快速預過濾離線生長模擬，具備 30 天超期時間戳記自動修剪。
* **切比雪夫同心水動力學**：水源方塊 8 格毛細濕潤半徑，按同心方環外擴排查，支援可選純水模式。
* **耕地韌性與選擇性防護**：皮革靴子/輕盈保護柔步保護，支援玩家/馴服寵物防踩保護開關 (`trample_immunity_players_only`)。
* **6 維互動收穫守衛**：主手防抖與潛行放置穿透的右鍵收穫並自動補種。
* **混耕生物多樣性**：在相鄰方塊種植不同種類作物時享有 +10% 生長加速加成。

---

## 🔒 ModVersionGuard 版本防崩防護

Agrarian Reform 在模組早期初始化 (`onInitialize()`) 期間執行零相依的 `ModVersionGuard` 類別檢查：
```java
ModVersionGuard.checkClass("Agrarian Reform", "net.minecraft.world.entity.EntityTypes");
```
如果載入了缺失核心類別的不相容 Minecraft 版本，Knot 類別載入器會立即擷取異常，並在日誌中輸出清晰易懂的錯誤診斷資訊，而不是在世界生成或 Tick 迴圈中隱蔽崩潰。

---

*參見：[[版本相容性與支援週期|zh_tw-Version-Compatibility]] 與 [[開發者環境與編譯指南|zh_tw-Developer-Setup-and-Building]]*。
