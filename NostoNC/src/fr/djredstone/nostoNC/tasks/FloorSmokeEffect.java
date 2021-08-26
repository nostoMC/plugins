package fr.djredstone.nostoNC.tasks;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.scheduler.BukkitRunnable;

import fr.djredstone.nostoNC.Main;

public class FloorSmokeEffect {

	public FloorSmokeEffect(Main main) {
		
		new BukkitRunnable() {

			@Override
			public void run() {
				
				if(Main.floorSmoke == true) {
					Bukkit.getWorld("Nightclub").spawnParticle(Particle.CLOUD, 0, 64, -8, 500, 10, 0, 10, 0.1);
				}
				
			}
			
		}.runTaskTimer(main, 0, 10);
	
	}

}
