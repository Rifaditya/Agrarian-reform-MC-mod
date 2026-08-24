# 🔄 Version Compatibility & Support Lifecycle

This page documents the version compatibility matrix, forward-compatibility design rules, and dependency management policies for **Agrarian Reform**.

> 📌 **Repository Source Disclaimer**: The documentation in this Wiki reflects the **current source code state in the repository**, which may include recent unreleased commits or developmental features ahead of public release builds on CurseForge and Modrinth.

---

## 📊 Minecraft Version Compatibility Matrix

| Minecraft Version | Mod Build | Status | Java Target | Fabric Loader | Dependency Constraint |
| :--- | :--- | :--- | :--- | :--- | :--- |
| **Minecraft 26.2** | `2.2.4+26.2` | **Active Mainline** | Java 25 | `>=0.16.9` | `"minecraft": ">=26.2-"` |
| **Minecraft 26.1.2**| `1.2.7+R-26.1.2` | Deprecated / Archived | Java 25 | `>=0.15.0` | `"minecraft": ">=26.1.2"` |

---

## 🛡️ 1 Jar 1 Version & Open-Ended Bounds

Agrarian Reform strictly follows the **1 Jar 1 Version Law** and **Forward Compatibility Mandate**:
1. **Dedicated Target Build**: Each compiled JAR is specifically built, tested, and archived for a targeted Minecraft release (e.g. `agrarian-reform-2.2.4+26.2.jar`).
2. **Open-Ended Lower Bound**: In `fabric.mod.json`, the Minecraft dependency is specified as:
   ```json
   "minecraft": ">=26.2-"
   ```
   This prevents Fabric Loader pre-release locks when playing on minor point releases or future patch updates while maintaining strict compilation safety.
3. **Mod Dependency Wildcards**: Dependencies like `fabric-api` and `dasik-library` use open-ended lower bounds or wildcards (`"dasik-library": "*"`) to avoid false version lock crashes when libraries increment patch versions.

---

## 🔄 Legacy Version Migration (2024 1.21.x vs 2026 26.x)

* **Annual Drop Versioning**: Minecraft shifted in 2026 to the Annual Drop system (`Year.Drop.Patch`). Version 26.2 represents the 2nd Drop of 2026.
* **Non-Obfuscated Era**: 26.x codebases operate strictly on non-obfuscated Mojang mappings. Legacy Yarn mapping terms (`world`, `getWorld`, `WorldSettings`) are completely obsolete and banned.
* **SavedData Modernization**: Persistent world state uses Mojang `SavedDataType` records with explicit Codecs rather than old NBT compound methods:
  ```java
  public static final SavedDataType<ContinuumData> TYPE = new SavedDataType<>(
      Identifier.fromNamespaceAndPath("agrarian_reform", "continuum_data"),
      ContinuumData::create,
      Codec.unboundedMap(Codec.STRING, Codec.LONG)...,
      DataFixTypes.SAVED_DATA_MAP_DATA
  );
  ```

---

*See also: [[Minecraft 26.2 Guide|Minecraft-26.2-Guide]] and [[API & Addon Integration|API-and-Addon-Integration]]*.
