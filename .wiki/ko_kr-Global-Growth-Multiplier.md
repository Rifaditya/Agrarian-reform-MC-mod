# ⚡ 글로벌 성장 배율 및 작물별 개별 튜닝

The **Growth Multiplier Engine** allows server administrators and players to fine-tune the growth speed of agriculture worldwide or tailor specific growth curves per plant species.

---

## 📊 성장 배율 인포박스

| Property | Specification |
| :--- | :--- |
| **Global GameRule** | `agrarian_reform:global_growth_multiplier` |
| **Dynamic Crop Rule** | `agrarian_reform:crop_growth_multiplier_<crop_id>` |
| **Default Global** | `100` (100% Vanilla Baseline) |
| **Valid Values** | `-1` (Frozen), `0` (Inherit Global / Off), `1` to `2147483647` |
| **Optimization** | Max-Age Early-Break (aborts extra tick loop upon maturity) |

---

## 📐 배율 계산 수학 공식 및 해석

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

## ⚠️ 설정 및 명령어

```bash
# Set global growth speed to 200% (Double speed)
/gamerule agrarian_reform:global_growth_multiplier 200

# Override Wheat specifically to grow at 400%
/gamerule agrarian_reform:crop_growth_multiplier_wheat 400

# Freeze Sugar Cane completely
/gamerule agrarian_reform:crop_growth_multiplier_sugar_cane -1
```

---

*See also: [[성능 최적화 및 대기열 조절|ko_kr-Performance-and-Queue-Throttling]], [[작물 레지스트리 및 범용 작물|ko_kr-Plant-Registry-and-Crop-Types]], and [[컨티넘 (오프라인 성장 지속)|ko_kr-The-Continuum-Offline-Persistence]]*.
