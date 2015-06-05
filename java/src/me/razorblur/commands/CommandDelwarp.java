package me.razorblur.commands;

import me.razorblur.iMadras.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;

/**
 * Created by Nicolai on 06.06.2015.
 */
public class CommandDelwarp implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(!player.hasPermission("lobby.admin")) {
                player.sendMessage(Main.name + "§7Keine Permission lobby.admin");
            } else {
                if(args.length == 1) {
                    String warp_name = args[0].toLowerCase();
                    delWarp(warp_name, player);
                } else {
                    player.sendMessage(Main.name + "/delwarp (name)");
                }
            }
        }

        return true;
    }

    private void delWarp(String warp_name, Player player) {
        File warp_file = new File(Main.dir+"//warps//"+warp_name+".yml");

        // Check if file exists
        if(!warp_file.exists()) {
            player.sendMessage(Main.name + "§7Warp §c" + warp_name + " §7existiert nicht");
        } else {
            warp_file.delete();
            player.sendMessage(Main.name + "§7Warp §6" + warp_name + " §7erfolgreich gelöscht");
        }

    }
}
