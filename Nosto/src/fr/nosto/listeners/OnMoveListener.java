package fr.nosto.listeners;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
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
		Location lobby = new Location(Bukkit.getWorld("world"), 0.5, 252, 0.5, 0f, 0f);
		if(Main.menuPlayers.contains(player)) {
			player.teleport(lobby);
			player.setGameMode(GameMode.ADVENTURE);
			if(player.getInventory().contains(Material.COMPASS)) {
				player.sendTitle("§eUtilisez la boussole", "§ePour vous téléporter quelque part !", 0, 60, 5);
			} else {
				player.sendTitle("§c§lVeuillez vous connecter", "§6§l/login <Mot de passe>", 0, 60, 5);
			}
		}
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