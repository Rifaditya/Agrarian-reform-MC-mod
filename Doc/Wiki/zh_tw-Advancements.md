# 🏆 進度與遊戲歷程系統

**Agrarian Reform** adheres to a clean, non-intrusive design philosophy. It does **not** register custom advancement popups or custom advancement JSON files (`data/agrarian_reform/advancements/`), ensuring your HUD remains uncluttered and free of redundant achievement notifications.

---

## 🌾 與原版「農牧業」進度樹的自然交互

Because Agrarian Reform enhances vanilla agriculture using standard block states, fluid interactions, and item tags, all vanilla **Husbandry** advancements progress naturally as you farm:

| Vanilla Advancement | Trigger Condition | Agrarian Reform Interaction |
| :--- | :--- | :--- |
| **A Seedy Place** | Plant any agricultural seed on farmland. | Functions normally when sowing crops or using seeds to cultivate grass on dirt (`seeds_grow_grass`). |
| **A Balanced Diet** | Consume all edible food items. | Harvested crops from right-click harvesting (`right_click_harvest`) qualify natively. |
| **A Serious Dedication** | Fully use up a Hoe or obtain a Netherite Hoe. | Soil resilience and Soft Step mechanics protect farmland from unnecessary tilling rework. |

---

## ⚙️ 真實代碼技術驗證

* **Data Pack Path**: `src/main/resources/data/agrarianreform/`
* **Registered Advancement JSONs**: `0` (Zero custom advancement files)
* **Design Track**: Instant Gratification & Vanilla Outsider Collections (Pastoral Immersion)

---

*See also: [[遊戲規則 (GameRules) 參考|zh_tw-GameRules]] and [[混耕與生物多樣性加成|zh_tw-Polyculture-and-Biodiversity]]*.
