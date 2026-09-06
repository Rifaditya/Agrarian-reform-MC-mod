# 🌾 農業改革：生機大地 (Agrarian Reform)

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> **「世界不應當只在你注視它時才運轉。」**

歡迎查閱 **農業改革 (Agrarian Reform)** 官方 Wiki！**Agrarian Reform** 是一款兼具農業模擬深度與沉浸感的模組，它將原版 Minecraft 依賴玩家臨場掛機的農田機制，重塑為一個擁有持久生機、自主演進的生態系統。本模組引入了 **時空連續體 (The Continuum)**（基於次區塊調色盤最佳化的離線作物補算引擎）、**通用模組作物自動註冊**（全自動識別並支援任意第三方模組作物的動態倍率微調）、深度耕地物理與玩家/寵物防踩踏選擇性保護、真實的切比雪夫同心水動力學，以及混耕生物多樣性生長加成。

> 📌 **代碼倉庫源碼聲明**：本 Wiki 中的文件反映了**代碼倉庫當前的最新源碼狀態**，可能包含尚未在 CurseForge 和 Modrinth 上發布的開發中功能或最新提交。

---

## 🛠️ 模組概覽與快速技術規範

| 參數 | 規範詳情 |
| :--- | :--- |
| **模組識別碼 (Mod ID)** | `agrarian_reform` |
| **支援遊戲版本** | Minecraft 26.2 (`>=26.2-`), Minecraft 26.3 (`>=26.3-` / `26.3-snapshot-6`) |
| **當前模組版本** | `2.2.18+26.2` / `2.2.18+26.3` |
| **模組載入器** | Fabric Loader (`>=0.16.9`) |
| **目標運行環境** | Java 25 (嚴格規範) |
| **核心前置相依** | Fabric API (`*`), DasikLibrary (`>=1.8.3`) |
| **可選整合模組** | ModMenu (`*`), YetAnotherConfigLib v3 (`*`) |
| **作者與主管** | **Dasik (Rifaditya)** |
| **開源協議** | GNU General Public License v3.0 (GPLv3) |

---

## 📦 Minecraft 版本目錄

* [[Minecraft 26.2 與 26.3 指南|zh_tw-Minecraft-26.2-Guide]] — 針對現代 Minecraft 26.2 與 26.3 穩定建置版本的完整綜合指南。
* [[版本相容性與支援週期|zh_tw-Version-Compatibility]] — 版本支援矩陣、開放下界版本相依與 Knot 類別載入器安全防護。

---

## 🎮 玩家與功能指南目錄

* [[時空連續體 (離線生長模擬)|zh_tw-The-Continuum-Offline-Persistence]] — 次區塊調色盤預過濾、卸載時間戳記、30天陳舊時間戳記修剪上限、節流模擬佇列與零磁碟寫入保護。
* [[作物註冊表與通用作物|zh_tw-Plant-Registry-and-Crop-Types]] — $O(1)$ 動態作物發現引擎、方塊屬性探測、`#c:crops` 標籤索引與獨立倍率設定。
* [[水動力學與灌溉系統|zh_tw-Hydro-Dynamics-and-Irrigation]] — 切比雪夫同心方環毛細水力浸潤（水源 8 格、流動水 4 格）、3D 立身高程 ($y \in [-1, 1]$) 與純水模式。
* [[耕地韌性與防踐踏機制|zh_tw-Soil-Resilience-and-Trample-Logic]] — 穿戴皮革靴子/輕盈保護的柔步保護、赤腳快速略過，以及玩家/寵物專屬防踩保護。
* [[右鍵收穫與自動補種|zh_tw-Right-Click-Harvest-and-Replanting]] — 6 維互動防護、主手防抖、潛行放置穿透與多餘產物自動掉落。
* [[混耕與生物多樣性加成|zh_tw-Polyculture-and-Biodiversity]] — 多樣化混作種植激勵與 +10% 基礎生長機率加成。
* [[播種與草方塊培植|zh_tw-Seed-Sowing-and-Grass-Cultivation]] — 手持農業種子右鍵泥土直接培植自然草方塊。
* [[通用骨粉機制|zh_tw-Universal-Bone-Meal]] — 為甘蔗、仙人掌、地獄疙瘩與藤蔓等非原生作物提供骨粉催熟支援。
* [[全域生長倍率與獨立調諧|zh_tw-Global-Growth-Multiplier]] — 伺服器全域及單作物獨立調諧：0%（停用）、-1（凍結）、100%（原版基準）、加速（>100%）。
* [[效能最佳化與佇列節流|zh_tw-Performance-and-Queue-Throttling]] — 伺服器 Tick 預算保護 (`CROPS_PER_TICK = 5`)、調色盤快速篩檢與無鎖任務佇列。
* [[遊戲規則 (GameRules) 參考|zh_tw-GameRules]] — 完整收錄 15 項命名空間 `agrarian_reform:*` 遊戲規則、動態作物規則與臨時診斷開關。
* [[Brigadier 指令與管理|zh_tw-Commands]] — Brigadier 原生指令體系管理與即時運行時設定覆寫。
* [[雙層設定系統|zh_tw-Configuration]] — Schema v2 JSON 範本、髒標記自動持久化與 3 分頁 YACL 客戶端介面。
* [[美學表現與環境音效|zh_tw-Aesthetics-and-Ambient-Feedback]] — 作物穿行沙沙音效、活力綠色粒子迸發與清晨微露視覺渲染。
* [[進度與成就系統|zh_tw-Advancements]] — 與原版「農牧業」進度樹自然無縫契合。

---

## 💻 開發者參考目錄

* [[開發者環境與編譯指南|zh_tw-Developer-Setup-and-Building]] — 基於 JDK 25、Gradle 9.3+、Loom 1.15+ 的編譯流程與無頭 JUnit 5 單元測試。
* [[架構設計與 Mixin 注入參考|zh_tw-Architecture-and-Mixins]] — 源碼套件結構層次與 Mixin 注入點完整對照表。
* [[API 與附加模組整合|zh_tw-API-and-Addon-Integration]] — DasikLibrary API 橋接、自訂標籤（`#agrarian_reform:soft_step_boots`）與擴充鉤子。

---

<p align="center">
  <em>Agrarian Reform 是即時滿足與原版局外人集合模組 (Instant Gratification & Vanilla Outsider Collections) 的核心成員。</em>
</p>
