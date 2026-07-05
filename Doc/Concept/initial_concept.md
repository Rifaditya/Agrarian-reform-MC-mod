# Agrarian Reform (The Living Earth)

## I. Philosophy
> "The world should not wait for you to watch it."

In vanilla Minecraft, nature is performative; it only acts when a player is present to witness it. A chunk unloading is effectively a pause button on reality. **Agrarian Reform** challenges this by asserting that the world is a living, breathing simulation that persists independently of the player's gaze.

The goal is not just "faster farming" or "easier automation," but **pastoral immersion**. Returning from a week-long expedition to find your fields overgrown and ready for harvest creates a sense of time passing and a world that lives on its own.

## II. Core Mechanics

### 1. The Continuum (Offline Persistence)
The cornerstone of the mod. It bridges the gap between gameplay sessions and exploration.
- **The Simulation**: When a chunk unloads, the mod timestamps the specific crop blocks.
- **The Return**: Upon reloading, the mod calculates the time delta. It doesn't just "set to max age"; it simulates the exact number of random ticks that *would* have occurred.
- **The Feeling**: You plant seeds, go exploring in the Nether for 3 hours, and return to a mature harvest. The farm feels like a living entity, not a machine that needs your proximity to function.

### 2. Soil Resilience (Trample Logic)
Farmland is dirt that has been cared for, not eggshells. The vanilla mechanic of instant destruction is archaic and punitive without interaction.
- **Soft Step**: Players wearing **Leather Boots** or utilizing **Feather Falling** enchantments cannot trample crops.
- **Mass & Velocity**: Walking on crops is safe. *Jumping* or falling (> 0.6m height) without protection causes trample.
- **Ravager Logic**: Large beasts (Ravagers, Iron Golems) will always trample, maintaining the threat of raids.
- **[IG Toggle] Total Immunity**: A GameRule `totalTrampleImmunity` can be enabled to provide absolute immunity to all trampling, overriding the nuanced system for players who prefer the Instant Gratification experience.

### 3. Hydro-Dynamics (Better Irrigation)
Water should feel like a resource that permeates the soil, not a binary toggle.
- **Capillary Action**: Water source blocks irritate farmland up to **8 blocks** away (vanilla is 4).
- **Flow vs Source**: Flowing water only irrigates 4 blocks. This incentivizes well-maintained irrigation channels (sources) over lazy streams.
- **Rainfall**: During rain, all exposed farmland behaves as if hydrated, even without a water source. This triggers a temporary growth spurt, making storms a time of celebration for the farmer.

### 4. Polyculture (Biodiversity)
Monocultures are visually repetitive and ecologically weak. This mechanic subtly encourages the "aesthetic" chaotic farm.
- **The Mechanic**: A crop block checks its neighbors (North/South/East/West).
- **The Boost**: If a neighbor is a *different* crop type (e.g., Wheat next to Carrots), both receive a small growth probability boost (~10%). This is toggleable via `growth_biodiversity_bonus`.
- **The Result**: Players are rewarded for creating "strip farms" or mixed garden patches, which look more organic and beautiful than $500\times500$ fields of wheat.

## III. Aesthetic & Feedback
- **Rustle**: Walking through fully grown crops plays a subtle "brushing" sound.
- **Morning Dew**: At sunrise, farmland appears slightly darker (visual only) to simulate morning moisture.
- **Growth Ticks**: Extremely rare chance for a "green sparkle" particle when a crop grows, making a busy field feel alive.

## IV. Performance & Optimization

### The Hybrid Model (Best of Both Worlds)
We use a two-stage approach to eliminate lag entirely:
1.  **Stage 1: The O(1) Math (Instant)**
    - Instead of simulating every tick (e.g., looping 10,000 times for a 10-minute absence), we use a single formula:
      `GrowthStages = (TimePassed / AverageGrowthTime) * Variance`
    - This calculation is instant and costs almost zero CPU, regardless of how long the player was away.

2.  **Stage 2: The Throttled Update (Lag Prevention)**
    - Even if the math is instant, *applying* the result (updating 1000 blocks, sending network packets, rebuilding chunk meshes) will freeze the game.
    - **Solution**: We queue these valid updates and apply them slowly (**5 per tick / 100 per second**).

### Summary
- **Math**: O(1) (Instant)
- **Block Updates**: Throttled (Smooth)

## VI. Future Expansion / TODO
- [x] **Feature Expansion**:
  - [x] Implement `alwaysWetFarmland` GameRule/Toggle logic (Hydrate regardless of water proximity).
