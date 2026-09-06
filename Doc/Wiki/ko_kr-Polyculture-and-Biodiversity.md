# 🌽 다모작 및 생물다양성 성장 보너스

In vanilla Minecraft, players build vast monoculture fields (hundreds of identical Wheat blocks) because there is no biological penalty for doing so. **Agrarian Reform** introduces **Polyculture Biodiversity**, rewarding diverse crop planting layouts with faster growth rates.

---

## 📊 메커니즘 인포박스

| Parameter | Value |
| :--- | :--- |
| **Feature Class** | `net.instantgratification.agrarianreform.util.GrowthHelper` |
| **Growth Speed Boost**| $+0.10$ (+10% base probability bonus) |
| **Detection Method** | 4-cardinal direction neighbor check (North, South, East, West) |
| **Target Tag** | `minecraft:crops` |
| **GameRule Toggle** | `agrarian_reform:growth_biodiversity_bonus` (Default: `true`) |

---

## 🧪 다모작 보너스 작동 원리

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

## ⚙️ 생물다양성 보너스 비활성화

```bash
/gamerule agrarian_reform:growth_biodiversity_bonus false
```

---

*See also: [[컨티넘 (오프라인 성장 지속)|ko_kr-The-Continuum-Offline-Persistence]] and [[작물 레지스트리 및 범용 작물|ko_kr-Plant-Registry-and-Crop-Types]]*.
