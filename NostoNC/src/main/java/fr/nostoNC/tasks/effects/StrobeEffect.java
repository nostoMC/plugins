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

	public static int timing = 5;

	public StrobeEffect(Main main) {
		
		new BukkitRunnable() {

			int t = 0;
			
			Boolean flash = false;
			
			@Override
			public void run() {

				if (t >= timing) {

					t = 0;

					flash = !flash;

					if (Main.activeEffects.get("strobe") != null) if (Main.activeEffects.get("strobe")) {

						if (flash) {

							flashON();
							setLightsMaterial(Material.SEA_LANTERN);

						} else {

							flashOFF();
							setLightsMaterial(Material.STONE);

						}

					} else {

						reset();
						setLightsMaterial(Material.STONE);

					}

				}

				t++;

			}
		}.runTaskTimer(main, 0, 1);
		
	}

	private static void setLightsMaterial(Material material) {
		for(int i = 112; i > 105; i = i-2) {
			Location loc1 = new Location(Main.defaultWorld, -9, i, 146);
			Location loc2 = new Location(Main.defaultWorld, -6, i, 145);
			Location loc3 = new Location(Main.defaultWorld, 1, i, 145);
			Location loc4 = new Location(Main.defaultWorld, 4, i, 146);
			Main.defaultWorld.getBlockAt(loc1).setType(material);
			Main.defaultWorld.getBlockAt(loc2).setType(material);
			Main.defaultWorld.getBlockAt(loc3).setType(material);
			Main.defaultWorld.getBlockAt(loc4).setType(material);
			if (material == Material.SEA_LANTERN) {
				Main.defaultWorld.spawnParticle(Particle.FLASH, loc1, 1, 0, 0, 0, 1);
				Main.defaultWorld.spawnParticle(Particle.FLASH, loc2, 1, 0, 0, 0, 1);
				Main.defaultWorld.spawnParticle(Particle.FLASH, loc3, 1, 0, 0, 0, 1);
				Main.defaultWorld.spawnParticle(Particle.FLASH, loc4, 1, 0, 0, 0, 1);
			}
		}
	}

	private static void flashON() {
		for (Player player : Bukkit.getOnlinePlayers()) {
			if (player.getWorld() == Main.defaultWorld) {
				PotionEffect potion = new PotionEffect(PotionEffectType.NIGHT_VISION, 10, 255, false);
				player.addPotionEffect(potion);
				player.removePotionEffect(PotionEffectType.BLINDNESS);
			}
		}
	}

	private static void flashOFF() {
		for (Player player : Bukkit.getOnlinePlayers()) {
			if (player.getWorld() == Main.defaultWorld) {
				PotionEffect potion = new PotionEffect(PotionEffectType.BLINDNESS, 10, 255, false);
				player.addPotionEffect(potion);
				player.removePotionEffect(PotionEffectType.NIGHT_VISION);
			}
		}
	}

	private static void reset() {
		for (Player player : Bukkit.getOnlinePlayers()) {
			if (player.getWorld() == Main.defaultWorld) {
				player.removePotionEffect(PotionEffectType.BLINDNESS);
				player.removePotionEffect(PotionEffectType.NIGHT_VISION);
			}
		}
	}

}
