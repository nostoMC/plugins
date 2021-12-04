package fr.nostoNC.tasks.effects;

import fr.nostoNC.Main;
import org.bukkit.Particle;
import org.bukkit.scheduler.BukkitRunnable;

public class FloorSmokeEffect {

	public FloorSmokeEffect(Main main) {
		
		new BukkitRunnable() {

			@Override
			public void run() {
				
				if(Main.activeEffects.get("floorSmoke")) {
					Main.defaultWorld.spawnParticle(Particle.CLOUD, -2.0, 101.0, 162.5, 500, 7, 0, 5, 0.05);
				}
				
			}
			
		}.runTaskTimer(main, 0, 5);
	
	}

}
