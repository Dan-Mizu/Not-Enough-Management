package dev.danmizu.not_enough_management.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.server.dedicated.management.OutgoingRpcMethods;

import dev.danmizu.not_enough_management.registry.EventRegistry;

@Mixin(OutgoingRpcMethods.class)
public class OutgoingRpcMethodsMixin {
    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void addCustomRpc(CallbackInfo ci) {
        // register all events
        EventRegistry.registerAll();
    }
}
