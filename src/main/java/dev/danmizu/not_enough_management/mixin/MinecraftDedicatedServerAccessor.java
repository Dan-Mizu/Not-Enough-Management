package dev.danmizu.not_enough_management.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.server.dedicated.MinecraftDedicatedServer;
import net.minecraft.server.dedicated.management.ManagementServer;

@Mixin(MinecraftDedicatedServer.class)
public interface MinecraftDedicatedServerAccessor {
    @Accessor("managementServer")
    ManagementServer nem$getManagementServer();
}
