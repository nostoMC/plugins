package fr.djredstone.nostoNC.tasks;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.scheduler.BukkitRunnable;

import fr.djredstone.nostoNC.Laser;
import fr.djredstone.nostoNC.Main;

public class GoboLaserEffect {
	
	Location goboLaserLeft = new Location(Bukkit.getWorld("Nightclub"), 13, 75, -20);
	Location goboLaserRight = new Location(Bukkit.getWorld("Nightclub"), -12, 75, -20);
	
	private int duration = -1;
	private int distance = -1;

	public GoboLaserEffect(Main main) {
		
		new BukkitRunnable() {
			
			int i = 0;
			
			@Override
			public void run() {
				
				try {
					Laser light1 = new Laser.GuardianLaser(new Location(Bukkit.getWorld("Nightclub"), 4, 66, 12), new Location(Bukkit.getWorld("Nightclub"), 13, 75, -20), duration, distance);
					
					new BukkitRunnable(){
			            double t = 0;
			            double r = 5;
			            public void run(){
			                t = t + Math.PI/16;
			                double x = r*Math.cos(t);
			                double y = r*Math.sin(t);
			                double z = 0;
			                Location loc = goboLaserLeft;
			                loc.add(x, y, z);
			                try {
			                	if(light1.isStarted()) light1.moveEnd(loc);
							} catch (ReflectiveOperationException e) {
								e.printStackTrace();
							}
			                loc.subtract(x, y, z);
			                if (t > Math.PI*8){
			                	t = 0;
			                }
			                
			                if(Main.activeEffects.get("goboLaser")) {
			                	if(!light1.isStarted()) light1.start(main);
			                } else {
			                	if(light1.isStarted()) light1.stop();
			                }
			                
			            }
			        }.runTaskTimer(Main.getInstance(), 0, 1);
					
				} catch (ReflectiveOperationException e) {
					e.printStackTrace();
				}
				
				try {
					Laser light2 = new Laser.GuardianLaser(new Location(Bukkit.getWorld("Nightclub"), -3, 66, 12), new Location(Bukkit.getWorld("Nightclub"), -12, 75, -20), duration, distance);
					
					new BukkitRunnable(){
			            double t = 0;
			            double r = 5;
			            public void run(){
			                t = t + Math.PI/16;
			                double x = r*Math.cos(t);
			                double y = r*Math.sin(t);
			                double z = 0;
			                Location loc = goboLaserRight;
			                loc.add(x, y, z);
			                try {
			                	if(light2.isStarted()) light2.moveEnd(loc);
							} catch (ReflectiveOperationException e) {
								e.printStackTrace();
							}
			                loc.subtract(x, y, z);
			                if (t > Math.PI*8){
			                	t = 0;
			                }
			                
			                if(Main.activeEffects.get("goboLaser")) {
			                	if(!light2.isStarted()) light2.start(main);
			                } else {
			                	if(light2.isStarted()) light2.stop();
			                }
			                
			            }
			        }.runTaskTimer(Main.getInstance(), 0, 1);
					
				} catch (ReflectiveOperationException e) {
					e.printStackTrace();
				}
				
				if(i == 16) this.cancel();
				
				i++;
				
			}
		}.runTaskTimer(main, 0, 2);
		
		new BukkitRunnable(){
            Location loc1 = new Location(Bukkit.getWorld("Nightclub"), -5, 70, -23);
            Location loc2 = new Location(Bukkit.getWorld("Nightclub"), 6, 70, -23);
            double t = 0;
            double r = 6;
            public void run(){
                t = t + Math.PI/64;
                
                double x;
                double y;
                double z;
                
                x = r*Math.cos(t);
                y = r*Math.sin(t);
                z = 0;
                loc1.add(x, y, z);
                Bukkit.getWorld("Nightclub").spawnParticle(Particle.FLAME, loc1, 0, 0, 0, 0, 1);
                goboLaserLeft = loc1;
                loc1.subtract(x, y, z);
                
                x = r*Math.sin(t);
                y = r*Math.cos(t);
                z = 0;
                loc2.add(x, y, z);
                Bukkit.getWorld("Nightclub").spawnParticle(Particle.FLAME, loc2, 0, 0, 0, 0, 1);
                goboLaserRight = loc2;
                loc2.subtract(x, y, z);
                
            }
        }.runTaskTimer(main, 0, 1);
		
	}

}
