package com.nocta.core.api.phase;

import net.minecraft.network.chat.Component;
import net.minecraft.util.StringRepresentable;

public enum Phase implements StringRepresentable {
    STONE("stone"),
    BRONZE("bronze"),
    IRON("iron"),
    AETHER("aether"),
    MECHANISM("mechanism"),
    RESONANCE("resonance"),
    VOIDFORGED("voidforged"),
    ETERNAL("eternal");

    public static final Phase STARTING_PHASE = STONE;

    private final String name;

    Phase(String name) {
        this.name = name;
    }

    @Override
    public String getSerializedName() {
        return name;
    }

    public String translationKey() {
        return "phase.nocta." + name;
    }

    public String descriptionKey() {
        return "phase.nocta." + name + ".description";
    }

    public Component displayName() {
        return Component.translatable(translationKey());
    }

    public Component description() {
        return Component.translatable(descriptionKey());
    }

    public boolean isAtLeast(Phase required) {
        return this.ordinal() >= required.ordinal();
    }

    public Phase next() {
        Phase[] values = values();
        int next = ordinal() + 1;
        return next < values.length ? values[next] : null;
    }

    public static Phase byName(String name) {
        for (Phase phase : values()) {
            if (phase.name.equals(name)) return phase;
        }
        return null;
    }

    public net.minecraft.world.item.Item iconItem() {
        return switch (this) {
            case STONE -> net.minecraft.world.item.Items.COBBLESTONE;
            case BRONZE -> net.minecraft.world.item.Items.RAW_COPPER;
            case IRON -> net.minecraft.world.item.Items.RAW_IRON;
            case AETHER -> net.minecraft.world.item.Items.AMETHYST_SHARD;
            case MECHANISM -> net.minecraft.world.item.Items.COPPER_INGOT;
            case RESONANCE -> net.minecraft.world.item.Items.AMETHYST_BLOCK;
            case VOIDFORGED -> net.minecraft.world.item.Items.NETHERITE_INGOT;
            case ETERNAL -> net.minecraft.world.item.Items.NETHER_STAR;
        };
    }

    public int themeColor() {
        return switch (this) {
            case STONE -> 0xFF888888;
            case BRONZE -> 0xFFCD7F32;
            case IRON -> 0xFFD0D0D0;
            case AETHER -> 0xFFC080FF;
            case MECHANISM -> 0xFFFFA040;
            case RESONANCE -> 0xFFFF80C0;
            case VOIDFORGED -> 0xFF402080;
            case ETERNAL -> 0xFFFFD700;
        };
    }
}