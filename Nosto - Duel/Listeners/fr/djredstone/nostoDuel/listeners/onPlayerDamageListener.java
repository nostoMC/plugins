package fr.djredstone.nostoDuel.listeners;

import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import fr.djredstone.nostoDuel.Main;

public class onPlayerDamageListener implements Listener {
	
	static ArrayList<Player> duelLobby = Main.getDuelLobbyList();

	public onPlayerDamageListener(Main main) {
		main.getServer().getPluginManager().registerEvents(this, main);
	}

	@EventHandler
	public void onPlayerDamage(EntityDamageEvent event) {
	    if(duelLobby.contains(event.getEntity())) {
	        event.setCancelled(true);
	    }
	}
	
}
