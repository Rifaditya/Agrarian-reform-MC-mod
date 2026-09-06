# 🌾 Agrarian Reform: A Terra Viva

🌐 **Languages**: [[🇺🇸 English|Home]] | [[🇨🇳 简体中文|zh_cn-Home]] | [[🇭🇰 繁體中文|zh_tw-Home]] | [[🇷🇺 Русский|ru_ru-Home]] | [[🇪🇸 Español|es_es-Home]] | [[🇩🇪 Deutsch|de_de-Home]] | [[🇫🇷 Français|fr_fr-Home]] | [[🇧🇷 Português|pt_br-Home]] | [[🇯🇵 日本語|ja_jp-Home]] | [[🇮🇩 Bahasa Indonesia|id_id-Home]] | [[🇰🇷 한국어|ko_kr-Home]]

> **"O mundo não deveria esperar que você olhe para ele para continuar vivo."**

Bem-vindo à Wiki oficial do **Agrarian Reform**! O **Agrarian Reform** é um mod de imersão e simulação agrícola que transforma o cultivo no Minecraft: de uma máquina dependente da proximidade do jogador para um ecossistema vivo e persistente. Ele introduz **O Continuum (The Continuum)** (simulação de crescimento offline otimizada por paleta de subchunks), **auto-descoberta universal de culturas de mods**, mecânica avançada de solo com proteção seletiva contra pisoteio, hidrodinâmica concêntrica de Chebyshev e bônus de biodiversidade por policultivo.

> 📌 **Aviso sobre o código-fonte**: A documentação nesta Wiki reflete o **estado atual do código-fonte no repositório**, que pode incluir commits recentes ou recursos em desenvolvimento antes dos lançamentos públicos no CurseForge e Modrinth.

---

## 🛠️ Visão Geral & Especificações Técnicas

| Parâmetro | Especificação |
| :--- | :--- |
| **Identificador do Mod (Mod ID)** | `agrarian_reform` |
| **Versões Suportadas do Minecraft** | Minecraft 26.2 (`>=26.2-`), Minecraft 26.3 (`>=26.3-` / `26.3-snapshot-6`) |
| **Versão Atual do Mod** | `2.2.18+26.2` / `2.2.18+26.3` |
| **Carregador de Mods** | Fabric Loader (`>=0.16.9`) |
| **Alvo Java** | Java 25 (Estrito) |
| **Dependências Principais** | Fabric API (`*`), DasikLibrary (`>=1.8.3`) |
| **Integrações Opcionais** | ModMenu (`*`), YetAnotherConfigLib v3 (`*`) |
| **Autor e Líder** | **Dasik (Rifaditya)** |
| **Licença** | GNU General Public License v3.0 (GPLv3) |

---

## 📦 Diretório de Versões do Minecraft

* [[Guia do Minecraft 26.2 e 26.3|pt_br-Minecraft-26.2-Guide]] — Guia abrangente para as versões modernas do Minecraft 26.2 e 26.3.
* [[Compatibilidade de versões e ciclo de vida|pt_br-Version-Compatibility]] — Matriz de suporte, limites abertos e proteções do carregador Knot.

---

## 🎮 Guias de Funcionalidades para Jogadores

* [[O Continuum (Crescimento offline)|pt_br-The-Continuum-Offline-Persistence]] — Filtragem de paleta de subchunk, marcas temporais, limpeza aos 30 dias e zero escritas desnecessárias no disco.
* [[Registro de plantas e culturas universais|pt_br-Plant-Registry-and-Crop-Types]] — Descoberta dinâmica em $O(1)$, inspeção de propriedades, tags `#c:crops` e multiplicadores dedicados.
* [[Hidrodinâmica e irrigação|pt_br-Hydro-Dynamics-and-Irrigation]] — Anéis concêntricos de Chebyshev (fonte 8 blocos, corrente 4), suporte 3D ($y \in [-1, 1]$) e água pura.
* [[Resiliência do solo e proteção contra pisoteio|pt_br-Soil-Resilience-and-Trample-Logic]] — Passo suave com botas de couro/peso-pena, descarte rápido com pés descalços e proteção jogador/pet.
* [[Colheita com botão direito e replantio|pt_br-Right-Click-Harvest-and-Replanting]] — Guarda de interação 6D, descarte na mão secundária, desvio ao agachar e colheita com botão direito.
* [[Policultivo e biodiversidade|pt_br-Polyculture-and-Biodiversity]] — Incentivos para plantio diversificado e bônus de +10% na probabilidade de crescimento.
* [[Semeadura e cultivo de grama|pt_br-Seed-Sowing-and-Grass-Cultivation]] — Semeadura de sementes na terra para restaurar blocos de grama.
* [[Farinha de osso universal|pt_br-Universal-Bone-Meal]] — Uso de farinha de osso em cana-de-açúcar, cactos, fungo do Nether e videiras.
* [[Multiplicador global de crescimento|pt_br-Global-Growth-Multiplier]] — Ajuste de velocidade global e por cultura: 0% (desativado), -1 (congelado), 100% (vanilla) ou acelerado (>100%).
* [[Desempenho e controle de filas|pt_br-Performance-and-Queue-Throttling]] — Preservação de ticks do servidor (`CROPS_PER_TICK = 5`) e fila sem travamentos.
* [[Regras de jogo (GameRules)|pt_br-GameRules]] — Lista completa de 15 regras de jogo `agrarian_reform:*`, regras dinâmicas e modo de depuração.
* [[Comandos Brigadier e administração|pt_br-Commands]] — Comandos Brigadier para administração e configuração dinâmica em tempo real.
* [[Configuração em dois níveis|pt_br-Configuration]] — Arquivo JSON Schema v2, salvamento por dirty-tracking e GUI YACL de 3 abas.
* [[Estética e ambientação sonora|pt_br-Aesthetics-and-Ambient-Feedback]] — Sons de farfalhar ao atravessar plantações, partículas de vitalidade e orvalho matinal.
* [[Progressos e conquistas|pt_br-Advancements]] — Integração perfeita com os progressos padrão de agricultura.

---

## 💻 Referência para Desenvolvedores

* [[Configuração de desenvolvimento e compilação|pt_br-Developer-Setup-and-Building]] — Compilação com JDK 25, Gradle 9.3+, Loom 1.15+ e testes unitários JUnit 5.
* [[Arquitetura e mixins|pt_br-Architecture-and-Mixins]] — Estrutura de pacotes e tabela completa de injeções Mixin.
* [[API e integração de addons|pt_br-API-and-Addon-Integration]] — Fachada de API DasikLibrary, tags de datapack (`#agrarian_reform:soft_step_boots`) e extensões.

---

<p align="center">
  <em>Agrarian Reform faz parte das coleções Instant Gratification e Vanilla Outsider.</em>
</p>
