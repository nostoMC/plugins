package fr.djredstone.nostoNC.tasks;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.scheduler.BukkitRunnable;

import fr.djredstone.nostoNC.Main;

public class floorSmokeEffect {
	
	static Boolean floorSmoke = Main.getfloorSmoke();

	public floorSmokeEffect(Main main) {
		
		new BukkitRunnable() {

			@Override
			public void run() {
				
				floorSmoke = Main.getfloorSmoke();
				if(floorSmoke == true) {
					Bukkit.getWorld("Nightclub").spawnParticle(Particle.SMOKE_LARGE, 0, 64, -8, 500, 10, 0, 10, 0.1);
				}
				
			}
			
		}.runTaskTimer(main, 0, 10);
	
	}

}
