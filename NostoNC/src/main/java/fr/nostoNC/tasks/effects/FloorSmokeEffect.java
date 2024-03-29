package fr.nostoNC.tasks.effects;

import fr.nostoNC.Utils;
import org.bukkit.Particle;
import org.bukkit.scheduler.BukkitRunnable;

import fr.nostoNC.Main;

public class FloorSmokeEffect {

	private static boolean inited = false;

	public static void init(Main main) {

		if (inited) return;
		inited = true;
		
		new BukkitRunnable() {

			@Override
			public void run() {
				
				if(Utils.getActiveEffects("floorSmoke")) {
					Utils.getDefaultWorld().spawnParticle(Particle.CLOUD, -2.0, 101.0, 162.5, 500, 7, 0, 5, 0.05);
				}
				
			}
			
		}.runTaskTimer(main, 0, 5);
	
	}

}
