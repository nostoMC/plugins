package fr.nosto.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.nosto.MessageManager;
import fr.nosto.Utils;

public class OnLeaveListener implements Listener {

	@EventHandler
	public void onLeave(PlayerQuitEvent event) {
		event.setQuitMessage("");

		final Player player = event.getPlayer();
		final String playerName = player.getName();

		if(Utils.getSurviesNames().contains(player.getWorld().getName())) {

			player.teleport(new Location(Bukkit.getWorld("MainLobby"), 0.5, 103.5, 0.5, 0f, 0f));

			String message = MessageManager.getMessage("leave")
					.replace("%player%", Utils.getGradeColor(player) + playerName);
			Utils.sendMessageToSurvival("\n" + message);
		}
		
		// ADMIN MESSAGE
		for(Player players : Bukkit.getOnlinePlayers()) {
			if(players.isOp()) {
				players.sendMessage("\n§5[LOG] §d" + playerName + "§5 left the server");
			}
		}
		
	}

}
