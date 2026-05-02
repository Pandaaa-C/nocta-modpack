package com.nocta.core.api.phase;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.nocta.core.NoctaCore;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.saveddata.SavedDataType;
import net.minecraft.resources.Identifier;

public class WorldPhaseData extends SavedData {
    public static final Identifier ID = Identifier.fromNamespaceAndPath(NoctaCore.MOD_ID, "world_phase");
    public static final String FILE_NAME = NoctaCore.MOD_ID + "_world_phase";

    public static final Codec<WorldPhaseData> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    StringRepresentable.fromEnum(Phase::values).fieldOf("phase").forGetter(d -> d.phase)
            ).apply(instance, WorldPhaseData::new)
    );

    public static final SavedDataType<WorldPhaseData> TYPE = new SavedDataType<>(
            FILE_NAME,
            ctx -> new WorldPhaseData(Phase.STARTING_PHASE),
            ctx -> CODEC,
            null
    );

    private Phase phase;

    public WorldPhaseData(Phase phase) {
        this.phase = phase;
    }

    public Phase phase() {
        return phase;
    }

    public static Phase get(ServerLevel level) {
        return getData(level).phase;
    }

    public static WorldPhaseData getData(ServerLevel level) {
        ServerLevel overworld = level.getServer().overworld();
        return overworld.getDataStorage().computeIfAbsent(TYPE);
    }

    public static boolean advanceTo(ServerLevel level, Phase target) {
        WorldPhaseData data = getData(level);
        if (target.isAtLeast(data.phase) && target != data.phase) {
            Phase old = data.phase;
            data.phase = target;
            data.setDirty();
            NoctaCore.LOGGER.info("World phase advanced: {} -> {}", old, target);
            broadcastToAllPlayers(level, target);
            return true;
        }
        return false;
    }

    public static void forceSet(ServerLevel level, Phase target) {
        WorldPhaseData data = getData(level);
        if (data.phase != target) {
            data.phase = target;
            data.setDirty();
            NoctaCore.LOGGER.info("World phase force-set to: {}", target);
            broadcastToAllPlayers(level, target);
        }
    }

    private static void broadcastToAllPlayers(ServerLevel level, Phase phase) {
        var payload = new com.nocta.core.network.WorldPhasePayload(phase);
        for (var player : level.getServer().getPlayerList().getPlayers()) {
            net.neoforged.neoforge.network.PacketDistributor.sendToPlayer(player, payload);
        }
    }
}