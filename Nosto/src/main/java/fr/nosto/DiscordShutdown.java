package fr.nosto;

public class DiscordShutdown {

	public DiscordShutdown(Main main) {

		DiscordSetup.jda.getTextChannelById("875315182556053524").sendMessage(DiscordSetup.decoEmbed.build()).queue();
		
		DiscordSetup.jda.shutdown();
		
	}

}
