# 🌾 农业改革：生机大地 (Agrarian Reform)

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> **“世界不应当只在你注视它时才运转。”**

欢迎查阅 **农业改革 (Agrarian Reform)** 官方 Wiki！**Agrarian Reform** 是一款兼具农业模拟深度与沉浸感的模组，它将原版 Minecraft 依赖玩家临场挂机的农田机制，重塑为一个拥有持久生机、自主演进的生态系统。本模组引入了 **时空连续体 (The Continuum)**（基于次区块调色板优化的离线作物补算引擎）、**通用模组作物自动注册**（全自动识别并支持任意第三方模组作物的动态倍率微调）、深度耕地物理与玩家/宠物防踩踏选择性保护、真实的切比雪夫同心水动力学，以及混耕生物多样性生长加成。

> 📌 **代码仓库源码声明**：本 Wiki 中的文档反映了**代码仓库当前的最新源码状态**，可能包含尚未在 CurseForge 和 Modrinth 上发布的开发中功能或最新提交。

---

## 🛠️ 模组概览与快速技术规范

| 参数 | 规范详情 |
| :--- | :--- |
| **模组标识符 (Mod ID)** | `agrarian_reform` |
| **支持游戏版本** | Minecraft 26.2 (`>=26.2-`), Minecraft 26.3 (`>=26.3-` / `26.3-snapshot-6`) |
| **当前模组版本** | `2.2.18+26.2` / `2.2.18+26.3` |
| **模组加载器** | Fabric Loader (`>=0.16.9`) |
| **目标运行环境** | Java 25 (严格规范) |
| **核心前置依赖** | Fabric API (`*`), DasikLibrary (`>=1.8.3`) |
| **可选集成模组** | ModMenu (`*`), YetAnotherConfigLib v3 (`*`) |
| **作者与主管** | **Dasik (Rifaditya)** |
| **开源协议** | GNU General Public License v3.0 (GPLv3) |

---

## 📦 Minecraft 版本目录

* [[Minecraft 26.2 与 26.3 指南|zh_cn-Minecraft-26.2-Guide]] — 针对现代 Minecraft 26.2 与 26.3 稳定构建版本的完整综合指南。
* [[版本兼容性与支持周期|zh_cn-Version-Compatibility]] — 版本支持矩阵、开放下界版本依赖与 Knot 类加载器安全保护。

---

## 🎮 玩家与功能指南目录

* [[时空连续体 (离线生长模拟)|zh_cn-The-Continuum-Offline-Persistence]] — 次区块调色板预过滤、卸载时间戳、30天陈旧时间戳修剪上限、节流模拟队列与零磁盘写入保护。
* [[作物注册表与通用作物|zh_cn-Plant-Registry-and-Crop-Types]] — $O(1)$ 动态作物发现引擎、方块属性探测、`#c:crops` 标签索引与独立倍率配置。
* [[水动力学与灌溉系统|zh_cn-Hydro-Dynamics-and-Irrigation]] — 切比雪夫同心方环毛细水力浸润（水源 8 格、流动水 4 格）、3D 立体高程 ($y \in [-1, 1]$) 与纯水模式。
* [[耕地韧性与防践踏机制|zh_cn-Soil-Resilience-and-Trample-Logic]] — 穿戴皮革靴子/摔落保护的柔步保护、赤脚快速跳过，以及玩家/宠物专属防踩保护。
* [[右键收获与自动补种|zh_cn-Right-Click-Harvest-and-Replanting]] — 6 维交互防护、主手防抖、潜行放置穿透与多余产物自动掉落。
* [[混耕与生物多样性加成|zh_cn-Polyculture-and-Biodiversity]] — 多样化混作种植激励与 +10% 基础生长概率加成。
* [[播种与草方块培植|zh_cn-Seed-Sowing-and-Grass-Cultivation]] — 手持农业种子右键泥土直接培植自然草方块。
* [[通用骨粉机制|zh_cn-Universal-Bone-Meal]] — 为甘蔗、仙人掌、下界疣与藤蔓等非原生作物提供骨粉催熟支持。
* [[全局生长倍率与独立调谐|zh_cn-Global-Growth-Multiplier]] — 服务器全局及单作物独立调谐：0%（禁用）、-1（冻结）、100%（原版基准）、加速（>100%）。
* [[性能优化与队列节流|zh_cn-Performance-and-Queue-Throttling]] — 服务器 Tick 预算保护 (`CROPS_PER_TICK = 5`)、调色板快速排查与无锁任务队列。
* [[游戏规则 (GameRules) 参考|zh_cn-GameRules]] — 完整收录 15 项命名空间 `agrarian_reform:*` 游戏规则、动态作物规则与临时诊断开关。
* [[Brigadier 命令与管理|zh_cn-Commands]] — Brigadier 原生命令体系管理与实时运行时配置覆写。
* [[双层配置系统|zh_cn-Configuration]] — Schema v2 JSON 模板、脏标记自动持久化与 3 标签页 YACL 客户端界面。
* [[美学表现与环境音效|zh_cn-Aesthetics-and-Ambient-Feedback]] — 作物穿行沙沙音效、活力绿色粒子迸发与清晨微露视觉渲染。
* [[进度与成就系统|zh_cn-Advancements]] — 与原版“农牧业”进度树自然无缝契合。

---

## 💻 开发者参考目录

* [[开发者环境与编译指南|zh_cn-Developer-Setup-and-Building]] — 基于 JDK 25、Gradle 9.3+、Loom 1.15+ 的编译流程与无头 JUnit 5 单元测试。
* [[架构设计与 Mixin 注入参考|zh_cn-Architecture-and-Mixins]] — 源码包结构层次与 Mixin 注入点完整对照表。
* [[API 与附加模组集成|zh_cn-API-and-Addon-Integration]] — DasikLibrary API 桥接、自定义标签（`#agrarian_reform:soft_step_boots`）与扩展钩子。

---

<p align="center">
  <em>Agrarian Reform 是即时满足与原版局外人集合模组 (Instant Gratification & Vanilla Outsider Collections) 的核心成员。</em>
</p>
