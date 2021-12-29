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

				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:nostoclub run summon minecraft:bat -2.0 107.99 146.0 {ActiveEffects:[{Id:14,Amplifier:0,Duration:2147483647,ShowParticles:0b}]}");
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

				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:nostoclub positioned -2 107 146 at @e[type=minecraft:bat,distance=..200] run summon firework_rocket ~ ~ ~ {LifeTime:0,FireworksItem:{id:firework_rocket,Count:1,tag:{Fireworks:{Explosions:[{Type:0,Flicker:1,Trail:1,Colors:[I;11743532,14602026,15435844],FadeColors:[I;11743532,14602026,15435844]}],Flight:1}}}}");
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:nostoclub positioned -2 107 146 run kill @e[type=minecraft:bat,distance=..200]");
				
			}
		}.runTaskLater(main, 200);
		
	}

}
