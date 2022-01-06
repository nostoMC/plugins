package fr.nostoNC.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

import fr.nostoNC.Utils;

public class HelmetRemoveListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (!Utils.isInClub(player)) return;

        if (!(event.getInventory().getType() == InventoryType.CRAFTING)) return;
        if (event.getSlotType() != InventoryType.SlotType.ARMOR) return;
        if (event.getSlot() != 39) return;

        if (player.hasPermission("nosto.nightclub.interact")) return;

        event.setCancelled(true);
    }
}
