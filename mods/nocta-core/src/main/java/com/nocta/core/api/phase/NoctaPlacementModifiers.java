package com.nocta.core.api.phase;

import com.nocta.core.NoctaCore;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class NoctaPlacementModifiers {

    public static final DeferredRegister<PlacementModifierType<?>> TYPES =
            DeferredRegister.create(Registries.PLACEMENT_MODIFIER_TYPE, NoctaCore.MOD_ID);

    public static final DeferredHolder<PlacementModifierType<?>, PlacementModifierType<PhaseGatedPlacement>> PHASE_GATED =
            TYPES.register("phase_gated", () -> () -> PhaseGatedPlacement.CODEC);

    public static void register(IEventBus modEventBus) {
        TYPES.register(modEventBus);
    }

    private NoctaPlacementModifiers() {}
}