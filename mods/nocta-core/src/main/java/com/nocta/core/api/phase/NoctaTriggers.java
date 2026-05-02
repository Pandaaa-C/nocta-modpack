package com.nocta.core.api.phase;

import com.nocta.core.NoctaCore;
import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class NoctaTriggers {

    public static final DeferredRegister<CriterionTrigger<?>> TRIGGERS =
            DeferredRegister.create(Registries.TRIGGER_TYPE, NoctaCore.MOD_ID);

    public static final DeferredHolder<CriterionTrigger<?>, PhaseReachedTrigger> PHASE_REACHED =
            TRIGGERS.register("phase_reached", PhaseReachedTrigger::new);

    public static void register(IEventBus modEventBus) {
        TRIGGERS.register(modEventBus);
    }

    private NoctaTriggers() {}
}