package fr.nostoNC.tasks;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import fr.nostoNC.Main;

public class VIPpass {

	public VIPpass(Main main) {

		new BukkitRunnable() {
			
			@Override
			public void run() {

				for(Player player : Bukkit.getOnlinePlayers()) {
					
					if(player.getWorld() == Main.defaultWorld && !player.hasPermission("nosto.nightclub.vip")) {
						
						Location loc = player.getLocation();
						if(loc.getBlockX() > -8 && loc.getBlockX() < -5) {
							if(loc.getBlockY() > 63 && loc.getBlockY() < 68) {
								if(loc.getBlockZ() > -37 && loc.getBlockZ() < -32) {
									Main.defaultWorld.playSound(loc, Sound.ENTITY_IRON_GOLEM_HURT, 100, 1);
									player.setVelocity(new Vector(1, 0.5, -0.4));
								}
							}
						}
						
					}
					
				}
				
			}
		}.runTaskTimer(main, 0, 1);

	}

}
