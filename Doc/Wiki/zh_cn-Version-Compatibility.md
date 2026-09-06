# 🔄 版本兼容性与生命周期支持

This page documents the version compatibility matrix, forward-compatibility design rules, and dependency management policies for **Agrarian Reform**.

> 📌 **Repository Source Disclaimer**: The documentation in this Wiki reflects the **current source code state in the repository**, which may include recent unreleased commits or developmental features ahead of public release builds on CurseForge and Modrinth.

---

## 📊 Minecraft 版本兼容性矩阵

| Minecraft Version | Mod Build | Status | Java Target | Fabric Loader | Dependency Constraint |
| :--- | :--- | :--- | :--- | :--- | :--- |
| **Minecraft 26.3** | `2.2.18+26.3` | **Active Mainline (Snapshot 6+)** | Java 25 | `>=0.16.9` | `"minecraft": ">=26.3-"` |
| **Minecraft 26.2** | `2.2.18+26.2` | **Active Mainline (Stable)** | Java 25 | `>=0.16.9` | `"minecraft": ">=26.2-"` |
| **Minecraft 26.1.2**| `1.2.7+R-26.1.2` | Deprecated / Archived | Java 25 | `>=0.15.0` | `"minecraft": ">=26.1.2"` |

---

## 🛡️ 1 Jar 1 Version 与开放版本界限

Agrarian Reform 严格遵循 **1 Jar 1 Version 铁律** 与 **向前兼容性规范**：
1. **专用目标构建**：每个编译出的 JAR 专为指定的 Minecraft 大版本构建、验证并归档（如 `agrarian-reform-2.2.18+26.2.jar` 与 `agrarian-reform-2.2.18+26.3.jar`）。
2. **开放式版本下界**：在 `fabric.mod.json` 中，Minecraft 依赖声明采用开放下界：
   ```json
   "minecraft": ">=26.2-"
   ```
   以及针对 26.3：
   ```json
   "minecraft": ">=26.3-"
   ```
   这能够防止在未来补丁发布时发生虚假的 Fabric Loader 预发布锁定，同时保持严格的编译安全性。
3. **通配符依赖安全**：`fabric-api` 和 `dasik-library` 等依赖使用通配符或开放下界（`"dasik-library": "*"`），避免因前置库更新补丁号而产生虚假崩溃。

---

## 🔄 现代架构与 SavedData Codec 序列化

* **年度大版本命名 (Annual Drop)**：Minecraft 自 2026 年起正式转向年度大版本发布体系（`Year.Drop.Patch`）。版本 26.2 与 26.3 代表当前活跃的主线 Drop。
* **反混淆原生时代**：26.x 代码库全面运行在非混淆的 Mojang 官方映射下，旧时代的 Yarn 映射术语（如 `world`, `getWorld`, `WorldSettings`）已被彻底废除。
* **SavedData 现代化序列化**：世界持久化数据采用 Mojang `SavedDataType` 记录与显式 Codec，完全取代了传统的 NBT 复合标签操作：
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

*参见：[[Minecraft 26.2 与 26.3 指南|zh_cn-Minecraft-26.2-Guide]] 与 [[API 与附加模组集成|zh_cn-API-and-Addon-Integration]]*。
