package fr.djredstone.nosto.listeners;

import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import fr.djredstone.nosto.Main;

public class onItemDropListener implements Listener {

	static ArrayList<Player> menuPlayers = Main.getMenuPlayersList();
	
	public onItemDropListener(Main main) {
		main.getServer().getPluginManager().registerEvents(this, main);
	}

	@EventHandler
    public void onItemDrop(PlayerDropItemEvent event) {
		Player player = event.getPlayer();
		if(menuPlayers.contains(player)) {
			event.setCancelled(true);
		}
	}
	
}
