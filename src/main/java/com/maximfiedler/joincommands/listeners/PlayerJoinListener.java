package com.maximfiedler.joincommands.listeners;

import com.maximfiedler.joincommands.Config;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
    private final Config config;
    public PlayerJoinListener(Config config) {
        this.config = config;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        var player = event.getPlayer();
        config.performCommands(player, config.getCommandsJoin());
    }
}
