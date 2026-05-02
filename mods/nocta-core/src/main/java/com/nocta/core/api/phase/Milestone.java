package com.nocta.core.api.phase;

import net.minecraft.network.chat.Component;

public record Milestone(String key, String descriptionKey) {
    public Component description() {
        return Component.translatable(descriptionKey);
    }
}