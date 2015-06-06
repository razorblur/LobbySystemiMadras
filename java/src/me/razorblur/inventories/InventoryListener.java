package me.razorblur.inventories;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;

/**
 * Created by Nicolai on 06.06.2015.
 */
public class InventoryListener implements Listener {


    @EventHandler
    public void onClick(InventoryClickEvent event) {
        ItemStack clicked = event.getCurrentItem();


        event.setCancelled(true);
    }


    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            ItemStack inHand= player.getItemInHand();
            if(inHand != null) {
                if(inHand.getType().equals(Material.STICK)) { // HIDE PLAYERS
                    Collection<? extends Player> players = Bukkit.getOnlinePlayers();
                    for(Player p : players) player.hidePlayer(p);
                    player.getInventory().setItem(0, Items.show_players);
                } else if(inHand.getType().equals(Material.COMPASS)) { // OPEN COMPASS MENU
                    player.openInventory(InventoryManager.getTeleporterInventory());
                } else if(inHand.getType().equals(Material.GOLD_INGOT)) { // OPEN SHOP
                    player.openInventory(InventoryManager.getShopInventory());
                } else if(inHand.getType().equals(Material.BLAZE_ROD)) { // SHOW PLAYERS
                    Collection<? extends Player> players = Bukkit.getOnlinePlayers();
                    for(Player p : players) player.showPlayer(p);
                    player.getInventory().setItem(0, Items.hide_players);
                } else if(inHand.getType().equals(Material.NAME_TAG)) {
                    player.openInventory(InventoryManager.renameInventory());
                }
            }
        }
    }
}
