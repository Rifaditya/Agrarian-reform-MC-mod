# 🌽 Policultivo e bônus de biodiversidade

In vanilla Minecraft, players build vast monoculture fields (hundreds of identical Wheat blocks) because there is no biological penalty for doing so. **Agrarian Reform** introduces **Polyculture Biodiversity**, rewarding diverse crop planting layouts with faster growth rates.

---

## 📊 Infobox de mecânica

| Parameter | Value |
| :--- | :--- |
| **Feature Class** | `net.instantgratification.agrarianreform.util.GrowthHelper` |
| **Growth Speed Boost**| $+0.10$ (+10% base probability bonus) |
| **Detection Method** | 4-cardinal direction neighbor check (North, South, East, West) |
| **Target Tag** | `minecraft:crops` |
| **GameRule Toggle** | `agrarian_reform:growth_biodiversity_bonus` (Default: `true`) |

---

## 🧪 Como funciona o bônus de policultivo

```
                           [ Wheat ]
                               │
            [ Carrot ] ─── [ Crop ] ─── [ Potato ]
                               │
                          [ Beetroot ]
```

If **at least one cardinal neighbor** contains a crop block belonging to a **different crop species** (e.g. Wheat adjacent to Carrots or Potatoes), the crop's base growth speed float $S$ is increased by **+0.10**:

$$S_{\text{effective}} = S_{\text{vanilla}} + 0.10$$

### Ideal Polyculture Farm Layouts
* **Alternating Strips**: Planting alternating rows (Row 1: Wheat, Row 2: Carrot, Row 3: Potato) ensures **100% of crops** receive the +10% biodiversity bonus.
* **Checkerboard Patchwork**: Interspersing crops in a 2x2 grid maximizes visual variety while securing maximum growth probability.

---

## ⚙️ Desativando o bônus de biodiversidade

```bash
/gamerule agrarian_reform:growth_biodiversity_bonus false
```

---

*See also: [[O Continuum (Crescimento offline)|pt_br-The-Continuum-Offline-Persistence]] and [[Registro de plantas e culturas universais|pt_br-Plant-Registry-and-Crop-Types]]*.
