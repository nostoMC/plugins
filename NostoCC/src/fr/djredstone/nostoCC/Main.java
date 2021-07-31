package fr.djredstone.nostoCC;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
	
	@Override
	public void onEnable() {
		
	}
	
	@Override
	public void onDisable() {
		
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onChat(AsyncPlayerChatEvent event) {

		Player player = event.getPlayer();
		String world = "";
		String grade = "";
		String msg = event.getMessage();
		
		// LES MONDES
		
		if(player.getWorld() == Bukkit.getWorld("survie")) {
			
			world = "Survival";
			
		} else if(player.getWorld() == Bukkit.getWorld("skyworld")){
			
			world = "Skyblock";
			
		}
		
		// LE GRADE
		
		if(player.hasPermission("group.administrateur")) {
			
			grade = "Administrateur";
			
		}
		
		// ASSEMBLEMENT
		
		event.setMessage("[" + world + "] " + "<" + grade + " " + player.getName() + "> " + msg);
		
	}
	
}