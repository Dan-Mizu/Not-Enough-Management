package dev.danmizu.not_enough_management.core;

import dev.danmizu.not_enough_management.mixin.ManagementServerAccessor;
import dev.danmizu.not_enough_management.mixin.MinecraftDedicatedServerAccessor;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.dedicated.MinecraftDedicatedServer;
import net.minecraft.server.dedicated.management.ManagementServer;
import net.minecraft.server.dedicated.management.OutgoingRpcMethod;

public class ManagementServerManager {
    private static ManagementServer managementServer;

    public static void init(MinecraftServer server) {
        // get management server from dedicated server
        if (server instanceof MinecraftDedicatedServer dedicatedServer) {
            managementServer = ((MinecraftDedicatedServerAccessor) dedicatedServer).nem$getManagementServer();
        }
    }

    @SuppressWarnings("unused")
    public static ManagementServer get() {
        return managementServer;
    }

    public static boolean isAvailable() {
        return managementServer == null;
    }

    /**
     * send json-rpc notification to connected clients
     *
     * @param method rpc notification method to use
     * @param payload payload to send
     * @param <T> payload type
     */
    public static <T> void broadcastNotificationToAll(
            OutgoingRpcMethod.NotificationRpcMethod<T> method,
            T payload
    ) {
        // haven't gotten management server reference yet (shouldn't happen)
        if (isAvailable()) return;

        // send notification to connected clients on management server
        ((ManagementServerAccessor) managementServer).nem$forEachConnection(connection ->
                connection.sendNotification(method, payload));
    }
}
