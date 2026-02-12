package com.nocta.world;

import com.nocta.world.datagen.NoctaWorldDataGen;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(NoctaWorld.MODID)
public final class NoctaWorld {
    public static final String MODID = "nocta_world";

    public NoctaWorld(IEventBus modBus) {
        modBus.addListener(NoctaWorldDataGen::gatherServer);
        modBus.addListener(NoctaWorldDataGen::gatherClient);
    }
}
