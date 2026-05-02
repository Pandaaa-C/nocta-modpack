package com.nocta.core.network;

import com.nocta.core.NoctaCore;
import com.nocta.core.client.ClientPhaseCache;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public final class ClientPhaseHandler {

    private ClientPhaseHandler() {}

    public static void handle(WorldPhasePayload payload, IPayloadContext context) {
        context.enqueueWork(() -> {
            ClientPhaseCache.set(payload.phase());
            NoctaCore.LOGGER.info("Received world phase from server: {}", payload.phase());
        });
    }
}