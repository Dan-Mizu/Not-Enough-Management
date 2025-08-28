package dev.danmizu.not_enough_management.event;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import dev.danmizu.not_enough_management.core.ManagementServerManager;

import net.fabricmc.fabric.api.message.v1.ServerMessageEvents;
import net.minecraft.server.dedicated.management.OutgoingRpcMethod;

import java.util.UUID;

public class ChatMessageEvent implements ManagementEvent<ChatMessageEvent.Payload> {
    public record Payload(UUID playerUUID, String playerName, String message) {}

    // json-rpc notification codec
    private final Codec<Payload> codec = RecordCodecBuilder.create(instance -> instance.group(
            Codec.STRING.fieldOf("id").forGetter(p -> p.playerUUID().toString()),
            Codec.STRING.fieldOf("name").forGetter(Payload::playerName),
            Codec.STRING.fieldOf("message").forGetter(Payload::message)
    ).apply(instance, (id, name, msg) -> new Payload(UUID.fromString(id), name, msg)));

    // json-rpc method
    private final OutgoingRpcMethod.NotificationRpcMethod<Payload> rpcMethod =
            OutgoingRpcMethod.createNotificationMethod(codec)
                    .description("Chat message from player")
                    .register("notification", getId());

    @Override
    public String getId() {
        return "chat_message";
    }

    @Override
    public Codec<Payload> getCodec() {
        return codec;
    }

    @Override
    public OutgoingRpcMethod.NotificationRpcMethod<Payload> getRpcMethod() {
        return rpcMethod;
    }

    @Override
    public void register() {
        // chat message event
        ServerMessageEvents.CHAT_MESSAGE.register((msg, sender, params) -> {
            // send chat message notification to connected clients on management server
            ManagementServerManager.broadcastNotificationToAll(
                    rpcMethod,
                    new Payload(
                            sender.getUuid(),
                            sender.getName().getString(),
                            msg.getContent().getString()
                    )
            );
        });
    }
}
