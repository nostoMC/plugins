package fr.nosto.listeners;

import java.awt.*;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

import fr.nosto.DiscordSetup;
import fr.nosto.MessageManager;
import fr.nosto.Utils;
import net.dv8tion.jda.api.EmbedBuilder;

public class OnPlayerChangeWorldListener implements Listener {
	
	@EventHandler
	public void onWorldChange(PlayerChangedWorldEvent event){

		final Player player = event.getPlayer();
		final String playerName = player.getName();

		final Set<String> survies_names = Utils.getSurviesNames();

		final boolean isToSurvivalWorld = survies_names.contains(player.getWorld().getName());
		final boolean isFromSurvivalWorld = survies_names.contains(event.getFrom().getName());

		final boolean EnterSurvival = !isFromSurvivalWorld && isToSurvivalWorld;
		final boolean LeaveSurvival = isFromSurvivalWorld && !isToSurvivalWorld;

		if (EnterSurvival || LeaveSurvival) {
			String message;
			EmbedBuilder embed = new EmbedBuilder();

			if (EnterSurvival) {
				message = MessageManager.getMessage("survival.join");

				embed.setAuthor("[+] " + playerName, null, "https://mc-heads.net/avatar/" + playerName);
				embed.setColor(Color.GREEN);
			}
			else {
				message = MessageManager.getMessage("survival.leave");

				embed.setAuthor("[-] " + playerName, null, "https://mc-heads.net/avatar/" + playerName);
				embed.setColor(Color.RED);
			}
			
			message = message.replace("%player%", Utils.getGradeColor(player) + playerName);
			Utils.sendMessageToSurvival("\n" + message);
			DiscordSetup.getChannelSurvie().sendMessageEmbeds(embed.build()).queue();
		}
		
		// ADMIN MESSAGE
		Bukkit.broadcast("\n§5[LOG] §d" + playerName + "§5 travels from §d" + event.getFrom().getName() + "§5 to §d" + player.getWorld().getName(),
				"nosto.logmessages.worldchange");
	}

}
