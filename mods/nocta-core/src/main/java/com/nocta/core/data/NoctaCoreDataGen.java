package com.nocta.core.data;

import com.nocta.core.NoctaCore;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = NoctaCore.MOD_ID)
public final class NoctaCoreDataGen {

    private NoctaCoreDataGen() {}

    @SubscribeEvent
    public static void onGatherData(GatherDataEvent.Client event) {
        PackOutput output = event.getGenerator().getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        event.addProvider(new NoctaCoreModelProvider(output));
        event.addProvider(new NoctaCoreLangProvider(output));
        event.addProvider(NoctaCoreAdvancementProvider.create(output, lookupProvider));
    }
}