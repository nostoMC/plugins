package fr.nosto.tasks;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.nosto.Main;

public class VanishLoop {

	public VanishLoop(Main main) {
		
		new BukkitRunnable() {
			
			@Override
			public void run() {
				
				for(Player players : Bukkit.getOnlinePlayers()) {
					players.setInvisible(Main.vanishList.contains(players));
				}
				
			}
		}.runTaskTimer(main, 0, 1);
		
	}

}
