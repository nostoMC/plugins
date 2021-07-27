package fr.djredstone.nosto.listeners;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.djredstone.nosto.Main;

public class onLeaveListener implements Listener {

	public onLeaveListener(Main main) {
		main.getServer().getPluginManager().registerEvents(this, main);
	}
	
	String[] messages = {"1", "2", "3", "4", "5", "6"};
	
	@EventHandler
	public void onLeave(PlayerQuitEvent event) {
		Bukkit.broadcastMessage("");
		if(messages[new Random().nextInt(messages.length)] == "1") {
			event.setQuitMessage("§6§l" + event.getPlayer().getName() + "§e est parti...");
		} else if(messages[new Random().nextInt(messages.length)] == "2") {
			event.setQuitMessage("§6§l" + event.getPlayer().getName() + "§e fait une pose.");
		} else if(messages[new Random().nextInt(messages.length)] == "3") {
			event.setQuitMessage("§6§l" + event.getPlayer().getName() + "§e est reparti !");
		} else if(messages[new Random().nextInt(messages.length)] == "4") {
			event.setQuitMessage("§eUne personne est parti, elle s'agit de §6§l" + event.getPlayer().getName() + "§e !");
		} else if(messages[new Random().nextInt(messages.length)] == "5") {
			event.setQuitMessage("§eBye bye §6§l" + event.getPlayer().getName() + "§e !");
		} else if(messages[new Random().nextInt(messages.length)] == "6") {
			event.setQuitMessage("§6§l" + event.getPlayer().getName() + "§e retourne au monde réel !");
		}
	}

}
