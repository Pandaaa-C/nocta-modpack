package com.nocta.core.event;

import com.nocta.core.NoctaCore;
import com.nocta.core.api.phase.NoctaTriggers;
import com.nocta.core.api.phase.Phase;
import com.nocta.core.api.phase.WorldPhaseData;
import com.nocta.core.network.WorldPhasePayload;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.ItemEntityPickupEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.network.PacketDistributor;

@EventBusSubscriber(modid = NoctaCore.MOD_ID)
public final class NoctaPhaseEvents {

    private NoctaPhaseEvents() {}

    public static void reachPhase(ServerPlayer player, Phase phase) {
        NoctaTriggers.PHASE_REACHED.get().trigger(player, phase);
        if (player.level() instanceof ServerLevel server) {
            WorldPhaseData.advanceTo(server, phase);
        }
    }

    private static Identifier itemId(Item item) {
        return BuiltInRegistries.ITEM.getKey(item);
    }

    private static boolean matches(ItemStack stack, String namespace, String path) {
        Identifier id = itemId(stack.getItem());
        return id.getNamespace().equals(namespace) && id.getPath().equals(path);
    }

    @SubscribeEvent
    public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;
        if (!(player.level() instanceof ServerLevel server)) return;

        Phase current = WorldPhaseData.get(server);
        PacketDistributor.sendToPlayer(player, new WorldPhasePayload(current));

        for (Phase phase : Phase.values()) {
            if (current.isAtLeast(phase)) {
                NoctaTriggers.PHASE_REACHED.get().trigger(player, phase);
            } else {
                break;
            }
        }
    }

    @SubscribeEvent
    public static void onItemSmelted(PlayerEvent.ItemSmeltedEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;
        ItemStack smelted = event.getSmelting();

        if (matches(smelted, "minecraft", "copper_ingot")) {
            reachPhase(player, Phase.BRONZE);
        }
    }

    @SubscribeEvent
    public static void onItemCrafted(PlayerEvent.ItemCraftedEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;
        ItemStack crafted = event.getCrafting();

        if (matches(crafted, "minecraft", "iron_pickaxe")) {
            reachPhase(player, Phase.IRON);
        }
    }

    @SubscribeEvent
    public static void onItemPickup(ItemEntityPickupEvent.Post event) {
        Player player = event.getPlayer();
        if (!(player instanceof ServerPlayer serverPlayer)) return;

        ItemStack stack = event.getOriginalStack();
        if (matches(stack, "nocta_ores", "raw_bronze")) {
            reachPhase(serverPlayer, Phase.BRONZE);
        }
    }
}