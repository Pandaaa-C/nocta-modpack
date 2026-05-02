package com.nocta.ores.content;

import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;

public final class NoctaOreItems {

    public static final DeferredItem<Item> RAW_BRONZE = NoctaOresRegistries.ORES.simpleItem("raw_bronze");

    public static void init() {
    }

    private NoctaOreItems() {}
}