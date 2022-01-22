package fr.nosto.listeners;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import fr.nosto.Main;
import fr.nosto.commands.CommandSit;
import org.spigotmc.event.entity.EntityDismountEvent;

public class SitListeners implements Listener {
	
	@EventHandler
	public void onDismount(EntityDismountEvent event) {
		
		if (!(event.getEntity() instanceof Player player)) return;
		if (!(event.getDismounted() instanceof ArmorStand as)) return;

		if (as.getScoreboardTags().contains("seat")) {
			CommandSit.sitting.remove(player);
			as.remove();

			Location loc = player.getLocation().add(0, .6, 0);
			
			// Cette task est nécessaire pour exécuter le code APRÈS que l'event ait fini d'etre calculé
			// (sinon le tp n'a pas d'effet)
			new BukkitRunnable() {

				@Override
				public void run() {
					player.teleport(loc);
				}
			}.runTask(Main.getPlugin(Main.class));
		}

	}

}
