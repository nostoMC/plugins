package fr.djredstone.nostoNC.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
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
	
	@EventHandler
	public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
		if(event.getPlayer().getWorld() == Bukkit.getWorld("Nightclub")) {
			if(!event.getPlayer().hasPermission("nosto.nightclub.interact")) {
				event.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
		if(event.getDamager().getWorld() == Bukkit.getWorld("Nightclub")) {
			if(!event.getDamager().hasPermission("nosto.nightclub.interact")) {
				event.setCancelled(true);
			}
		}
	}

}
