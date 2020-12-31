package com.gmail.alexwazzan1.tools;

import com.gmail.alexwazzan1.tools.commands.Copy;
import com.gmail.alexwazzan1.tools.commands.Set;
import com.gmail.alexwazzan1.tools.commands.Reset;
import com.gmail.alexwazzan1.tools.commands.Undo;
import com.gmail.alexwazzan1.tools.listeners.BreakBlock;
import com.gmail.alexwazzan1.tools.listeners.PlaceBlock;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class Main extends JavaPlugin {

    public static HashMap<String, Size> players;
    public static HashMap<String, Location[]> copied;
    public static HashMap<Location, Material> recentlyChanged;

    @Override
    public void onEnable() {
        getLogger().info("onEnable is called!");

        players = new HashMap<>();
        copied = new HashMap<>();
        recentlyChanged = new HashMap<>();

        new PlaceBlock(this);
        new Set(this);
        new Reset(this);
        new Undo(this);
        new Copy(this);
        new BreakBlock(this);
    }

}
