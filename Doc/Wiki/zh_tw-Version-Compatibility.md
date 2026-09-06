# 🔄 版本相容性與生命週期支援

This page documents the version compatibility matrix, forward-compatibility design rules, and dependency management policies for **Agrarian Reform**.

> 📌 **Repository Source Disclaimer**: The documentation in this Wiki reflects the **current source code state in the repository**, which may include recent unreleased commits or developmental features ahead of public release builds on CurseForge and Modrinth.

---

## 📊 Minecraft 版本相容性矩陣

| Minecraft Version | Mod Build | Status | Java Target | Fabric Loader | Dependency Constraint |
| :--- | :--- | :--- | :--- | :--- | :--- |
| **Minecraft 26.3** | `2.2.18+26.3` | **Active Mainline (Snapshot 6+)** | Java 25 | `>=0.16.9` | `"minecraft": ">=26.3-"` |
| **Minecraft 26.2** | `2.2.18+26.2` | **Active Mainline (Stable)** | Java 25 | `>=0.16.9` | `"minecraft": ">=26.2-"` |
| **Minecraft 26.1.2**| `1.2.7+R-26.1.2` | Deprecated / Archived | Java 25 | `>=0.15.0` | `"minecraft": ">=26.1.2"` |

---

## 🛡️ 1 Jar 1 Version 與開放版本界限

Agrarian Reform 嚴格遵循 **1 Jar 1 Version 鐵律** 與 **向前相容性規範**：
1. **專用目標建置**：每個編譯出的 JAR 專為指定的 Minecraft 大版本建置、驗證並歸檔（如 `agrarian-reform-2.2.18+26.2.jar` 與 `agrarian-reform-2.2.18+26.3.jar`）。
2. **開放式版本下界**：在 `fabric.mod.json` 中，Minecraft 相依宣告採用開放下界：
   ```json
   "minecraft": ">=26.2-"
   ```
   以及針對 26.3：
   ```json
   "minecraft": ">=26.3-"
   ```
   這能夠防止在未來修補程式發布時發生虛假的 Fabric Loader 預發布鎖定，同時保持嚴格的編譯安全性。
3. **萬用字元相依安全**：`fabric-api` 和 `dasik-library` 等相依使用萬用字元或開放下界（`"dasik-library": "*"`），避免因前置庫更新修補程式號而產生虛假崩潰。

---

## 🔄 現代架構與 SavedData Codec 序列化

* **年度大版本命名 (Annual Drop)**：Minecraft 自 2026 年起正式轉向年度大版本發布體系（`Year.Drop.Patch`）。版本 26.2 與 26.3 代表當前活躍的主線 Drop。
* **反混淆原生時代**：26.x 代碼庫全面運行在非混淆的 Mojang 官方映射下，舊時代的 Yarn 映射術語（如 `world`, `getWorld`, `WorldSettings`）已被徹底廢除。
* **SavedData 現代化序列化**：世界持久化資料採用 Mojang `SavedDataType` 記錄與顯式 Codec，完全取代了傳統的 NBT 複合標籤操作：
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

*參見：[[Minecraft 26.2 與 26.3 指南|zh_tw-Minecraft-26.2-Guide]] 與 [[API 與附加模組整合|zh_tw-API-and-Addon-Integration]]*。
