package com.nocta.core.api.registry;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;

public class NoctaRegistry {
    private final String modId;
    private final DeferredRegister.Blocks blocks;
    private final DeferredRegister.Items items;

    public NoctaRegistry(String modId) {
        this.modId = modId;
        this.blocks = DeferredRegister.createBlocks(modId);
        this.items = DeferredRegister.createItems(modId);
    }

    public String modId() {
        return modId;
    }

    public DeferredRegister.Blocks blocks() {
        return blocks;
    }

    public DeferredRegister.Items items() {
        return items;
    }

    public <T extends Item> DeferredItem<T> item(String name, Function<Item.Properties, T> factory) {
        return items.registerItem(name, factory);
    }

    public DeferredItem<Item> simpleItem(String name) {
        return items.registerSimpleItem(name);
    }

    public <T extends Block> DeferredBlock<T> block(String name, Function<net.minecraft.world.level.block.state.BlockBehaviour.Properties, T> factory) {
        return blocks.registerBlock(name, factory);
    }

    public <T extends Block> DeferredBlock<T> blockWithItem(
            String name,
            Function<net.minecraft.world.level.block.state.BlockBehaviour.Properties, T> factory
    ) {
        DeferredBlock<T> block = blocks.registerBlock(name, factory);
        items.registerSimpleBlockItem(block);
        return block;
    }

    public void register(IEventBus modEventBus) {
        blocks.register(modEventBus);
        items.register(modEventBus);
    }
}