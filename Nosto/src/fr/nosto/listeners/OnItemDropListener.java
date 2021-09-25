package fr.nosto.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import fr.nosto.Main;

public class OnItemDropListener implements Listener {

	@EventHandler
    public void onItemDrop(PlayerDropItemEvent event) {
		Player player = event.getPlayer();
		if(Main.menuPlayers.contains(player)) {
			event.setCancelled(true);
		}
	}
	
}
