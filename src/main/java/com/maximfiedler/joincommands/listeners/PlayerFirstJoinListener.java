package com.maximfiedler.joincommands.listeners;

import com.maximfiedler.joincommands.Config;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerFirstJoinListener implements Listener {
    private final Config config;
    public PlayerFirstJoinListener(Config config) {
        this.config = config;
    }

    @EventHandler
    public void onFirstPlayerJoin(PlayerJoinEvent event) {
        var player = event.getPlayer();
        if(player.hasPlayedBefore()) return;
        config.performCommands(player, config.getCommandsFirstJoin());
    }
}
