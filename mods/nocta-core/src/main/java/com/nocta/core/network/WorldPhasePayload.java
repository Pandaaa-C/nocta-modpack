package com.nocta.core.network;

import com.nocta.core.NoctaCore;
import com.nocta.core.api.phase.Phase;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

public record WorldPhasePayload(Phase phase) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<WorldPhasePayload> TYPE =
            new CustomPacketPayload.Type<>(Identifier.fromNamespaceAndPath(NoctaCore.MOD_ID, "world_phase"));

    public static final StreamCodec<ByteBuf, WorldPhasePayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.idMapper(
                    ordinal -> Phase.values()[ordinal],
                    Phase::ordinal),
            WorldPhasePayload::phase,
            WorldPhasePayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}