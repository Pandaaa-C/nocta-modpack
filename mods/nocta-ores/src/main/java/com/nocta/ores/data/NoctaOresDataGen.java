package com.nocta.ores.data;

import com.nocta.ores.NoctaOres;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = NoctaOres.MOD_ID)
public final class NoctaOresDataGen {

    private NoctaOresDataGen() {}

    @SubscribeEvent
    public static void onGatherData(GatherDataEvent.Client event) {
        PackOutput output = event.getGenerator().getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        event.addProvider(new NoctaOresModelProvider(output));
        event.addProvider(new NoctaOresLangProvider(output));

        event.addProvider(new NoctaOresBlockTagsProvider(output, lookupProvider));
        event.addProvider(new LootTableProvider(
                output, Set.of(), NoctaOresLootTableProvider.SUB_PROVIDERS, lookupProvider));

        event.addProvider(new NoctaOresRecipeProvider.Runner(output, lookupProvider));
    }
}