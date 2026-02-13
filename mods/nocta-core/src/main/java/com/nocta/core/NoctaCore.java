package com.nocta.core;

import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

@Mod(NoctaCore.MODID)
public final class NoctaCore {
    public static final String MODID = "nocta_core";

    private static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);

    public NoctaCore(IEventBus modBus) {
        ITEMS.register(modBus);
        modBus.addListener(NoctaCore::addToTabs);
    }

    private static void addToTabs(BuildCreativeModeTabContentsEvent event) {}
}
