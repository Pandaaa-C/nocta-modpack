package com.nocta.core.content;

import com.nocta.core.client.PhaseGuidebookScreen;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class PhaseGuidebookItem extends Item {

    public PhaseGuidebookItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        if (level.isClientSide()) {
            openScreen();
        }
        return InteractionResult.SUCCESS;
    }

    private static void openScreen() {
        try {
            PhaseGuidebookScreen.open();
        } catch (Throwable t) {
            com.nocta.core.NoctaCore.LOGGER.error("Failed to open guidebook screen", t);
        }
    }
}