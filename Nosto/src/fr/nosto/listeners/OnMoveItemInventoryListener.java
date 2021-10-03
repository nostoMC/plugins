package fr.nosto.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class OnMoveItemInventoryListener implements Listener {

	@EventHandler
	public void moveItemInventory (InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		if(player.getWorld() == Bukkit.getWorld("MainLobby") && !player.isOp()) {
			e.setCancelled(true);
		}
	}
	
}
