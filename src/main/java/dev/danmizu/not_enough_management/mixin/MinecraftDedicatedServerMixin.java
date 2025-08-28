package dev.danmizu.not_enough_management.mixin;

import net.minecraft.server.dedicated.MinecraftDedicatedServer;
import net.minecraft.server.dedicated.management.ManagementServer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(MinecraftDedicatedServer.class)
public abstract class MinecraftDedicatedServerMixin implements MinecraftDedicatedServerAccessor {
    @Shadow private ManagementServer managementServer;

    @Override
    public ManagementServer nem$getManagementServer() {
        return managementServer;
    }
}
