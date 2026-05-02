package com.nocta.core.content;

import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;

public class NoctaItems {
    public static final DeferredItem<Item> TEST_ITEM =
            NoctaRegistries.CORE.simpleItem("test_item");

    public static void init() {
        // Touching this class triggers static initialization of all fields above.
    }
}