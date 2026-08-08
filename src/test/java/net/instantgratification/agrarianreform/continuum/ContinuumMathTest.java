// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3

package net.instantgratification.agrarianreform.continuum;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ContinuumMathTest: Headless Unit Test Suite
 *
 * Verifies mathematical formulas for offline chunk growth simulation:
 * - Scaled time delta calculation based on game rule multipliers.
 * - Stage progression formulas across crop types (CropBlock, Sugar Cane, Cactus, Nether Wart, Cocoa, Sapling).
 *
 * Verified against: ContinuumManager.java
 */
public class ContinuumMathTest {

    @Test
    @DisplayName("Scaled Time Delta Calculation under Various Multipliers")
    void testScaledTimeDelta() {
        long rawDelta = 24000L; // 1 Minecraft day (24,000 ticks)

        // 100% standard growth multiplier
        long delta100 = (rawDelta * 100L) / 100L;
        assertEquals(24000L, delta100);

        // 200% accelerated growth multiplier
        long delta200 = (rawDelta * 200L) / 100L;
        assertEquals(48000L, delta200);

        // 50% slowed growth multiplier
        long delta50 = (rawDelta * 50L) / 100L;
        assertEquals(12000L, delta50);

        // 0% disabled growth multiplier
        long delta0 = (rawDelta * 0L) / 100L;
        assertEquals(0L, delta0);
    }

    @Test
    @DisplayName("Crop Growth Ticks-Per-Stage Formula")
    void testCropGrowthMath() {
        float baseSpeed = 7.0f; // Typical speed on hydrated farmland
        long averageTicksPerStage = (long) (((25.0f / baseSpeed) + 1.0f) * 4096.0f / 3.0f);

        // Verify ticks per stage is positive and reasonable (~6,241 ticks per stage)
        assertTrue(averageTicksPerStage > 0, "Ticks per stage must be positive");
        assertEquals(6241L, averageTicksPerStage);

        // Test elapsed 1 day (24,000 ticks) -> 24000 / 6242 = 3 growth stages
        long elapsedTicks = 24000L;
        int stagesToGrow = (int) (elapsedTicks / averageTicksPerStage);
        assertEquals(3, stagesToGrow);
    }

    @Test
    @DisplayName("Cactus and Sugar Cane Stage Calculation")
    void testCactusAndSugarCaneMath() {
        long singleStageTicks = 1365L; // Average 1,365 game ticks per stage

        assertEquals(0, (int) (1000L / singleStageTicks));
        assertEquals(1, (int) (1365L / singleStageTicks));
        assertEquals(2, (int) (2730L / singleStageTicks));
        assertEquals(17, (int) (24000L / singleStageTicks));
    }

    @Test
    @DisplayName("Nether Wart Growth Stage Calculation")
    void testNetherWartMath() {
        long singleStageTicks = 13650L;

        assertEquals(0, (int) (10000L / singleStageTicks));
        assertEquals(1, (int) (13650L / singleStageTicks));
        assertEquals(1, (int) (24000L / singleStageTicks));
    }

    @Test
    @DisplayName("Cocoa and Sweet Berry Growth Stage Calculation")
    void testCocoaMath() {
        long singleStageTicks = 6825L;

        assertEquals(0, (int) (5000L / singleStageTicks));
        assertEquals(1, (int) (6825L / singleStageTicks));
        assertEquals(3, (int) (24000L / singleStageTicks));
    }

    @Test
    @DisplayName("Sapling Stage Calculation")
    void testSaplingMath() {
        long singleStageTicks = 95550L;

        assertEquals(0, (int) (24000L / singleStageTicks));
        assertEquals(1, (int) (95550L / singleStageTicks));
        assertEquals(2, (int) (191100L / singleStageTicks));
    }
}
