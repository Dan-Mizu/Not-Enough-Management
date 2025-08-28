package dev.danmizu.not_enough_management;

import dev.danmizu.not_enough_management.core.ManagementServerManager;
import dev.danmizu.not_enough_management.registry.EventRegistry;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NotEnoughManagement implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("NotEnoughManagement");

    @Override
    public void onInitialize() {
        // register events
        EventRegistry.registerAll();

        // store reference to management server when server starts
        ServerLifecycleEvents.SERVER_STARTED.register(ManagementServerManager::init);
    }
}
