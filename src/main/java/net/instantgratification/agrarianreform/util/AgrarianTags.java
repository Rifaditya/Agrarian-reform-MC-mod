// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3

package net.instantgratification.agrarianreform.util;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

/**
 * AgrarianTags: Data-Driven Block and Item Tag Registry (MC 1.20.1)
 *
 * Verified against: TagKey.java (1.20.1)
 */
public class AgrarianTags {
    public static final TagKey<Block> CONTINUUM_PLANTS = TagKey.create(
            Registries.BLOCK,
            new ResourceLocation("agrarianreform", "continuum_plants")
    );

    public static final TagKey<Item> SOFT_STEP_BOOTS = TagKey.create(
            Registries.ITEM,
            new ResourceLocation("agrarian_reform", "soft_step_boots")
    );

    public static final TagKey<Item> CHICKEN_FOOD = TagKey.create(
            Registries.ITEM,
            new ResourceLocation("minecraft", "chicken_food")
    );

    public static final TagKey<Item> C_SEEDS = TagKey.create(
            Registries.ITEM,
            new ResourceLocation("c", "seeds")
    );

    public static final TagKey<Item> CONVENTIONAL_SOFT_BOOTS = TagKey.create(
            Registries.ITEM,
            new ResourceLocation("c", "boots/soft")
    );
}
