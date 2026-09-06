# ⚡ Multiplicador global de crescimento e ajuste por cultura

The **Growth Multiplier Engine** allows server administrators and players to fine-tune the growth speed of agriculture worldwide or tailor specific growth curves per plant species.

---

## 📊 Infobox de multiplicador

| Property | Specification |
| :--- | :--- |
| **Global GameRule** | `agrarian_reform:global_growth_multiplier` |
| **Dynamic Crop Rule** | `agrarian_reform:crop_growth_multiplier_<crop_id>` |
| **Default Global** | `100` (100% Vanilla Baseline) |
| **Valid Values** | `-1` (Frozen), `0` (Inherit Global / Off), `1` to `2147483647` |
| **Optimization** | Max-Age Early-Break (aborts extra tick loop upon maturity) |

---

## 📐 Matemática de cálculo e resolução

The random tick loop intercepts plant growth via `BlockStateBaseMixin` and `GrowthHelper.handleRandomTick`:

### 1. Hierarchy Resolution
1. **Dynamic Specific Override ($> 0$)**: If a specific crop rule is set, it overrides the global multiplier.
2. **Frozen / Disabled ($-1$ or Global $0$)**: Cancels random ticks and offline Continuum deltas completely ($0\%$ growth).
3. **Inherit Global ($0$)**: Falls back to the global multiplier $M$.

### 2. Slowed Growth ($0 < M < 100$)
When $M < 100$, random ticks execute with a probability of $\frac{M}{100}$. For example, at $M = 50$, random growth ticks occur at **50% of vanilla speed**.

### 3. Accelerated Growth ($M > 100$)
When $M > 100$, the engine calculates guaranteed extra random tick runs $E$:

$$E = \left\lfloor \frac{M}{100} \right\rfloor - 1$$

A fractional remainder check with probability $\text{rem} = M \pmod{100}$ awards an additional tick run.

### 4. Max-Age Early-Break Optimization
During accelerated loops (e.g. $M = 500$, executing 5 runs), `handleRandomTick` queries `AgrarianCropRules.isMaxAge(currentState)` after each run. The moment the crop reaches maximum maturity (e.g. Wheat Age 7), the loop terminates immediately, saving unnecessary block evaluations.

---

## ⚠️ Configuração e comandos

```bash
# Set global growth speed to 200% (Double speed)
/gamerule agrarian_reform:global_growth_multiplier 200

# Override Wheat specifically to grow at 400%
/gamerule agrarian_reform:crop_growth_multiplier_wheat 400

# Freeze Sugar Cane completely
/gamerule agrarian_reform:crop_growth_multiplier_sugar_cane -1
```

---

*See also: [[Desempenho e controle de filas|pt_br-Performance-and-Queue-Throttling]], [[Registro de plantas e culturas universais|pt_br-Plant-Registry-and-Crop-Types]], and [[O Continuum (Crescimento offline)|pt_br-The-Continuum-Offline-Persistence]]*.
