package me.razorblur.iMadras;

import me.razorblur.commands.CommandDelwarp;
import me.razorblur.commands.CommandSetwarp;
import me.razorblur.commands.CommandWarp;
import me.razorblur.inventories.InventoryListener;
import me.razorblur.listeners.GeneralListeners;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

/**
 * Created by Nicolai on 05.06.2015.
 */
public class Main extends JavaPlugin {

    public static Main instance;
    public static String name = "[iMadras]";

    public static String dir;
    public static String config;
    public static String warps_dir;

    @Override
    public void onEnable() {
        // Variables
        instance = this;
        dir = "plugins//" + this.getDescription().getName();
        config = dir + "//config.yml";
        warps_dir = dir +"//warps";

        checkFolders();

        PluginManager pluginManager = this.getServer().getPluginManager();

        // Register Listeners
        pluginManager.registerEvents(new GeneralListeners(), this);
        pluginManager.registerEvents(new InventoryListener(), this);

        // Register Commands
        this.getCommand("setwarp").setExecutor(new CommandSetwarp());
        this.getCommand("warp").setExecutor(new CommandWarp());
        this.getCommand("delwarp").setExecutor(new CommandDelwarp());
    }

    @Override
    public void onDisable() {

    }

    private void checkFolders() {
        File f_dir = new File(dir);
        File warps = new File(warps_dir);
        File f_config = new File(config);
        if(!f_dir.exists()) f_dir.mkdir();
        if(!warps.exists()) warps.mkdir();
        if(!f_config.exists()) {
            try {
                f_config.createNewFile();
            } catch (IOException e) {
                System.out.println("Fehler: Datei config.yml konnte nicht erstellt werden");
            }
        }
    }

}
