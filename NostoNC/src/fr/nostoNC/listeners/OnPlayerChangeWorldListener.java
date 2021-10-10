package fr.nostoNC.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

public class OnPlayerChangeWorldListener implements Listener {

	@EventHandler
	public void onPlayerChangeWorld(PlayerChangedWorldEvent event) {
		
		Player player = event.getPlayer();
		
		System.out.println(player.getName() + " change de monde !");
		
		if(player.getWorld() == Bukkit.getWorld("Nightclub")) {
			
			System.out.println(player.getName() + " est dans la boîte de nuit !");
			
			player.setResourcePack("https://www.dropbox.com/sh/hwhqynt5xvhvyj1/AAAki-6eHwpMLNlsMS6p4ySfa?dl=1");
			
		}
		
		if(event.getFrom() == Bukkit.getWorld("Nightclub")) {
			
			System.out.println(player.getName() + " sort de la boîte de nuit !");
			
			player.setResourcePack("");
			
		}
		
	}

}
