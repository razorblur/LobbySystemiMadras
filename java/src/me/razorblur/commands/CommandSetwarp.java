package me.razorblur.commands;

import me.razorblur.iMadras.Main;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

/**
 * Created by Nicolai on 05.06.2015.
 */
public class CommandSetwarp implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;

            if(!player.hasPermission("lobby.admin")) {
                player.sendMessage(Main.name + "§7Du hast keine Permission lobby.admin");
                return true;
            }

            if(args.length == 1) {
                String warp_name = args[0].toLowerCase();
                if(args[0].equals("lobby")) {
                    Location loc = player.getLocation();
                    player.getWorld().setSpawnLocation(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
                    player.sendMessage(Main.name +"§7Du hast den Lobby Warp gesetzt");
                } else {
                    if(setWarp(warp_name, player.getLocation())) {
                        player.sendMessage(Main.name + "§7Du hast den Warp §c" +warp_name + " §7gesetzt");
                    } else {
                        player.sendMessage(Main.name + "§cError: Warp konnte nicht erstellt werden");
                    }
                }
            } else {
                player.sendMessage(Main.name + "§7/setwarp [lobby|warpname]");
            }
        }

        return true;
    }

    private boolean setWarp(String warp_name, Location loc) {
        File cfg_file = new File(Main.dir+"//warps//"+warp_name+".yml");

        // Check if file exists
        if(!cfg_file.exists()) {
            try {
                cfg_file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error: Datei konnte nicht erstellt werden");
            }
        }

        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(cfg_file);
        String path = "warp." + warp_name.toLowerCase() + ".";
        cfg.set(path+"X", loc.getX());
        cfg.set(path+"Y", loc.getY());
        cfg.set(path+"Z", loc.getZ());
        cfg.set(path+"Yaw", loc.getYaw());
        cfg.set(path+"Pitch", loc.getPitch());
        cfg.set(path+"World", loc.getWorld().getName());
        try {
            cfg.save(cfg_file);
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}
