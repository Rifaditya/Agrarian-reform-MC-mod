# 🔄 バージョン互換性とサポートライフサイクル

This page documents the version compatibility matrix, forward-compatibility design rules, and dependency management policies for **Agrarian Reform**.

> 📌 **Repository Source Disclaimer**: The documentation in this Wiki reflects the **current source code state in the repository**, which may include recent unreleased commits or developmental features ahead of public release builds on CurseForge and Modrinth.

---

## 📊 Minecraft バージョン互換性マトリクス

| Minecraft Version | Mod Build | Status | Java Target | Fabric Loader | Dependency Constraint |
| :--- | :--- | :--- | :--- | :--- | :--- |
| **Minecraft 26.3** | `2.2.18+26.3` | **Active Mainline (Snapshot 6+)** | Java 25 | `>=0.16.9` | `"minecraft": ">=26.3-"` |
| **Minecraft 26.2** | `2.2.18+26.2` | **Active Mainline (Stable)** | Java 25 | `>=0.16.9` | `"minecraft": ">=26.2-"` |
| **Minecraft 26.1.2**| `1.2.7+R-26.1.2` | Deprecated / Archived | Java 25 | `>=0.15.0` | `"minecraft": ">=26.1.2"` |

---

## 🛡️ 1 Jar 1 Version ポリシーとオープンエンド境界

Agrarian Reform は、厳格な **1 Jar 1 Version ポリシー** と **前方互換性規約** に従います：
1. **対象バージョン専用ビルド**: コンパイルされた各 JAR は特定の Minecraft リリース向けに個別にビルド・テスト・アーカイブされます (`agrarian-reform-2.2.18+26.2.jar` および `agrarian-reform-2.2.18+26.3.jar`)。
2. **オープンエンドな下限定義**: `fabric.mod.json` において、Minecraft のバージョン指定はオープンエンド形式を採用しています：
   ```json
   "minecraft": ">=26.2-"
   ```
   26.3 向け：
   ```json
   "minecraft": ">=26.3-"
   ```
   これによりマイナーパッチ更新時の Fabric Loader の誤ロックを防ぎます。
3. **ライブラリ依存ワイルドカード**: `dasik-library` などの前提 Mod にはワイルドカード (`"*"`) を指定し、パッチ更新時の誤クラッシュを防止します。

---

## 🔄 現代的アーキテクチャと SavedData Codec

* **アニュアルドロップ命名規則**: Minecraft は `Year.Drop.Patch` 形式に移行しました。バージョン 26.2 および 26.3 は現在のアクティブなドロップです。
* **非難読化ランタイム**: 26.x は Mojang 公式マッピング上で動作し、旧式の Yarn マッピング用語は完全に廃止されています。
* **SavedData の近代化**: ワールド保存データには直接的な NBT 処理に代わり、明示的な Codec を備えた Mojang `SavedDataType` レコードが使用されます：
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

*関連ページ：[[Minecraft 26.2 & 26.3 ガイド|ja_jp-Minecraft-26.2-Guide]] および [[API とアドオン統合|ja_jp-API-and-Addon-Integration]]*。
