package com.nocta.ores;

import com.nocta.ores.content.NoctaOreBlocks;
import com.nocta.ores.content.NoctaOreCreativeTabs;
import com.nocta.ores.content.NoctaOreItems;
import com.nocta.ores.content.NoctaOresRegistries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;
import com.mojang.logging.LogUtils;

@Mod(NoctaOres.MOD_ID)
public class NoctaOres {
    public static final String MOD_ID = "nocta_ores";
    public static final Logger LOGGER = LogUtils.getLogger();

    public NoctaOres(IEventBus modEventBus, ModContainer modContainer) {
        LOGGER.info("Initializing Nocta Ores");

        NoctaOreBlocks.init();
        NoctaOreItems.init();

        NoctaOresRegistries.ORES.register(modEventBus);
        NoctaOreCreativeTabs.register(modEventBus);
    }
}