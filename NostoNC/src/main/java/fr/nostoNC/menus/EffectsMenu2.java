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
import fr.nostoNC.tasks.EffectsManager;
import fr.nostoNC.tasks.effects.TopLaser;

public class EffectsMenu2 implements Listener {

    private static final String title = "§2§lGestioraire des effets (page 2)";

    @SuppressWarnings("deprecation")
    public static void openMenu(Player player) {

        Inventory inv = Bukkit.createInventory(null, 54, title);

        // ----------------------------------------------------------------------------------------------------------------------

        inv.setItem(10, Utils.createItem(Material.END_CRYSTAL, "§f§eLights Top"));
        inv.setItem(11, Utils.createItem(Material.REDSTONE_TORCH, "§f§eDown"));
        inv.setItem(12, Utils.createItem(Material.STICK, "§f§eRandom"));
        inv.setItem(19, Utils.createItem(Material.RED_CONCRETE, "§f§eSTOP"));
        inv.setItem(20, Utils.createItem(Material.WHITE_CONCRETE, "§f§lFull"));
        inv.setItem(21, Utils.createItem(Material.GRAY_CONCRETE, "§7§lSemi"));
        inv.setItem(22, Utils.createItem(Material.COMPASS, "§7§lTiming",
                "§7La vitesse est actuellement à §6§l" + TopLaser.timing,
                "§8Click droit: §a+1",
                "§8Click gauche: §c-1"));
        inv.setItem(23, Utils.createItem(Material.BLACK_CONCRETE, "§0§lBlack"));

        inv.setItem(28, Utils.createItem(Material.END_CRYSTAL, "§f§eLights Wall"));
        inv.setItem(29, Utils.createItem(Material.STONE_BUTTON, "§f§eFront"));
        inv.setItem(30, Utils.createItem(Material.REPEATER, "§f§eWave"));
        inv.setItem(31, Utils.createItem(Material.COMPARATOR, "§f§eEdge"));
        inv.setItem(37, Utils.createItem(Material.RED_CONCRETE_POWDER, "§f§eSTOP"));
        inv.setItem(38, Utils.createItem(Material.WHITE_CONCRETE_POWDER, "§f§lFull"));
        inv.setItem(39, Utils.createItem(Material.GRAY_CONCRETE_POWDER, "§7§lSemi"));
        inv.setItem(40, Utils.createItem(Material.CLOCK, "§7§lTiming",
                "§7La vitesse est actuellement à §6§l" + TopLaser.timing,
                "§8Click droit: §a+1",
                "§8Click gauche: §c-1"));
        inv.setItem(41, Utils.createItem(Material.BLACK_CONCRETE_POWDER, "§0§lBlack"));

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

                case REDSTONE_TORCH -> EffectsManager.topLaserDown();
                case STICK -> EffectsManager.topLaserRandom();
                case RED_CONCRETE -> EffectsManager.topLaserStop();
                case WHITE_CONCRETE -> EffectsManager.topLaserFull();
                case GRAY_CONCRETE -> EffectsManager.topLaserSemi();
                case BLACK_CONCRETE -> EffectsManager.topLaserBlack();

                case STONE_BUTTON -> EffectsManager.wallLaserFront();
                case REPEATER -> EffectsManager.wallLaserWave();
                case COMPARATOR -> EffectsManager.wallLaserEdge();
                case RED_CONCRETE_POWDER -> EffectsManager.wallLaserStop();

                case ARROW -> {
                    EffectsMenu.openMenu(player);
                    return;
                }
            }
            openMenu(player);
        }
    }
}
