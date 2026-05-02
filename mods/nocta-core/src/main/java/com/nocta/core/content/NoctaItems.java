package com.nocta.core.content;

import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;

public class NoctaItems {
    public static final DeferredItem<Item> TEST_ITEM =
            NoctaRegistries.CORE.simpleItem("test_item");

    public static final DeferredItem<PhaseGuidebookItem> PHASE_GUIDEBOOK =
            NoctaRegistries.CORE.item("phase_guidebook",
                    props -> new PhaseGuidebookItem(props.stacksTo(1)));

    public static void init() {
        // Touching this class triggers static initialization of all fields above.
    }
}