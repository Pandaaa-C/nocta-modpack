package com.nocta.world.datagen;

import com.nocta.world.NoctaWorld;
import com.nocta.world.worldgen.NoctaWorldgen;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;

import java.util.Set;

public final class NoctaWorldDataGen {

    public static void gatherServer(net.neoforged.neoforge.data.event.GatherDataEvent.Server event) {
        addProviders(event);
    }

    public static void gatherClient(net.neoforged.neoforge.data.event.GatherDataEvent.Client event) {
        addProviders(event);
    }

    private static void addProviders(net.neoforged.neoforge.data.event.GatherDataEvent event) {
        PackOutput out = event.getGenerator().getPackOutput();
        var lookup = event.getLookupProvider();

        event.getGenerator().addProvider(true,
                new DatapackBuiltinEntriesProvider(out, lookup, NoctaWorldgen.BUILDER, Set.of(NoctaWorld.MODID))
        );
    }
}
