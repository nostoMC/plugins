package fr.djredstone.nostoS;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import fr.djredstone.nostoS.tasks.clearLag;

public class Main extends JavaPlugin {
	
	public static Set<String> survivalWorld = new HashSet<String>();
	
	@Override
	public void onEnable() {
		
		survivalWorld.add("survie");
		survivalWorld.add("survie_the_end");
		survivalWorld.add("survie_nether");
		
		new clearLag(this);
		
	}
	
	@Override
	public void onDisable() {
		
	}
	
	public static void sendMessageInSurvivalWorld(String message) {
		
		if(!Bukkit.getOnlinePlayers().isEmpty()) {
			
			for(Player player : Bukkit.getOnlinePlayers()) {
				
				if(survivalWorld.contains(player.getWorld().getName())) {
					
					player.sendMessage("");
					player.sendMessage(message);
					
				}
				
			}
			
		}
		
	}

}
