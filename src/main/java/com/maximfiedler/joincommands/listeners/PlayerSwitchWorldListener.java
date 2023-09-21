package com.maximfiedler.joincommands.listeners;

import com.maximfiedler.joincommands.Config;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerSwitchWorldListener implements Listener {
    private final Config config;
    public PlayerSwitchWorldListener(Config config) {
        this.config = config;
    }

    @EventHandler
    public void onSwitchWorld(PlayerChangedWorldEvent event) {
        var player = event.getPlayer();
        config.performCommands(player, config.getCommandsSwitch());
    }
}
