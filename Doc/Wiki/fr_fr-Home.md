# 🌾 Agrarian Reform : La Terre Vivante

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> **« Le monde ne devrait pas attendre que vous le regardiez pour vivre. »**

Bienvenue sur le Wiki officiel d'**Agrarian Reform** ! **Agrarian Reform** est un mod de simulation agricole et d'immersion qui transforme l'agriculture de Minecraft : au lieu d'un système statique dépendant de la présence du joueur, les cultures s'épanouissent dans un écosystème autonome et persistant. Il intègre **Le Continuum (The Continuum)** (simulation de croissance hors-ligne optimisée par palettes de sous-chunks), l'**auto-enregistrement universel des cultures moddées**, une physique des sols soignée avec protection sélective contre le piétinement, une hydrodynamique concentrique de Tchebychev et des bonus de biodiversité en polyculture.

> 📌 **Avertissement relatif au code source** : La documentation de ce Wiki reflète l'**état actuel du code source dans le dépôt**, pouvant inclure des commits récents ou des fonctionnalités en développement avant leur publication sur CurseForge et Modrinth.

---

## 🛠️ Vue d'ensemble & Spécifications techniques

| Paramètre | Spécification |
| :--- | :--- |
| **Identifiant du mod (Mod ID)** | `agrarian_reform` |
| **Versions Minecraft prises en charge** | Minecraft 26.2 (`>=26.2-`), Minecraft 26.3 (`>=26.3-` / `26.3-snapshot-6`) |
| **Version actuelle du mod** | `2.2.18+26.2` / `2.2.18+26.3` |
| **Chargeur de mods** | Fabric Loader (`>=0.16.9`) |
| **Cible Java** | Java 25 (Strict) |
| **Dépendances principales** | Fabric API (`*`), DasikLibrary (`>=1.8.3`) |
| **Intégrations optionnelles** | ModMenu (`*`), YetAnotherConfigLib v3 (`*`) |
| **Auteur et responsable** | **Dasik (Rifaditya)** |
| **Licence** | GNU General Public License v3.0 (GPLv3) |

---

## 📦 Répertoire des versions Minecraft

* [[Guide Minecraft 26.2 et 26.3|fr_fr-Minecraft-26.2-Guide]] — Guide complet pour les versions modernes de Minecraft 26.2 et 26.3.
* [[Compatibilité des versions et cycle de vie|fr_fr-Version-Compatibility]] — Matrice de support, dépendances ouvertes et sécurités du chargeur Knot.

---

## 🎮 Guides des mécaniques pour joueurs

* [[Le Continuum (Croissance hors-ligne)|fr_fr-The-Continuum-Offline-Persistence]] — Pré-filtrage par palette, horodatages, purge à 30 jours, file régulée et 0 écriture inutile sur disque.
* [[Registre des plantes et cultures universelles|fr_fr-Plant-Registry-and-Crop-Types]] — Détection dynamique en $O(1)$, inspection d'âge, tags `#c:crops` et multiplicateurs personnalisés.
* [[Hydrodynamique et irrigation|fr_fr-Hydro-Dynamics-and-Irrigation]] — Anneaux concentriques de Tchebychev (source 8 blocs, eau courante 4), 3D ($y \in [-1, 1]$) et eau pure.
* [[Résilience des terres et protection du piétinement|fr_fr-Soil-Resilience-and-Trample-Logic]] — Pas feutré avec bottes en cuir/chute amortie, élimination rapide pieds nus et filtre joueur/familier.
* [[Récolte par clic droit et replantation|fr_fr-Right-Click-Harvest-and-Replanting]] — Garde d'interaction en 6D, filtrage main principale, contournement accroupi et récolte au clic droit.
* [[Polyculture et biodiversité|fr_fr-Polyculture-and-Biodiversity]] — Récompense pour les cultures diversifiées et bonus de probabilité de croissance de +10%.
* [[Semis de graines et culture d'herbe|fr_fr-Seed-Sowing-and-Grass-Cultivation]] — Ensemencement de graines agricoles sur la terre pour la transformer en herbe.
* [[Poudre d'os universelle|fr_fr-Universal-Bone-Meal]] — Utilisation de poudre d'os sur la canne à sucre, les cactus, les verrues du Nether et les lianes.
* [[Multiplicateur global de croissance|fr_fr-Global-Growth-Multiplier]] — Ajustement de vitesse global et par plante : 0% (désactivé), -1 (gelé), 100% (vanilla) ou accéléré (>100%).
* [[Performances et régulation de file d'attente|fr_fr-Performance-and-Queue-Throttling]] — Préservation des ticks serveur (`CROPS_PER_TICK = 5`) et files de tâches sans verrouillage.
* [[Règles de jeu (GameRules)|fr_fr-GameRules]] — Registre complet des 15 règles de jeu `agrarian_reform:*`, règles dynamiques et mode débogage.
* [[Commandes Brigadier et administration|fr_fr-Commands]] — Commandes Brigadier d'administration et modification dynamique en jeu.
* [[Configuration à deux niveaux|fr_fr-Configuration]] — Modèle JSON Schema v2, sauvegarde optimisée par dirty-tracking et interface graphique YACL.
* [[Esthétique et retours d'ambiance|fr_fr-Aesthetics-and-Ambient-Feedback]] — Bruissement audio lors du passage dans les champs, particules de vitalité et rosée matinale.
* [[Progrès et accomplissements|fr_fr-Advancements]] — Compatibilité totale et naturelle avec les progrès agricoles vanilla.

---

## 💻 Référence pour développeurs

* [[Environnement de développement et compilation|fr_fr-Developer-Setup-and-Building]] — Compilation avec JDK 25, Gradle 9.3+, Loom 1.15+ et tests unitaires headless JUnit 5.
* [[Architecture et cibles Mixin|fr_fr-Architecture-and-Mixins]] — Hiérarchie des packages et tableau détaillé des injections Mixin.
* [[API et intégration d'addons|fr_fr-API-and-Addon-Integration]] — Façade DasikLibrary API, tags personnalisés (`#agrarian_reform:soft_step_boots`) et points d'extension.

---

<p align="center">
  <em>Agrarian Reform est développé au sein des collections Instant Gratification et Vanilla Outsider.</em>
</p>
