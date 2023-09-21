package com.maximfiedler.joincommands.listeners;

import com.maximfiedler.joincommands.Config;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {
    private final Config config;
    public PlayerQuitListener(Config config) {
        this.config = config;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        var player = event.getPlayer();
        config.performCommands(player, config.getCommandsQuit());
    }
}
