# 📦 Guía de Minecraft 26.2 y 26.3

The **Minecraft 26.2** and **Minecraft 26.3** releases are the primary target versions for **Agrarian Reform**. Built natively on **Java 25**, **Fabric Loader 0.16.9+**, and **Fabric API**, these builds provide full stability, universal modded crop auto-population, and zero legacy obfuscation mapping overhead.

---

## 📋 Infobox de metadatos de versión

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

## 🚀 Instalación y configuración

1. **Instalar Fabric Loader**: Descarga e instala Fabric Loader versión `0.16.9` o superior para Minecraft 26.2 o 26.3.
2. **Instalar librerías requeridas**:
   - Coloca **Fabric API** en tu directorio `.minecraft/mods/`.
   - Coloca **DasikLibrary** (`1.8.3` o posterior) en `.minecraft/mods/`.
3. **Instalar Agrarian Reform**: Coloca `agrarian-reform-2.2.18+26.2.jar` (o `+26.3.jar`) en `.minecraft/mods/`.
4. **Mejora opcional del cliente**: Instala **ModMenu** y **YetAnotherConfigLib v3 (YACL)** para acceder a la pantalla de configuración gráfica de 3 pestañas.

---

## 🔑 Características clave en 26.2 y 26.3

* **Auto-población universal de cultivos**: Detección automática y sin configuración de cualquier cultivo modded (`#c:crops`), registrando GameRules dinámicas y controles en GUI.
* **El Continuo**: Puesta al día offline con pre-filtrado de paleta de subchunks y purga automática de marcas de tiempo de más de 30 días.
* **Hidrodinámica concéntrica de Chebyshev**: Rango de irrigación de 8 bloques para fuentes de agua con anillos concéntricos y filtrado opcional de agua pura.
* **Resistencia del suelo y protección selectiva**: Paso suave (botas de cuero/caída de pluma) con restricción para jugadores y mascotas domadas (`trample_immunity_players_only`).
* **Guardia de interacción en 6D**: Cosecha y resiembra con clic derecho, debouncing de mano principal y bypass al agacharse.
* **Biodiversidad por policultivo**: +10% de probabilidad de crecimiento al alternar especies de cultivos en bloques adyacentes.

---

## 🔒 Protección ModVersionGuard

Agrarian Reform ejecuta una comprobación de clases con cero dependencias mediante `ModVersionGuard` al inicio (`onInitialize()`):
```java
ModVersionGuard.checkClass("Agrarian Reform", "net.minecraft.world.entity.EntityTypes");
```
Si se carga una versión incompatible de Minecraft que carece de clases centrales, Knot detecta la discrepancia inmediatamente, generando un registro de error legible y comprensible en lugar de colapsar silenciosamente durante la partida.

---

*Véase también: [[Compatibilidad de versiones y ciclo de soporte|es_es-Version-Compatibility]] y [[Configuración de desarrollo y compilación|es_es-Developer-Setup-and-Building]]*.
