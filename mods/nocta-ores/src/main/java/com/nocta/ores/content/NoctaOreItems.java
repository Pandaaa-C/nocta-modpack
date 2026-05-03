package com.nocta.ores.content;

import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;

public final class NoctaOreItems {

    public static final DeferredItem<Item> RAW_BRONZE = NoctaOresRegistries.ORES.simpleItem("raw_bronze");
    public static final DeferredItem<Item> RAW_TIN = NoctaOresRegistries.ORES.simpleItem("raw_tin");
    public static final DeferredItem<Item> TIN_INGOT = NoctaOresRegistries.ORES.simpleItem("tin_ingot");
    public static final DeferredItem<Item> BRONZE_INGOT = NoctaOresRegistries.ORES.simpleItem("bronze_ingot");

    public static void init() {
    }

    private NoctaOreItems() {}
}