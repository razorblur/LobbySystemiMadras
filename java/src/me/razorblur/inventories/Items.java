package me.razorblur.inventories;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Nicolai on 06.06.2015.
 */
public class Items {

    public static final ItemStack hide_players = InventoryManager.createItem("§7Spieler Verstecken", Material.STICK);
    public static final ItemStack teleporter = InventoryManager.createItem("§7Teleporter", Material.COMPASS);
    public static final ItemStack shop = InventoryManager.createItem("§6Shop", Material.GOLD_INGOT);
    public static final ItemStack show_players = InventoryManager.createItem("§4Spieler anzeigen", Material.BLAZE_ROD);


}
