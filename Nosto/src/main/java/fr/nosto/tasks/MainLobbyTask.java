package fr.nosto.tasks;

import java.util.Objects;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.BoundingBox;

import fr.nosto.Main;

public class MainLobbyTask {

	private static boolean inited = false;
	private static World mainLobby;

	public static void init(Main main) {

		if (inited) return;
		inited = true;

		new BukkitRunnable() {

			@Override
			public void run() {

				mainLobby = Objects.requireNonNull(Bukkit.getWorld("MainLobby"));
				startLoop(main);
				
			}

		}.runTaskLater(main, 5);

	}

	private static void startLoop(Main main) {

		new BukkitRunnable() {

			@Override
			public void run() {

				for(Player player : mainLobby.getPlayers()){

					if (BoundingBox.of(new Location(Bukkit.getWorld("MainLobby"), -7, 94, 11), new Location(Bukkit.getWorld("MainLobby"), -4, 95, 14)).contains(player.getLocation().toVector())
							|| BoundingBox.of(new Location(Bukkit.getWorld("MainLobby"), 5, 94, 11), new Location(Bukkit.getWorld("MainLobby"), 8, 95, 14)).contains(player.getLocation().toVector())) {

						player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 3, 6, false, false, false));
						mainLobby.spawnParticle(Particle.VILLAGER_HAPPY,
								player.getLocation(),
								1, //count
								.3, 0, .3); //offset
					}

				}

			}
		}.runTaskTimer(main, 0, 1);
	}

}
