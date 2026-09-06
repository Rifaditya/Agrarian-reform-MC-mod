# 🏆 발전 과제 및 게임플레이 진행

**Agrarian Reform** adheres to a clean, non-intrusive design philosophy. It does **not** register custom advancement popups or custom advancement JSON files (`data/agrarian_reform/advancements/`), ensuring your HUD remains uncluttered and free of redundant achievement notifications.

---

## 🌾 바닐라 '농업' 발전 과제와의 자연스러운 연동

Because Agrarian Reform enhances vanilla agriculture using standard block states, fluid interactions, and item tags, all vanilla **Husbandry** advancements progress naturally as you farm:

| Vanilla Advancement | Trigger Condition | Agrarian Reform Interaction |
| :--- | :--- | :--- |
| **A Seedy Place** | Plant any agricultural seed on farmland. | Functions normally when sowing crops or using seeds to cultivate grass on dirt (`seeds_grow_grass`). |
| **A Balanced Diet** | Consume all edible food items. | Harvested crops from right-click harvesting (`right_click_harvest`) qualify natively. |
| **A Serious Dedication** | Fully use up a Hoe or obtain a Netherite Hoe. | Soil resilience and Soft Step mechanics protect farmland from unnecessary tilling rework. |

---

## ⚙️ 소스 코드 기반 기술 검증

* **Data Pack Path**: `src/main/resources/data/agrarianreform/`
* **Registered Advancement JSONs**: `0` (Zero custom advancement files)
* **Design Track**: Instant Gratification & Vanilla Outsider Collections (Pastoral Immersion)

---

*See also: [[게임 규칙 (GameRules) 레퍼런스|ko_kr-GameRules]] and [[다모작 및 생물다양성 보너스|ko_kr-Polyculture-and-Biodiversity]]*.
