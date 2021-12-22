package fr.nostoNC.tasks;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.Vector;

import fr.nostoNC.Main;

public class GolemPass {

	private static boolean inited = false;

	private static final BoundingBox vipBox = BoundingBox.of(new Vector(-1f, 101f, 182f), new Vector(0f, 104f, 185f));
	private static final BoundingBox staffBox = BoundingBox.of(new Vector(-22f, 112f, 173f), new Vector(-27f, 109f, 170f));

	private static final Map<UUID, Integer> cooldown = new HashMap<>();

	public static void init(Main main) {

		if (inited) return;
		inited = true;

		new BukkitRunnable() {

			@Override
			public void run() {

				for(Player player : Main.defaultWorld.getPlayers()) {
					UUID uuid = player.getUniqueId();
					Location loc = player.getLocation();

					if (vipBox.contains(loc.toVector()) && !player.hasPermission("nosto.nightclub.vip")) {
						playSoundAndMessage(player, "\n§c[Golem] §6Cette zone est réservée aux VIP!");
						player.setVelocity(new Vector(-.8, .5, .2));
					}

					else if (staffBox.contains(loc.toVector()) && !player.hasPermission("nosto.nightclub.staff")) {
						playSoundAndMessage(player, "\n§c[Golem] §6Cette zone est réservée aux artistes et aux membres du staff!");
						player.setVelocity(new Vector(.8, .5, .3));
					}

				}
				for (UUID uuid : cooldown.keySet()) {
					int cooldown = GolemPass.cooldown.get(uuid) - 1;

					if (cooldown <= 0) GolemPass.cooldown.remove(uuid);
					else GolemPass.cooldown.put(uuid, cooldown);
				}
				
			}
		}.runTaskTimer(main, 5, 1);

	}

	private static void playSoundAndMessage(Player player, String message) {
		UUID uuid = player.getUniqueId();
		
		if (cooldown.get(uuid) == null) {
			Main.defaultWorld.playSound(player.getLocation(), Sound.ENTITY_IRON_GOLEM_HURT, 1, 1);
			player.sendMessage(message);
			cooldown.put(uuid, 5);
		}
	}

}
