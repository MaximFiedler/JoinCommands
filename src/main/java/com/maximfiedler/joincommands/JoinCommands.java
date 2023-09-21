package com.maximfiedler.joincommands;

import com.maximfiedler.joincommands.commands.ReloadCommand;
import com.maximfiedler.joincommands.listeners.*;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class JoinCommands extends JavaPlugin {

    @Override
    public void onEnable() {
        var config = new Config();
        registerListeners(config);
        getCommand("joincommands-reload").setExecutor(new ReloadCommand(config));
    }

    private void registerListeners(Config config) {
        register(new PlayerDeathListener(config));
        register(new PlayerFirstJoinListener(config));
        register(new PlayerJoinListener(config));
        register(new PlayerQuitListener(config));
        register(new PlayerSwitchWorldListener(config));
    }

    private void register(Listener listener) {
        getServer().getPluginManager().registerEvents(listener, this);
    }

}
