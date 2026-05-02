package com.nocta.ores;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

@Mod(value = NoctaOres.MOD_ID, dist = Dist.CLIENT)
public final class NoctaOresClient {
    public NoctaOresClient(IEventBus modEventBus, ModContainer modContainer) {
        NoctaOres.LOGGER.info("Initializing Nocta Ores (client)");
    }
}