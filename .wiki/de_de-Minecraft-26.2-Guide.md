# 📦 Minecraft 26.2 & 26.3 Moderner Leitfaden

The **Minecraft 26.2** and **Minecraft 26.3** releases are the primary target versions for **Agrarian Reform**. Built natively on **Java 25**, **Fabric Loader 0.16.9+**, and **Fabric API**, these builds provide full stability, universal modded crop auto-population, and zero legacy obfuscation mapping overhead.

---

## 📋 Versions-Metadaten Infobox

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

## 🚀 Installation & Einrichtung

1. **Fabric Loader installieren**: Lade Fabric Loader in Version `0.16.9` oder höher für Minecraft 26.2 oder 26.3 herunter und installiere ihn.
2. **Erforderliche Bibliotheken installieren**:
   - Platziere **Fabric API** im Ordner `.minecraft/mods/`.
   - Platziere **DasikLibrary** (`1.8.3` oder neuer) im Ordner `.minecraft/mods/`.
3. **Agrarian Reform installieren**: Kopiere `agrarian-reform-2.2.18+26.2.jar` (oder `+26.3.jar`) in `.minecraft/mods/`.
4. **Optionale Client-Erweiterung**: Installiere **ModMenu** und **YetAnotherConfigLib v3 (YACL)** für die 3-Reiter-Konfiguration im Spiel.

---

## 🔑 Hauptmerkmale in 26.2 & 26.3

* **Universelle automatische Pflanzenerkennung**: Konfigurationslose Erkennung beliebiger Mod-Pflanzen (`#c:crops`) mit dynamischen GameRules und GUI-Steuerung.
* **Das Kontinuum**: Offline-Wachstumssimulation mit Sub-Chunk-Palettenfilterung und automatischer 30-Tage-Bereinigung.
* **Konzentrische Chebyshev-Hydrodynamik**: 8 Blöcke Bewässerungsradius für Wasserquellen mit quadratischen Ringen und reinem Wasser-Modus.
* **Bodenresilienz & selektiver Schutz**: Sanftschritt-Schutz (Lederstiefel/Federfall) mit Beschränkung auf Spieler und Haustiere (`trample_immunity_players_only`).
* **6D-Interaktionswächter**: Rechtsklick-Ernte & Nachpflanzung mit Haupthand-Entprellung und Sneak-Bypass.
* **Mischkultur-Biodiversität**: +10% Wachstumsbonus bei Pflanzung verschiedener Arten auf benachbarten Blöcken.

---

## 🔒 ModVersionGuard Schutz

Agrarian Reform führt während der frühen Initialisierung (`onInitialize()`) eine abhängigkeitsfreie Klassenprüfung via `ModVersionGuard` durch:
```java
ModVersionGuard.checkClass("Agrarian Reform", "net.minecraft.world.entity.EntityTypes");
```
Wird eine inkompatible Minecraft-Version geladen, in der Kernklassen fehlen, erkennt der Knot-Klassenlader dies sofort und protokolliert einen lesbaren Diagnosefehler, statt mitten im Spiel abzustürzen.

---

*Siehe auch: [[Versionskompatibilität und Support-Lebenszyklus|de_de-Version-Compatibility]] und [[Entwickler-Setup und Kompilierung|de_de-Developer-Setup-and-Building]]*.
