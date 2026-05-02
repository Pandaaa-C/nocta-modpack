package com.nocta.ores.data;

import com.nocta.ores.NoctaOres;
import com.nocta.ores.content.NoctaOreBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;

import java.util.concurrent.CompletableFuture;

public final class NoctaOresBlockTagsProvider extends BlockTagsProvider {

    public NoctaOresBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, NoctaOres.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(NoctaOreBlocks.BRONZE_ORE.get());
        tag(BlockTags.NEEDS_STONE_TOOL).add(NoctaOreBlocks.BRONZE_ORE.get());
    }
}