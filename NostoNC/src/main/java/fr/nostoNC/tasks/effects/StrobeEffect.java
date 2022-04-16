package fr.nostoNC.tasks.effects;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import fr.nostoNC.Main;
import fr.nostoNC.Utils;

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

					if (Utils.getActiveEffects("strobe")) {
						activated = true;

						if (flash) {
							flashON();
						} else {
							flashOFF();
						}

					} else if (activated) {
						activated = false;
						
						reset();
					}
				}

				t++;
			}
		}.runTaskTimer(main, 0, 1);
		
	}

	private static void flashON() {
		Set<Vector> set = new HashSet<>();
		set.add(new Vector(-15.5, 113.0, 148.5));
		set.add(new Vector(-9.5, 113.0, 148.5));
		set.add(new Vector(-3.5, 113.0, 148.5));
		set.add(new Vector(-0.5, 113.0, 148.5));
		set.add(new Vector(5.5, 113.0, 148.5));
		set.add(new Vector(11.5, 113.0, 148.5));

		for (Vector vector : set) {
			Utils.getDefaultWorld().spawnParticle(Particle.FLASH, new Location(Utils.getDefaultWorld(), vector.getX(), vector.getY(), vector.getZ()), 1, 0, 0, 0, 1, null, true);
		}

		PotionEffect potion = new PotionEffect(PotionEffectType.NIGHT_VISION, 25, 255, false, false, false);

		for (Player player : Utils.getDefaultWorld().getPlayers()) {
			player.addPotionEffect(potion);
			player.removePotionEffect(PotionEffectType.BLINDNESS);
		}
	}

	private static void flashOFF() {
		PotionEffect potion = new PotionEffect(PotionEffectType.BLINDNESS, 25, 255, false, false, false);

		for (Player player : Utils.getDefaultWorld().getPlayers()) {
			player.addPotionEffect(potion);
			player.removePotionEffect(PotionEffectType.NIGHT_VISION);
		}
	}

	private static void reset() {
		for (Player player : Utils.getDefaultWorld().getPlayers()) {
			player.removePotionEffect(PotionEffectType.BLINDNESS);
			player.removePotionEffect(PotionEffectType.NIGHT_VISION);
		}
	}

}
