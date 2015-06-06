package me.razorblur.commands;

import me.razorblur.iMadras.Main;
import me.razorblur.listeners.GeneralListeners;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Nicolai on 06.06.2015.
 */
public class CommandBuild implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(!player.hasPermission("lobby.admin")) {
                player.sendMessage(Main.name + "§4Keine Permission: lobby.admin");
            } else {
                if(GeneralListeners.allowed_to_build.contains(player.getUniqueId())) {
                    GeneralListeners.allowed_to_build.remove(player.getUniqueId());
                    player.sendMessage(Main.name + "Du hast bauen für dich §4deaktiviert");
                } else {
                    GeneralListeners.allowed_to_build.add(player.getUniqueId());
                    player.sendMessage(Main.name + "Du hast bauen für dich §4Aktiviert");
                }
            }
        }

        return true;
    }
}
