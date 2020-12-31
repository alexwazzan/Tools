package com.gmail.alexwazzan1.tools.commands;

import com.gmail.alexwazzan1.tools.Main;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Map;

public class Undo implements CommandExecutor {

    private Main plugin;

    public Undo(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("undo").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players may execute this command.");
            return true;
        }

        if (!Main.recentlyChanged.isEmpty()) {
            for (Map.Entry entry : Main.recentlyChanged.entrySet()) {
                Location l = (Location) entry.getKey();
                Material m = (Material) entry.getValue();
                // Update the block:
                l.getBlock().setType(m);
            }
            // Clear the existing list:
            Main.recentlyChanged.clear();
        } else {
            sender.sendMessage(ChatColor.RED + "Nothing to undo.");
        }

        return true;
    }
}
