package com.maximfiedler.joincommands.listeners;

import com.maximfiedler.joincommands.Config;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {
    private final Config config;
    public PlayerDeathListener(Config config) {
        this.config = config;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        config.performCommands(event.getEntity(), config.getCommandsDeath());
    }
}
