package com.nocta.core.data;

import com.nocta.core.NoctaCore;
import com.nocta.core.api.phase.NoctaTriggers;
import com.nocta.core.api.phase.Phase;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.advancements.AdvancementProvider;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Items;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public final class NoctaCoreAdvancementProvider {

    public static AdvancementProvider create(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        return new AdvancementProvider(output, registries, List.of(new PhaseAdvancements()));
    }

    private static final class PhaseAdvancements implements AdvancementSubProvider {
        private static final Identifier BACKGROUND = Identifier.withDefaultNamespace("textures/block/stone.png");

        @Override
        public void generate(HolderLookup.Provider registries, Consumer<AdvancementHolder> consumer) {
            AdvancementHolder previous = null;

            for (Phase phase : Phase.values()) {
                boolean isRoot = (previous == null);

                Advancement.Builder builder = Advancement.Builder.advancement()
                        .display(
                                Items.STONE.getDefaultInstance(),
                                phase.displayName(),
                                phase.description(),
                                isRoot ? BACKGROUND : null,        // ← background only on root
                                isRoot ? AdvancementType.TASK : AdvancementType.GOAL,
                                true,
                                true,
                                false
                        )
                        .addCriterion("reach_" + phase.getSerializedName(),
                                NoctaTriggers.PHASE_REACHED.get().createCriterion(phase));

                if (previous != null) {
                    builder.parent(previous);
                }

                previous = builder.save(consumer,
                        Identifier.fromNamespaceAndPath(NoctaCore.MOD_ID, "phase/" + phase.getSerializedName()).toString());
            }
        }
    }
}