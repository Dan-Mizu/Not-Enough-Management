package dev.danmizu.not_enough_management.mixin;

import java.util.function.Consumer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.server.dedicated.management.ManagementServer;
import net.minecraft.server.dedicated.management.ManagementConnectionHandler;

@Mixin(ManagementServer.class)
public interface ManagementServerAccessor {
    @Invoker("forEachConnection")
    void nem$forEachConnection(Consumer<ManagementConnectionHandler> consumer);
}
