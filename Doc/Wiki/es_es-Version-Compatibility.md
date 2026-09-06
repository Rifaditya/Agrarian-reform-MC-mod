# 🔄 Compatibilidad de versiones y ciclo de soporte

This page documents the version compatibility matrix, forward-compatibility design rules, and dependency management policies for **Agrarian Reform**.

> 📌 **Repository Source Disclaimer**: The documentation in this Wiki reflects the **current source code state in the repository**, which may include recent unreleased commits or developmental features ahead of public release builds on CurseForge and Modrinth.

---

## 📊 Matriz de compatibilidad de versiones de Minecraft

| Minecraft Version | Mod Build | Status | Java Target | Fabric Loader | Dependency Constraint |
| :--- | :--- | :--- | :--- | :--- | :--- |
| **Minecraft 26.3** | `2.2.18+26.3` | **Active Mainline (Snapshot 6+)** | Java 25 | `>=0.16.9` | `"minecraft": ">=26.3-"` |
| **Minecraft 26.2** | `2.2.18+26.2` | **Active Mainline (Stable)** | Java 25 | `>=0.16.9` | `"minecraft": ">=26.2-"` |
| **Minecraft 26.1.2**| `1.2.7+R-26.1.2` | Deprecated / Archived | Java 25 | `>=0.15.0` | `"minecraft": ">=26.1.2"` |

---

## 🛡️ 1 Jar 1 Version y límites abiertos

Agrarian Reform sigue estrictamente la política de **1 Jar 1 Version** y el mandato de **compatibilidad hacia adelante**:
1. **Compilaciones dedicadas**: Cada archivo JAR se compila, prueba y archiva específicamente para una versión objetivo de Minecraft (`agrarian-reform-2.2.18+26.2.jar` y `agrarian-reform-2.2.18+26.3.jar`).
2. **Límite inferior abierto**: En `fabric.mod.json`, la dependencia de Minecraft se especifica con un límite abierto:
   ```json
   "minecraft": ">=26.2-"
   ```
   y para 26.3:
   ```json
   "minecraft": ">=26.3-"
   ```
   Esto evita bloqueos innecesarios de Fabric Loader en versiones de parche posteriores.
3. **Comodines en dependencias**: Las dependencias como `dasik-library` usan comodines (`"*"`) para evitar bloqueos falsos al actualizar librerías.

---

## 🔄 Arquitectura moderna y Codecs de SavedData

* **Lanzamientos anuales (Annual Drops)**: Minecraft adoptó el sistema `Year.Drop.Patch`. Las versiones 26.2 y 26.3 representan los drops activos modernos.
* **Era no ofuscada**: Las bases de código 26.x funcionan con mappings oficiales de Mojang. Términos heredados de Yarn están obsoletos.
* **Modernización de SavedData**: Los datos persistentes del mundo usan `SavedDataType` con Codecs explícitos en lugar de métodos antiguos de NBT:
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

*Véase también: [[Guía de Minecraft 26.2 y 26.3|es_es-Minecraft-26.2-Guide]] y [[API e integración de addons|es_es-API-and-Addon-Integration]]*.
