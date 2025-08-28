package dev.danmizu.not_enough_management.mixin;

import net.minecraft.server.dedicated.MinecraftDedicatedServer;
import net.minecraft.server.dedicated.management.ManagementServer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(MinecraftDedicatedServer.class)
public interface MinecraftDedicatedServerAccessor {
    @Accessor("managementServer")
    ManagementServer nem$getManagementServer();
}
