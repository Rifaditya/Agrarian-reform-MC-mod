# ⚡ Global Growth Multiplier

The **Global Growth Multiplier** allows server administrators and singleplayer worlds to fine-tune the growth speed of all agricultural flora world-wide.

---

## 📊 Multiplier Infobox

| Property | Specification |
| :--- | :--- |
| **GameRule Key** | `agrarian_reform:global_growth_multiplier` |
| **Data Type** | Integer |
| **Default Value** | `100` (100% Vanilla Baseline) |
| **Valid Bounds** | `0` (Disabled) to high integers |
| **Safety Warning** | Values excessive $>1000$ will cause high random tick load |

---

## 📐 Multiplier Calculation Math

The random tick loop intercepts plant growth via `BlockStateBaseMixin` and `GrowthHelper.handleRandomTick`:

### 1. Disabled Growth ($M = 0$)
When set to `0`, all plant random ticks are cancelled, freezing agricultural growth completely.

### 2. Slowed Growth ($0 < M < 100$)
When $M < 100$, random ticks execute with a probability of $\frac{M}{100}$. For example, at $M = 50$, random growth ticks occur at **50% of vanilla speed**.

### 3. Accelerated Growth ($M > 100$)
When $M > 100$, the engine calculates extra random tick runs $E$:

$$E = \left\lfloor \frac{M}{100} \right\rfloor - 1$$

A remainder check with probability $\text{rem} = M \pmod{100}$ awards an additional tick run if passed. The target plant executes $E + 1$ random ticks in succession during a single random tick event.

---

## ⚠️ Performance & Server Warnings

> [!WARNING]
> Setting `global_growth_multiplier` to extremely high values (e.g. `10000`) forces thousands of instant block updates per tick. Maintain values between `50` and `500` for optimal server performance.

```bash
# Example: Set crop growth speed to 200% (Double speed)
/gamerule agrarian_reform:global_growth_multiplier 200
```

---

*See also: [[Performance & Queue Throttling|Performance-and-Queue-Throttling]] and [[The Continuum (Offline Growth)|The-Continuum-Offline-Persistence]]*.
