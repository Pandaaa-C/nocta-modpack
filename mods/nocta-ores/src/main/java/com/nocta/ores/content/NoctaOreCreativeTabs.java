package com.nocta.ores.content;

import com.nocta.ores.NoctaOres;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class NoctaOreCreativeTabs {

    public static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, NoctaOres.MOD_ID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> ORES_TAB =
            TABS.register("ores", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.nocta_ores"))
                    .icon(() -> new ItemStack(NoctaOreItems.RAW_BRONZE.get()))
                    .displayItems((params, output) -> {
                        output.accept(NoctaOreBlocks.BRONZE_ORE.get());
                        output.accept(NoctaOreItems.RAW_BRONZE.get());
                    })
                    .build());

    public static void register(IEventBus modEventBus) {
        TABS.register(modEventBus);
    }

    private NoctaOreCreativeTabs() {}
}