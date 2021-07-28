package fr.djredstone.nosto.utils.vanish;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.djredstone.nosto.Main;

public class VanishLoop {
	
	ArrayList<Player> vanishList = Main.getVanishList();

	public VanishLoop(Main main) {
		
		new BukkitRunnable() {
			
			@Override
			public void run() {
				
				for(Player players : Bukkit.getOnlinePlayers()) {
					vanishList = Main.getVanishList();
					if(vanishList.contains(players)) {
						players.setInvisible(true);
					} else {
						players.setInvisible(false);
					}
				}
				
			}
		}.runTaskTimer(main, 0, 1);
		
	}

}
