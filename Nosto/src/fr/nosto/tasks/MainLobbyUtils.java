package fr.nosto.tasks;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.BoundingBox;

import fr.nosto.Main;

public class MainLobbyUtils {

	public MainLobbyUtils(Main main) {

		new BukkitRunnable() {
			
			@Override
			public void run() {

				for(Player player : Bukkit.getOnlinePlayers()){
					
					if(player.getWorld() == Bukkit.getWorld("MainLobby")) {
						
						if (BoundingBox.of(new Location(Bukkit.getWorld("MainLobby"), -8, 93, 10), new Location(Bukkit.getWorld("MainLobby"), -4, 95, 14)).contains(player.getLocation().toVector()) || 
								BoundingBox.of(new Location(Bukkit.getWorld("MainLobby"), 5, 94, 11), new Location(Bukkit.getWorld("MainLobby"), 8, 96, 14)).contains(player.getLocation().toVector())) {
								
							player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 2, 7, true));
							
						}
						
					}
					
				}
				
			}
		}.runTaskTimer(main, 0, 1);
		
	}

}
