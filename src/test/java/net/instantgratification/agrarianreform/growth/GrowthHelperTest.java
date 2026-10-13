// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3

package net.instantgratification.agrarianreform.growth;

import net.instantgratification.agrarianreform.util.GrowthHelper;
import net.minecraft.SharedConstants;
import net.minecraft.server.Bootstrap;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * GrowthHelperTest: Headless Unit Test Suite (MC 26.3)
 *
 * Verifies calculation math for:
 * - Dynamic & global growth multiplier resolution with uncapped Integer.MAX_VALUE saturation.
 * - Rain acceleration crop age progression and integer overflow protection.
 * - Biodiversity polyculture bonus speed calculation and NaN/Infinity safety.
 *
 * Verified against: GrowthHelper.java
 */
public class GrowthHelperTest {

    @BeforeAll
    static void initMinecraft() {
        try {
            SharedConstants.tryDetectVersion();
            Bootstrap.bootStrap();
        } catch (Throwable ignored) {
        }
    }

    @Test
    @DisplayName("Multiplier Scaling and Saturated Uncapped Bounds")
    void testEffectiveGrowthMultiplier() {
        // Standard global 100%, no crop override (0)
        assertEquals(100, GrowthHelper.calculateEffectiveGrowthMultiplier(100, 0));

        // Crop override takes precedence
        assertEquals(250, GrowthHelper.calculateEffectiveGrowthMultiplier(100, 250));

        // Frozen growth (0%)
        assertEquals(0, GrowthHelper.calculateEffectiveGrowthMultiplier(0, 0));

        // Negative values saturated to 0 (no negative probability)
        assertEquals(0, GrowthHelper.calculateEffectiveGrowthMultiplier(-50, 0));
        assertEquals(0, GrowthHelper.calculateEffectiveGrowthMultiplier(100, -20));
        assertEquals(0, GrowthHelper.calculateEffectiveGrowthMultiplier(Integer.MIN_VALUE, 0));
        assertEquals(0, GrowthHelper.calculateEffectiveGrowthMultiplier(100, Integer.MIN_VALUE));

        // Extreme values preserved up to Integer.MAX_VALUE (Freedom Over Anti-Crash, no nanny clamps)
        assertEquals(50000, GrowthHelper.calculateEffectiveGrowthMultiplier(50000, 0));
        assertEquals(99999, GrowthHelper.calculateEffectiveGrowthMultiplier(100, 99999));
        assertEquals(Integer.MAX_VALUE, GrowthHelper.calculateEffectiveGrowthMultiplier(Integer.MAX_VALUE, 0));
        assertEquals(Integer.MAX_VALUE, GrowthHelper.calculateEffectiveGrowthMultiplier(100, Integer.MAX_VALUE));
    }

    @Test
    @DisplayName("Rain Growth Acceleration Progression and Overflow Protection")
    void testRainSpurtAge() {
        // Current age 2, max age 7, spurt 1 -> 3
        assertEquals(3, GrowthHelper.calculateRainSpurtAge(2, 7, 1));

        // Current age 6, max age 7, spurt 3 -> 7 (capped at max age)
        assertEquals(7, GrowthHelper.calculateRainSpurtAge(6, 7, 3));

        // Already at max age -> remains max age
        assertEquals(7, GrowthHelper.calculateRainSpurtAge(7, 7, 2));

        // Non-positive spurt -> unchanged
        assertEquals(4, GrowthHelper.calculateRainSpurtAge(4, 7, 0));
        assertEquals(4, GrowthHelper.calculateRainSpurtAge(4, 7, -1));
        assertEquals(4, GrowthHelper.calculateRainSpurtAge(4, 7, Integer.MIN_VALUE));

        // Integer.MAX_VALUE spurt overflow protection -> saturates at maxAge without wrapping negative
        assertEquals(7, GrowthHelper.calculateRainSpurtAge(2, 7, Integer.MAX_VALUE));
    }

    @Test
    @DisplayName("Biodiversity Polyculture Bonus Calculation and NaN/Infinity Safety")
    void testBiodiversityBonus() {
        float baseSpeed = 7.0f;

        // Neighboring different crop yields +0.10f speed bonus
        float withBonus = GrowthHelper.calculateBiodiversityBonus(baseSpeed, true);
        assertEquals(7.10f, withBonus, 0.0001f);

        // Monoculture yields exact base speed
        float withoutBonus = GrowthHelper.calculateBiodiversityBonus(baseSpeed, false);
        assertEquals(7.0f, withoutBonus, 0.0001f);

        // NaN and negative base speeds saturate to 0.0f
        assertEquals(0.0f, GrowthHelper.calculateBiodiversityBonus(Float.NaN, true));
        assertEquals(0.0f, GrowthHelper.calculateBiodiversityBonus(-5.0f, true));
        assertEquals(0.0f, GrowthHelper.calculateBiodiversityBonus(Float.NEGATIVE_INFINITY, true));

        // Infinite and extreme base speeds saturate to Float.MAX_VALUE
        assertEquals(Float.MAX_VALUE, GrowthHelper.calculateBiodiversityBonus(Float.POSITIVE_INFINITY, true));
        assertEquals(Float.MAX_VALUE, GrowthHelper.calculateBiodiversityBonus(Float.MAX_VALUE, true));
    }
}
