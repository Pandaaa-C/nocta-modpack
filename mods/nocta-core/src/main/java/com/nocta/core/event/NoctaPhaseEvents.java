package com.nocta.core.event;

import com.nocta.core.NoctaCore;
import com.nocta.core.api.phase.NoctaTriggers;
import com.nocta.core.api.phase.Phase;
import com.nocta.core.api.phase.WorldPhaseData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.ItemEntityPickupEvent;

@EventBusSubscriber(modid = NoctaCore.MOD_ID)
public final class NoctaPhaseEvents {

    private NoctaPhaseEvents() {}

    public static void reachPhase(ServerPlayer player, Phase phase) {
        NoctaTriggers.PHASE_REACHED.get().trigger(player, phase);

        if (player.level() instanceof ServerLevel server) {
            WorldPhaseData.advanceTo(server, phase);
        }
    }

    @SubscribeEvent
    public static void onPlayerJoin(net.neoforged.neoforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;
        if (!(player.level() instanceof ServerLevel server)) return;

        Phase current = WorldPhaseData.get(server);

        net.neoforged.neoforge.network.PacketDistributor.sendToPlayer(player,
                new com.nocta.core.network.WorldPhasePayload(current));

        for (Phase phase : Phase.values()) {
            if (current.isAtLeast(phase)) {
                NoctaTriggers.PHASE_REACHED.get().trigger(player, phase);
            } else {
                break;
            }
        }
    }

    @SubscribeEvent
    public static void onItemPickup(ItemEntityPickupEvent.Post event) {
        Player player = event.getPlayer();
        if (!(player instanceof ServerPlayer serverPlayer)) return;

        ItemStack stack = event.getOriginalStack();
        var key = net.minecraft.core.registries.BuiltInRegistries.ITEM.getKey(stack.getItem());
        if (key.getNamespace().equals("nocta_ores") && key.getPath().equals("raw_bronze")) {
            reachPhase(serverPlayer, Phase.BRONZE);
        }
    }
}