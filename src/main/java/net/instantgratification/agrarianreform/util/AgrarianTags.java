// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3

package net.instantgratification.agrarianreform.util;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

/**
 * AgrarianTags: Data-Driven Block and Item Tag Registry (MC 1.21.1)
 *
 * Verified against: TagKey.java (1.21.1)
 */
public class AgrarianTags {
    public static final TagKey<Block> CONTINUUM_PLANTS = TagKey.create(
            Registries.BLOCK,
            ResourceLocation.fromNamespaceAndPath("agrarianreform", "continuum_plants")
    );

    public static final TagKey<Item> SOFT_STEP_BOOTS = TagKey.create(
            Registries.ITEM,
            ResourceLocation.fromNamespaceAndPath("agrarian_reform", "soft_step_boots")
    );

    public static final TagKey<Item> CONVENTIONAL_SOFT_BOOTS = TagKey.create(
            Registries.ITEM,
            ResourceLocation.fromNamespaceAndPath("c", "boots/soft")
    );
}
