# 📦 Guia Moderno do Minecraft 26.2 e 26.3

The **Minecraft 26.2** and **Minecraft 26.3** releases are the primary target versions for **Agrarian Reform**. Built natively on **Java 25**, **Fabric Loader 0.16.9+**, and **Fabric API**, these builds provide full stability, universal modded crop auto-population, and zero legacy obfuscation mapping overhead.

---

## 📋 Infobox de metadados da versão

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

## 🚀 Instalação e configuração

1. **Instalar o Fabric Loader**: Baixe e instale o Fabric Loader versão `0.16.9` ou superior para Minecraft 26.2 ou 26.3.
2. **Instalar bibliotecas necessárias**:
   - Coloque o **Fabric API** na pasta `.minecraft/mods/`.
   - Coloque o **DasikLibrary** (`1.8.3` ou mais recente) na pasta `.minecraft/mods/`.
3. **Instalar o Agrarian Reform**: Adicione `agrarian-reform-2.2.18+26.2.jar` (ou `+26.3.jar`) em `.minecraft/mods/`.
4. **Melhoria opcional no cliente**: Instale o **ModMenu** e o **YetAnotherConfigLib v3 (YACL)** para acessar a tela gráfica de configuração em 3 abas.

---

## 🔑 Principais recursos no 26.2 e 26.3

* **Auto-povoamento universal de culturas**: Descoberta sem configuração de qualquer cultura de mod (`#c:crops`), registrando GameRules dinâmicas e controles na GUI.
* **O Continuum**: Simulação offline com pré-filtragem de paleta de subchunk e limpeza automática de marcas com mais de 30 dias.
* **Hidrodinâmica concêntrica de Chebyshev**: Raio de irrigação de 8 blocos para fontes de água com anéis concêntricos e filtro opcional de água pura.
* **Resiliência do solo & proteção seletiva**: Passo suave (botas de couro/peso-pena) com restrição opcional para jogadores e pets domados (`trample_immunity_players_only`).
* **Guarda de interação 6D**: Colheita e replantio com clique direito, debouncing na mão principal e desvio ao agachar.
* **Biodiversidade por policultivo**: +10% de velocidade de crescimento ao intercalar culturas diferentes em blocos vizinhos.

---

## 🔒 Proteção ModVersionGuard

O Agrarian Reform executa uma verificação de classes sem dependências via `ModVersionGuard` na inicialização (`onInitialize()`):
```java
ModVersionGuard.checkClass("Agrarian Reform", "net.minecraft.world.entity.EntityTypes");
```
Caso uma versão incompatível do Minecraft sem classes essenciais seja carregada, o carregador Knot detecta a discrepância imediatamente, exibindo um log claro em vez de travar durante o jogo.

---

*Veja também: [[Compatibilidade de versões e ciclo de vida|pt_br-Version-Compatibility]] e [[Configuração de desenvolvimento e compilação|pt_br-Developer-Setup-and-Building]]*.
