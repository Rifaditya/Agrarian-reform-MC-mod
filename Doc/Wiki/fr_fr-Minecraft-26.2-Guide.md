# 📦 Guide moderne Minecraft 26.2 et 26.3

The **Minecraft 26.2** and **Minecraft 26.3** releases are the primary target versions for **Agrarian Reform**. Built natively on **Java 25**, **Fabric Loader 0.16.9+**, and **Fabric API**, these builds provide full stability, universal modded crop auto-population, and zero legacy obfuscation mapping overhead.

---

## 📋 Infobox des métadonnées de version

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

## 🚀 Installation et configuration

1. **Installer Fabric Loader** : Téléchargez et installez Fabric Loader `0.16.9` ou supérieur pour Minecraft 26.2 ou 26.3.
2. **Installer les bibliothèques requises** :
   - Placez **Fabric API** dans votre dossier `.minecraft/mods/`.
   - Placez **DasikLibrary** (`1.8.3` ou ultérieur) dans `.minecraft/mods/`.
3. **Installer Agrarian Reform** : Déposez `agrarian-reform-2.2.18+26.2.jar` (ou `+26.3.jar`) dans `.minecraft/mods/`.
4. **Amélioration client optionnelle** : Installez **ModMenu** et **YetAnotherConfigLib v3 (YACL)** pour accéder à l'interface graphique à 3 onglets.

---

## 🔑 Fonctionnalités clés dans 26.2 et 26.3

* **Enregistrement universel automatique des cultures** : Découverte sans configuration de toute plante moddée (`#c:crops`), avec GameRules dynamiques et options dans l'interface.
* **Le Continuum** : Croissance hors-ligne optimisée par palettes de sous-chunks avec purge automatique des données de plus de 30 jours.
* **Hydrodynamique concentrique de Tchebychev** : Rayon d'irrigation de 8 blocs pour les sources d'eau avec recherche en anneaux et option d'eau pure.
* **Résilience du sol & protection sélective** : Pas feutré (bottes en cuir/chute amortie) avec restriction réservée aux joueurs et familiers (`trample_immunity_players_only`).
* **Garde d'interaction en 6D** : Récolte et replantation au clic droit avec filtrage de main principale et contournement accroupi.
* **Biodiversité en polyculture** : Bonus de croissance de +10% en alternant des espèces différentes sur des parcelles adjacentes.

---

## 🔒 Protection ModVersionGuard

Agrarian Reform exécute une vérification de classe sans dépendance via `ModVersionGuard` lors de l'initialisation précoce (`onInitialize()`) :
```java
ModVersionGuard.checkClass("Agrarian Reform", "net.minecraft.world.entity.EntityTypes");
```
Si une version incompatible de Minecraft dépourvue des classes essentielles est chargée, le chargeur Knot intercepte l'erreur et génère un rapport lisible au lieu de provoquer un crash silencieux en plein jeu.

---

*Voir aussi : [[Compatibilité des versions et cycle de vie|fr_fr-Version-Compatibility]] et [[Environnement de développement et compilation|fr_fr-Developer-Setup-and-Building]]*.
