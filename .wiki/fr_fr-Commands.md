# 💻 Commandes Brigadier et administration

**Agrarian Reform** integrates seamlessly with Minecraft's Brigadier command engine and native `/gamerule` system.

---

## 📊 Infobox des commandes

| Property | Value |
| :--- | :--- |
| **Command Engine** | Mojang Brigadier (`net.minecraft.commands`) |
| **Primary Admin Entry**| `/gamerule agrarian_reform:<rule>` |
| **Permission Level** | Level 2 (OP / Admin required for updates) |
| **Tab Completion** | Dynamic tab completion for all namespaced rules |

---

## 🛠️ Exemples d'utilisation et d'administration

### 1. Soil & Protection Controls
```bash
# Enable total trample immunity for relaxed gameplay
/gamerule agrarian_reform:total_trample_immunity true

# Re-enable realistic trample mechanics (Soft Step required)
/gamerule agrarian_reform:total_trample_immunity false
```

### 2. Irrigation & Weather Tuning
```bash
# Expand water source block irrigation radius to 10 blocks
/gamerule agrarian_reform:hydration_source_range 10

# Increase rain spurt growth acceleration to 2 extra stages per rain tick
/gamerule agrarian_reform:rain_growth_acceleration 2
```

### 3. Global Growth Speed Calibration
```bash
# Accelerate all crop growth to double speed (200%)
/gamerule agrarian_reform:global_growth_multiplier 200

# Pause all crop growth world-wide (0%)
/gamerule agrarian_reform:global_growth_multiplier 0
```

---

*See also: [[Règles de jeu (GameRules)|fr_fr-GameRules]] and [[Configuration à deux niveaux|fr_fr-Configuration]]*.
