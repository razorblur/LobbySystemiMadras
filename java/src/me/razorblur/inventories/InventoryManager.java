package me.razorblur.inventories;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * Created by Nicolai on 06.06.2015.
 */
public class InventoryManager {

    public static void setPlayerInventory(Player player) {
        Inventory inv = player.getInventory();
        inv.clear();
        inv.setItem(0, createItem("§7Spieler Verstecken", Material.STICK)); // SPIELER VERSTECKEN
        inv.setItem(4, createItem("§7Teleporter", Material.COMPASS)); // TELEPORTER
        inv.setItem(8, createItem("§6Shop", Material.GOLD_INGOT)); // SHOP
    }

    public static void setAdminInventory(Player player) {
        Inventory inv = player.getInventory();
        inv.clear();
        inv.setItem(2, createItem("§2Silent Lobby", Material.BOOK)); // SILENT LOBBY
        inv.setItem(6, createItem("§6Namen Ändern", Material.NAME_TAG)); // NAMEN ÄNDERN
    }

    private static ItemStack createItem(String name, Material material) {
        ItemStack item = new ItemStack(material, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        item.setItemMeta(meta);
        return item;
    }

}
