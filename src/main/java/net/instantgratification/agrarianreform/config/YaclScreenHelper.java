/*
 * Copyright (C) 2026 Dasik (Rifaditya)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package net.instantgratification.agrarianreform.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.BooleanControllerBuilder;
import dev.isxander.yacl3.api.controller.IntegerFieldControllerBuilder;
import dev.isxander.yacl3.gui.controllers.slider.IntegerSliderController;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

/**
 * YaclScreenHelper: YetAnotherConfigLib UI Screen Builder
 *
 * Builds options categories, groups, binding controls, and warning notices.
 *
 * Verified against: YaclScreenHelper.java (YACL 3.9.5+)
 */
public class YaclScreenHelper {
    public static ConfigScreenFactory<?> createScreen() {
        return YaclScreenHelper::buildScreen;
    }

    private static Screen buildScreen(Screen parent) {
        AgrarianConfig config = AgrarianConfig.get();

        return YetAnotherConfigLib.createBuilder()
            .title(Component.translatable("config.agrarian_reform.title"))
            .category(ConfigCategory.createBuilder()
                .name(Component.translatable("config.agrarian_reform.category.general"))
                .group(OptionGroup.createBuilder()
                    .name(Component.translatable("config.agrarian_reform.warning"))

                    // Hydration Source Range
                    .option(Option.<Integer>createBuilder()
                        .name(Component.translatable("gamerule.agrarian_reform.hydration_source_range"))
                        .description(OptionDescription.of(Component.translatable("gamerule.agrarian_reform.hydration_source_range.description")))
                        .binding(
                            8,
                            () -> config.hydrationSourceRange,
                            val -> config.hydrationSourceRange = val
                        )
                        .customController(opt -> new IntegerSliderController(opt, 0, 32, 1))
                        .build()
                    )

                    // Hydration Flowing Range
                    .option(Option.<Integer>createBuilder()
                        .name(Component.translatable("gamerule.agrarian_reform.hydration_flowing_range"))
                        .description(OptionDescription.of(Component.translatable("gamerule.agrarian_reform.hydration_flowing_range.description")))
                        .binding(
                            4,
                            () -> config.hydrationFlowingRange,
                            val -> config.hydrationFlowingRange = val
                        )
                        .customController(opt -> new IntegerSliderController(opt, 0, 32, 1))
                        .build()
                    )

                    // Rain Growth Acceleration
                    .option(Option.<Integer>createBuilder()
                        .name(Component.translatable("gamerule.agrarian_reform.rain_growth_acceleration"))
                        .description(OptionDescription.of(Component.translatable("gamerule.agrarian_reform.rain_growth_acceleration.description")))
                        .binding(
                            1,
                            () -> config.rainGrowthAcceleration,
                            val -> config.rainGrowthAcceleration = val
                        )
                        .customController(opt -> new IntegerSliderController(opt, 0, 10, 1))
                        .build()
                    )

                    // Biodiversity Bonus
                    .option(Option.<Boolean>createBuilder()
                        .name(Component.translatable("gamerule.agrarian_reform.growth_biodiversity_bonus"))
                        .description(OptionDescription.of(Component.translatable("gamerule.agrarian_reform.growth_biodiversity_bonus.description")))
                        .binding(
                            true,
                            () -> config.growthBiodiversityBonus,
                            val -> config.growthBiodiversityBonus = val
                        )
                        .controller(BooleanControllerBuilder::create)
                        .build()
                    )

                    // Seeds Grow Grass
                    .option(Option.<Boolean>createBuilder()
                        .name(Component.translatable("gamerule.agrarian_reform.seeds_grow_grass"))
                        .description(OptionDescription.of(Component.translatable("gamerule.agrarian_reform.seeds_grow_grass.description")))
                        .binding(
                            true,
                            () -> config.seedsGrowGrass,
                            val -> config.seedsGrowGrass = val
                        )
                        .controller(BooleanControllerBuilder::create)
                        .build()
                    )

                    // Right Click Harvest
                    .option(Option.<Boolean>createBuilder()
                        .name(Component.translatable("gamerule.agrarian_reform.right_click_harvest"))
                        .description(OptionDescription.of(Component.translatable("gamerule.agrarian_reform.right_click_harvest.description")))
                        .binding(
                            true,
                            () -> config.rightClickHarvest,
                            val -> config.rightClickHarvest = val
                        )
                        .controller(BooleanControllerBuilder::create)
                        .build()
                    )

                    // Universal Bone Meal
                    .option(Option.<Boolean>createBuilder()
                        .name(Component.translatable("gamerule.agrarian_reform.universal_bonemeal"))
                        .description(OptionDescription.of(Component.translatable("gamerule.agrarian_reform.universal_bonemeal.description")))
                        .binding(
                            true,
                            () -> config.universalBonemeal,
                            val -> config.universalBonemeal = val
                        )
                        .controller(BooleanControllerBuilder::create)
                        .build()
                    )

                    // Global Growth Multiplier
                    .option(Option.<Integer>createBuilder()
                        .name(Component.translatable("gamerule.agrarian_reform.global_growth_multiplier"))
                        .description(OptionDescription.of(Component.translatable("gamerule.agrarian_reform.global_growth_multiplier.description")))
                        .binding(
                            100,
                            () -> config.globalGrowthMultiplier,
                            val -> config.globalGrowthMultiplier = val
                        )
                        .controller(opt -> IntegerFieldControllerBuilder.create(opt)
                            .min(0)
                            .max(Integer.MAX_VALUE)
                        )
                        .build()
                    )

                    .build()
                )
                .build()
            )
            .category(ConfigCategory.createBuilder()
                .name(Component.translatable("config.agrarian_reform.category.immersion"))
                .group(OptionGroup.createBuilder()
                    .name(Component.translatable("config.agrarian_reform.warning"))

                    // Total Trample Immunity
                    .option(Option.<Boolean>createBuilder()
                        .name(Component.translatable("gamerule.agrarian_reform.total_trample_immunity"))
                        .description(OptionDescription.of(Component.translatable("gamerule.agrarian_reform.total_trample_immunity.description")))
                        .binding(
                            false,
                            () -> config.totalTrampleImmunity,
                            val -> config.totalTrampleImmunity = val
                        )
                        .controller(BooleanControllerBuilder::create)
                        .build()
                    )

                    // Always Wet Farmland
                    .option(Option.<Boolean>createBuilder()
                        .name(Component.translatable("gamerule.agrarian_reform.always_wet_farmland"))
                        .description(OptionDescription.of(Component.translatable("gamerule.agrarian_reform.always_wet_farmland.description")))
                        .binding(
                            false,
                            () -> config.alwaysWetFarmland,
                            val -> config.alwaysWetFarmland = val
                        )
                        .controller(BooleanControllerBuilder::create)
                        .build()
                    )

                    // Ambient Crop Rustle
                    .option(Option.<Boolean>createBuilder()
                        .name(Component.translatable("gamerule.agrarian_reform.ambient_crop_rustle"))
                        .description(OptionDescription.of(Component.translatable("gamerule.agrarian_reform.ambient_crop_rustle.description")))
                        .binding(
                            true,
                            () -> config.ambientCropRustle,
                            val -> config.ambientCropRustle = val
                        )
                        .controller(BooleanControllerBuilder::create)
                        .build()
                    )

                    // Ambient Vitality Particles
                    .option(Option.<Boolean>createBuilder()
                        .name(Component.translatable("gamerule.agrarian_reform.ambient_vitality_particles"))
                        .description(OptionDescription.of(Component.translatable("gamerule.agrarian_reform.ambient_vitality_particles.description")))
                        .binding(
                            true,
                            () -> config.ambientVitalityParticles,
                            val -> config.ambientVitalityParticles = val
                        )
                        .controller(BooleanControllerBuilder::create)
                        .build()
                    )

                    .build()
                )
                .build()
            )
            .save(AgrarianConfig::save)
            .build()
            .generateScreen(parent);
    }
}
