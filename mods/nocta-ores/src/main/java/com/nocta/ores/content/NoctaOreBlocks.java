package com.nocta.ores.content;

import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.util.valueproviders.UniformInt;
import net.neoforged.neoforge.registries.DeferredBlock;

public final class NoctaOreBlocks{
    public static final DeferredBlock<DropExperienceBlock> BRONZE_ORE = NoctaOresRegistries.ORES.blockWithItem(
            "bronze_ore",
            props -> new DropExperienceBlock(
                    UniformInt.of(0, 2),
                    props.mapColor(MapColor.STONE)
                            .strength(3.0F, 3.0F)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.STONE)
            )
    );

    public static final DeferredBlock<DropExperienceBlock> TIN_ORE = NoctaOresRegistries.ORES.blockWithItem(
            "tin_ore",
            props -> new DropExperienceBlock(
                    UniformInt.of(0, 2),
                    props.mapColor(MapColor.STONE)
                            .strength(3.0F, 3.0F)
                            .requiresCorrectToolForDrops()
                            .sound(SoundType.STONE)
            )
    );

    public static void init() {
        // Touch this class to trigger static init.
    }

    private NoctaOreBlocks() {}

    public static Iterable<net.minecraft.world.level.block.Block> knownBlocks() {
        return NoctaOresRegistries.ORES.blocks().getEntries().stream()
                .map(holder -> (net.minecraft.world.level.block.Block) holder.value())
                .toList();
    }
}