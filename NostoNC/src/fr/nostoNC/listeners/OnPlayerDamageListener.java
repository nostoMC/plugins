package fr.nostoNC.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class OnPlayerDamageListener implements Listener {

	@EventHandler
	public void onPlayerDamage(EntityDamageEvent event) {
		if(event.getEntity() instanceof Player) {
			if(event.getEntity().getWorld() == Bukkit.getWorld("Nightclub")) {
				if(!event.getEntity().hasPermission("nosto.nightclub.interact")) {
			        event.setCancelled(true);
			    }
			}
			
		}
	    
	}
	
}
