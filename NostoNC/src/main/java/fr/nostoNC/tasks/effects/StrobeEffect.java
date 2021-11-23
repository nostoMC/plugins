package fr.nostoNC.tasks.effects;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import fr.nostoNC.Main;

public class StrobeEffect {

	public StrobeEffect(Main main) {
		
		new BukkitRunnable() {
			
			int i = 0;
			
			@Override
			public void run() {
				
				if(i == 0) {
					i = 1;
				} else {
					i = 0;
				}
				
				if(Main.activeEffects.get("strobe") != null) if(Main.activeEffects.get("strobe")) {
					for(Player players : Bukkit.getOnlinePlayers()) {
						if(players.getWorld() == Main.defaultWorld) {
							if(i == 1) {
								for(int i = 113; i > 105; i--) {
									i--;
									Main.defaultWorld.getBlockAt(new Location(Main.defaultWorld, -9, i, 146)).setType(Material.STONE);
									Main.defaultWorld.getBlockAt(new Location(Main.defaultWorld, -6, i, 145)).setType(Material.STONE);
								}
								for(int i = 113; i > 105; i--) {
									i--;
									Main.defaultWorld.getBlockAt(new Location(Main.defaultWorld, 1, i, 145)).setType(Material.STONE);
									Main.defaultWorld.getBlockAt(new Location(Main.defaultWorld, 4, i, 146)).setType(Material.STONE);
								}
								players.removePotionEffect(PotionEffectType.BLINDNESS);
								PotionEffect potion = new PotionEffect(PotionEffectType.NIGHT_VISION, 10, 255, true);
								players.addPotionEffect(potion);
							} else {
								for(int i = 113; i > 105; i--) {
									i--;
									Main.defaultWorld.getBlockAt(new Location(Main.defaultWorld, -9, i, 146)).setType(Material.SEA_LANTERN);
									Main.defaultWorld.getBlockAt(new Location(Main.defaultWorld, -6, i, 145)).setType(Material.SEA_LANTERN);
								}
								Main.defaultWorld.spawnParticle(Particle.FLASH, -9, 112, 146, 1, 0, 0, 0, 1);
								Main.defaultWorld.spawnParticle(Particle.FLASH, -9, 110, 146, 1, 0, 0, 0, 1);
								Main.defaultWorld.spawnParticle(Particle.FLASH, -9, 108, 146, 1, 0, 0, 0, 1);
								Main.defaultWorld.spawnParticle(Particle.FLASH, -9, 106, 146, 1, 0, 0, 0, 1);
								
								Main.defaultWorld.spawnParticle(Particle.FLASH, -6, 112, 145, 1, 0, 0, 0, 1);
								Main.defaultWorld.spawnParticle(Particle.FLASH, -6, 110, 145, 1, 0, 0, 0, 1);
								Main.defaultWorld.spawnParticle(Particle.FLASH, -6, 108, 145, 1, 0, 0, 0, 1);
								Main.defaultWorld.spawnParticle(Particle.FLASH, -6, 106, 145, 1, 0, 0, 0, 1);
								
								for(int i = 113; i > 105; i--) {
									i--;
									Main.defaultWorld.getBlockAt(new Location(Main.defaultWorld, 1, i, 145)).setType(Material.SEA_LANTERN);
									Main.defaultWorld.getBlockAt(new Location(Main.defaultWorld, 4, i, 146)).setType(Material.SEA_LANTERN);
								}
								Main.defaultWorld.spawnParticle(Particle.FLASH, 1, 112, 145, 1, 0, 0, 0, 1);
								Main.defaultWorld.spawnParticle(Particle.FLASH, 1, 110, 145, 1, 0, 0, 0, 1);
								Main.defaultWorld.spawnParticle(Particle.FLASH, 1, 108, 145, 1, 0, 0, 0, 1);
								Main.defaultWorld.spawnParticle(Particle.FLASH, 1, 106, 145, 1, 0, 0, 0, 1);
								
								Main.defaultWorld.spawnParticle(Particle.FLASH, 4, 112, 146, 1, 0, 0, 0, 1);
								Main.defaultWorld.spawnParticle(Particle.FLASH, 4, 110, 146, 1, 0, 0, 0, 1);
								Main.defaultWorld.spawnParticle(Particle.FLASH, 4, 108, 146, 1, 0, 0, 0, 1);
								Main.defaultWorld.spawnParticle(Particle.FLASH, 4, 106, 146, 1, 0, 0, 0, 1);
								players.removePotionEffect(PotionEffectType.NIGHT_VISION);
								PotionEffect potion = new PotionEffect(PotionEffectType.BLINDNESS, 10, 255, true);
								players.addPotionEffect(potion);
							}
						}
					}
				} else {
					for(Player players : Bukkit.getOnlinePlayers()) {
						if(players.getWorld() == Main.defaultWorld) {
							for(int i = 113; i > 105; i--) {
								i--;
								Main.defaultWorld.getBlockAt(new Location(Main.defaultWorld, -9, i, 146)).setType(Material.STONE);
								Main.defaultWorld.getBlockAt(new Location(Main.defaultWorld, -6, i, 145)).setType(Material.STONE);
							}
							for(int i = 113; i > 105; i--) {
								i--;
								Main.defaultWorld.getBlockAt(new Location(Main.defaultWorld, 1, i, 145)).setType(Material.STONE);
								Main.defaultWorld.getBlockAt(new Location(Main.defaultWorld, 4, i, 146)).setType(Material.STONE);
							}
							players.removePotionEffect(PotionEffectType.BLINDNESS);
							players.removePotionEffect(PotionEffectType.NIGHT_VISION);
						}
					}
				}
				
				
			}
		}.runTaskTimer(main, 0, 1);
		
	}

}
