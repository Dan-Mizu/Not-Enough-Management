package dev.danmizu.not_enough_management.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;

import dev.danmizu.not_enough_management.event.ChatMessageEvent;

@Mixin(ServerPlayNetworkHandler.class)
public abstract class ChatMessageMixin {
    @Shadow public ServerPlayerEntity player;

    @Inject(method = "onChatMessage", at = @At("TAIL"))
    private void afterChatMessage(ChatMessageC2SPacket packet, CallbackInfo ci) {
        // send chat message event to management server
        ChatMessageEvent.handleMessage(packet.chatMessage(), player);
    }
}
