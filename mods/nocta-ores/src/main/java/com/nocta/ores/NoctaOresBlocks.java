package com.nocta.ores;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;

public final class NoctaOresBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(Registries.BLOCK, NoctaOres.MODID);

    private static ResourceKey<Block> key(String id) {
        return ResourceKey.create(
                Registries.BLOCK,
                Identifier.parse(NoctaOres.MODID + ":" + id)
        );
    }


    public static final DeferredHolder<Block, Block> NOCTITE_ORE = BLOCKS.register("noctite_ore", () ->
            new Block(BlockBehaviour.Properties.of()
                    .setId(key("noctite_ore"))
                    .strength(3.0F, 3.0F)
                    .sound(SoundType.STONE)
                    .requiresCorrectToolForDrops()
            )
    );

    public static final DeferredHolder<Block, Block> DEEPSLATE_NOCTITE_ORE = BLOCKS.register("deepslate_noctite_ore", () ->
            new Block(BlockBehaviour.Properties.of()
                    .setId(key("deepslate_noctite_ore"))
                    .strength(4.5F, 3.0F)
                    .sound(SoundType.DEEPSLATE)
                    .requiresCorrectToolForDrops()
            )
    );

    public static void register(IEventBus bus) {
        BLOCKS.register(bus);
    }

    private NoctaOresBlocks() {}
}
