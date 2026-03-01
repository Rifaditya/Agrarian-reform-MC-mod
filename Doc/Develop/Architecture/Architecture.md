# Agrarian Reform Architecture

## System Overview
Agrarian Reform operates on a hybrid event-math model to ensure zero-lag synchronization of agricultural states across time deltas.

```mermaid
graph TD
    A[World/Chunk Event] --> B{Event Type?}
    B -- Unload --> C[Timestamp ChunkPos]
    B -- Load --> D[Calculate Time Delta]
    D --> E[Simulate Random Ticks O-1]
    E --> F[Queue Throttled Updates]
    F --> G[Apply Growth to BlockState]
    
    H[Block Tick] --> I{GameRule Check?}
    I -- Rain --> J[Force Hydration]
    I -- Polyculture --> K[Adjacency Bonus]
    J --> L[Vanilla Growth Logic]
    K --> L
```

## Core Systems

### 1. The Continuum (Offline Manager)
- **Data Source**: `ContinuumData` (PersistentState).
- **Trigger**: `ServerChunkEvents.CHUNK_LOAD`.
- **Logic**: Mathematical approximation of random tick probability over a long time delta to prevent iterative loops.

### 2. Hydro-Dynamics
- **FarmlandBlockMixin**: Intercepts hydration checks to extend range based on water state (Source vs Flowing).
- **Weather Integration**: Dynamic hydration during rain events.

### 3. Polyculture Synergy
- **CropBlockMixin**: Injects adjacency checks into `getGrowthSpeed` to reward biodiversity.
