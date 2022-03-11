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

import fr.nostoNC.Main;
import fr.nostoNC.Utils;
import fr.nostoNC.tasks.effects.LaserUpDown;

public class EffectsMenu2 implements Listener {

    private static final String title = "§2§lGestioraire des effets (page 2)";

    @SuppressWarnings("deprecation")
    public static void openMenu(Player player) {

        Inventory inv = Bukkit.createInventory(null, 54, title);

        // ----------------------------------------------------------------------------------------------------------------------

        inv.setItem(10, Utils.createItem(Material.REDSTONE_TORCH, "§a§lLaser Up/Down", LaserUpDown.isStarted() ? Utils.getOnLore().get(0) : Utils.getOffLore().get(0)));

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

        if(event.getView().getTitle().equalsIgnoreCase(title)) {
            event.setCancelled(true);

            if (event.getClick() == ClickType.DOUBLE_CLICK) return;

            switch (current.getType()) {
                case REDSTONE_TORCH -> {
                    if (LaserUpDown.isStarted()) LaserUpDown.stopLaser();
                    else LaserUpDown.startLaser(Main.getInstance());
                    openMenu(player);
                }

                case ARROW -> EffectsMenu.openMenu(player);

                default -> {}
            }
        }
    }
}
