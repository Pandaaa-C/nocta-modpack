package com.nocta.world;

import com.nocta.world.content.NoctaWorldRegistries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;
import com.mojang.logging.LogUtils;

@Mod(NoctaWorld.MOD_ID)
public class NoctaWorld {
    public static final String MOD_ID = "nocta_world";
    public static final Logger LOGGER = LogUtils.getLogger();

    public NoctaWorld(IEventBus modEventBus, ModContainer modContainer) {
        LOGGER.info("Initializing Nocta World");

        NoctaWorldRegistries.WORLD.register(modEventBus);
    }
}