# Continuum Improvements Design Plan

This document tracks planned improvements for **The Continuum** (unloaded chunk growth simulator) to enhance parity, mod compatibility, and detail simulation accuracy.

---

## 📋 Feature Checklist

### 1. 🪱 Compost & Fertilizer Persistence
Integrate soil enrichment values with offline simulation.
- [ ] Record soil nutrient/fertilizer values in the NBT data of the chunk at unload.
- [ ] Consume fertilizer charges over the offline period to dynamically speed up the simulated growth ticks.
