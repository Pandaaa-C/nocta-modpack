package com.nocta.core.content;

import com.nocta.core.NoctaCore;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class NoctaCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, NoctaCore.MOD_ID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> NOCTA_TAB =
            TABS.register("nocta", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.nocta"))
                    .icon(() -> new ItemStack(NoctaItems.TEST_ITEM.get()))
                    .displayItems((params, output) -> {
                        output.accept(NoctaItems.TEST_ITEM.get());
                        output.accept(NoctaItems.PHASE_GUIDEBOOK.get());
                    })
                    .build());

    public static void register(IEventBus modEventBus) {
        TABS.register(modEventBus);
    }
}