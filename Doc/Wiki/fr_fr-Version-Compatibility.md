# 🔄 Compatibilité des versions et cycle de vie

This page documents the version compatibility matrix, forward-compatibility design rules, and dependency management policies for **Agrarian Reform**.

> 📌 **Repository Source Disclaimer**: The documentation in this Wiki reflects the **current source code state in the repository**, which may include recent unreleased commits or developmental features ahead of public release builds on CurseForge and Modrinth.

---

## 📊 Matrice de compatibilité des versions Minecraft

| Minecraft Version | Mod Build | Status | Java Target | Fabric Loader | Dependency Constraint |
| :--- | :--- | :--- | :--- | :--- | :--- |
| **Minecraft 26.3** | `2.2.18+26.3` | **Active Mainline (Snapshot 6+)** | Java 25 | `>=0.16.9` | `"minecraft": ">=26.3-"` |
| **Minecraft 26.2** | `2.2.18+26.2` | **Active Mainline (Stable)** | Java 25 | `>=0.16.9` | `"minecraft": ">=26.2-"` |
| **Minecraft 26.1.2**| `1.2.7+R-26.1.2` | Deprecated / Archived | Java 25 | `>=0.15.0` | `"minecraft": ">=26.1.2"` |

---

## 🛡️ 1 Jar 1 Version et bornes ouvertes

Agrarian Reform applique la règle **1 Jar 1 Version** et l'impératif de **compatibilité ascendante** :
1. **Build dédié** : Chaque fichier JAR est compilé, testé et archivé pour une version cible précise de Minecraft (`agrarian-reform-2.2.18+26.2.jar` et `agrarian-reform-2.2.18+26.3.jar`).
2. **Bornes inférieures ouvertes** : Dans `fabric.mod.json`, la dépendance Minecraft est déclarée avec une borne ouverte :
   ```json
   "minecraft": ">=26.2-"
   ```
   et pour 26.3 :
   ```json
   "minecraft": ">=26.3-"
   ```
   Cela empêche le blocage de Fabric Loader sur les mises à jour mineures ou correctifs.
3. **Dépendances ouvertes** : Les bibliothèques comme `dasik-library` utilisent des jokers (`"*"`) pour éviter les conflits lors des montées de version mineures.

---

## 🔄 Architecture moderne et Codecs SavedData

* **Versionnage annuel (Annual Drop)** : Minecraft utilise le cycle `Year.Drop.Patch`. Les versions 26.2 et 26.3 représentent les releases actives.
* **Ère non-offusquée** : Les codes 26.x reposent sur les mappings officiels de Mojang. L'ancien vocabulaire Yarn est banni.
* **Modernisation de SavedData** : Les données persistantes utilisent `SavedDataType` avec des Codecs explicites au lieu de la manipulation directe de NBT :
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

*Voir aussi : [[Guide Minecraft 26.2 et 26.3|fr_fr-Minecraft-26.2-Guide]] et [[API et intégration d'addons|fr_fr-API-and-Addon-Integration]]*.
