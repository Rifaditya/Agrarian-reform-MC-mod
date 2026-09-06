# 🔄 Version Compatibility & Support Lifecycle

This page documents the version compatibility matrix, forward-compatibility design rules, and dependency management policies for **Agrarian Reform**.

> 📌 **Repository Source Disclaimer**: The documentation in this Wiki reflects the **current source code state in the repository**, which may include recent unreleased commits or developmental features ahead of public release builds on CurseForge and Modrinth.

---

## 📊 Minecraft Version Compatibility Matrix

| Minecraft Version | Mod Build | Status | Java Target | Fabric Loader | Dependency Constraint |
| :--- | :--- | :--- | :--- | :--- | :--- |
| **Minecraft 26.3** | `2.2.18+26.3` | **Active Mainline (Snapshot 6+)** | Java 25 | `>=0.16.9` | `"minecraft": ">=26.3-"` |
| **Minecraft 26.2** | `2.2.18+26.2` | **Active Mainline (Stable)** | Java 25 | `>=0.16.9` | `"minecraft": ">=26.2-"` |
| **Minecraft 26.1.2**| `1.2.7+R-26.1.2` | Deprecated / Archived | Java 25 | `>=0.15.0` | `"minecraft": ">=26.1.2"` |

---

## 🛡️ 1 Jar 1 Version & Open-Ended Bounds

Agrarian Reform strictly follows the **1 Jar 1 Version Law** and **Forward Compatibility Mandate**:
1. **Dedicated Target Build**: Each compiled JAR is specifically built, tested, and archived for a targeted Minecraft release (e.g. `agrarian-reform-2.2.18+26.2.jar` and `agrarian-reform-2.2.18+26.3.jar`).
2. **Open-Ended Lower Bound**: In `fabric.mod.json`, the Minecraft dependency is specified with open bounds:
   ```json
   "minecraft": ">=26.2-"
   ```
   and for 26.3:
   ```json
   "minecraft": ">=26.3-"
   ```
   This prevents Fabric Loader pre-release locks when playing on minor point releases or future patch updates while maintaining strict compilation safety.
3. **Mod Dependency Wildcards**: Dependencies like `fabric-api` and `dasik-library` use open-ended lower bounds or wildcards (`"dasik-library": "*"`) to avoid false version lock crashes when libraries increment patch versions.

---

## 🔄 Modern Architecture & SavedData Codecs

* **Annual Drop Versioning**: Minecraft shifted in 2026 to the Annual Drop system (`Year.Drop.Patch`). Version 26.2 and 26.3 represent the modern active drops.
* **Non-Obfuscated Era**: 26.x codebases operate strictly on non-obfuscated Mojang mappings. Legacy Yarn mapping terms (`world`, `getWorld`, `WorldSettings`) are completely obsolete and banned.
* **SavedData Modernization**: Persistent world state uses Mojang `SavedDataType` records with explicit Codecs rather than old NBT compound methods:
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

*See also: [[MC 26.2 & 26.3 Guide|Minecraft-26.2-Guide]] and [[API & Addon Integration|API-and-Addon-Integration]]*.
