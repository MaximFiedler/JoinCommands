package com.maximfiedler.joincommands;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class Config {
    private File fileJoin = new File("plugins/AdvancedJoinCommands", "PlayerJoin-Commands.yml");
    private File filefirstJoin = new File("plugins/AdvancedJoinCommands", "PlayerFirstJoin-Commands.yml");
    private File fileQuit = new File("plugins/AdvancedJoinCommands", "PlayerQuit-Commands.yml");
    private File fileDeath = new File("plugins/AdvancedJoinCommands", "PlayerDeath-Commands.yml");
    private File fileSwitch = new File("plugins/AdvancedJoinCommands", "PlayerSwitchWorld-Commands.yml");

    private YamlConfiguration cfgJoin = new YamlConfiguration().loadConfiguration(fileJoin);
    private YamlConfiguration cfgFirstJoin = new YamlConfiguration().loadConfiguration(fileJoin);
    private YamlConfiguration cfgQuit = new YamlConfiguration().loadConfiguration(fileQuit);
    private YamlConfiguration cfgDeath = new YamlConfiguration().loadConfiguration(fileDeath);
    private YamlConfiguration cfgSwitch = new YamlConfiguration().loadConfiguration(fileSwitch);

    private Commands commandsJoin;
    private Commands commandsFirstJoin;
    private Commands commandsQuit;
    private Commands commandsDeath;
    private Commands commandsSwitch;


    public Config() {
        setDefaultCommands(cfgJoin);
        setDefaultCommands(cfgFirstJoin);
        setDefaultCommands(cfgQuit);
        setDefaultCommands(cfgDeath);
        setDefaultCommands(cfgSwitch);
        saveConfig();
        initValues();
    }


    private void initValues() {
        commandsJoin = getCommands(cfgJoin);
        commandsFirstJoin = getCommands(cfgFirstJoin);
        commandsQuit = getCommands(cfgQuit);
        commandsDeath = getCommands(cfgDeath);
        commandsSwitch = getCommands(cfgSwitch);
    }

    public void reload() {
        this.cfgJoin = new YamlConfiguration().loadConfiguration(fileJoin);
        this.cfgFirstJoin = new YamlConfiguration().loadConfiguration(fileJoin);
        this.cfgQuit = new YamlConfiguration().loadConfiguration(fileQuit);
        this.cfgDeath = new YamlConfiguration().loadConfiguration(fileDeath);
        this.cfgSwitch = new YamlConfiguration().loadConfiguration(fileSwitch);
        initValues();
    }

    public void saveConfig() {
        try {
            cfgJoin.save(fileJoin);
            cfgJoin.save(filefirstJoin);
            cfgJoin.save(fileQuit);
            cfgJoin.save(fileDeath);
            cfgJoin.save(fileSwitch);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void performCommands(Player player, Commands commands) {
        for(String command : commands.commandsPlayer) {
            player.performCommand(command.replace("%player%", player.getName()));
        }
        for(String command : commands.commandsConsole) {
            if(command.contains("with your commands") || command.contains("replace these lines")) continue;
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replace("%player%", player.getName()));
        }
    }

    private void setDefaultCommands(YamlConfiguration yamlConfiguration) {
        var commands = new Commands();
        final var list = new ArrayList<>(Arrays.asList("replace these lines", "with your commands"));
        if(!yamlConfiguration.isSet("Commands.Player")) yamlConfiguration.set("Commands.Player", list);
        if(!yamlConfiguration.isSet("Commands.Console")) yamlConfiguration.set("Commands.Console", list);
    }

    private Commands getCommands(YamlConfiguration yamlConfiguration) {
        var commands = new Commands();
        commands.commandsPlayer = yamlConfiguration.getStringList("Commands.Player");
        commands.commandsConsole = yamlConfiguration.getStringList("Commands.Console");
        return commands;
    }

    public class Commands {
        public List<String> commandsPlayer = new ArrayList<>();
        public List<String> commandsConsole = new ArrayList<>();
    }

    public void setValue(String path, Object value, YamlConfiguration yamlConfiguration) {
        yamlConfiguration.set(path, value);
    }

    public Object getValue(String path, YamlConfiguration yamlConfiguration) {
        return yamlConfiguration.get(path);
    }

}
