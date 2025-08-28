package dev.danmizu.not_enough_management;

import dev.danmizu.not_enough_management.mixin.ManagementServerAccessor;
import dev.danmizu.not_enough_management.mixin.MinecraftDedicatedServerAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.minecraft.server.dedicated.MinecraftDedicatedServer;
import net.minecraft.server.dedicated.management.ManagementServer;
import net.minecraft.server.dedicated.management.OutgoingRpcMethod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.message.v1.ServerMessageEvents;

import dev.danmizu.not_enough_management.rpc.ChatMessagePayload;
import dev.danmizu.not_enough_management.rpc.RpcCodecs;

public class NotEnoughManagement implements ModInitializer {
    private static final Logger LOGGER = LoggerFactory.getLogger("NotEnoughManagement");

    public static OutgoingRpcMethod.NotificationRpcMethod<ChatMessagePayload> CHAT_MESSAGE;

    @Override
    public void onInitialize() {
        CHAT_MESSAGE = OutgoingRpcMethod.createNotificationMethod(RpcCodecs.CHAT_MESSAGE_CODEC)
                .description("Chat message from player")
                .register("notification", "chat_message");

        ServerMessageEvents.CHAT_MESSAGE.register((msg, sender, params) -> {
            if (sender.getServer() == null || !sender.getServer().isDedicated()) return;

            MinecraftDedicatedServer server = (MinecraftDedicatedServer) sender.getServer();
            ManagementServer managementServer = ((MinecraftDedicatedServerAccessor) server).nem$getManagementServer();

            if (managementServer == null) return;

            ((ManagementServerAccessor) managementServer).nem$forEachConnection(connection -> connection.sendNotification(CHAT_MESSAGE, new ChatMessagePayload(sender.getUuid(), sender.getName().getString(), msg.getContent().getString())));
        });
    }
}
