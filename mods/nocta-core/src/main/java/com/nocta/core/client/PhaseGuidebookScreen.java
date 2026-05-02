package com.nocta.core.client;

import com.nocta.core.api.phase.Phase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class PhaseGuidebookScreen extends Screen {

    public PhaseGuidebookScreen() {
        super(Component.translatable("screen.nocta_core.phase_guidebook"));
    }

    public static void open() {
        Minecraft.getInstance().setScreen(new PhaseGuidebookScreen());
    }

    @Override
    protected void init() {
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        graphics.fill(0, 0, this.width, this.height, 0xC0000000);
        super.render(graphics, mouseX, mouseY, partialTick);

        graphics.drawCenteredString(this.font, this.title, this.width / 2, 15, 0xFFFFFFFF);

        Phase current = ClientPhaseCache.getOrDefault();
        var currentLine = net.minecraft.network.chat.Component.translatable(
                "gui.nocta_core.guidebook.current", current.displayName());
        graphics.drawCenteredString(this.font, currentLine, this.width / 2, 30, 0xFFAAAAAA);

        graphics.fill(this.width / 2 - 150, 45, this.width / 2 + 150, 46, 0xFF606060);

        int rowHeight = 32;
        int listTop = 55;
        int listLeft = this.width / 2 - 150;
        int listRight = this.width / 2 + 150;

        for (int i = 0; i < Phase.values().length; i++) {
            Phase phase = Phase.values()[i];
            int rowTop = listTop + i * rowHeight;
            boolean unlocked = current.isAtLeast(phase);
            boolean isCurrent = phase == current;

            renderPhaseRow(graphics, phase, listLeft, rowTop, listRight - listLeft, rowHeight, unlocked, isCurrent);
        }
    }

    private void renderPhaseRow(GuiGraphics graphics, Phase phase, int x, int y, int width, int height, boolean unlocked, boolean isCurrent) {
        int bgColor = isCurrent ? 0x40FFD040 : (unlocked ? 0x20FFFFFF : 0x10000000);
        graphics.fill(x, y, x + width, y + height - 2, bgColor);

        if (isCurrent) {
            int borderColor = 0xFFFFD040;
            graphics.fill(x, y, x + width, y + 1, borderColor);
            graphics.fill(x, y + height - 3, x + width, y + height - 2, borderColor);
            graphics.fill(x, y, x + 1, y + height - 2, borderColor);
            graphics.fill(x + width - 1, y, x + width, y + height - 2, borderColor);
        }

        String indicator = unlocked ? "✓" : "✗";
        int indicatorColor = unlocked ? 0xFF60FF60 : 0xFFFF6060;
        graphics.drawString(this.font, indicator, x + 6, y + 6, indicatorColor);

        int nameColor = unlocked ? 0xFFFFFFFF : 0xFF808080;
        graphics.drawString(this.font, phase.displayName(), x + 22, y + 6, nameColor);

        int descColor = unlocked ? 0xFFC0C0C0 : 0xFF606060;
        graphics.drawString(this.font, phase.description(), x + 22, y + 18, descColor);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}