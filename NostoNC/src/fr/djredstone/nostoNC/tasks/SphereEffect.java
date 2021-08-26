package fr.djredstone.nostoNC.tasks;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.scheduler.BukkitRunnable;

import fr.djredstone.nostoNC.Main;

public class SphereEffect {

	public SphereEffect(Main main) {
		
		final Location loc = new Location(Bukkit.getWorld("Nightclub"), 0.5, 75, -12.5);

		new BukkitRunnable() {
			
			double phi = 0;

			@Override
			public void run() {
				phi += Math.PI/30;
				for(double theta = 0; theta <= 2*Math.PI; theta += Math.PI/40) {
					double r = 5;
					double x = r * Math.cos(theta) * Math.sin(phi);
					double y = r * Math.cos(phi) + 1.5;
					double z = r * Math.sin(theta) * Math.sin(phi);
					loc.add(x, y, z);
					if(Main.sphere) {
						Bukkit.getWorld("Nightclub").spawnParticle(Particle.DRAGON_BREATH, loc, 0, 0, 0, 0);
					}
					loc.subtract(x, y, z);
				}
				
				if(phi > 8*Math.PI) {
					phi = 0;
				}
				
			}
			
		}.runTaskTimer(main, 0, 2);
		
	}

}
