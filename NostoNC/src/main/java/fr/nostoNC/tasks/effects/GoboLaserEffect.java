package fr.nostoNC.tasks.effects;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;

import fr.nostoNC.Main;
import fr.nostoNC.tasks.Laser;

public class GoboLaserEffect {
	
	Location goboLaserLeft = new Location(Main.defaultWorld, 13, 75, -20);
	Location goboLaserRight = new Location(Main.defaultWorld, -12, 75, -20);
	
	private int duration = -1;
	private int distance = -1;

	public GoboLaserEffect(Main main) {
		
		new BukkitRunnable() {
			
			int i = 0;
			
			@Override
			public void run() {
				
				try {
					Laser light1 = new Laser.GuardianLaser(new Location(Main.defaultWorld, 4, 66, 12), new Location(Main.defaultWorld, 13, 75, -20), duration, distance);
					
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
					Laser light2 = new Laser.GuardianLaser(new Location(Main.defaultWorld, -3, 66, 12), new Location(Main.defaultWorld, -12, 75, -20), duration, distance);
					
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
		
	}

}
