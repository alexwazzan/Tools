package com.gmail.alexwazzan1.tools.commands;

import com.gmail.alexwazzan1.tools.Main;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Copy implements CommandExecutor {

    private Main plugin;

    public Copy(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("copy").setExecutor(this);
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

        // Toggle ON:
        if (!(Main.copied.containsKey(p.getName()))) {
            p.sendMessage("Copy: " + ChatColor.GREEN + "[ON] " + ChatColor.RED + "[OFF]");
            Location l[] = new Location[2];
            Main.copied.put(p.getName(), l);
        // Toggle OFF:
        } else {
            p.sendMessage("Copy: " + ChatColor.RED + "[ON] " + ChatColor.GREEN + "[OFF]");
            Main.copied.remove(p.getName());
        }

        return true;
    }
}
