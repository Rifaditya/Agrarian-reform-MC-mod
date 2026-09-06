# 📦 Руководство по Minecraft 26.2 и 26.3

The **Minecraft 26.2** and **Minecraft 26.3** releases are the primary target versions for **Agrarian Reform**. Built natively on **Java 25**, **Fabric Loader 0.16.9+**, and **Fabric API**, these builds provide full stability, universal modded crop auto-population, and zero legacy obfuscation mapping overhead.

---

## 📋 Инфоблок метаданных версий

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

## 🚀 Установка и настройка

1. **Установите Fabric Loader**: Скачайте и установите Fabric Loader версии `0.16.9` или выше для Minecraft 26.2 или 26.3.
2. **Установите обязательные библиотеки**:
   - Поместите **Fabric API** в папку `.minecraft/mods/`.
   - Поместите **DasikLibrary** (`1.8.3` или новее) в папку `.minecraft/mods/`.
3. **Установите Agrarian Reform**: Поместите `agrarian-reform-2.2.18+26.2.jar` (или `+26.3.jar`) в папку `.minecraft/mods/`.
4. **Опциональные клиентские моды**: Установите **ModMenu** и **YetAnotherConfigLib v3 (YACL)** для доступа к графическому меню настроек с 3 вкладками.

---

## 🔑 Ключевые особенности в 26.2 и 26.3

* **Универсальное автообнаружение культур**: Без дополнительной настройки регистрирует любые сторонние культуры (`#c:crops`), создавая динамические правила GameRule и регуляторы в GUI.
* **Континуум (The Continuum)**: Моделирование офлайн-роста с отсевом пустых субчанков и автоочисткой устаревших меток за 30 дней.
* **Концентрическая гидродинамика Чебышёва**: Радиус полива 8 блоков для источников воды, проверка концентрическими кольцами и режим чистой воды.
* **Устойчивость почвы и выборочная защита**: Мягкий шаг (кожаные ботинки/невесомость) и ограничение защиты игроками/питомцами (`trample_immunity_players_only`).
* **6D-страж сбора урожая**: Сбор ПКМ с защитой от двойного срабатывания и обходом в режиме скрытности.
* **Поликультура и биоразнообразие**: Бонус +10% к скорости роста при посадке разнородных культур на соседних грядках.

---

## 🔒 Защита ModVersionGuard

Agrarian Reform выполняет проверку классов `ModVersionGuard` с нулевыми зависимостями на ранней стадии инициализации (`onInitialize()`):
```java
ModVersionGuard.checkClass("Agrarian Reform", "net.minecraft.world.entity.EntityTypes");
```
Если загружена несовместимая версия Minecraft, в которой отсутствуют нужные классы, загрузчик Knot немедленно обнаружит несоответствие и запишет в журнал понятную диагностическую ошибку вместо внезапного сбоя во время тиков.

---

*См. также: [[Совместимость версий и жизненный цикл|ru_ru-Version-Compatibility]] и [[Настройка среды разработки и сборка|ru_ru-Developer-Setup-and-Building]]*.
