package fr.nostoNC.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;
import org.bukkit.event.player.PlayerResourcePackStatusEvent.Status;

public class OnResourcepackStatusListener implements Listener {

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onResourcepackStatusEvent(PlayerResourcePackStatusEvent event) {
		
		if(event.getStatus() == Status.DECLINED) {
			
			event.getPlayer().kickPlayer("§cVous devez accepter le pack pour accéder au club !");
			
		}
		
	}

}
