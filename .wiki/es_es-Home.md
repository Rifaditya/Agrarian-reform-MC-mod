# 🌾 Agrarian Reform: La Tierra Viva

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> **«El mundo no debería esperar a que lo mires para seguir vivo.»**

¡Bienvenido a la Wiki oficial de **Agrarian Reform**! **Agrarian Reform** es un mod de inmersión y simulación agrícola que transforma la agricultura de Minecraft: deja de ser una máquina dependiente de la proximidad del jugador para convertirse en un ecosistema vivo y persistente. Introduce **El Continuo (The Continuum)** (simulación de crecimiento offline optimizada por paletas de subchunks), **auto-descubrimiento universal de cultivos modded**, resistencia avanzada del suelo con protección selectiva contra pisoteo, hidrodinámica concéntrica de Chebyshev y bonificaciones de biodiversidad por policultivo.

> 📌 **Aviso del código fuente del repositorio**: La documentación de esta Wiki refleja el **estado actual del código fuente en el repositorio**, que puede incluir confirmaciones recientes o características en desarrollo antes de las compilaciones públicas en CurseForge y Modrinth.

---

## 🛠️ Resumen y especificaciones técnicas

| Parámetro | Especificación |
| :--- | :--- |
| **Identificador del mod (Mod ID)** | `agrarian_reform` |
| **Versiones de juego soportadas** | Minecraft 26.2 (`>=26.2-`), Minecraft 26.3 (`>=26.3-` / `26.3-snapshot-6`) |
| **Versión actual del mod** | `2.2.18+26.2` / `2.2.18+26.3` |
| **Cargador de mods** | Fabric Loader (`>=0.16.9`) |
| **Objetivo de Java** | Java 25 (Estricto) |
| **Dependencias obligatorias** | Fabric API (`*`), DasikLibrary (`>=1.8.3`) |
| **Integraciones opcionales** | ModMenu (`*`), YetAnotherConfigLib v3 (`*`) |
| **Autor y desarrollador principal**| **Dasik (Rifaditya)** |
| **Licencia** | GNU General Public License v3.0 (GPLv3) |

---

## 📦 Directorio de versiones de Minecraft

* [[Guía de Minecraft 26.2 y 26.3|es_es-Minecraft-26.2-Guide]] — Guía completa para las versiones modernas de Minecraft 26.2 y 26.3.
* [[Compatibilidad de versiones y ciclo de soporte|es_es-Version-Compatibility]] — Matriz de soporte, límites abiertos y protecciones de classloader Knot.

---

## 🎮 Guías de características y mecánicas para jugadores

* [[El Continuo (Crecimiento offline)|es_es-The-Continuum-Offline-Persistence]] — Filtrado por paleta, marcas de tiempo, purga a los 30 días, cola throttled y 0 escrituras innecesarias a disco.
* [[Registro de cultivos y soporte universal|es_es-Plant-Registry-and-Crop-Types]] — Detección dinámica $O(1)$, inspección de propiedades, tags `#c:crops` y multiplicadores dedicados.
* [[Hidrodinámica e irrigación|es_es-Hydro-Dynamics-and-Irrigation]] — Anillos concéntricos de Chebyshev (fuente 8 bloques, corriente 4), soporte 3D ($y \in [-1, 1]$) y filtrado de agua pura.
* [[Resistencia del suelo y protección contra pisoteo|es_es-Soil-Resilience-and-Trample-Logic]] — Paso suave con botas de cuero/caída de pluma, descarte rápido de pies descalzos y protección jugador/mascota.
* [[Cosecha con clic derecho y resiembra|es_es-Right-Click-Harvest-and-Replanting]] — Guardia de interacción en 6D, descarte de mano secundaria, bypass con sneak y resembrado automático.
* [[Policultivo y biodiversidad|es_es-Polyculture-and-Biodiversity]] — Incentivos para campos mixtos y bonificación del +10% en probabilidad de crecimiento.
* [[Siembra de semillas y cultivo de hierba|es_es-Seed-Sowing-and-Grass-Cultivation]] — Siembra de semillas agrícolas en tierra para restaurar bloques de hierba.
* [[Polvo de hueso universal|es_es-Universal-Bone-Meal]] — Uso de polvo de hueso en caña de azúcar, cactus, verruga del Nether y enredaderas.
* [[Multiplicador global de crecimiento|es_es-Global-Growth-Multiplier]] — Ajuste global e individual: 0% (desactivado), -1 (congelado), 100% (vainilla) o acelerado (>100%).
* [[Rendimiento y control de colas|es_es-Performance-and-Queue-Throttling]] — Preservación del rendimiento del servidor (`CROPS_PER_TICK = 5`) y colas libres de bloqueos.
* [[Reglas de juego (GameRules)|es_es-GameRules]] — Lista completa de 15 GameRules `agrarian_reform:*`, reglas dinámicas y modo depuración.
* [[Comandos Brigadier y administración|es_es-Commands]] — Comandos Brigadier para administración y ajuste dinámico en vivo.
* [[Configuración de dos niveles|es_es-Configuration]] — Archivo JSON Schema v2, guardado inteligente por dirty-tracking e interfaz gráfica YACL.
* [[Estética y respuesta ambiental|es_es-Aesthetics-and-Ambient-Feedback]] — Sonidos de roce al atravesar cultivos, partículas de vitalidad y rocío matutino.
* [[Progresos y logros|es_es-Advancements]] — Integración nativa con los progresos de agricultura vainilla.

---

## 💻 Referencia para desarrolladores

* [[Configuración de desarrollo y compilación|es_es-Developer-Setup-and-Building]] — Compilación con JDK 25, Gradle 9.3+, Loom 1.15+ y pruebas unitarias headless JUnit 5.
* [[Arquitectura y mixins|es_es-Architecture-and-Mixins]] — Jerarquía de paquetes y tabla exhaustiva de Mixins inyectados.
* [[API e integración de addons|es_es-API-and-Addon-Integration]] — Integración de DasikLibrary API, tags de datapack (`#agrarian_reform:soft_step_boots`) y hooks.

---

<p align="center">
  <em>Agrarian Reform forma parte de las colecciones Instant Gratification y Vanilla Outsider.</em>
</p>
