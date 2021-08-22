package fr.djredstone.nosto.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import fr.djredstone.nosto.Main;

public class OnItemDropListener implements Listener {
	
	public OnItemDropListener(Main main) {
		main.getServer().getPluginManager().registerEvents(this, main);
	}

	@EventHandler
    public void onItemDrop(PlayerDropItemEvent event) {
		Player player = event.getPlayer();
		if(Main.menuPlayers.contains(player)) {
			event.setCancelled(true);
		}
	}
	
}
