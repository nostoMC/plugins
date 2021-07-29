package fr.djredstone.nostoDuel.listeners;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import fr.djredstone.nostoDuel.Main;

public class OnMoveItemInventoryListener implements Listener {
	
static ArrayList<Player> duelLobby = Main.getDuelLobbyList();
	
	public OnMoveItemInventoryListener(Main main) {
		main.getServer().getPluginManager().registerEvents(this, main);
	}

	@EventHandler
	public void moveItemInventory (InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		if(player.getWorld() == Bukkit.getWorld("duel")) {
			e.setCancelled(true);
		}
	}

}
