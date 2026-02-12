package com.nocta.world.worldgen;

import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public final class NoctaWorldgen {
    private static void configured(BootstrapContext<ConfiguredFeature<?, ?>> ctx) {}

    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder();
}
