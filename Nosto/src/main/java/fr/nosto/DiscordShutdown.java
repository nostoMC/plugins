package fr.nosto;

public class DiscordShutdown {

	public DiscordShutdown(Main main) {

		DiscordSetup.getChannelEtatServeur().sendMessageEmbeds(DiscordSetup.decoEmbed.build()).queue();
		
		DiscordSetup.jda.shutdown();
		
	}

}
