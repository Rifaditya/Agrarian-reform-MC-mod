// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3

package net.instantgratification.agrarianreform.util;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

/**
 * AgrarianTags: Data-Driven Block and Item Tag Registry
 *
 * Verified against: TagKey.java (26.2+)
 */
public class AgrarianTags {
    public static final TagKey<Block> CONTINUUM_PLANTS = TagKey.create(
            Registries.BLOCK,
            Identifier.fromNamespaceAndPath("agrarianreform", "continuum_plants")
    );
}
