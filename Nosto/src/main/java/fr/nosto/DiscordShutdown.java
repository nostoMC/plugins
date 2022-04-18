package fr.nosto;

import org.bukkit.Bukkit;

public class DiscordShutdown {

	public DiscordShutdown(Main main) {

		if (!Bukkit.hasWhitelist()) DiscordSetup.getChannelEtatServeur().sendMessageEmbeds(DiscordSetup.getCloseServerEmbed().build()).queue();
		
		DiscordSetup.jda.shutdown();
		
	}

}
