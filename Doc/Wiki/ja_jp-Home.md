# 🌾 Agrarian Reform: 生きている大地

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> **「世界は、あなたが見ているときだけ動くべきではない。」**

**Agrarian Reform** 公式 Wiki へようこそ！**Agrarian Reform** は、Minecraft の農業をプレイヤーの近接待機に依存した作業から、自律的に維持・成長する持続可能な生態系へと再構築する本格農業シミュレーション＆没入感向上 Mod です。本 Mod では、**コンティニュアム (The Continuum)**（サブチャンクパレット最適化によるオフライン成長再現エンジン）、**汎用 Mod 作物自動登録**（あらゆる外部作物の動的検出と個別成長倍率調整）、プレイヤーおよび飼いならされたペットを保護する選択的踏み荒らし防止機能、チェビシェフ同心円水力学、そして混作による生物多様性ボーナスが導入されます。

> 📌 **リポジトリソースコードに関する注意事項**：本 Wiki の記載内容は、**リポジトリ内の最新ソースコード状態**を反映しており、CurseForge や Modrinth での公開リリース前の開発中機能や最新コミットが含まれる場合があります。

---

## 🛠️ Mod 概要と仕様一覧

| パラメータ | 仕様詳細 |
| :--- | :--- |
| **Mod ID** | `agrarian_reform` |
| **対応ゲームバージョン** | Minecraft 26.2 (`>=26.2-`), Minecraft 26.3 (`>=26.3-` / `26.3-snapshot-6`) |
| **現在の Mod バージョン** | `2.2.18+26.2` / `2.2.18+26.3` |
| **Mod ローダー** | Fabric Loader (`>=0.16.9`) |
| **開発言語環境** | Java 25 (厳格適用) |
| **必須前提ライブラリ** | Fabric API (`*`), DasikLibrary (`>=1.8.3`) |
| **任意連携 Mod** | ModMenu (`*`), YetAnotherConfigLib v3 (`*`) |
| **作者・開発リーダー** | **Dasik (Rifaditya)** |
| **ライセンス** | GNU General Public License v3.0 (GPLv3) |

---

## 📦 Minecraft バージョン一覧

* [[Minecraft 26.2 & 26.3 ガイド|ja_jp-Minecraft-26.2-Guide]] — 最新の Minecraft 26.2 および 26.3 安定ビルド向け総合ガイド。
* [[バージョン互換性とサポートライフサイクル|ja_jp-Version-Compatibility]] — バージョン対応マトリクス、オープンエンド依存関係、Knot クラスローダー安全性ガード。

---

## 🎮 プレイヤー向け機能・メカニクスガイド

* [[コンティニュアム (オフライン成長持続)|ja_jp-The-Continuum-Offline-Persistence]] — サブチャンクパレット事前選別、アンロード時タイムスタンプ、30日上限自動刈り込み、キュー分散処理、ディスク書き込みゼロ最適化。
* [[作物レジストリと汎用作物|ja_jp-Plant-Registry-and-Crop-Types]] — $O(1)$ 動的作物検出、ブロックプロパティ検査、`#c:crops` タグ索引、作物別成長倍率設定。
* [[水力学と灌漑システム|ja_jp-Hydro-Dynamics-and-Irrigation]] — チェビシェフ同心正方形リング水和（水源 8 ブロック、水流 4 ブロック）、3D 高低差 ($y \in [-1, 1]$)、純水限定モード。
* [[耕地耐久性と踏み荒らし保護|ja_jp-Soil-Resilience-and-Trample-Logic]] — 革のブーツ/落下耐性によるソフトステップ保護、裸足高速バイパス、プレイヤー・ペット保護選別。
* [[右クリック収穫と自動再植|ja_jp-Right-Click-Harvest-and-Replanting]] — 6次元インタラクションガード、メインハンド誤作動防止、スニーク設置バイパス、右クリック収穫。
* [[混作栽培と生物多様性ボーナス|ja_jp-Polyculture-and-Biodiversity]] — 多種作物の混作配置インセンティブと +10% 成長確率加算ボーナス。
* [[種蒔きと草ブロック育成|ja_jp-Seed-Sowing-and-Grass-Cultivation]] — 農業の種を土ブロックに使用して自然な草ブロックへと復元。
* [[汎用骨粉システム|ja_jp-Universal-Bone-Meal]] — サトウキビ、サボテン、ネザーウォート、ツタに対する骨粉肥料サポートの拡張。
* [[グローバル成長倍率と個別調整|ja_jp-Global-Growth-Multiplier]] — 全体および作物別の個別速度調整：0%（停止）、-1（凍結）、100%（バニラ基準）、高速化（>100%）。
* [[パフォーマンスとキュー調整|ja_jp-Performance-and-Queue-Throttling]] — サーバー Tick 負荷保護 (`CROPS_PER_TICK = 5`)、ロックフリータスクキュー。
* [[ゲームルール (GameRules) リファレンス|ja_jp-GameRules]] — 15 個の `agrarian_reform:*` 名前空間ルール、動的ルール、診断用一時設定の全一覧。
* [[Brigadier コマンドと管理|ja_jp-Commands]] — Brigadier コマンドツリーによるリアルタイム管理とゲーム内設定変更。
* [[2層設定システム|ja_jp-Configuration]] — Schema v2 JSON 構成テンプレート、ダーティトラッキング保存、3タブ YACL GUI 設定。
* [[演出と環境フィードバック|ja_jp-Aesthetics-and-Ambient-Feedback]] — 作物接触時の擦れ音、活力パーティクル演出、朝露の湿潤ビジュアル表現。
* [[進捗システム連携|ja_jp-Advancements]] — バニラの「農業」進捗ツリーとの自然な連動。

---

## 💻 開発者向け技術リファレンス

* [[開発環境の構築とビルド|ja_jp-Developer-Setup-and-Building]] — JDK 25、Gradle 9.3+、Loom 1.15+ によるビルドと JUnit 5 自動ユニットテスト。
* [[アーキテクチャと Mixin ターゲット|ja_jp-Architecture-and-Mixins]] — パッケージ構造と Mixin 注入ターゲットの完全対照表。
* [[API とアドオン統合|ja_jp-API-and-Addon-Integration]] — DasikLibrary API ファサード、データパックタグ（`#agrarian_reform:soft_step_boots`）、拡張フック。

---

<p align="center">
  <em>Agrarian Reform は Instant Gratification および Vanilla Outsider コレクションの一環として開発されています。</em>
</p>
