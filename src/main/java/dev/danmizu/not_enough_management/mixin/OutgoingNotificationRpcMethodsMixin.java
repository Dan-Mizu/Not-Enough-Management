package dev.danmizu.not_enough_management.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.server.dedicated.management.OutgoingNotificationRpcMethods;

import dev.danmizu.not_enough_management.registry.EventRegistry;

@Mixin(OutgoingNotificationRpcMethods.class)
public class OutgoingNotificationRpcMethodsMixin {
    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void addCustomRpc(CallbackInfo ci) {
        // register all events
        EventRegistry.registerAll();
    }
}
