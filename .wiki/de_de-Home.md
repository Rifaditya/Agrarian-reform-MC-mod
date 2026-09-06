# 🌾 Agrarian Reform: Die lebendige Erde

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> **„Die Welt sollte nicht darauf warten müssen, dass du ihr zusiehst.“**

Willkommen im offiziellen **Agrarian Reform** Wiki! **Agrarian Reform** ist eine Landwirtschafts- und Immersionsmodifikation, die den Ackerbau in Minecraft von einer näheabhängigen Maschine in ein lebendiges, persistentes Ökosystem verwandelt. Die Mod führt **Das Kontinuum (The Continuum)** (eine über Sub-Chunk-Paletten optimierte Offline-Wachstumssimulation), **universelle Auto-Registrierung von Mod-Pflanzen**, robuste Bodenmechanik mit selektivem Trampelschutz für Spieler und Haustiere, realistische konzentrische Chebyshev-Hydrodynamik sowie Mischkultur-Biodiversitätsboni ein.

> 📌 **Quellcode-Hinweis**: Die Dokumentation in diesem Wiki spiegelt den **aktuellen Quellcode-Stand im Repository** wider, welcher noch unveröffentlichte Commits oder Entwicklungsfunktionen vor den offiziellen Builds auf CurseForge und Modrinth enthalten kann.

---

## 🛠️ Überblick & Technische Daten

| Parameter | Spezifikation |
| :--- | :--- |
| **Mod-Identifikator (Mod ID)** | `agrarian_reform` |
| **Unterstützte Spielversionen** | Minecraft 26.2 (`>=26.2-`), Minecraft 26.3 (`>=26.3-` / `26.3-snapshot-6`) |
| **Aktuelle Mod-Version** | `2.2.18+26.2` / `2.2.18+26.3` |
| **Mod-Loader** | Fabric Loader (`>=0.16.9`) |
| **Java-Zielversion** | Java 25 (Strikter Standard) |
| **Hauptabhängigkeiten** | Fabric API (`*`), DasikLibrary (`>=1.8.3`) |
| **Optionale Integrationen** | ModMenu (`*`), YetAnotherConfigLib v3 (`*`) |
| **Autor & Leitung** | **Dasik (Rifaditya)** |
| **Lizenz** | GNU General Public License v3.0 (GPLv3) |

---

## 📦 Minecraft-Versionsverzeichnis

* [[Minecraft 26.2 & 26.3 Leitfaden|de_de-Minecraft-26.2-Guide]] — Umfassender Leitfaden für moderne Minecraft 26.2 und 26.3 Builds.
* [[Versionskompatibilität und Support-Lebenszyklus|de_de-Version-Compatibility]] — Versionsmatrix, offene Versionsgrenzen und Knot-Klassenlader-Sicherheitsprüfungen.

---

## 🎮 Spieler- & Funktionsanleitungen

* [[Das Kontinuum (Offline-Wachstum)|de_de-The-Continuum-Offline-Persistence]] — Sub-Chunk-Palettenfilterung, Entladezeitstempel, 30-Tage-Bereinigung, gedrosselte Warteschlange und Null-Festplatten-Schreibschutz.
* [[Pflanzenregister und universelle Feldfrüchte|de_de-Plant-Registry-and-Crop-Types]] — $O(1)$ dynamische Pflanzenerkennung, Eigenschaftsinspektion, `#c:crops`-Tags und individuelle Multiplikatoren.
* [[Hydrodynamik und Bewässerung|de_de-Hydro-Dynamics-and-Irrigation]] — Konzentrische Chebyshev-Ringe (Wasserquelle 8 Blöcke, fließend 4), 3D-Höhe ($y \in [-1, 1]$) und reines Wasser.
* [[Bodenresilienz und Trampelschutz|de_de-Soil-Resilience-and-Trample-Logic]] — Sanftschritt-Schutz mit Lederstiefeln/Federfall, Barfuß-Schnellprüfung und Spieler/Haustier-Filterung.
* [[Rechtsklick-Ernte & Nachpflanzung|de_de-Right-Click-Harvest-and-Replanting]] — 6-dimensionaler Interaktionsschutz, Haupthand-Entprellung, Sneak-Bypass und automatisches Nachpflanzen.
* [[Mischkultur und Biodiversitätsbonus|de_de-Polyculture-and-Biodiversity]] — Anreize für Mischkulturen und +10% Wachstums-Wahrscheinlichkeitsbonus.
* [[Saataussaat und Graskultivierung|de_de-Seed-Sowing-and-Grass-Cultivation]] — Aussaat von Agrarsamen auf Erde zur Wiederherstellung von Grasblöcken.
* [[Universelles Knochenmehl|de_de-Universal-Bone-Meal]] — Knochenmehl-Düngung für Zuckerrohr, Kakteen, Netherwarzen und Ranken.
* [[Globaler Wachstums-Multiplikator|de_de-Global-Growth-Multiplier]] — Globale und individuelle Geschwindigkeitseinstellung: 0% (aus), -1 (eingefroren), 100% (Vanilla), beschleunigt (>100%).
* [[Leistung und Warteschlangen-Drosselung|de_de-Performance-and-Queue-Throttling]] — Server-Tick-Budgetschutz (`CROPS_PER_TICK = 5`), Paletten-Vorauswahl und sperrenfreie Aufgabenwarteschlange.
* [[Spielregeln (GameRules)|de_de-GameRules]] — Komplette Übersicht aller 15 `agrarian_reform:*` Spielregeln, dynamischen Regeln und temporären Debug-Schaltern.
* [[Brigadier-Befehle und Administration|de_de-Commands]] — Brigadier-Befehlsintegration zur Laufzeitkonfiguration.
* [[Zwei-Ebenen-Konfiguration|de_de-Configuration]] — Schema v2 JSON-Vorlage, Dirty-Tracking-Speicherung und 3-Reiter-YACL-GUI.
* [[Ästhetik und Umgebungsfeedback|de_de-Aesthetics-and-Ambient-Feedback]] — Pflanzenrascheln beim Durchlaufen, Vitalitäts-Partikel und Morgentau-Effekte.
* [[Fortschritte und Erfolge|de_de-Advancements]] — Nahtlose Integration in Vanilla-Landwirtschaftsfortschritte.

---

## 💻 Entwickler-Referenzverzeichnis

* [[Entwickler-Setup und Kompilierung|de_de-Developer-Setup-and-Building]] — Entwicklungsumgebung mit JDK 25, Gradle 9.3+, Loom 1.15+ und JUnit 5 Tests.
* [[Architektur und Mixin-Ziele|de_de-Architecture-and-Mixins]] — Pakethierarchie und vollständige Tabelle der Mixin-Injektionen.
* [[API und Addon-Integration|de_de-API-and-Addon-Integration]] — DasikLibrary API-Fassade, Datapack-Tags (`#agrarian_reform:soft_step_boots`) und Schnittstellen.

---

<p align="center">
  <em>Agrarian Reform wird im Rahmen der Instant Gratification und Vanilla Outsider Kollektionen entwickelt.</em>
</p>
