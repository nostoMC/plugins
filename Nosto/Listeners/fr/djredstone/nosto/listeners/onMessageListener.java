package fr.djredstone.nosto.listeners;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import fr.djredstone.nosto.Main;

public class onMessageListener implements Listener {
	
	ArrayList<Player> menuPlayers = Main.getMenuPlayersList();

	public onMessageListener(Main main) {
		main.getServer().getPluginManager().registerEvents(this, main);
	}
	
	@EventHandler
	public void onMessage(AsyncPlayerChatEvent event) {
		
		Player player = event.getPlayer();
		
		menuPlayers = Main.getMenuPlayersList();
		if(menuPlayers.contains(player)) {
			event.setCancelled(true);
			return;
		}
		
		String group = "";
		
		if(player.hasPermission("group.dev")) {
			group = "§bDevelopper ";
		}
		if(player.hasPermission("group.buildeur")) {
			group = "§aBuildeur ";
		}
		if(player.hasPermission("group.administrateur")) {
			group = "§cAdministrateur ";
		}
		
		String format = "§f<" + group + "§f" + player.getName() + "> " + event.getMessage();
		Bukkit.broadcastMessage("");
		event.setFormat(format);
		event.setMessage(event.getMessage().replaceAll("&", "§"));
	}

}
