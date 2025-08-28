package dev.danmizu.not_enough_management.event;

import com.mojang.serialization.Codec;
import net.minecraft.server.dedicated.management.OutgoingRpcMethod;

public interface ManagementEvent<T> {
    /**
     * @return the unique event id (e.g. "chat_message")
     */
    String getId();

    /**
     * @return the codec for this event's payload
     */
    @SuppressWarnings("unused")
    Codec<T> getCodec();

    /**
     * @return the outgoing RPC method for this event
     */
    @SuppressWarnings("unused")
    OutgoingRpcMethod.NotificationRpcMethod<T> getRpcMethod();

    /**
     * Hook to actually register with Fabric events.
     */
    void register();
}
