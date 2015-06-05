package me.razorblur.commands;

import me.razorblur.iMadras.Main;
import org.apache.commons.io.FilenameUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

/**
 * Created by Nicolai on 05.06.2015.
 */
public class CommandWarp implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;

            if(args.length == 1) {
                String warp_name = args[0].toLowerCase();
                warp(warp_name, player);
            } else {
                String warps = getWarps();
                player.sendMessage(Main.name + "§7Verfügbare Warps: " + warps);
            }
        } else {
            System.out.println("Error: Du musst ein Spieler sein!");
        }

        return true;
    }


    private void warp(String warp_name, Player player) {
        File cfg_file = new File(Main.dir+"//warps//"+warp_name+".yml");
        if(!cfg_file.exists()) {
            player.sendMessage(Main.name + "Warp §c" + warp_name + " §7existiert nicht");
            return;
        }

        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(cfg_file);
        String path = "warp." + warp_name.toLowerCase() + ".";
        Location loc = player.getLocation();

        loc.setX(cfg.getDouble(path + "X"));
        loc.setY(cfg.getDouble(path + "Y"));
        loc.setZ(cfg.getDouble(path + "Z"));
        loc.setYaw((float) cfg.getDouble(path + "Yaw"));
        loc.setPitch((float) cfg.getDouble(path + "Pitch"));
        loc.setWorld(Bukkit.getWorld(cfg.getString(path+"World")));

        player.teleport(loc);
        player.sendMessage(Main.name + "§7Du hast dich zum Warp §c" + warp_name + " §7teleportiert");
    }

    private String getWarps() {
        String warps = "";
        File cfg_file = new File(Main.dir+"//warps");
        for(File file : cfg_file.listFiles()) {
            String warp_name = FilenameUtils.removeExtension(file.getName());

            warps += "§6"+warp_name +"§7,§6 ";
        }
        return warps;
    }
}
