# Agrarian Reform: Project Summary

**Version:** 1.2.7 (Targeting Minecraft 26.1.2)  
**Dependencies:** Java 25, Fabric Loader >=0.18.4, DasikLibrary >=1.6.9+build.24

## 1. Project Philosophy (Vanilla Outsider)

Adheres to the "One Click, One Action" rule. Agrarian Reform enhances vanilla farming and crop interactions without industrial automation or complex pipes.

*   **Offline Persistence (The Continuum)**: Calculates tick offsets when chunk loads to simulate offline growth using high-performance mathematical modeling.
*   **Dynamic Irrigation**: Extends farmland hydration up to 8 blocks for source blocks and leverages weather events.
*   **Polyculture Synergy**: Rewards biodiversity by granting a growth speed boost to mixed crops.

## 2. Dependencies & Environment

*   `minecraft`: `26.1.2`
*   `java`: `25`
*   `fabric-api`: `0.145.4+`
*   `dasik-library`: `>=1.6.9+build.24`

## 3. Technical Implementation

*   **Offline Simulation**: Uses `ContinuumData` (World Saved Data) to store timestamps mapping chunk positions to unload times.
*   **Linear Growth Model**: Uses a single-pass O(1) math function rather than iterative ticking to prevent lag spikes on loading long-unloaded chunks.
*   **Throttled Event Dispatcher**: Employs a thread-safe update queue to apply growth stages incrementally, keeping client rendering and server TPS stable.
*   **Mixins**:
    - `mixin.FarmlandBlockMixin`: Implements custom hydration range calculations and weather integration.
    - `mixin.CropBlockMixin`: Integrates the polyculture adjacency bonus into the crop's growth speed.

## 4. Audit & Safety

*   **Network**: Runs offline, making no external web calls.
*   **Performance**: Hybrid simulation prevents CPU spikes.
*   **Memory**: Purges entries from the chunk unload map upon loading.

## 5. Documentation Map

*   [History of Changes](Changelogs/History.md)
*   [Moderator Audit Helper](../Audit_Helper_For_Moderators.md)
*   [Technical Architecture](Architecture/architecture.md)
