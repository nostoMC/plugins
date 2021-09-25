package fr.nosto.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import fr.nosto.Main;

public class OnMoveItemInventoryListener implements Listener {

	@EventHandler
	public void moveItemInventory (InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		if(Main.menuPlayers.contains(player)) {
			e.setCancelled(true);
		}
	}
	
}
