package fr.nostoNC.tasks;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.Vector;

import fr.nostoNC.Main;

public class VIPpass {

	private static final BoundingBox box = BoundingBox.of(new Vector(-1f, 101f, 182f), new Vector(0f, 104f, 185f));
	private static int soundCooldown = 0;

	public VIPpass(Main main) {

		new BukkitRunnable() {

			@Override
			public void run() {

				for(Player player : Main.defaultWorld.getPlayers()) {

					if(!player.hasPermission("nosto.nightclub.vip")) {

						Location loc = player.getLocation();
						if (box.contains(loc.toVector())) {
							if (soundCooldown == 0) {
								Main.defaultWorld.playSound(loc, Sound.ENTITY_IRON_GOLEM_HURT, 1, 1);
								soundCooldown = 2;
							}
							player.setVelocity(new Vector(-.8, .5, .2));
						}

					}
					
				}
				if (soundCooldown > 0) soundCooldown--;
				
			}
		}.runTaskTimer(main, 5, 1);

	}

}
