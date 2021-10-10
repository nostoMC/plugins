package fr.nosto.tasks;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import fr.nosto.Main;

public class MainLobbyUtils {
	
	Set<String> cooldown = new HashSet<String>();

	public MainLobbyUtils(Main main) {

		new BukkitRunnable() {
			
			@Override
			public void run() {

				for(Player player : Bukkit.getOnlinePlayers()){
					
					if(player.getWorld() == Bukkit.getWorld("MainLobby")) {
						
						if(new Location(Bukkit.getWorld("MainLobby"), -5.5, 94, 12.5).distance(player.getLocation()) < 2 || 
								new Location(Bukkit.getWorld("MainLobby"), 6.5, 94, 12.5).distance(player.getLocation()) < 2) {
							
							if(!cooldown.contains("" + player.getUniqueId())) {
								
								player.setVelocity(new Vector(player.getVelocity().getX(), player.getVelocity().getY() + 1.2, player.getVelocity().getZ() - 0.3));
								cooldown.add("" + player.getUniqueId());
								new BukkitRunnable() {
									@Override
									public void run() {
										cooldown.remove("" + player.getUniqueId());
									}
								}.runTaskLater(main, 20);
								
							}
							
						}
						
					}
					
				}
				
			}
		}.runTaskTimer(main, 0, 1);
		
	}

}