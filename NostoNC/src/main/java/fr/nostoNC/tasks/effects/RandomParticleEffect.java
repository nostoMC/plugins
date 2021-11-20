package fr.nostoNC.tasks.effects;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.scheduler.BukkitRunnable;

import fr.nostoNC.Main;

public class RandomParticleEffect {

	public RandomParticleEffect(Main main) {

		new BukkitRunnable() {
			
			int i = 0;
			
			@Override
			public void run() {

				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:nightclub run summon minecraft:bat 0 71 15 {ActiveEffects:[{Id:14,Amplifier:0,Duration:2147483647,ShowParticles:0b}]}");
				i++;
				if(i == 20) this.cancel();
				
			}
		}.runTaskTimer(main, 0, 1);
		
		new BukkitRunnable() {
			
			int i = 0;
			
			@Override
			public void run() {
				
				for(Entity entity : Main.defaultWorld.getEntities()) {
					if(entity.getType() == EntityType.BAT) {
						Main.defaultWorld.spawnParticle(Particle.FLAME, entity.getLocation().getX(), entity.getLocation().getY(), entity.getLocation().getZ(), 0, 0, 0, 0, 1, true);
					}
				}
				i++;
				if(i == 200) this.cancel();
				
			}
		}.runTaskTimer(main, 0, 1);
		
		new BukkitRunnable() {
			
			@Override
			public void run() {

				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:nightclub positioned 0 70 10 run kill @e[type=minecraft:bat,distance=..200]");
				
			}
		}.runTaskLater(main, 200);
		
	}

}
