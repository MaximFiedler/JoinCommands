package com.maximfiedler.joincommands.commands;

import com.maximfiedler.joincommands.Config;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadCommand implements CommandExecutor {
    private Config config;
    public ReloadCommand(Config config) {
        this.config = config;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!sender.hasPermission("joincommands.reload")) {
            sender.sendMessage(ChatColor.RED + "No permission!");
             return false;
        }
        config.reload();
        sender.sendMessage(ChatColor.GREEN + "Config reloaded!");
        return false;
    }
}
