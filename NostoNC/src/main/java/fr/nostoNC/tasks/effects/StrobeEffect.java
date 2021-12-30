package fr.nostoNC.tasks.effects;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import fr.nostoNC.Main;

public class StrobeEffect {

	private static boolean inited = false;

	public static int timing = 5;
	public static boolean activated;

	public static void init(Main main) {

		if (inited) return;
		inited = true;
		
		new BukkitRunnable() {

			int t = 0;
			Boolean flash = false;
			
			@Override
			public void run() {

				if (t >= timing) {
					t = 0;
					flash = !flash;

					if (Main.activeEffects.get("strobe") != null && Main.activeEffects.get("strobe")) {
						activated = true;

						if (flash) {
							flashON();
							setLightsMaterial(Material.SEA_LANTERN);
						} else {
							flashOFF();
							setLightsMaterial(Material.STONE);
						}

					} else if (activated) {
						activated = false;
						
						reset();
						setLightsMaterial(Material.STONE);
					}
				}

				t++;
			}
		}.runTaskTimer(main, 0, 1);
		
	}

	private static void setLightsMaterial(Material material) {
		Set<Location> locations = new HashSet<>();

		for(int i = 112; i > 102; i = i-2) {
			locations.add(new Location(Main.defaultWorld, -9, i, 146));
			locations.add(new Location(Main.defaultWorld, -6, i, 145));
			locations.add(new Location(Main.defaultWorld, 1, i, 145));
			locations.add(new Location(Main.defaultWorld, 4, i, 146));
		}

		for (Location loc : locations) {
			Main.defaultWorld.getBlockAt(loc).setType(material);
			if (material == Material.SEA_LANTERN)
				Main.defaultWorld.spawnParticle(Particle.FLASH, loc, 1, 0, 0, 0, 1, null, true);
		}
	}

	private static void flashON() {
		PotionEffect potion = new PotionEffect(PotionEffectType.NIGHT_VISION, 25, 255, false, false, false);

		for (Player player : Main.defaultWorld.getPlayers()) {
			player.addPotionEffect(potion);
			player.removePotionEffect(PotionEffectType.BLINDNESS);
		}
	}

	private static void flashOFF() {
		PotionEffect potion = new PotionEffect(PotionEffectType.BLINDNESS, 25, 255, false, false, false);

		for (Player player : Main.defaultWorld.getPlayers()) {
			player.addPotionEffect(potion);
			player.removePotionEffect(PotionEffectType.NIGHT_VISION);
		}
	}

	private static void reset() {
		for (Player player : Main.defaultWorld.getPlayers()) {
			player.removePotionEffect(PotionEffectType.BLINDNESS);
			player.removePotionEffect(PotionEffectType.NIGHT_VISION);
		}
	}

}
