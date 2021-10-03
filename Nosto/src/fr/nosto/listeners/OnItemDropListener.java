package fr.nosto.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class OnItemDropListener implements Listener {

	@EventHandler
    public void onItemDrop(PlayerDropItemEvent event) {
		Player player = event.getPlayer();
		if(player.getWorld() == Bukkit.getWorld("MainLobby") && !player.isOp()) {
			event.setCancelled(true);
		}
	}
	
}
