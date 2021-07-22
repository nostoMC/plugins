package fr.djredstone.nostoDuel.listeners;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import fr.djredstone.nostoDuel.Main;

public class onItemDropListener implements Listener {
	
	static Boolean duelStart = Main.getDuelStart();
	static ArrayList<Player> duel = Main.getDuelList();
	static ArrayList<Player> duelLobby = Main.getDuelLobbyList();
	
	public onItemDropListener(Main main) {
		main.getServer().getPluginManager().registerEvents(this, main);
	}

	@EventHandler
    public void onItemDrop(PlayerDropItemEvent event) {
		Player player = event.getPlayer();
		if(player.getWorld() == Bukkit.getWorld("duel")) {
			event.setCancelled(true);
		}
	}
	
}
