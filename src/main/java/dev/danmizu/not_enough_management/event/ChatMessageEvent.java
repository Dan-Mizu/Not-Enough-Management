package dev.danmizu.not_enough_management.event;

import java.util.UUID;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.server.dedicated.management.OutgoingRpcMethod;
import net.minecraft.server.network.ServerPlayerEntity;

import dev.danmizu.not_enough_management.core.ManagementServerManager;

public class ChatMessageEvent implements ManagementEvent<ChatMessageEvent.Payload> {
    public record Payload(UUID playerUUID, String playerName, String message) {}

    @Override
    public String getId() {
        return "chat_message";
    }

    // json-rpc codec
    private static final Codec<Payload> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.STRING.fieldOf("id").forGetter(p -> p.playerUUID().toString()),
            Codec.STRING.fieldOf("name").forGetter(Payload::playerName),
            Codec.STRING.fieldOf("message").forGetter(Payload::message)
    ).apply(instance, (id, name, msg) -> new Payload(UUID.fromString(id), name, msg)));

    @Override
    public Codec<Payload> getCodec() {
        return CODEC;
    }

    // json-rpc method registered once
    private static final OutgoingRpcMethod.NotificationRpcMethod<Payload> RPC_METHOD =
            OutgoingRpcMethod.createNotificationMethod(CODEC)
                    .description("Chat message from player")
                    .register("notification", "chat_message"); // <-- only once

    @Override
    public OutgoingRpcMethod.NotificationRpcMethod<Payload> getRpcMethod() {
        return RPC_METHOD;
    }

    // called when a chat message is sent
    public static void handleMessage(String msg, ServerPlayerEntity sender) {
        // send chat message notification to clients connected to management server
        ManagementServerManager.broadcastNotificationToAll(
                RPC_METHOD,
                new Payload(sender.getUuid(), sender.getName().getString(), msg)
        );
    }
}
