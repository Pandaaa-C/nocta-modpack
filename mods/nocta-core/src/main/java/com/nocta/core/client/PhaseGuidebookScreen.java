package com.nocta.core.client;

import com.nocta.core.api.phase.Phase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.network.chat.Component;

public class PhaseGuidebookScreen extends Screen {
    private final java.util.List<RowHit> rowHits = new java.util.ArrayList<>();

    private record RowHit(Phase phase, int x, int y, int width, int height) {}

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

        int listLeft = this.width / 2 - 150;
        int listRight = this.width / 2 + 150;
        int listWidth = listRight - listLeft;

        rowHits.clear();
        int y = 55;
        for (Phase phase : Phase.values()) {
            boolean unlocked = current.isAtLeast(phase);
            boolean isCurrent = phase == current;
            int rowHeight = renderPhaseRow(graphics, phase, listLeft, y, listWidth, unlocked, isCurrent);
            rowHits.add(new RowHit(phase, listLeft, y, listWidth, rowHeight));
            y += rowHeight + 2;
        }
    }

    private int renderPhaseRow(GuiGraphics graphics, Phase phase, int x, int y, int width,
                               boolean unlocked, boolean isCurrent) {
        int themeColor = phase.themeColor();
        int textIndent = 30;
        int rightPadding = 8;
        int descWidth = width - textIndent - rightPadding;

        var descLines = this.font.split(phase.description(), descWidth);
        int height = 12 + descLines.size() * 10 + 6;

        int bgAlpha = isCurrent ? 0x60 : (unlocked ? 0x30 : 0x18);
        int rowBg = (bgAlpha << 24) | (themeColor & 0x00FFFFFF);
        graphics.fill(x, y, x + width, y + height, rowBg);

        if (isCurrent) {
            graphics.fill(x, y, x + width, y + 1, themeColor);
            graphics.fill(x, y + height - 1, x + width, y + height, themeColor);
            graphics.fill(x, y, x + 1, y + height, themeColor);
            graphics.fill(x + width - 1, y, x + width, y + height, themeColor);
        }

        graphics.renderItem(phase.iconItem().getDefaultInstance(), x + 6, y + 4);

        int nameColor = unlocked ? 0xFFFFFFFF : 0xFF808080;
        graphics.drawString(this.font, phase.displayName(), x + textIndent, y + 6, nameColor);

        int descColor = unlocked ? 0xFFC0C0C0 : 0xFF606060;
        int descY = y + 18;
        for (var line : descLines) {
            graphics.drawString(this.font, line, x + textIndent, descY, descColor);
            descY += 10;
        }

        return height;
    }

    @Override
    public boolean mouseClicked(MouseButtonEvent event, boolean doubleClick) {
        if (event.button() == 0) {
            double mouseX = event.x();
            double mouseY = event.y();
            for (RowHit hit : rowHits) {
                if (mouseX >= hit.x && mouseX <= hit.x + hit.width &&
                        mouseY >= hit.y && mouseY <= hit.y + hit.height) {
                    openDetailPage(hit.phase);
                    return true;
                }
            }
        }
        return super.mouseClicked(event, doubleClick);
    }

    private void openDetailPage(Phase phase) {
        net.minecraft.client.Minecraft.getInstance().setScreen(new PhaseDetailScreen(phase, this));
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}