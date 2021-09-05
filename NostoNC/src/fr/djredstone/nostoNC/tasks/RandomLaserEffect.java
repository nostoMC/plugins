package fr.djredstone.nostoNC.tasks;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;

import fr.djredstone.nostoNC.Laser;
import fr.djredstone.nostoNC.Main;

public class RandomLaserEffect {
	
	private int duration = -1;
	private int distance = -1;
	
	private Random random = new Random();

	public RandomLaserEffect(Main main) {
		
		int timing = 40;
		
		for(int i = 0; i < 51; i++) {
			
			try {
				Laser light = new Laser.GuardianLaser(new Location(Bukkit.getWorld("Nightclub"), 0.5, 76.5, -12.5), new Location(Bukkit.getWorld("Nightclub"), -20 + (20 - -20) * random.nextDouble(), 62, -30 + (8 - -30) * random.nextDouble()), duration, distance);
				
				new BukkitRunnable() {
					
					@Override
					public void run() {

						if(light.isStarted()) light.moveEnd(new Location(Bukkit.getWorld("Nightclub"), -21 + (21 - -21) * random.nextDouble(), 62 + (87 - 62) * random.nextDouble(), -31 + (18 - -31) * random.nextDouble()), timing, null);
						
						if(Main.activeEffects.get("randomLaser")) {
							if(!light.isStarted()) light.start(main);
		                } else {
		                	if(light.isStarted()) light.stop();
		                }
						
					}
				}.runTaskTimer(main, 0, timing);
				
			} catch (ReflectiveOperationException e) {
				e.printStackTrace();
			}
			
		}
		
	}

}
