package fr.djredstone.nosto.listeners;

import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import fr.djredstone.nosto.Main;

public class OnMoveItemInventoryListener implements Listener {
	
	static ArrayList<Player> menuPlayers = Main.getMenuPlayersList();
	
	public OnMoveItemInventoryListener(Main main) {
		main.getServer().getPluginManager().registerEvents(this, main);
	}

	@EventHandler
	public void moveItemInventory (InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		if(menuPlayers.contains(player)) {
			e.setCancelled(true);
		}
	}
	
}
