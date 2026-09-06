# 📦 Minecraft 26.2 & 26.3 モダンガイド

The **Minecraft 26.2** and **Minecraft 26.3** releases are the primary target versions for **Agrarian Reform**. Built natively on **Java 25**, **Fabric Loader 0.16.9+**, and **Fabric API**, these builds provide full stability, universal modded crop auto-population, and zero legacy obfuscation mapping overhead.

---

## 📋 バージョンメタデータ情報ボックス

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

## 🚀 インストールとセットアップ

1. **Fabric Loader のインストール**: Minecraft 26.2 または 26.3 向けに Fabric Loader バージョン `0.16.9` 以上をインストールします。
2. **前提ライブラリの配置**:
   - **Fabric API** を `.minecraft/mods/` フォルダに配置します。
   - **DasikLibrary** (`1.8.3` 以上) を `.minecraft/mods/` フォルダに配置します。
3. **Agrarian Reform の導入**: `agrarian-reform-2.2.18+26.2.jar`（または `+26.3.jar`）を `.minecraft/mods/` に配置します。
4. **クライアント向け任意拡張**: **ModMenu** および **YetAnotherConfigLib v3 (YACL)** を導入すると、ゲーム内 3 タブ GUI 設定画面が利用可能になります。

---

## 🔑 26.2 および 26.3 の主要機能

* **汎用作物の全自動検出・登録**: 外部 Mod 作物 (`#c:crops`) を自動認識し、動的 GameRule および GUI 成長倍率スライダーを生成。
* **コンティニュアム (The Continuum)**: サブチャンクパレットによる高速オフライン成長追従と、30日経過タイムスタンプの自動刈り込み。
* **チェビシェフ同心円水力学**: 水源ブロックから 8 ブロックの毛細水和範囲、外拡同心正方形走査、純水限定モード。
* **耕地耐久性と選択的保護**: 革のブーツ/落下耐性によるソフトステップ、プレイヤーおよび飼いならされたペット専用保護設定 (`trample_immunity_players_only`)。
* **6次元収穫インタラクションガード**: メインハンド判定、スニーク配置バイパスを備えた右クリック自動収穫・再植。
* **混作生物多様性ボーナス**: 隣接ブロックに異なる種類の作物を配置することで +10% の成長確率向上。

---

## 🔒 ModVersionGuard による安全保護

Agrarian Reform は、Mod の初期化処理 (`onInitialize()`) において依存関係ゼロの `ModVersionGuard` クラス検証を実行します：
```java
ModVersionGuard.checkClass("Agrarian Reform", "net.minecraft.world.entity.EntityTypes");
```
必要なコアクラスが存在しない非互換の Minecraft バージョンが読み込まれた場合、Knot クラスローダーが即座に不整合を検知し、ゲームループ中のサイレントクラッシュを防ぎ、ログに明瞭なエラー診断を出力します。

---

*関連ページ：[[バージョン互換性とサポートライフサイクル|ja_jp-Version-Compatibility]] および [[開発環境の構築とビルド|ja_jp-Developer-Setup-and-Building]]*。
