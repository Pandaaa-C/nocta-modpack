package com.nocta.world;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

@Mod(value = NoctaWorld.MOD_ID, dist = Dist.CLIENT)
public final class NoctaWorldClient {
    public NoctaWorldClient(IEventBus modEventBus, ModContainer modContainer) {
        NoctaWorld.LOGGER.info("Initializing Nocta World (client)");
    }
}