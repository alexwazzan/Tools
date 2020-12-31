package com.gmail.alexwazzan1.tools.commands;

import com.gmail.alexwazzan1.tools.Main;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Reset implements CommandExecutor {

    private Main plugin;

    public Reset(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("reset").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players may execute this command.");
            return true;
        }

        Player p = (Player) sender;

        // Check for an invalid command:
        if (args.length != 0) {
            p.sendMessage(ChatColor.RED + "Invalid command. Try again.");
            return true;
        }

        if (Main.players.containsKey(p.getName())) {
            Main.players.remove(p.getName());
        }

        return true;
    }

}
