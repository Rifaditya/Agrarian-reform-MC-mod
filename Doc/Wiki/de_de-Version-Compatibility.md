# 🔄 Versionskompatibilität und Support-Lebenszyklus

This page documents the version compatibility matrix, forward-compatibility design rules, and dependency management policies for **Agrarian Reform**.

> 📌 **Repository Source Disclaimer**: The documentation in this Wiki reflects the **current source code state in the repository**, which may include recent unreleased commits or developmental features ahead of public release builds on CurseForge and Modrinth.

---

## 📊 Minecraft-Versionskompatibilitätsmatrix

| Minecraft Version | Mod Build | Status | Java Target | Fabric Loader | Dependency Constraint |
| :--- | :--- | :--- | :--- | :--- | :--- |
| **Minecraft 26.3** | `2.2.18+26.3` | **Active Mainline (Snapshot 6+)** | Java 25 | `>=0.16.9` | `"minecraft": ">=26.3-"` |
| **Minecraft 26.2** | `2.2.18+26.2` | **Active Mainline (Stable)** | Java 25 | `>=0.16.9` | `"minecraft": ">=26.2-"` |
| **Minecraft 26.1.2**| `1.2.7+R-26.1.2` | Deprecated / Archived | Java 25 | `>=0.15.0` | `"minecraft": ">=26.1.2"` |

---

## 🛡️ 1 Jar 1 Version & Offene Versionsgrenzen

Agrarian Reform folgt der strikten **1 Jar 1 Version Richtlinie** und dem Gebot der **Vorwärtskompatibilität**:
1. **Dedizierte Ziel-Builds**: Jede kompilierte JAR-Datei wird speziell für eine Minecraft-Version erstellt und archiviert (`agrarian-reform-2.2.18+26.2.jar` und `agrarian-reform-2.2.18+26.3.jar`).
2. **Offene untere Versionsgrenzen**: In `fabric.mod.json` wird die Minecraft-Abhängigkeit mit einer offenen Untergrenze definiert:
   ```json
   "minecraft": ">=26.2-"
   ```
   und für 26.3:
   ```json
   "minecraft": ">=26.3-"
   ```
   Dies verhindert Versionssperren bei zukünftigen Patch-Releases.
3. **Abhängigkeits-Wildcards**: Bibliotheken wie `dasik-library` verwenden Wildcards (`"*"`), um Inkompatibilitäten bei einfachen Patch-Updates auszuschließen.

---

## 🔄 Moderne Architektur & SavedData Codecs

* **Annual Drop Versionierung**: Minecraft nutzt das System `Year.Drop.Patch`. Die Versionen 26.2 und 26.3 repräsentieren die aktuellen Drops.
* **Nicht-obfuskierte Ära**: 26.x verwendet offizielle Mojang-Mappings. Veraltete Yarn-Begriffe sind vollständig entfernt.
* **SavedData-Modernisierung**: Persistente Weltdaten nutzen `SavedDataType` mit expliziten Codecs statt alter NBT-Methoden:
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

*Siehe auch: [[Minecraft 26.2 & 26.3 Leitfaden|de_de-Minecraft-26.2-Guide]] und [[API und Addon-Integration|de_de-API-and-Addon-Integration]]*.
