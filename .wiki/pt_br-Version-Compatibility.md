# 🔄 Compatibilidade de versões e ciclo de vida

This page documents the version compatibility matrix, forward-compatibility design rules, and dependency management policies for **Agrarian Reform**.

> 📌 **Repository Source Disclaimer**: The documentation in this Wiki reflects the **current source code state in the repository**, which may include recent unreleased commits or developmental features ahead of public release builds on CurseForge and Modrinth.

---

## 📊 Matriz de compatibilidade do Minecraft

| Minecraft Version | Mod Build | Status | Java Target | Fabric Loader | Dependency Constraint |
| :--- | :--- | :--- | :--- | :--- | :--- |
| **Minecraft 26.3** | `2.2.18+26.3` | **Active Mainline (Snapshot 6+)** | Java 25 | `>=0.16.9` | `"minecraft": ">=26.3-"` |
| **Minecraft 26.2** | `2.2.18+26.2` | **Active Mainline (Stable)** | Java 25 | `>=0.16.9` | `"minecraft": ">=26.2-"` |
| **Minecraft 26.1.2**| `1.2.7+R-26.1.2` | Deprecated / Archived | Java 25 | `>=0.15.0` | `"minecraft": ">=26.1.2"` |

---

## 🛡️ 1 Jar 1 Version e limites abertos

O Agrarian Reform segue rigorosamente a política **1 Jar 1 Version** e o mandato de **compatibilidade futura**:
1. **Builds dedicados**: Cada arquivo JAR é compilado e testado exclusivamente para uma versão específica do Minecraft (`agrarian-reform-2.2.18+26.2.jar` e `agrarian-reform-2.2.18+26.3.jar`).
2. **Limites inferiores abertos**: No `fabric.mod.json`, a dependência do Minecraft usa limites abertos:
   ```json
   "minecraft": ">=26.2-"
   ```
   e para 26.3:
   ```json
   "minecraft": ">=26.3-"
   ```
   Isso impede bloqueios indevidos do Fabric Loader em atualizações secundárias.
3. **Curingas de dependências**: Dependências como `dasik-library` usam curingas (`"*"`) para evitar bloqueios ao atualizar versões de correção.

---

## 🔄 Arquitetura moderna e Codecs de SavedData

* **Versões anuais (Annual Drops)**: O Minecraft adotou o padrão `Year.Drop.Patch`. As versões 26.2 e 26.3 são as entregas oficiais ativas.
* **Era não-ofuscada**: O código 26.x roda nativamente com mapeamentos oficiais da Mojang. Termos legados do Yarn foram eliminados.
* **Modernização do SavedData**: Os dados do mundo utilizam `SavedDataType` com Codecs dedicados em vez de manipulação manual de NBT:
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

*Veja também: [[Guia do Minecraft 26.2 e 26.3|pt_br-Minecraft-26.2-Guide]] e [[API e integração de addons|pt_br-API-and-Addon-Integration]]*.
