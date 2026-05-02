package com.nocta.core.api.phase;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.levelgen.placement.PlacementContext;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;

import java.util.stream.Stream;

public class PhaseGatedPlacement extends PlacementModifier {
    public static final MapCodec<PhaseGatedPlacement> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    StringRepresentable.fromEnum(Phase::values)
                            .fieldOf("required_phase")
                            .forGetter(p -> p.requiredPhase)
            ).apply(instance, PhaseGatedPlacement::new)
    );

    private final Phase requiredPhase;

    public PhaseGatedPlacement(Phase requiredPhase) {
        this.requiredPhase = requiredPhase;
    }

    public static PhaseGatedPlacement of(Phase requiredPhase) {
        return new PhaseGatedPlacement(requiredPhase);
    }

    @Override
    public Stream<BlockPos> getPositions(PlacementContext context, RandomSource random, BlockPos pos) {
        if (context.getLevel().getLevel() instanceof ServerLevel serverLevel) {
            if (PhaseGate.worldHasReached(serverLevel, requiredPhase)) {
                return Stream.of(pos);
            }
        }
        return Stream.empty();
    }

    @Override
    public PlacementModifierType<?> type() {
        return NoctaPlacementModifiers.PHASE_GATED.get();
    }
}