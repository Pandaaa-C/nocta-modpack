package com.nocta.ores.data;

import com.nocta.ores.content.NoctaOreBlocks;
import com.nocta.ores.content.NoctaOreItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Set;

public final class NoctaOresLootTableProvider {

    public static final List<LootTableProvider.SubProviderEntry> SUB_PROVIDERS = List.of(
            new LootTableProvider.SubProviderEntry(BlockLoot::new, LootContextParamSets.BLOCK)
    );

    private NoctaOresLootTableProvider() {}

    private static final class BlockLoot extends BlockLootSubProvider {
        protected BlockLoot(HolderLookup.Provider registries) {
            super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
        }

        @Override
        protected void generate() {
            add(NoctaOreBlocks.BRONZE_ORE.get(),
                    block -> createOreDrop(block, NoctaOreItems.RAW_BRONZE.get()));
        }

        @Override
        protected Iterable<net.minecraft.world.level.block.Block> getKnownBlocks() {
            return NoctaOreBlocks.knownBlocks();
        }
    }
}