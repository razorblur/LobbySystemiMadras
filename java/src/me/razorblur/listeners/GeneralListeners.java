package me.razorblur.listeners;

import me.razorblur.iMadras.Main;
import me.razorblur.inventories.InventoryManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Nicolai on 05.06.2015.
 */
public class GeneralListeners implements Listener {

    public static ArrayList<UUID> allowed_to_build = new ArrayList<UUID>();


    /* Join Listener */

    /**
     * This method sets a join message
     * @param event The Player join event which has been caused
     */
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if(player.getFirstPlayed() == 0) { // Player was never before on this server
            player.sendMessage("");
            player.sendMessage("§7Wilkommen §a[" + player.getName() +"] §7auf " + Main.name);
            player.sendMessage("");
            player.sendMessage("§7Wir bieten dir sehr viele Spielmodis an ❤");
            player.sendMessage("§7Wilkommen in unserer Community ❤");
            player.sendMessage("");
            player.sendMessage("§7Eine kleine Einweisung? §c/tutorial");
            player.sendMessage("");
        } else {
            player.sendMessage("§7Willkommen zurück §a[" + player.getName() + "] §7auf iMadras!");
        }
        if(!player.hasPermission("admin.lobby")) {
            InventoryManager.setPlayerInventory(player);
        } else {
            InventoryManager.setAdminInventory(player);
        }
    }

    /**
     * This method sets the QuitMessage to null
     * @param event The PlayerQuitEvent which causes when somebody quits
     */
    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        event.setQuitMessage(null);
    }

    /**
     * This method sets the DeathMessage to null
     * @param event The PlayerQuitEvent which causes when somebody quits
     */
    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        event.setDeathMessage(null);
    }

    /**
     * Deny players to enter beds
     * @param event The given event by the server
     */
    @EventHandler
    public void onBedEnter(PlayerBedEnterEvent event) {
        event.setCancelled(true);
    }

    /**
     * This method blocks fall damage and denys pvp
     * @param event
     */
    @EventHandler
    public void onFallDamage(EntityDamageEvent event) {
        if(event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if(event.getCause().equals(EntityDamageEvent.DamageCause.FALL) || event.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_ATTACK)) {
                event.setCancelled(true);
            }
        }
    }

    /**
     * This Event denys players to break blocks who are not in the builder mode
     * @param event
     */
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player breaker = event.getPlayer();
        if(!allowed_to_build.contains(breaker.getUniqueId())) {
            breaker.sendMessage(Main.name + "§cDu darfst hier nicht bauen");
            event.setCancelled(true);
        }
    }

    /**
     * This Event denys players to place blocks who are not in the builder mode
     * @param event
     */
    @EventHandler
    public void onBlockBreak(BlockPlaceEvent event) {
        Player breaker = event.getPlayer();
        if(!allowed_to_build.contains(breaker.getUniqueId())) {
            breaker.sendMessage(Main.name + "§cDu darfst hier nicht bauen");
            event.setCancelled(true);
            event.setBuild(false);
        }
    }

    @EventHandler
    public void onPickUp(PlayerPickupItemEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        event.setCancelled(true);
    }

    public GeneralListeners() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.instance, new Runnable() {
            @Override
            public void run() {
                Bukkit.getServer().getWorld("world").setTime(0);
            }
        }, 0L, 20 * 120L);
    }

}
