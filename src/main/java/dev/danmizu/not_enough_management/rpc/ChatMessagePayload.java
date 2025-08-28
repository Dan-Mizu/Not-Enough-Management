package dev.danmizu.not_enough_management.rpc;

import java.util.UUID;

public record ChatMessagePayload(UUID playerUUID, String playerName, String message) {}
