package com.gmail.alexwazzan1.tools.commands;

import com.gmail.alexwazzan1.tools.Main;
import com.gmail.alexwazzan1.tools.Size;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Set implements CommandExecutor {

    private Main plugin;

    public Set(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("set").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players may execute this command.");
            return true;
        }

        Player p = (Player) sender;

        if (args.length <= 2) {
            int missingParameters = 3 - args.length;
            p.sendMessage(ChatColor.RED + "Missing " + missingParameters + " parameter(s). Try again.");
            return true;
        }

        if (args.length >= 4) {
            int extraParameters = args.length - 3;
            p.sendMessage(ChatColor.RED + "You entered " + extraParameters + " extra parameter(s). Try again.");
            return true;
        }

        try {
            int x = Integer.parseInt(args[0]);
            int y = Integer.parseInt(args[1]);
            int z = Integer.parseInt(args[2]);

            // Add/Update the player's Size in the HashMap:
            Size s = new Size(x, y, z);
            Main.players.put(p.getName(), s);

            // Notify the player:
            p.sendMessage("X: " + x + "\nY: " + y + "\nZ: " + z);
        } catch (NumberFormatException e) {
            p.sendMessage(ChatColor.RED + "All 3 parameters must be integers.");
        }

        return true;
    }

}
