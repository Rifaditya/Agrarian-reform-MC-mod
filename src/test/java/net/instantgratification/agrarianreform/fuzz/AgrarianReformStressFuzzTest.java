// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3

package net.instantgratification.agrarianreform.fuzz;

import net.instantgratification.agrarianreform.continuum.ContinuumData;
import net.instantgratification.agrarianreform.util.GrowthHelper;
import net.minecraft.world.level.ChunkPos;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

/**
 * AgrarianReformStressFuzzTest: Pillar 1 Chaos & Property Fuzzing Suite (MC 1.21.11)
 *
 * Executes 10,000+ randomized iterations with deterministic seed 0xDA51C263L
 * testing saturated arithmetic, extreme numeric bounds, NaN/Infinity guards,
 * and ContinuumData unload timestamp lifecycle invariants.
 */
public class AgrarianReformStressFuzzTest {

    private static final long SEED = 0xDA51C263L;
    private static final int ITERATIONS = 12_000;

    private static final int[] EXTREME_INTS = {
        Integer.MIN_VALUE, Integer.MIN_VALUE + 1, -100_000, -1, 0, 1, 7, 15, 100, 10_000, Integer.MAX_VALUE - 1, Integer.MAX_VALUE
    };

    private static final long[] EXTREME_LONGS = {
        Long.MIN_VALUE, Long.MIN_VALUE + 1L, -1000L, -1L, 0L, 1L, 1000L,
        ContinuumData.MAX_UNLOAD_AGE_TICKS - 1L,
        ContinuumData.MAX_UNLOAD_AGE_TICKS,
        ContinuumData.MAX_UNLOAD_AGE_TICKS + 1L,
        Long.MAX_VALUE - 1L, Long.MAX_VALUE
    };

    private static final float[] EXTREME_FLOATS = {
        Float.NaN, Float.NEGATIVE_INFINITY, Float.POSITIVE_INFINITY,
        -Float.MAX_VALUE, -1.0f, -0.0f, 0.0f, Float.MIN_VALUE, 1.0f, 7.0f, 25.0f, Float.MAX_VALUE
    };

    @Test
    @DisplayName("Pillar 1: 12,000-Iteration Chaos Fuzzing of GrowthHelper & ContinuumData")
    void testChaosFuzzingInvariants() {
        Random rng = new Random(SEED);
        ContinuumData continuumData = ContinuumData.create();

        for (int i = 0; i < ITERATIONS; i++) {
            // 1. Fuzz calculateEffectiveGrowthMultiplier
            int globalMult = pickInt(rng);
            int cropMult = pickInt(rng);
            int effectiveMult = GrowthHelper.calculateEffectiveGrowthMultiplier(globalMult, cropMult);
            assertTrue(effectiveMult >= 0, "Effective multiplier must never be negative: " + effectiveMult);
            if (cropMult > 0) {
                assertEquals(cropMult, effectiveMult);
            } else if (cropMult < 0) {
                assertEquals(0, effectiveMult);
            } else {
                assertEquals(Math.max(0, globalMult), effectiveMult);
            }

            // 2. Fuzz calculateRainSpurtAge
            int maxAge = Math.max(1, rng.nextInt(256));
            int currentAge = rng.nextInt(maxAge + 5);
            int spurtAmount = pickInt(rng);
            int resultAge = GrowthHelper.calculateRainSpurtAge(currentAge, maxAge, spurtAmount);
            if (currentAge >= maxAge || spurtAmount <= 0) {
                assertEquals(currentAge, resultAge);
            } else {
                assertTrue(resultAge >= currentAge && resultAge <= maxAge,
                        "Rain spurt age out of bounds: current=" + currentAge + ", max=" + maxAge + ", result=" + resultAge);
            }

            // 3. Fuzz calculateBiodiversityBonus
            float baseSpeed = pickFloat(rng);
            boolean hasNeighbor = rng.nextBoolean();
            float boostedSpeed = GrowthHelper.calculateBiodiversityBonus(baseSpeed, hasNeighbor);
            assertFalse(Float.isNaN(boostedSpeed), "Biodiversity speed must never be NaN");
            assertFalse(Float.isInfinite(boostedSpeed), "Biodiversity speed must never be Infinite");
            assertTrue(boostedSpeed >= 0.0f, "Biodiversity speed must be non-negative");

            // 4. Fuzz ContinuumData setUnloadTime / getUnloadTime / pruneStaleEntries
            int chunkX = rng.nextInt(2048) - 1024;
            int chunkZ = rng.nextInt(2048) - 1024;
            ChunkPos pos = new ChunkPos(chunkX, chunkZ);
            long unloadTime = pickLong(rng);
            continuumData.setUnloadTime(pos, unloadTime);
            assertEquals(unloadTime, continuumData.getUnloadTime(pos));

            long currentGametime = pickLong(rng);
            assertDoesNotThrow(() -> continuumData.pruneStaleEntries(currentGametime));

            if (i % 500 == 0) {
                continuumData.remove(pos);
                assertEquals(-1L, continuumData.getUnloadTime(pos));
            }
        }
    }

    private static int pickInt(Random rng) {
        if (rng.nextInt(4) == 0) {
            return EXTREME_INTS[rng.nextInt(EXTREME_INTS.length)];
        }
        return rng.nextInt();
    }

    private static long pickLong(Random rng) {
        if (rng.nextInt(4) == 0) {
            return EXTREME_LONGS[rng.nextInt(EXTREME_LONGS.length)];
        }
        return rng.nextLong();
    }

    private static float pickFloat(Random rng) {
        if (rng.nextInt(4) == 0) {
            return EXTREME_FLOATS[rng.nextInt(EXTREME_FLOATS.length)];
        }
        return (rng.nextFloat() - 0.2f) * 1000.0f;
    }
}
