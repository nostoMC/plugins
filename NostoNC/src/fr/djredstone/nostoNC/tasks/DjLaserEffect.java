package fr.djredstone.nostoNC.tasks;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitRunnable;

import fr.djredstone.nostoNC.Laser;
import fr.djredstone.nostoNC.Main;

public class DjLaserEffect {
	
	public static Laser light1, light2, light3, light4, light5;
	
	private int duration = -1;
	private int distance = -1;

	public DjLaserEffect(Main main) {
		
		World w = Bukkit.getWorld("Nightclub");
		
        try {
        	light1 = new Laser.GuardianLaser(new Location(w, 0.5, 67, 11.0), new Location(w, -17.5, 70, -23.9), duration, distance);
        	light2 = new Laser.GuardianLaser(new Location(w, 0.5, 67, 11.0), new Location(w, -8.5, 70, -25.9), duration, distance);
        	light3 = new Laser.GuardianLaser(new Location(w, 0.5, 67, 11.0), new Location(w, 0.5, 70, -28.9), duration, distance);
        	light4 = new Laser.GuardianLaser(new Location(w, 0.5, 67, 11.0), new Location(w, 9.5, 70, -25.9), duration, distance);
        	light5 = new Laser.GuardianLaser(new Location(w, 0.5, 67, 11.0), new Location(w, 18.5, 70, -23.9), duration, distance);
        	int timing = 20;
            new BukkitRunnable() {
            	
                boolean up = true;
                
                @Override
                public void run() {
                	
                	if(light1.isStarted()) {
                		if(up) {
                			if(light1.isStarted()) light1.moveEnd(new Location(w, -17.5, 85.9, -23.9), timing, null);
                			if(light2.isStarted()) light2.moveEnd(new Location(w, -8.5, 85.9, -25.9), timing, null);
                			if(light3.isStarted()) light3.moveEnd(new Location(w, 0.5, 85.9, -28.9), timing, null);
                			if(light4.isStarted()) light4.moveEnd(new Location(w, 9.5, 85.9, -25.9), timing, null);
                			if(light5.isStarted()) light5.moveEnd(new Location(w, 18.5, 85.9, -23.9), timing, null);
                    		up = false;
                    	} else {
                    		if(light1.isStarted()) light1.moveEnd(new Location(w, -17.5, 55.0, -23.9), timing, null);
                    		if(light2.isStarted()) light2.moveEnd(new Location(w, -8.5, 55.0, -25.9), timing, null);
                    		if(light3.isStarted()) light3.moveEnd(new Location(w, 0.5, 55.0, -28.9), timing, null);
                    		if(light4.isStarted()) light4.moveEnd(new Location(w, 9.5, 55.0, -25.9), timing, null);
                    		if(light5.isStarted()) light5.moveEnd(new Location(w, 18.5, 55.0, -23.9), timing, null);
                    		up = true;
                    	}
                	}
                		
                }
            }.runTaskTimer(Main.getInstance(), 0, timing);
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
		
	}

}
