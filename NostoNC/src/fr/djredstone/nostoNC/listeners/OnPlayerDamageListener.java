package fr.djredstone.nostoNC.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import fr.djredstone.nostoNC.Main;

public class OnPlayerDamageListener implements Listener {

	public OnPlayerDamageListener(Main main) {
		main.getServer().getPluginManager().registerEvents(this, main);
	}

	@EventHandler
	public void onPlayerDamage(EntityDamageEvent event) {
		if(event.getEntity() instanceof Player) {
			if(event.getEntity().getWorld() == Bukkit.getWorld("Nightclub")) {
		        event.setCancelled(true);
		    }
		}
	    
	}
	
}
