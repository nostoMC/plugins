package fr.djredstone.nostoNC.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class OnPlayerInteractListener implements Listener {
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		
		if(event.getPlayer().getWorld() == Bukkit.getWorld("Nightclub")) {
			if(!event.getPlayer().hasPermission("nosto.nightclub.interact")) {
				event.setCancelled(true);
			}
		}
		
	}

}
