package fr.djredstone.nostoNC.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

import fr.djredstone.nostoNC.Main;

public class OnPlayerChangeWorldListener implements Listener {
	
	public OnPlayerChangeWorldListener(Main main) {
		Bukkit.getPluginManager().registerEvents(this, main);
	}

	public void onPlayerChangeWorld(PlayerChangedWorldEvent event) {
		
		Player player = event.getPlayer();
		
		if(player.getWorld() == Bukkit.getWorld("Nightclub")) {
			
			player.setResourcePack("https://www.dropbox.com/sh/s22qs07ad57visv/AADTQzJw8ze296BzD1HJxsh7a?dl=1");
			
		}
		
		if(event.getFrom() == Bukkit.getWorld("Nightclub")) {
			
			player.setResourcePack("");
			
		}
		
	}

}
