package com.nocta.ores.data;

import com.nocta.ores.NoctaOres;
import com.nocta.ores.content.NoctaOreBlocks;
import com.nocta.ores.content.NoctaOreItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.data.PackOutput;

public final class NoctaOresModelProvider extends ModelProvider {

    public NoctaOresModelProvider(PackOutput output) {
        super(output, NoctaOres.MOD_ID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        blockModels.createTrivialCube(NoctaOreBlocks.BRONZE_ORE.get());

        itemModels.generateFlatItem(NoctaOreItems.RAW_BRONZE.get(), net.minecraft.client.data.models.model.ModelTemplates.FLAT_ITEM);
    }
}