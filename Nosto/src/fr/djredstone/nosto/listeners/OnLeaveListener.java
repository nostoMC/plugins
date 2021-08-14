package fr.djredstone.nosto.listeners;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.djredstone.nosto.Main;

public class OnLeaveListener implements Listener {

	public OnLeaveListener(Main main) {
		main.getServer().getPluginManager().registerEvents(this, main);
	}
	
	String[] messages = {"1", "2", "3", "4", "5", "6"};
	
	@EventHandler
	public void onLeave(PlayerQuitEvent event) {
		event.setQuitMessage("");
		
		for(Player players : Bukkit.getOnlinePlayers()) {
			
			if(event.getPlayer().getWorld() == Bukkit.getWorld("survie") || event.getPlayer().getWorld() == Bukkit.getWorld("survie_nether") || event.getPlayer().getWorld() == Bukkit.getWorld("survie_the_end")) {
			
				if(players.getWorld() == Bukkit.getWorld("survie") || players.getWorld() == Bukkit.getWorld("survie_nether") || players.getWorld() == Bukkit.getWorld("survie_the_end")) {
				
					players.sendMessage("");
					if(messages[new Random().nextInt(messages.length)] == "1") {
						players.sendMessage("§6§l" + event.getPlayer().getName() + "§e est parti...");
					} else if(messages[new Random().nextInt(messages.length)] == "2") {
						players.sendMessage("§6§l" + event.getPlayer().getName() + "§e fait une pose.");
					} else if(messages[new Random().nextInt(messages.length)] == "3") {
						players.sendMessage("§6§l" + event.getPlayer().getName() + "§e est reparti !");
					} else if(messages[new Random().nextInt(messages.length)] == "4") {
						players.sendMessage("§eUne personne est parti, elle s'agit de §6§l" + event.getPlayer().getName() + "§e !");
					} else if(messages[new Random().nextInt(messages.length)] == "5") {
						players.sendMessage("§eBye bye §6§l" + event.getPlayer().getName() + "§e !");
					} else if(messages[new Random().nextInt(messages.length)] == "6") {
						players.sendMessage("§6§l" + event.getPlayer().getName() + "§e retourne au monde réél !");
					}
				
				}
				
			}
			
		}
		
	}

}
