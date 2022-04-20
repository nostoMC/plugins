package fr.nosto.discord;

import org.bukkit.Bukkit;

import fr.nosto.Main;
import fr.nosto.discord.DiscordSetup;

public class DiscordShutdown {

	public DiscordShutdown(Main main) {

		if (!Bukkit.hasWhitelist()) DiscordSetup.getChannelEtatServeur().sendMessageEmbeds(DiscordSetup.getCloseServerEmbed().build()).queue();
		
		DiscordSetup.jda.shutdown();
		
	}

}
