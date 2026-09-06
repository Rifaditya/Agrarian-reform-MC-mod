# ⚙️ 雙層設定架構體系

**Agrarian Reform** implements a **Two-Tier Configuration System** combining global JSON template storage with live in-game GameRules and automatic modded crop dirty-tracking persistence.

---

## 🏗️ 架構設計概覽

```
                      GLOBAL CONFIG TEMPLATE (Schema v2)
                         config/agrarian-reform.json
                                     │
                                     ▼ (Server / Integrated World Startup)
                         DYNAMIC GAMERULE MANAGER
                   Registers default world GameRules & discovers crops
                                     │
                                     ▼ (Runtime Administration)
        MODMENU & YACL v3 3-TAB CLIENT GUI  <--->  IN-GAME /gamerule COMMANDS
                                     │
                                     ▼ (Auto-Save on Unload / Save Event)
                       THREAD-SAFE DIRTY-TRACKING PERSISTENCE
```

### 1. Global JSON Template (`config/agrarian-reform.json`)
The global JSON file stores default values applied when **NEW** worlds are generated. Modifying `config/agrarian-reform.json` updates baseline settings for future worlds without altering existing world GameRule states.

### 2. Live World GameRules (`agrarian_reform:*`)
Active worlds manage runtime settings directly through native Minecraft GameRules. Changes made via the ModMenu GUI or `/gamerule` update the live world state instantly without requiring a server reboot.

### 3. Thread-Safe Dirty-Tracking Persistence & Mod Removal Resilience
* **Asynchronous Save Throttling**: When modded crops are discovered during gameplay, `AgrarianConfig` automatically marks its memory state as `dirty = true` without performing immediate disk writes. The configuration is safely written to disk only during natural chunk save points (`ServerLifecycleEvents.BEFORE_SAVE`) and server shutdown (`ServerLifecycleEvents.SERVER_STOPPING`).
* **Uninstalled Mod Safety**: Discovered crops are stored as raw String identifiers (`"farmersdelight:tomatoes"`) in `forcedCrops` and `forcedGrowthMultipliers`. If a mod is uninstalled, these entries remain harmlessly stored in the JSON config and `level.dat` without causing parsing crashes, and are seamlessly re-activated if the mod is re-installed in the future.

---

## 📜 預設 `config/agrarian-reform.json` (Schema v2)

```json
{
  "configVersion": 2,
  "hydrationSourceRange": 8,
  "hydrationFlowingRange": 4,
  "pureWaterHydrationOnly": false,
  "rainGrowthAcceleration": 1,
  "growthBiodiversityBonus": true,
  "totalTrampleImmunity": false,
  "trampleImmunityPlayersOnly": false,
  "alwaysWetFarmland": false,
  "seedsGrowGrass": true,
  "rightClickHarvest": true,
  "universalBonemeal": true,
  "globalGrowthMultiplier": 100,
  "ambientCropRustle": true,
  "ambientVitalityParticles": true,
  "forcedCrops": [
    "minecraft:wheat",
    "minecraft:carrots",
    "minecraft:potatoes",
    "minecraft:beetroots"
  ],
  "forcedGrowthMultipliers": {
    "minecraft:wheat": 0,
    "minecraft:carrots": 0,
    "minecraft:potatoes": 0,
    "minecraft:beetroots": 0
  }
}
```

---

## 🖥️ 可選客戶端 GUI 整合 (ModMenu & YACL v3)

When **ModMenu** and **YetAnotherConfigLib v3 (YACL)** are installed on the client, players can access an interactive 3-tab graphical configuration screen:

1. **General Options Tab**: Hydration radii sliders ($0 \to 32$), pure water toggle, rain growth acceleration slider ($0 \to 10$), biodiversity toggle, grass sowing, right-click harvest, universal bone meal, and global growth multiplier.
2. **Immersion & Aesthetics Tab**: Total trample immunity, player/tamed pet entity restriction toggle, always wet farmland, ambient crop rustling audio, and vitality particle sparkles.
3. **Crop Multipliers Tab**: Dynamically generated list of all discovered and registered modded crops in alphabetical order with integer field controls ($-1 = \text{frozen}$, $0 = \text{inherit global}$, $\ge 100 = \text{custom speed}$).

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

> 🛡️ **Dedicated Server Crash Safety**: All YACL screen classes are strictly isolated inside `YaclScreenHelper` and instantiated conditionally, ensuring dedicated server builds never attempt to load client GUI bytecode!

---

*See also: [[遊戲規則 (GameRules) 參考|zh_tw-GameRules]], [[作物註冊表與通用作物|zh_tw-Plant-Registry-and-Crop-Types]], and [[開發者環境與編譯指南|zh_tw-Developer-Setup-and-Building]]*.
