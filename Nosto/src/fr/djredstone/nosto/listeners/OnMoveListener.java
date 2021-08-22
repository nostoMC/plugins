package fr.djredstone.nosto.listeners;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import fr.djredstone.nosto.Main;
import fr.djredstone.nosto.tasks.ParticleEffectTask;

public class OnMoveListener implements Listener {
	
	ArrayList<Player> menuPlayers = Main.getMenuPlayersList();
	ArrayList<Player> frozen = Main.getFreezList();
	ArrayList<Player> afks = Main.getAfksList();

	public OnMoveListener(Main main) {
		main.getServer().getPluginManager().registerEvents(this, main);
	}
	
	@EventHandler
	public void onMove(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		Location lobby = new Location(Bukkit.getWorld("world"), 0.5, 252, 0.5, 0f, 0f);
		menuPlayers = Main.getMenuPlayersList();
		if(menuPlayers.contains(player)) {
			player.teleport(lobby);
			player.setGameMode(GameMode.ADVENTURE);
			if(player.getInventory().contains(Material.COMPASS)) {
				player.sendTitle("§eUtilisez la boussole", "§ePour vous téléporter quelque part !", 0, 60, 5);
			} else {
				player.sendTitle("§c§lVeuillez vous connecter", "§6§l/login <Mot de passe>", 0, 60, 5);
			}
		}
		frozen = Main.getFreezList();
		if(frozen.contains(player)) {
			player.sendMessage("");
			player.sendMessage("§cVous ne pouvez pas bouger !");
			event.setCancelled(true);
		}
		afks = Main.getAfksList();
		if(afks.contains(player)) {
			afks.remove(player);
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
