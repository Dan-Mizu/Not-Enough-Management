package dev.danmizu.not_enough_management.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.server.MinecraftServer;

import dev.danmizu.not_enough_management.core.ManagementServerManager;

@Mixin(MinecraftServer.class)
public abstract class MinecraftServerMixin {
    @Inject(method = "loadWorld", at = @At("TAIL"))
    private void afterWorldLoad(CallbackInfo ci) {
        // get management server reference once server is fully started
        ManagementServerManager.init((MinecraftServer)(Object)this);
    }
}
