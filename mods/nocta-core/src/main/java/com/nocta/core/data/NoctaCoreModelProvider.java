package com.nocta.core.data;

import com.nocta.core.NoctaCore;
import com.nocta.core.content.NoctaItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.data.PackOutput;

public final class NoctaCoreModelProvider extends ModelProvider {

    public NoctaCoreModelProvider(PackOutput output) {
        super(output, NoctaCore.MOD_ID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        itemModels.generateFlatItem(NoctaItems.TEST_ITEM.get(), ModelTemplates.FLAT_ITEM);
    }
}