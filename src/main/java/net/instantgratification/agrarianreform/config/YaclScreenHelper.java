// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3

package net.instantgratification.agrarianreform.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import dev.isxander.yacl3.api.YetAnotherConfigLib;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionGroup;
import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.OptionDescription;
import dev.isxander.yacl3.api.controller.BooleanControllerBuilder;
import dev.isxander.yacl3.api.controller.IntegerFieldControllerBuilder;
import dev.isxander.yacl3.gui.controllers.slider.IntegerSliderController;
import net.dasik.social.api.config.DasikSupportHelper;
import net.dasik.social.api.gamerule.DynamicGameRuleManager;
import net.instantgratification.agrarianreform.registry.AgrarianCropRules;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

import java.util.Set;
import java.util.TreeSet;

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

        // 1. General Category
        OptionGroup.Builder generalGroup = OptionGroup.createBuilder()
            .name(Component.translatable("config.agrarian_reform.category.general"));

        Option<?> supportButton = (Option<?>) DasikSupportHelper.createYaclButton();
        if (supportButton != null) {
            generalGroup.option(supportButton);
        }

        ConfigCategory.Builder generalCategory = ConfigCategory.createBuilder()
            .name(Component.translatable("config.agrarian_reform.category.general"))
            .group(generalGroup
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

                // Pure Water Hydration Only
                .option(Option.<Boolean>createBuilder()
                    .name(Component.translatable("gamerule.agrarian_reform.pure_water_hydration_only"))
                    .description(OptionDescription.of(Component.translatable("gamerule.agrarian_reform.pure_water_hydration_only.description")))
                    .binding(
                        false,
                        () -> config.pureWaterHydrationOnly,
                        val -> config.pureWaterHydrationOnly = val
                    )
                    .controller(BooleanControllerBuilder::create)
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
            );

        // 2. Immersion Category
        OptionGroup.Builder immersionGroup = OptionGroup.createBuilder()
            .name(Component.translatable("config.agrarian_reform.category.immersion"));

        Option<?> immersionSupportButton = (Option<?>) DasikSupportHelper.createYaclButton();
        if (immersionSupportButton != null) {
            immersionGroup.option(immersionSupportButton);
        }

        ConfigCategory.Builder immersionCategory = ConfigCategory.createBuilder()
            .name(Component.translatable("config.agrarian_reform.category.immersion"))
            .group(immersionGroup
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

                // Trample Immunity Players Only
                .option(Option.<Boolean>createBuilder()
                    .name(Component.translatable("gamerule.agrarian_reform.trample_immunity_players_only"))
                    .description(OptionDescription.of(Component.translatable("gamerule.agrarian_reform.trample_immunity_players_only.description")))
                    .binding(
                        false,
                        () -> config.trampleImmunityPlayersOnly,
                        val -> config.trampleImmunityPlayersOnly = val
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
            );

        // 3. Crop Multipliers Category
        ConfigCategory.Builder cropsCategory = ConfigCategory.createBuilder()
            .name(Component.translatable("config.agrarian_reform.category.crops"));

        OptionGroup.Builder cropsGroup = OptionGroup.createBuilder()
            .name(Component.translatable("config.agrarian_reform.category.crops"));

        Option<?> cropsSupportButton = (Option<?>) DasikSupportHelper.createYaclButton();
        if (cropsSupportButton != null) {
            cropsGroup.option(cropsSupportButton);
        }

        Set<String> allCrops = new TreeSet<>();
        for (Identifier id : AgrarianCropRules.DYNAMIC_CROPS) {
            allCrops.add(id.toString());
        }
        allCrops.addAll(config.getAllForcedCropIds());

        for (String cropIdStr : allCrops) {
            Identifier id = Identifier.tryParse(cropIdStr);
            String readableName = id != null ? DynamicGameRuleManager.generateReadableName(id.getPath()) : cropIdStr;

            cropsGroup.option(Option.<Integer>createBuilder()
                .name(Component.literal(readableName + " Multiplier"))
                .description(OptionDescription.of(Component.literal("Growth speed multiplier percentage for " + cropIdStr + " (0 = inherit global, 100 = 1x vanilla, 200 = 2x, -1 = frozen / 0%).")))
                .binding(
                    0,
                    () -> config.getForcedGrowthMultiplier(cropIdStr),
                    val -> config.setForcedGrowthMultiplier(cropIdStr, val)
                )
                .controller(opt -> IntegerFieldControllerBuilder.create(opt)
                    .min(-1)
                    .max(Integer.MAX_VALUE)
                )
                .build()
            );
        }

        cropsCategory.group(cropsGroup.build());

        return YetAnotherConfigLib.createBuilder()
            .title(Component.translatable("config.agrarian_reform.title"))
            .category(generalCategory.build())
            .category(immersionCategory.build())
            .category(cropsCategory.build())
            .save(AgrarianConfig::save)
            .build()
            .generateScreen(parent);
    }
}
