package dev.danmizu.not_enough_management.rpc;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import java.util.UUID;

public class RpcCodecs {
    public static final Codec<ChatMessagePayload> CHAT_MESSAGE_CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.STRING.fieldOf("id").forGetter(payload -> payload.playerUUID().toString()),
            Codec.STRING.fieldOf("name").forGetter(ChatMessagePayload::playerName),
            Codec.STRING.fieldOf("message").forGetter(ChatMessagePayload::message)
    ).apply(instance, (playerIdStr, playerName, message) ->
            new ChatMessagePayload(UUID.fromString(playerIdStr), playerName, message)
    ));
}
