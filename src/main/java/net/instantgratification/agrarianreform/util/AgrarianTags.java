// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3

package net.instantgratification.agrarianreform.util;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

/**
 * AgrarianTags: Data-Driven Block and Item Tag Registry (MC 1.21.11)
 *
 * Verified against: TagKey.java (1.21.11)
 */
public class AgrarianTags {
    public static final TagKey<Block> CONTINUUM_PLANTS = TagKey.create(
            Registries.BLOCK,
            Identifier.fromNamespaceAndPath("agrarianreform", "continuum_plants")
    );

    public static final TagKey<Item> SOFT_STEP_BOOTS = TagKey.create(
            Registries.ITEM,
            Identifier.fromNamespaceAndPath("agrarian_reform", "soft_step_boots")
    );

    public static final TagKey<Item> CONVENTIONAL_SOFT_BOOTS = TagKey.create(
            Registries.ITEM,
            Identifier.fromNamespaceAndPath("c", "boots/soft")
    );
}
