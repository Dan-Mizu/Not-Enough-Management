package dev.danmizu.not_enough_management.registry;

import java.util.ArrayList;
import java.util.List;

import dev.danmizu.not_enough_management.event.ChatMessageEvent;
import dev.danmizu.not_enough_management.event.ManagementEvent;

public class EventRegistry {
    private static final List<ManagementEvent<?>> EVENTS = new ArrayList<>();

    @SuppressWarnings("unused")
    public static List<ManagementEvent<?>> getEvents() {
        return EVENTS;
    }

    public static void registerAll() {
        // all events are registered here
        //noinspection RedundantCollectionOperation
        EVENTS.addAll(List.of(new ChatMessageEvent()));
    }
}
