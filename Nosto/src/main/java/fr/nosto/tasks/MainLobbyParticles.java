package fr.nosto.tasks;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.scheduler.BukkitRunnable;

import fr.nosto.Main;

public class MainLobbyParticles {

	private static boolean inited = false;

	public static void init(Main main) {

		if (inited) {
			Bukkit.getLogger().warning("MainLobbyParticles.init() ran twice!");
			return;
		}
		inited = true;

		new BukkitRunnable() {
			
			final Location loc = new Location(Bukkit.getWorld("MainLobby"), 0.5, 104.5, 0.5);
			double loop = 0;
			
			@Override
			public void run() {

				if(loop > Math.PI*4) loop = 0;
				loop += .05;
				
				double yOffset = Math.cos(loop);
				double xOffset = Math.sin(loop * 4);
				double zOffset = Math.cos(loop * 4);
				
				Bukkit.getWorld("MainLobby").spawnParticle(Particle.REDSTONE, loc.getX() + xOffset, loc.getY() + yOffset, loc.getZ() + zOffset, 1, 0, 0, 0, 0, 
						new Particle.DustOptions(Color.fromRGB(127, 0, 230), 1.2f), true);
				
			}
		}.runTaskTimer(main, 0, 1);
		
	}

}
