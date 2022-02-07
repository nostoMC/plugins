package fr.nostoNC.listeners;

import fr.nostoNC.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

import fr.nostoNC.Main;

public class OnPlayerChangeWorldListener implements Listener {

	@EventHandler
	public void onPlayerChangeWorld(PlayerChangedWorldEvent event) {
		
		Player player = event.getPlayer();
		
		if(event.getFrom() == Utils.defaultWorld) {
			
			player.setResourcePack("https://www.dropbox.com/sh/y64429u85j3nb7p/AADbPee76vWP5XiXGB2MzuRGa?dl=1");
			
		}
		
	}

}
