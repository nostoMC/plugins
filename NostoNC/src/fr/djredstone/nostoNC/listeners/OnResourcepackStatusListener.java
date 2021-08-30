package fr.djredstone.nostoNC.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;
import org.bukkit.event.player.PlayerResourcePackStatusEvent.Status;

public class OnResourcepackStatusListener implements Listener {

	@EventHandler
	public void onResourcepackStatusEvent(PlayerResourcePackStatusEvent event) {
		
		if(event.getStatus() == Status.DECLINED) {
			
			event.getPlayer().kickPlayer("Vous n'avez pas accépter le resource pack !");
			
		} else if(event.getStatus() == Status.FAILED_DOWNLOAD) {
			
			event.getPlayer().kickPlayer("Téléchargement interompu du resource pack !");
			
		}
		
	}

}
