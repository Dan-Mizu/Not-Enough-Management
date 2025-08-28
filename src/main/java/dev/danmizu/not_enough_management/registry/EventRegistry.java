package dev.danmizu.not_enough_management.registry;

import dev.danmizu.not_enough_management.event.ChatMessageEvent;
import dev.danmizu.not_enough_management.event.ManagementEvent;

import java.util.ArrayList;
import java.util.List;

public class EventRegistry {
    private static final List<ManagementEvent<?>> EVENTS = new ArrayList<>();

    public static void registerAll() {
        // all events are registered here
        register(new ChatMessageEvent());
    }

    /*
     *  helpers
     */
    private static void register(ManagementEvent<?> event) {
        EVENTS.add(event);
        event.register();
    }

    @SuppressWarnings("unused")
    public static List<ManagementEvent<?>> getEvents() {
        return EVENTS;
    }
}
