package com.nocta.core.data;

import com.nocta.core.NoctaCore;
import com.nocta.core.content.NoctaItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public final class NoctaCoreLangProvider extends LanguageProvider {

    public NoctaCoreLangProvider(PackOutput output) {
        super(output, NoctaCore.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        // Creative tab
        add("itemGroup.nocta", "Nocta");

        // Items
        add(NoctaItems.TEST_ITEM.get(), "Test Item");

        // Phases
        add("phase.nocta.stone", "Stone Age");
        add("phase.nocta.stone.description", "The beginning of the journey. Master the basics of survival.");
        add("phase.nocta.bronze", "Bronze Age");
        add("phase.nocta.bronze.description", "Smelt your first alloys and forge basic Nocta tools.");
        add("phase.nocta.iron", "Iron Age");
        add("phase.nocta.iron.description", "Refined metallurgy unlocks stronger equipment.");
        add("phase.nocta.aether", "Aetheric Awakening");
        add("phase.nocta.aether.description", "Magic stirs in the world. Mana flows through ley lines.");
        add("phase.nocta.mechanism", "Age of Mechanism");
        add("phase.nocta.mechanism.description", "Energy and electricity power new machines.");
        add("phase.nocta.resonance", "Resonance");
        add("phase.nocta.resonance.description", "The boundary between magic and machine dissolves.");
        add("phase.nocta.voidforged", "Voidforged");
        add("phase.nocta.voidforged.description", "Forbidden dimensions yield their secrets.");
        add("phase.nocta.eternal", "Eternal");
        add("phase.nocta.eternal.description", "Beyond the end.");

        // Placement modifier type (optional but tidy)
        add("placement_modifier_type.nocta_core.phase_gated", "Phase Gated");

        add(NoctaItems.PHASE_GUIDEBOOK.get(), "Phase Guidebook");
        add("screen.nocta_core.phase_guidebook", "Phase Guidebook");
        add("gui.nocta_core.guidebook.current", "Current era: %s");
    }
}