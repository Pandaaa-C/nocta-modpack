package com.nocta.core.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.nocta.core.api.phase.Phase;
import com.nocta.core.api.phase.WorldPhaseData;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.permissions.Permission;
import net.minecraft.server.permissions.PermissionLevel;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

import java.util.Arrays;

public final class NoctaCommands {
    private static final Permission GAMEMASTER = new Permission.HasCommandLevel(PermissionLevel.GAMEMASTERS);

    private static final SuggestionProvider<CommandSourceStack> PHASE_SUGGESTIONS =
            (ctx, builder) -> SharedSuggestionProvider.suggest(
                    Arrays.stream(Phase.values()).map(Phase::getSerializedName), builder);

    private NoctaCommands() {}

    public static void register() {
        NeoForge.EVENT_BUS.register(NoctaCommands.class);
    }

    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        register(event.getDispatcher());
    }

    private static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("nocta")
                .requires(src -> src.permissions().hasPermission(GAMEMASTER))
                .then(Commands.literal("phase")
                        .then(Commands.literal("get")
                                .executes(NoctaCommands::getPhase))
                        .then(Commands.literal("set")
                                .then(Commands.argument("phase", StringArgumentType.word())
                                        .suggests(PHASE_SUGGESTIONS)
                                        .executes(NoctaCommands::setPhase)))
                        .then(Commands.literal("advance")
                                .then(Commands.argument("phase", StringArgumentType.word())
                                        .suggests(PHASE_SUGGESTIONS)
                                        .executes(NoctaCommands::advancePhase)))));
    }

    private static int getPhase(CommandContext<CommandSourceStack> ctx) {
        ServerLevel level = ctx.getSource().getLevel();
        Phase current = WorldPhaseData.get(level);
        ctx.getSource().sendSuccess(
                () -> Component.literal("Current world phase: ").append(current.displayName()),
                false);
        return 1;
    }

    private static int setPhase(CommandContext<CommandSourceStack> ctx) {
        String name = StringArgumentType.getString(ctx, "phase");
        Phase target = Phase.byName(name);
        if (target == null) {
            ctx.getSource().sendFailure(Component.literal("Unknown phase: " + name));
            return 0;
        }
        WorldPhaseData.forceSet(ctx.getSource().getLevel(), target);
        ctx.getSource().sendSuccess(
                () -> Component.literal("World phase force-set to: ").append(target.displayName()),
                true);
        return 1;
    }

    private static int advancePhase(CommandContext<CommandSourceStack> ctx) {
        String name = StringArgumentType.getString(ctx, "phase");
        Phase target = Phase.byName(name);
        if (target == null) {
            ctx.getSource().sendFailure(Component.literal("Unknown phase: " + name));
            return 0;
        }
        boolean changed = WorldPhaseData.advanceTo(ctx.getSource().getLevel(), target);
        if (changed) {
            ctx.getSource().sendSuccess(
                    () -> Component.literal("World phase advanced to: ").append(target.displayName()),
                    true);
        } else {
            ctx.getSource().sendSuccess(
                    () -> Component.literal("World phase unchanged (already at or above " + target.getSerializedName() + ")"),
                    false);
        }
        return 1;
    }
}