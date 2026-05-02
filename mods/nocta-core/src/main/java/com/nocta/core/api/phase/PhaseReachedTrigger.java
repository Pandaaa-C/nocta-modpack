package com.nocta.core.api.phase;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.criterion.ContextAwarePredicate;
import net.minecraft.advancements.criterion.SimpleCriterionTrigger;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.StringRepresentable;

import java.util.Optional;

public class PhaseReachedTrigger extends SimpleCriterionTrigger<PhaseReachedTrigger.Instance> {

    @Override
    public Codec<Instance> codec() {
        return Instance.CODEC;
    }

    public void trigger(ServerPlayer player, Phase phase) {
        this.trigger(player, instance -> instance.matches(phase));
    }

    public record Instance(Optional<ContextAwarePredicate> player, Phase requiredPhase)
            implements SimpleCriterionTrigger.SimpleInstance {

        public static final Codec<Instance> CODEC = RecordCodecBuilder.create(builder ->
                builder.group(
                        ContextAwarePredicate.CODEC.optionalFieldOf("player").forGetter(Instance::player),
                        StringRepresentable.fromEnum(Phase::values)
                                .fieldOf("phase").forGetter(Instance::requiredPhase)
                ).apply(builder, Instance::new)
        );

        public boolean matches(Phase phase) {
            return phase.isAtLeast(requiredPhase);
        }
    }

    public Criterion<Instance> createCriterion(Phase phase) {
        return this.createCriterion(new Instance(Optional.empty(), phase));
    }
}