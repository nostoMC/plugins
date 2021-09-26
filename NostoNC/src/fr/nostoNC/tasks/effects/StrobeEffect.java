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
						if(players.getWorld() == Bukkit.getWorld("Nightclub")) {
							if(i == 1) {
								for(int i = 77; i > 69; i--) {
									i--;
									Bukkit.getWorld("Nightclub").getBlockAt(new Location(Bukkit.getWorld("Nightclub"), 16, i, 8)).setType(Material.STONE);
									Bukkit.getWorld("Nightclub").getBlockAt(new Location(Bukkit.getWorld("Nightclub"), 14, i, 10)).setType(Material.STONE);
								}
								for(int i = 77; i > 69; i--) {
									i--;
									Bukkit.getWorld("Nightclub").getBlockAt(new Location(Bukkit.getWorld("Nightclub"), -16, i, 8)).setType(Material.STONE);
									Bukkit.getWorld("Nightclub").getBlockAt(new Location(Bukkit.getWorld("Nightclub"), -14, i, 10)).setType(Material.STONE);
								}
								players.removePotionEffect(PotionEffectType.BLINDNESS);
								PotionEffect potion = new PotionEffect(PotionEffectType.NIGHT_VISION, 10, 255, true);
								players.addPotionEffect(potion);
							} else {
								for(int i = 77; i > 69; i--) {
									i--;
									Bukkit.getWorld("Nightclub").getBlockAt(new Location(Bukkit.getWorld("Nightclub"), 16, i, 8)).setType(Material.SEA_LANTERN);
									Bukkit.getWorld("Nightclub").getBlockAt(new Location(Bukkit.getWorld("Nightclub"), 14, i, 10)).setType(Material.SEA_LANTERN);
								}
								Bukkit.getWorld("Nightclub").spawnParticle(Particle.FLASH, 16, 76, 8, 1, 0, 0, 0, 1);
								Bukkit.getWorld("Nightclub").spawnParticle(Particle.FLASH, 16, 74, 8, 1, 0, 0, 0, 1);
								Bukkit.getWorld("Nightclub").spawnParticle(Particle.FLASH, 16, 72, 8, 1, 0, 0, 0, 1);
								Bukkit.getWorld("Nightclub").spawnParticle(Particle.FLASH, 16, 70, 8, 1, 0, 0, 0, 1);
								
								Bukkit.getWorld("Nightclub").spawnParticle(Particle.FLASH, 14, 76, 10, 1, 0, 0, 0, 1);
								Bukkit.getWorld("Nightclub").spawnParticle(Particle.FLASH, 14, 74, 10, 1, 0, 0, 0, 1);
								Bukkit.getWorld("Nightclub").spawnParticle(Particle.FLASH, 14, 72, 10, 1, 0, 0, 0, 1);
								Bukkit.getWorld("Nightclub").spawnParticle(Particle.FLASH, 14, 70, 10, 1, 0, 0, 0, 1);
								
								for(int i = 77; i > 69; i--) {
									i--;
									Bukkit.getWorld("Nightclub").getBlockAt(new Location(Bukkit.getWorld("Nightclub"), -16, i, 8)).setType(Material.SEA_LANTERN);
									Bukkit.getWorld("Nightclub").getBlockAt(new Location(Bukkit.getWorld("Nightclub"), -14, i, 10)).setType(Material.SEA_LANTERN);
								}
								Bukkit.getWorld("Nightclub").spawnParticle(Particle.FLASH, -16, 76, 8, 1, 0, 0, 0, 1);
								Bukkit.getWorld("Nightclub").spawnParticle(Particle.FLASH, -16, 74, 8, 1, 0, 0, 0, 1);
								Bukkit.getWorld("Nightclub").spawnParticle(Particle.FLASH, -16, 72, 8, 1, 0, 0, 0, 1);
								Bukkit.getWorld("Nightclub").spawnParticle(Particle.FLASH, -16, 70, 8, 1, 0, 0, 0, 1);
								
								Bukkit.getWorld("Nightclub").spawnParticle(Particle.FLASH, -14, 76, 10, 1, 0, 0, 0, 1);
								Bukkit.getWorld("Nightclub").spawnParticle(Particle.FLASH, -14, 74, 10, 1, 0, 0, 0, 1);
								Bukkit.getWorld("Nightclub").spawnParticle(Particle.FLASH, -14, 72, 10, 1, 0, 0, 0, 1);
								Bukkit.getWorld("Nightclub").spawnParticle(Particle.FLASH, -14, 70, 10, 1, 0, 0, 0, 1);
								players.removePotionEffect(PotionEffectType.NIGHT_VISION);
								PotionEffect potion = new PotionEffect(PotionEffectType.BLINDNESS, 10, 255, true);
								players.addPotionEffect(potion);
							}
						}
					}
				} else {
					for(Player players : Bukkit.getOnlinePlayers()) {
						if(players.getWorld() == Bukkit.getWorld("Nightclub")) {
							for(int i = 77; i > 69; i--) {
								i--;
								Bukkit.getWorld("Nightclub").getBlockAt(new Location(Bukkit.getWorld("Nightclub"), 16, i, 8)).setType(Material.STONE);
								Bukkit.getWorld("Nightclub").getBlockAt(new Location(Bukkit.getWorld("Nightclub"), 14, i, 10)).setType(Material.STONE);
							}
							for(int i = 77; i > 69; i--) {
								i--;
								Bukkit.getWorld("Nightclub").getBlockAt(new Location(Bukkit.getWorld("Nightclub"), -16, i, 8)).setType(Material.STONE);
								Bukkit.getWorld("Nightclub").getBlockAt(new Location(Bukkit.getWorld("Nightclub"), -14, i, 10)).setType(Material.STONE);
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
