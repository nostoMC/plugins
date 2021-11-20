package fr.nostoNC.tasks.effects;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;

import fr.nostoNC.Main;
import fr.nostoNC.tasks.Laser;

public class RandomLaserEffect {
	
	private int duration = -1;
	private int distance = -1;
	
	private Random random = new Random();

	public RandomLaserEffect(Main main) {
		
		int timing = 40;
		
		for(int i = 0; i < 30; i++) {
			
			try {
				Laser light = new Laser.GuardianLaser(new Location(Main.defaultWorld, 0.5, 76.5, -12.5), new Location(Main.defaultWorld, -20 + (20 - -20) * random.nextDouble(), 62, -30 + (8 - -30) * random.nextDouble()), duration, distance);
				
				new BukkitRunnable() {
					
					@Override
					public void run() {

						if(light.isStarted()) light.moveEnd(new Location(Main.defaultWorld, -21 + (21 - -21) * random.nextDouble(), 62 + (87 - 62) * random.nextDouble(), -31 + (18 - -31) * random.nextDouble()), timing, null);
						
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
