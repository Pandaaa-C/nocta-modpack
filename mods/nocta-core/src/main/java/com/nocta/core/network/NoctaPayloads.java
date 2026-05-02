package com.nocta.core.network;

import com.nocta.core.NoctaCore;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(modid = NoctaCore.MOD_ID)
public final class NoctaPayloads {

    private NoctaPayloads() {}

    @SubscribeEvent
    public static void onRegister(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar(NoctaCore.MOD_ID).versioned("1");

        registrar.playToClient(
                WorldPhasePayload.TYPE,
                WorldPhasePayload.STREAM_CODEC,
                ClientPhaseHandler::handle
        );
    }
}