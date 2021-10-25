package fr.nosto.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class OnPlayerDamageListener implements Listener {
	
	@EventHandler
	public void onPlayerDamage(EntityDamageEvent event) {
		if(event.getEntity() instanceof Player) {
			if(event.getEntity().getWorld().getName().endsWith("Lobby")) {
				if(!event.getEntity().hasPermission("nosto.lobby.interact")) {
			        event.setCancelled(true);
			    }
			}
			
		}
	    
	}

}
