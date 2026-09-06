# 🌽 Bonus Pertumbuhan Polikultur & Keanekaragaman Hayati

In vanilla Minecraft, players build vast monoculture fields (hundreds of identical Wheat blocks) because there is no biological penalty for doing so. **Agrarian Reform** introduces **Polyculture Biodiversity**, rewarding diverse crop planting layouts with faster growth rates.

---

## 📊 Infobox Mekanik

| Parameter | Value |
| :--- | :--- |
| **Feature Class** | `net.instantgratification.agrarianreform.util.GrowthHelper` |
| **Growth Speed Boost**| $+0.10$ (+10% base probability bonus) |
| **Detection Method** | 4-cardinal direction neighbor check (North, South, East, West) |
| **Target Tag** | `minecraft:crops` |
| **GameRule Toggle** | `agrarian_reform:growth_biodiversity_bonus` (Default: `true`) |

---

## 🧪 Cara Kerja Bonus Polikultur

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

## ⚙️ Menonaktifkan Bonus Keanekaragaman Hayati

```bash
/gamerule agrarian_reform:growth_biodiversity_bonus false
```

---

*See also: [[The Continuum (Pertumbuhan Luar Jaringan)|id_id-The-Continuum-Offline-Persistence]] and [[Registri Tanaman & Tanaman Universal|id_id-Plant-Registry-and-Crop-Types]]*.
