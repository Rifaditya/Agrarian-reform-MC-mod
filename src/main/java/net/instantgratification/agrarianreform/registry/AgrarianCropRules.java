// Copyright (C) 2026 Dasik (Rifaditya) | GNU GPLv3

package net.instantgratification.agrarianreform.registry;

import net.dasik.social.api.gamerule.DynamicGameRuleManager;
import net.dasik.social.api.registry.DynamicRegistryScanner;
import net.instantgratification.agrarianreform.AgrarianGameRules;
import net.instantgratification.agrarianreform.config.AgrarianConfig;
import net.instantgratification.agrarianreform.util.AgrarianTags;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AttachedStemBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.CactusBlock;
import net.minecraft.world.level.block.CocoaBlock;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.NetherWartBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.StemBlock;
import net.minecraft.world.level.block.SugarCaneBlock;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.block.VineBlock;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.gamerules.GameRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * AgrarianCropRules: Dynamic Crop Rules & Discovery Engine
 *
 * Manages dynamic per-crop GameRules, fast O(1) block identity caching,
 * and universal agricultural discovery across vanilla and modded blocks.
 *
 * Verified against: DynamicRegistryScanner.java (DasikLibrary 1.8.3+)
 */
public class AgrarianCropRules {

    private static final Logger LOGGER = LoggerFactory.getLogger(AgrarianCropRules.class);

    public static final List<Identifier> DYNAMIC_CROPS = new ArrayList<>();
    public static final Set<Identifier> FORCED_CROPS = ConcurrentHashMap.newKeySet();
    private static final Map<Block, Boolean> CROP_BLOCK_CACHE = new ConcurrentHashMap<>();

    public static void clearCropCache() {
        CROP_BLOCK_CACHE.clear();
    }

    public static boolean isCropBlock(Block block) {
        if (block == null) return false;
        return CROP_BLOCK_CACHE.computeIfAbsent(block, AgrarianCropRules::isCropBlockRaw);
    }

    private static boolean isCropBlockRaw(Block block) {
        try {
            Identifier id = BuiltInRegistries.BLOCK.getKey(block);
            if (id != null && (FORCED_CROPS.contains(id) || AgrarianConfig.get().isForcedCrop(id.toString()))) {
                return true;
            }

            if (block instanceof CropBlock
                    || block instanceof SugarCaneBlock
                    || block instanceof CactusBlock
                    || block instanceof NetherWartBlock
                    || block instanceof CocoaBlock
                    || block instanceof VineBlock
                    || block instanceof SaplingBlock
                    || block instanceof SweetBerryBushBlock
                    || block instanceof StemBlock
                    || block instanceof AttachedStemBlock) {
                return true;
            }

            if (block.defaultBlockState().is(BlockTags.CROPS)
                    || block.defaultBlockState().is(AgrarianTags.CONTINUUM_PLANTS)) {
                return true;
            }

            if (block instanceof BushBlock) {
                for (Property<?> prop : block.defaultBlockState().getProperties()) {
                    String propName = prop.getName().toLowerCase();
                    if (propName.equals("age") || propName.equals("growth") || propName.equals("stage") || propName.equals("level")) {
                        return true;
                    }
                }
            }
        } catch (Throwable ignored) {}
        return false;
    }

    public static void register() {
        AgrarianConfig config = AgrarianConfig.get();

        // 1. Immediately register all explicitly configured/forced crops at startup
        for (String idStr : config.getAllForcedCropIds()) {
            Identifier id = Identifier.tryParse(idStr);
            if (id != null) {
                registerDynamicRules(id);
                if (!DYNAMIC_CROPS.contains(id)) {
                    DYNAMIC_CROPS.add(id);
                }
                FORCED_CROPS.add(id);
            }
        }

        // 2. Universal 3-tier dynamic registry scanner
        DynamicRegistryScanner.subscribe(
            BuiltInRegistries.BLOCK,
            AgrarianCropRules::isCropBlock,
            (id, block) -> {
                config.recordDiscoveredCrop(id.toString());
                FORCED_CROPS.add(id);
                registerDynamicRules(id);
                if (!DYNAMIC_CROPS.contains(id)) {
                    DYNAMIC_CROPS.add(id);
                }
                LOGGER.info("[AgrarianReform:CropRules] Discovered agricultural block: {}", id);
            }
        );
    }

    public static void registerDynamicRules(Identifier id) {
        AgrarianConfig config = AgrarianConfig.get();
        String growthRuleName = "agrarian_reform:growth_" + id.getNamespace() + "_" + id.getPath();
        int defaultGrowth = config.getForcedGrowthMultiplier(id.toString());

        DynamicGameRuleManager.integerRule(growthRuleName, AgrarianGameRules.AGRARIAN_REFORM, defaultGrowth)
            .name(DynamicGameRuleManager.generateReadableName(id.getPath()) + " Growth Multiplier")
            .description("Growth speed multiplier percentage for " + id + " (0 = inherit global multiplier, 100 = 1x vanilla, 200 = 2x, -1 = frozen / 0%). Warning: Extremely high values increase CPU load per tick.")
            .range(Integer.MIN_VALUE, Integer.MAX_VALUE)
            .register();
        LOGGER.debug("[AgrarianReform:CropRules] Registered dynamic GameRule for crop: {}", id);
    }

    public static int getEffectiveGrowthMultiplier(Level level, Block block) {
        if (block != null) {
            Identifier id = BuiltInRegistries.BLOCK.getKey(block);
            if (id != null) {
                String growthRuleName = "agrarian_reform:growth_" + id.getNamespace() + "_" + id.getPath();
                @SuppressWarnings("unchecked")
                GameRule<Integer> dynamicRule = (GameRule<Integer>) DynamicGameRuleManager.getDynamicRules().get(growthRuleName);
                if (dynamicRule == null) {
                    int defGrowth = AgrarianConfig.get().getForcedGrowthMultiplier(id.toString());
                    dynamicRule = DynamicGameRuleManager.integerRule(growthRuleName,
                            AgrarianGameRules.AGRARIAN_REFORM, defGrowth).range(Integer.MIN_VALUE, Integer.MAX_VALUE).register();
                    if (dynamicRule != null && !DYNAMIC_CROPS.contains(id)) {
                        DYNAMIC_CROPS.add(id);
                    }
                }
                if (dynamicRule != null) {
                    int dynamicVal = DynamicGameRuleManager.getInt(level, dynamicRule);
                    if (dynamicVal != 0) {
                        return dynamicVal < 0 ? 0 : dynamicVal;
                    }
                }

                int forcedVal = AgrarianConfig.get().getForcedGrowthMultiplier(id.toString());
                if (forcedVal != 0) {
                    return forcedVal < 0 ? 0 : forcedVal;
                }
            }
        }

        int global = DynamicGameRuleManager.getInt(level, AgrarianGameRules.GLOBAL_GROWTH_MULTIPLIER);
        return global != 0 ? (global < 0 ? 0 : global) : 100;
    }
}
