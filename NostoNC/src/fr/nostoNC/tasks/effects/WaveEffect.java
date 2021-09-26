package fr.nostoNC.tasks.effects;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.scheduler.BukkitRunnable;

import fr.nostoNC.Main;

public class WaveEffect {

	public WaveEffect(Main main) {

        new BukkitRunnable(){
            double t = Math.PI/4;
            Location loc = new Location(Bukkit.getWorld("Nightclub"), 0, 64, -9);
            public void run(){
                t = t + 0.1*Math.PI;
                for (double theta = 0; theta <= 2*Math.PI; theta = theta + Math.PI/32){
                    double x = t*Math.cos(theta);
                    double y = 2*Math.exp(-0.1*t) * Math.sin(t) + 1.5;
                    double z = t*Math.sin(theta);
                    loc.add(x,y,z);
                    if(Main.activeEffects.get("wave")) {
                    	Bukkit.getWorld("Nightclub").spawnParticle(Particle.FIREWORKS_SPARK, loc, 1, 0, 0, 0, 0);
                    }
                    loc.subtract(x,y,z);
                 
                    theta = theta + Math.PI/64;
                 
                    x = t*Math.cos(theta);
                    y = 2*Math.exp(-0.1*t) * Math.sin(t) + 1.5;
                    z = t*Math.sin(theta);
                    loc.add(x,y,z);
                    if(Main.activeEffects.get("wave")) {
                    	Bukkit.getWorld("Nightclub").spawnParticle(Particle.SPELL_WITCH, loc, 1, 0, 0, 0);
                    }
                    loc.subtract(x,y,z);
                }
                if (t > 20){
                    t = 0;
                    loc = new Location(Bukkit.getWorld("Nightclub"), 0, 64, -9);
                }
            }
                     
        }.runTaskTimer(main, 0, 1);
		
	}

}
