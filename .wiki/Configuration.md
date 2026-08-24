# ⚙️ Two-Tier Configuration Architecture

**Agrarian Reform** implements a **Two-Tier Configuration System** combining local JSON template storage with live in-game GameRules.

---

## 🏗️ Architecture Overview

```
                      GLOBAL CONFIG TEMPLATE
                  config/agrarian-reform.json
                                │
                                ▼ (Server / Integrated World Startup)
                    DYNAMIC GAMERULE MANAGER
                 Registers default world GameRules
                                │
                                ▼ (Runtime Administration)
                 MODMENU & YACL v3 CLIENT GUI  <--->  IN-GAME /gamerule COMMANDS
```

### 1. Global JSON Template (`config/agrarian-reform.json`)
The global JSON file stores default values applied when **NEW** worlds are generated. Modifying `config/agrarian-reform.json` updates baseline settings for future worlds without altering existing world GameRule states.

### 2. Live World GameRules (`agrarian_reform:*`)
Active worlds manage runtime settings directly through native Minecraft GameRules. Changes made via ModMenu GUI or `/gamerule` update the live world state instantly without requiring a server reboot.

---

## 📜 Default `config/agrarian-reform.json` Format

```json
{
  "configVersion": 1,
  "hydrationSourceRange": 8,
  "hydrationFlowingRange": 4,
  "rainGrowthAcceleration": 1,
  "growthBiodiversityBonus": true,
  "totalTrampleImmunity": false,
  "alwaysWetFarmland": false,
  "seedsGrowGrass": true,
  "rightClickHarvest": true,
  "universalBonemeal": true,
  "globalGrowthMultiplier": 100,
  "ambientCropRustle": true,
  "ambientVitalityParticles": true
}
```

---

## 🖥️ Optional Client GUI Integration (ModMenu & YACL v3)

When **ModMenu** and **YetAnotherConfigLib v3 (YACL)** are installed on the client, players can access a native configuration screen:

```java
public class ModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        if (FabricLoader.getInstance().isModLoaded("yet-another-config-lib")) {
            return YaclScreenHelper::createScreen;
        }
        return parent -> null;
    }
}
```

> 🛡️ **Dedicated Server Crash Safety**: All YACL screen classes are strictly isolated inside `YaclScreenHelper` and instantiated conditionally, ensuring dedicated server builds never attempt to load client GUI classes!

---

*See also: [[GameRules|GameRules]] and [[Developer Setup & Building|Developer-Setup-and-Building]]*.
