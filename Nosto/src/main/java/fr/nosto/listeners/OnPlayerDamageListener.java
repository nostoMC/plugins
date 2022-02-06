package fr.nosto.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class OnPlayerDamageListener implements Listener {
	
	@EventHandler
	public void onPlayerDamage(EntityDamageEvent event) {
		if (!(event.getEntity() instanceof Player player)) return;

		if (!player.getWorld().getName().endsWith("Lobby")) return;

		event.setCancelled(true);

		// trou sous le portail principal
		if (player.getLocation().getY() < 83) {
			Location spawn = new Location(Bukkit.getWorld("MainLobby"), 0.5, 103.0, 0.5, 0f, 0f);
			player.teleport(spawn);
			player.sendMessage("\n§cVous êtes tombé dans le trou infini de la mort!");
		}
	}

}
