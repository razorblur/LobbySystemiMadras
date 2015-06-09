package me.razorblur.inventories;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
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
        inv.setItem(0, Items.hide_players); // SPIELER VERSTECKEN
        inv.setItem(4, Items.teleporter); // TELEPORTER
        inv.setItem(8, Items.shop); // SHOPf
    }

    public static void setAdminInventory(Player player) {
        Inventory inv = player.getInventory();
        inv.clear();
        inv.setItem(2, createItem("§2Silent Lobby", Material.BOOK)); // SILENT LOBBY
        inv.setItem(6, createItem("§6Namen Ändern", Material.NAME_TAG)); // NAMEN ÄNDERN
    }

    public static ItemStack createItem(String name, Material material) {
        ItemStack item = new ItemStack(material, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        item.setItemMeta(meta);
        return item;
    }

    public static Inventory getTeleporterInventory() {
        Inventory inv = Bukkit.createInventory(null, 9*2, "§4Teleporter");
        return inv;
    }

    public static Inventory getShopInventory() {
        Inventory inv = Bukkit.createInventory(null, 9*2, "§4Shop");
        return inv;
    }

    public static Inventory renameInventory() {
        Inventory inv = Bukkit.createInventory(null, InventoryType.ANVIL, "Rename");
        return inv;
    }

}
