package com.nocta.core.api.phase;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;

public final class PhaseGate {
    private PhaseGate() {}

    public static boolean worldHasReached(ServerLevel level, Phase required) {
        return WorldPhaseData.get(level).isAtLeast(required);
    }

    public static boolean worldHasReached(Player player, Phase required) {
        if (player.level() instanceof ServerLevel server) {
            return worldHasReached(server, required);
        }
        return false;
    }
}