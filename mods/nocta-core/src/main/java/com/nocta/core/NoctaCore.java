package com.nocta.core;

import com.nocta.core.api.phase.NoctaPlacementModifiers;
import com.nocta.core.api.phase.NoctaTriggers;
import com.nocta.core.command.NoctaCommands;
import com.nocta.core.content.NoctaRegistries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import org.slf4j.Logger;
import com.mojang.logging.LogUtils;

import com.nocta.core.content.NoctaBlocks;
import com.nocta.core.content.NoctaItems;
import com.nocta.core.content.NoctaCreativeTabs;

@Mod(NoctaCore.MOD_ID)
public class NoctaCore {
    public static final String MOD_ID = "nocta_core";
    public static final Logger LOGGER = LogUtils.getLogger();

    public NoctaCore(IEventBus modEventBus, ModContainer modContainer) {
        LOGGER.info("Initializing Nocta Core");

        NoctaBlocks.init();
        NoctaItems.init();

        NoctaRegistries.CORE.register(modEventBus);
        NoctaCreativeTabs.register(modEventBus);
        NoctaPlacementModifiers.register(modEventBus);
        NoctaTriggers.register(modEventBus);

        NoctaCommands.register();

        modEventBus.addListener(this::commonSetup);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("Nocta Core common setup complete");
    }
}