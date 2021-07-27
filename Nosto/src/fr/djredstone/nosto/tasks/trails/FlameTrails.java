package fr.djredstone.nosto.tasks.trails;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.djredstone.nosto.Main;

public class FlameTrails {
	
	static HashMap<Player, Boolean> trailsFlame = Main.getPlayerFlameTrails();

	public FlameTrails(Main main) {
		
		new BukkitRunnable() {
			
			@Override
			public void run() {
				
				for(Player player : Bukkit.getOnlinePlayers()) {
					trailsFlame = Main.getPlayerFlameTrails();
					if(trailsFlame.get(player) != null) {
						if(trailsFlame.get(player) == true) {
							Location loc = player.getLocation();
							player.getWorld().spawnParticle(Particle.FLAME, loc.getX(), loc.getY(), loc.getZ(), 10, 1, 1, 1, 0.0);
						}
					} else {
						trailsFlame.put(player, false);
					}
				}
				
			}
		}.runTaskTimer(main, 0, 5);
		
	}

}
