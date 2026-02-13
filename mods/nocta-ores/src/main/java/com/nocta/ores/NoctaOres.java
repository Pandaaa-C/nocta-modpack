package com.nocta.ores;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(NoctaOres.MODID)
public final class NoctaOres {
    public static final String MODID = "nocta_ores";

    public NoctaOres(IEventBus bus) {
        NoctaOresBlocks.register(bus);
        NoctaOresItems.register(bus);
    }
}