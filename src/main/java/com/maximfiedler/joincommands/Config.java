package com.maximfiedler.joincommands;

import lombok.Getter;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class Config {
    @Getter private File fileJoin = new File("plugins/AdvancedJoinCommands", "PlayerJoin-Commands.yml");
    @Getter private File filefirstJoin = new File("plugins/AdvancedJoinCommands", "PlayerFirstJoin-Commands.yml");
    @Getter private File fileQuit = new File("plugins/AdvancedJoinCommands", "PlayerQuit-Commands.yml");
    @Getter private File fileDeath = new File("plugins/AdvancedJoinCommands", "PlayerDeath-Commands.yml");
    @Getter private File fileSwitch = new File("plugins/AdvancedJoinCommands", "PlayerSwitchWorld-Commands.yml");

    @Getter private YamlConfiguration cfgJoin = new YamlConfiguration().loadConfiguration(fileJoin);
    @Getter private YamlConfiguration cfgFirstJoin = new YamlConfiguration().loadConfiguration(fileJoin);
    @Getter private YamlConfiguration cfgQuit = new YamlConfiguration().loadConfiguration(fileQuit);
    @Getter private YamlConfiguration cfgDeath = new YamlConfiguration().loadConfiguration(fileDeath);
    @Getter private YamlConfiguration cfgSwitch = new YamlConfiguration().loadConfiguration(fileSwitch);


    public Commands commandsJoin;
    public Commands commandsFirstJoin;
    public Commands commandsQuit;
    public Commands commandsDeath;
    public Commands commandsSwitch;


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
        this.fileJoin = new File("plugins/AdvancedJoinCommands", "PlayerJoin-Commands.yml");
        this.filefirstJoin = new File("plugins/AdvancedJoinCommands", "PlayerFirstJoin-Commands.yml");
        this.fileQuit = new File("plugins/AdvancedJoinCommands", "PlayerQuit-Commands.yml");
        this.fileDeath = new File("plugins/AdvancedJoinCommands", "PlayerDeath-Commands.yml");
        this.fileSwitch = new File("plugins/AdvancedJoinCommands", "PlayerSwitchWorld-Commands.yml");
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
