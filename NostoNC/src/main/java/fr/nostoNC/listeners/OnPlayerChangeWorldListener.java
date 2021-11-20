package fr.nostoNC.listeners;

import fr.nostoNC.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

public class OnPlayerChangeWorldListener implements Listener {

	@EventHandler
	public void onPlayerChangeWorld(PlayerChangedWorldEvent event) {
		
		Player player = event.getPlayer();
		
		if(player.getWorld() == Main.defaultWorld) {
			
			player.setResourcePack("https://www.dropbox.com/sh/hwhqynt5xvhvyj1/AAAki-6eHwpMLNlsMS6p4ySfa?dl=1");
			
		}
		
		if(event.getFrom() == Main.defaultWorld) {
			
			player.setResourcePack("https://www.dropbox.com/sh/y64429u85j3nb7p/AADbPee76vWP5XiXGB2MzuRGa?dl=1");
			
		}
		
	}

}
