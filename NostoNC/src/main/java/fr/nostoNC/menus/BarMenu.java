package fr.nostoNC.menus;

import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import fr.nostoNC.Main;
import fr.nostoNC.Utils;
import fr.nostoNC.customConsumables.Products;
import net.kyori.adventure.text.Component;

public class BarMenu implements Listener {

    public static final Component TITLE = Component.text("Produits disponibles");

    public static void openMenu(Player player) {

        Inventory inv = Bukkit.createInventory(null, 9 * 6, TITLE);

        for (int i = 0; i < 9; i++) {
            inv.setItem(i, Utils.clearSlot);
        }

        for (int i = 9*5; i < 9*6; i++) {
            inv.setItem(i, Utils.clearSlot);
        }

        inv.setItem(45, Utils.createItem(Material.BUCKET, "§7Poubelle",
                "§8Mettez ici tous les items",
                "§8dont vous voulez vous débarasser."));
        inv.setItem(53, Utils.createItem(Material.BARRIER, "§cFermer"));

        inv.setItem(9, Products.products.get("calvados").getItem());
        inv.setItem(10, Products.products.get("gin").getItem());
        inv.setItem(11, Products.products.get("jet27").getItem());
        inv.setItem(12, Products.products.get("rhum").getItem());
        inv.setItem(13, Products.products.get("vodka").getItem());

        inv.setItem(18, Products.products.get("menthe").getItem());
        inv.setItem(19, Products.products.get("double").getItem());
        inv.setItem(20, Products.products.get("bicarbonate").getItem());

        inv.setItem(27, Products.products.get("cookie").getItem());
        inv.setItem(28, Products.products.get("honey").getItem());

        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES, 100, 1);
        player.openInventory(inv);
    }

    @SuppressWarnings("deprecation")
    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (!event.getView().title().equals(TITLE)) return;

        event.setCancelled(true);

        if (event.getClick() == ClickType.DOUBLE_CLICK) return;

        Inventory clickedInventory = event.getClickedInventory();
        if (clickedInventory == null) return;

        Player player = (Player) event.getWhoClicked();
        InventoryAction action = event.getAction();
        int slot = event.getSlot();

        if (action == InventoryAction.MOVE_TO_OTHER_INVENTORY || action == InventoryAction.COLLECT_TO_CURSOR) return;

        if (clickedInventory.getType() == InventoryType.PLAYER) {
            event.setCancelled(false);
        }
        if (clickedInventory.getType() == InventoryType.CHEST) { // the custom ui type is CHEST

            // player can take products from the inventory
            if (slot >= 9 && slot < 9 * 5 && action == InventoryAction.PICKUP_ALL) {
                event.setCancelled(false);

                ItemStack current = event.getCurrentItem();
                if (current == null) return;
                ItemStack replaceItem = current.clone();

                new BukkitRunnable() {
                    @Override
                    public void run() {
                        // putting back the item 1 sec after
                        clickedInventory.setItem(slot, replaceItem);
                    }
                }.runTaskLater(Main.getInstance(), 20);
            }
            // close button
            if (slot == 53) {
                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 100, 1);
                player.closeInventory();
            }
            // trash
            if (slot == 45 && action == InventoryAction.SWAP_WITH_CURSOR) {
                event.setCancelled(true);
                event.setCursor(new ItemStack(Material.AIR));
                player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_TRADE, 100, 1);
            }

        }
    }

    @EventHandler
    public void onDrag(InventoryDragEvent e) {
        if (!e.getView().title().equals(TITLE)) return;

        Set<Integer> slots = e.getRawSlots();

        for (Integer slot : slots) {
            if (slot < 54) e.setCancelled(true);
        }
    }
}
