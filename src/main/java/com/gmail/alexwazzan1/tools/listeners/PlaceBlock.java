package com.gmail.alexwazzan1.tools.listeners;

import com.gmail.alexwazzan1.tools.Main;
import com.gmail.alexwazzan1.tools.Size;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.entity.Player;

import static java.lang.Math.abs;

public class PlaceBlock implements Listener {

    private Main plugin;

    public PlaceBlock(Main plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlaceBlock(BlockPlaceEvent e) {
        Player p = e.getPlayer();

        if (Main.players.containsKey(p.getName())) {
            Block b = e.getBlockPlaced();
            Material m = b.getType();
            Location l = b.getLocation();

            Size s = Main.players.get(p.getName());

            float yaw = p.getLocation().getYaw();
            yaw = (yaw %= 360) >= 0 ? yaw : (yaw + 360);

            int x = s.getX();
            int y = s.getY();
            int z = s.getZ();

            int xSign;
            int ySign = (y < 0) ? -1 : 1;
            int zSign;
            if (yaw <= 45) {
                xSign = -1;
                zSign = 1;
            } else if (yaw <= 90) {
                // Swap X and Z:
                x += z - (z = x);
                xSign = -1;
                zSign = 1;
            } else if (yaw <= 135) {
                // Swap X and Z:
                x += z - (z = x);
                xSign = -1;
                zSign = -1;
            } else if (yaw <= 180) {
                xSign = -1;
                zSign = -1;
            } else if (yaw <= 225) {
                xSign = 1;
                zSign = -1;
            } else if (yaw <= 270) {
                // Swap X and Z:
                x += z - (z = x);
                xSign = 1;
                zSign = -1;
            } else if (yaw <= 315) {
                // Swap X and Z:
                x += z - (z = x);
                xSign = 1;
                zSign = 1;
            } else {
                xSign = 1;
                zSign = 1;
            }

            xSign *= (x < 0) ? -1 : 1;
            zSign *= (z < 0) ? -1 : 1;

            // Clear the existing HashMap:
            Main.recentlyChanged.clear();
            // Add the first block to the HashMap:
            Main.recentlyChanged.put(l.clone(), e.getBlockReplacedState().getType());

            for (int i = 0; i < abs(x); i++) {
                for (int j = 0; j < abs(y); j++) {
                    for (int k = 0; k < abs(z); k++) {
                        // Store the block's location:
                        Location current = l.clone().add(xSign * i, ySign * j, zSign * k);
                        if (i != 0 || j != 0 || k != 0) {
                            // Store the block's previous material:
                            Main.recentlyChanged.put(current, current.getBlock().getType());
                        }
                        // Update the block:
                        current.getBlock().setType(m);
                    }
                }
            }
        }

        return;
    }

}