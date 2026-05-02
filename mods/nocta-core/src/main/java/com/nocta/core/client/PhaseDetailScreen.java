package com.nocta.core.client;

import com.nocta.core.api.phase.Milestone;
import com.nocta.core.api.phase.Phase;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class PhaseDetailScreen extends Screen {

    private final Phase phase;
    private final Screen parent;

    public PhaseDetailScreen(Phase phase, Screen parent) {
        super(Component.translatable("screen.nocta_core.phase_detail", phase.displayName()));
        this.phase = phase;
        this.parent = parent;
    }

    @Override
    protected void init() {
        super.init();

        this.addRenderableWidget(
                Button.builder(
                        Component.translatable("gui.nocta_core.guidebook.back"),
                        btn -> this.minecraft.setScreen(parent)
                ).bounds(15, 15, 60, 20).build()
        );
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        graphics.fill(0, 0, this.width, this.height, 0xC0000000);
        super.render(graphics, mouseX, mouseY, partialTick);

        Phase current = ClientPhaseCache.getOrDefault();
        boolean unlocked = current.isAtLeast(phase);
        int themeColor = phase.themeColor();

        int contentLeft = this.width / 2 - 160;
        int contentRight = this.width / 2 + 160;
        int contentWidth = contentRight - contentLeft;

        int headerY = 50;
        graphics.renderItem(phase.iconItem().getDefaultInstance(), contentLeft, headerY);
        graphics.drawString(this.font, phase.displayName(), contentLeft + 24, headerY + 6, 0xFFFFFFFF);

        Component status = Component.translatable(unlocked
                ? "gui.nocta_core.guidebook.unlocked"
                : "gui.nocta_core.guidebook.locked");
        int statusColor = unlocked ? 0xFF60FF60 : 0xFFFF6060;
        int statusWidth = this.font.width(status);
        graphics.drawString(this.font, status, contentRight - statusWidth, headerY + 6, statusColor);

        int descY = headerY + 26;
        var descLines = this.font.split(phase.description(), contentWidth);
        for (var line : descLines) {
            graphics.drawString(this.font, line, contentLeft, descY, 0xFFCCCCCC);
            descY += 10;
        }

        int dividerY = descY + 10;
        graphics.fill(contentLeft, dividerY, contentRight, dividerY + 1,
                (0x80 << 24) | (themeColor & 0x00FFFFFF));

        int milestonesY = dividerY + 12;
        graphics.drawString(this.font,
                Component.translatable("gui.nocta_core.guidebook.milestones"),
                contentLeft, milestonesY, 0xFFFFFFFF);

        int milestoneRowY = milestonesY + 16;
        if (phase.milestones().isEmpty()) {
            graphics.drawString(this.font,
                    Component.literal("(No milestones — starting phase)"),
                    contentLeft + 12, milestoneRowY, 0xFF808080);
        } else {
            for (Milestone milestone : phase.milestones()) {
                renderMilestone(graphics, milestone, contentLeft, milestoneRowY, contentWidth, unlocked);
                milestoneRowY += 14;
            }
        }
    }

    private void renderMilestone(GuiGraphics graphics, Milestone milestone, int x, int y, int width, boolean phaseUnlocked) {
        String indicator = phaseUnlocked ? "✓" : "○";
        int indicatorColor = phaseUnlocked ? 0xFF60FF60 : 0xFF808080;
        graphics.drawString(this.font, indicator, x, y, indicatorColor);

        int textColor = phaseUnlocked ? 0xFFCCCCCC : 0xFFA0A0A0;
        graphics.drawString(this.font, milestone.description(), x + 14, y, textColor);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}