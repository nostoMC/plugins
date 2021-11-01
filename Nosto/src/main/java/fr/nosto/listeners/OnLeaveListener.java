package fr.nosto.listeners;

import java.awt.*;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.nosto.DiscordSetup;
import fr.nosto.Utils;
import net.dv8tion.jda.api.EmbedBuilder;

public class OnLeaveListener implements Listener {

	@EventHandler
	public void onLeave(PlayerQuitEvent event) {
		event.setQuitMessage("");
		
		Player player = event.getPlayer();

		if(Utils.getSurviesNames().contains(player.getWorld().getName())) {
			
			int nb = (int) Math.floor(Math.random() * 6);

			String message = "";
			if(nb == 0) message = ("§6§l" + event.getPlayer().getName() + "§e est parti...");
			else if(nb == 1) message = ("§6§l" + event.getPlayer().getName() + "§e fait une pose.");
			else if(nb == 2) message = ("§6§l" + event.getPlayer().getName() + "§e est reparti !");
			else if(nb == 3) message = ("§eUne personne est partie, elle s'agit de §6§l" + event.getPlayer().getName() + "§e !");
			else if(nb == 4) message = ("§eBye bye §6§l" + event.getPlayer().getName() + "§e !");
			else if(nb == 5) message = ("§6§l" + event.getPlayer().getName() + "§e retourne au monde réél !");

			Utils.sendMessageToSurvival(message);

			EmbedBuilder embed = new EmbedBuilder();
			embed.setAuthor("[-] " + player.getName(), null, "https://mc-heads.net/avatar/" + player.getName());
			embed.setColor(Color.RED);
			
			DiscordSetup.getChannelSurvie().sendMessageEmbeds(embed.build()).queue();
		}
		
		// ADMIN MESSAGE
		for(Player players : Bukkit.getOnlinePlayers()) {
			if(players.isOp()) {
				players.sendMessage("");
				players.sendMessage("§5[LOG] §d" + player.getName() + "§5 left the server");
			}
		}
		
	}

}
