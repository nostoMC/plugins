package fr.nosto.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import fr.nosto.Main;
import fr.nosto.tasks.ParticleEffectTask;

public class OnMoveListener implements Listener {
	
	@EventHandler
	public void onMove(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		if(Main.frozen.contains(player)) {
			player.sendMessage("");
			player.sendMessage("§cVous ne pouvez pas bouger !");
			event.setCancelled(true);
		}
		if(Main.afks.contains(player)) {
			Main.afks.remove(player);
			Bukkit.broadcastMessage("");
			Bukkit.broadcastMessage("§7§l" + player.getName() + " §7n'est plus AFK");
			player.setCustomName(player.getName());
		}

		if (!event.getFrom().toVector().equals(event.getTo().toVector())
				&& ParticleEffectTask.bigEffects.containsKey(player.getUniqueId())) {
			ParticleEffectTask.bigEffects.get(player.getUniqueId()).startCoolDown();
		}
	}

}
