package dev.danmizu.not_enough_management.event;

import com.mojang.serialization.Codec;

import net.minecraft.server.dedicated.management.OutgoingRpcMethod;

public interface ManagementEvent<T> {
    /**
     * @return the unique event id (e.g. "chat_message")
     */
    @SuppressWarnings("unused")
    String getId();

    /**
     * @return the codec for this event's payload
     */
    @SuppressWarnings("unused")
    Codec<T> getCodec();

    /**
     * @return the outgoing json-rpc method for this event
     */
    @SuppressWarnings("unused")
    OutgoingRpcMethod.Notification<T> getRpcMethod();

}
