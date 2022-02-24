package fr.nostoNC.menus;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import fr.nostoNC.Utils;

public class EffectsMenu2 implements Listener {

    private static final String title = "§2§lGestioraire des effets (page 2)";

    @SuppressWarnings("deprecation")
    public static void openMenu(Player player) {

        Inventory inv = Bukkit.createInventory(null, 54, title);

        // ----------------------------------------------------------------------------------------------------------------------

        Utils.createAndCheckActiveEffectItem(inv, Material.REDSTONE_TORCH, "§a§lLaser Up/Down", "laserUpDown", 10);

        inv.setItem(45, Utils.createItem(Material.ARROW, "Page 1"));

        Utils.fillEmptyItem(inv);

        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 100, 1);
        player.openInventory(inv);
    }

    @SuppressWarnings("deprecation")
    @EventHandler
    public void onClick(InventoryClickEvent event) {

        ItemStack current = event.getCurrentItem();
        if(current == null) return;

        Player player = (Player) event.getWhoClicked();

        if(event.getView().getTitle().equalsIgnoreCase("§2§lGestioraire des effets")) {
            event.setCancelled(true);

            if (event.getClick() == ClickType.DOUBLE_CLICK) return;

            switch (current.getType()) {
                case REDSTONE_TORCH -> {
                    Utils.checkActiveEffectItem(player, "laserUpDown");
                    openMenu(player);
                }

                case ARROW -> EffectsMenu.openMenu(player);

                default -> {}
            }
        }
    }
}
