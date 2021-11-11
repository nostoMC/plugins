package fr.nosto.listeners;

import java.awt.*;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.nosto.DiscordSetup;
import fr.nosto.MessageManager;
import fr.nosto.Utils;
import net.dv8tion.jda.api.EmbedBuilder;

public class OnLeaveListener implements Listener {

	@EventHandler
	public void onLeave(PlayerQuitEvent event) {
		event.setQuitMessage("");

		final Player player = event.getPlayer();
		final String playerName = player.getName();

		if(Utils.getSurviesNames().contains(player.getWorld().getName())) {

			String message = MessageManager.getMessage("leave")
					.replace("%player%", Utils.getGradeColor(player) + playerName);
			Utils.sendMessageToSurvival("\n" + message);

			EmbedBuilder embed = new EmbedBuilder();
			embed.setAuthor("[-] " + playerName, null, "https://mc-heads.net/avatar/" + playerName);
			embed.setColor(Color.RED);
			DiscordSetup.getChannelSurvie().sendMessageEmbeds(embed.build()).queue();
		}
		
		// ADMIN MESSAGE
		for(Player players : Bukkit.getOnlinePlayers()) {
			if(players.isOp()) {
				players.sendMessage("\n§5[LOG] §d" + playerName + "§5 left the server");
			}
		}
		
	}

}
