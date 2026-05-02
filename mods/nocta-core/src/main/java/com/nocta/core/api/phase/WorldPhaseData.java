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

    /** Reads the world phase. Always returns a non-null phase; defaults to STARTING_PHASE. */
    public static Phase get(ServerLevel level) {
        return getData(level).phase;
    }

    /** Reads the underlying SavedData, creating it with default phase if absent. */
    public static WorldPhaseData getData(ServerLevel level) {
        // Always anchor world phase to the overworld so all dimensions agree.
        ServerLevel overworld = level.getServer().overworld();
        return overworld.getDataStorage().computeIfAbsent(TYPE);
    }

    /**
     * Advances the world to the given phase if it's higher than the current phase.
     * Returns true if a change occurred.
     */
    public static boolean advanceTo(ServerLevel level, Phase target) {
        WorldPhaseData data = getData(level);
        if (target.isAtLeast(data.phase) && target != data.phase) {
            Phase old = data.phase;
            data.phase = target;
            data.setDirty();
            NoctaCore.LOGGER.info("World phase advanced: {} -> {}", old, target);
            // TODO: fire a PhaseAdvancedEvent so other systems can react.
            return true;
        }
        return false;
    }

    /** Force-set the phase, even backwards. For commands and testing only. */
    public static void forceSet(ServerLevel level, Phase target) {
        WorldPhaseData data = getData(level);
        if (data.phase != target) {
            data.phase = target;
            data.setDirty();
            NoctaCore.LOGGER.info("World phase force-set to: {}", target);
        }
    }
}