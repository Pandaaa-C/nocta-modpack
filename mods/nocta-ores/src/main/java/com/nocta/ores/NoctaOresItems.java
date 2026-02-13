package com.nocta.ores;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

public final class NoctaOresItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(Registries.ITEM, NoctaOres.MODID);

    private static ResourceKey<Item> key(String id) {
        return ResourceKey.create(
                Registries.ITEM,
                Identifier.parse(NoctaOres.MODID + ":" + id)
        );
    }

    public static final DeferredHolder<Item, Item> NOCTITE_ORE = ITEMS.register("noctite_ore", () ->
            new BlockItem(
                    NoctaOresBlocks.NOCTITE_ORE.get(),
                    new Item.Properties().setId(key("noctite_ore"))
            )
    );

    public static final DeferredHolder<Item, Item> DEEPSLATE_NOCTITE_ORE = ITEMS.register("deepslate_noctite_ore", () ->
            new BlockItem(
                    NoctaOresBlocks.DEEPSLATE_NOCTITE_ORE.get(),
                    new Item.Properties().setId(key("deepslate_noctite_ore"))
            )
    );

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }

    private NoctaOresItems() {}
}
