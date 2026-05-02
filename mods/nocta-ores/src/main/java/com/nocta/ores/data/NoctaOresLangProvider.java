package com.nocta.ores.data;

import com.nocta.ores.NoctaOres;
import com.nocta.ores.content.NoctaOreBlocks;
import com.nocta.ores.content.NoctaOreItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public final class NoctaOresLangProvider extends LanguageProvider {

    public NoctaOresLangProvider(PackOutput output) {
        super(output, NoctaOres.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add("itemGroup.nocta_ores", "Nocta Ores");
        add(NoctaOreBlocks.BRONZE_ORE.get(), "Bronze Ore");
        add(NoctaOreItems.RAW_BRONZE.get(), "Raw Bronze");
    }
}