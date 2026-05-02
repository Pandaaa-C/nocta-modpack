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
}