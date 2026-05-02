package com.nocta.core.client;

import com.nocta.core.api.phase.Phase;
import org.jetbrains.annotations.Nullable;

public final class ClientPhaseCache {

    @Nullable
    private static volatile Phase phase = null;

    private ClientPhaseCache() {}

    public static void set(@Nullable Phase phase) {
        ClientPhaseCache.phase = phase;
    }

    public static Phase getOrDefault() {
        Phase p = phase;
        return p != null ? p : Phase.STARTING_PHASE;
    }

    @Nullable
    public static Phase get() {
        return phase;
    }

    public static boolean hasReached(Phase required) {
        Phase p = phase;
        return p != null && p.isAtLeast(required);
    }

    public static void clear() {
        phase = null;
    }
}