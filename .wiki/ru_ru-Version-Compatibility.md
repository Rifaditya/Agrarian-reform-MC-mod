# 🔄 Совместимость версий и жизненный цикл

This page documents the version compatibility matrix, forward-compatibility design rules, and dependency management policies for **Agrarian Reform**.

> 📌 **Repository Source Disclaimer**: The documentation in this Wiki reflects the **current source code state in the repository**, which may include recent unreleased commits or developmental features ahead of public release builds on CurseForge and Modrinth.

---

## 📊 Матрица совместимости версий Minecraft

| Minecraft Version | Mod Build | Status | Java Target | Fabric Loader | Dependency Constraint |
| :--- | :--- | :--- | :--- | :--- | :--- |
| **Minecraft 26.3** | `2.2.18+26.3` | **Active Mainline (Snapshot 6+)** | Java 25 | `>=0.16.9` | `"minecraft": ">=26.3-"` |
| **Minecraft 26.2** | `2.2.18+26.2` | **Active Mainline (Stable)** | Java 25 | `>=0.16.9` | `"minecraft": ">=26.2-"` |
| **Minecraft 26.1.2**| `1.2.7+R-26.1.2` | Deprecated / Archived | Java 25 | `>=0.15.0` | `"minecraft": ">=26.1.2"` |

---

## 🛡️ 1 Jar 1 Version и открытые границы версий

Agrarian Reform строго придерживается политики **1 Jar 1 Version** и правил **прямой совместимости**:
1. **Индивидуальная сборка**: Каждый скомпилированный JAR собирается и тестируется под конкретный целевой релиз Minecraft (`agrarian-reform-2.2.18+26.2.jar` и `agrarian-reform-2.2.18+26.3.jar`).
2. **Открытые нижние границы**: В `fabric.mod.json` зависимость Minecraft задается открытым диапазоном:
   ```json
   "minecraft": ">=26.2-"
   ```
   и для 26.3:
   ```json
   "minecraft": ">=26.3-"
   ```
   Это предотвращает ложные блокировки Fabric Loader при игре на минорных патчах.
3. **Свободные зависимости**: Зависимости `fabric-api` и `dasik-library` используют открытые границы (`"*"`), предотвращая конфликты при обновлении библиотек.

---

## 🔄 Современная архитектура и SavedData Codec

* **Ежегодная система релизов**: Minecraft перешел на систему `Year.Drop.Patch`. Версии 26.2 и 26.3 представляют собой актуальные релизы.
* **Эра без обфускации**: Базы кода 26.x работают на официальных маппингах Mojang. Устаревшие маппинги Yarn (`world`, `getWorld`) полностью удалены.
* **Современный SavedData**: Сохранение данных мира использует `SavedDataType` с кодеками Codec вместо устаревшего ручного разбора NBT:
```java
public static final SavedDataType<ContinuumData> TYPE = new SavedDataType<>(
    Identifier.fromNamespaceAndPath("agrarian_reform", "continuum"),
    ContinuumData::create,
    Codec.unboundedMap(Codec.STRING, Codec.LONG).xmap(
        map -> {
            ContinuumData data = new ContinuumData();
            map.forEach((k, v) -> data.timestamps.put(Long.parseLong(k), v));
            return data;
        },
        data -> {
            Map<String, Long> map = new HashMap<>();
            data.timestamps.forEach((k, v) -> map.put(k.toString(), v));
            return map;
        }
    ),
    DataFixTypes.SAVED_DATA_MAP_DATA
);
```

---

*См. также: [[Руководство по Minecraft 26.2 и 26.3|ru_ru-Minecraft-26.2-Guide]] и [[API и интеграция аддонов|ru_ru-API-and-Addon-Integration]]*.
