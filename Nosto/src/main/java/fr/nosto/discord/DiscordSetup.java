package fr.nosto.discord;

import javax.security.auth.login.LoginException;
import java.awt.*;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import fr.nosto.Main;
import fr.nosto.discord.commands.DCommandHelp;
import fr.nosto.discord.commands.DCommandMinecraft;
import fr.nosto.discord.commands.TempDCommandShow;
import fr.nosto.listeners.OnMessageListener;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import org.jetbrains.annotations.NotNull;

public class DiscordSetup implements EventListener {
	
	public static String token;
	public static JDA jda;

	public static final String prefix = ";";

	private static TextChannel channel_survie;
	private static TextChannel channel_nightclub;
	private static TextChannel channel_etat_serveur;

	public DiscordSetup(Main main) {
		
		if (!main.getConfig().contains("discordToken")) {
			main.getConfig().set("discordToken", "YOUR TOKEN HERE");
		}
		
		main.saveConfig();
		
		token = main.getConfig().getString("discordToken");

		try {
			jda = JDABuilder.createDefault(token).build();
		} catch (LoginException e) {
			e.printStackTrace();
		}
		
		JDABuilder builder = JDABuilder.createDefault(token, GatewayIntent.GUILD_MESSAGES);
	    
	    builder.disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE, CacheFlag.EMOTE);
	    try {
			builder.build();
		} catch (LoginException e) {
			e.printStackTrace();
		}
	    
	    try {
			jda.awaitReady();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		jda.addEventListener(new OnMessageListener());
		jda.addEventListener(this);

		addCommands();

		channel_survie = jda.getTextChannelById("832554910301290506");
		channel_nightclub = jda.getTextChannelById("877675571193200670");
		channel_etat_serveur = jda.getTextChannelById("875315182556053524");

		new BukkitRunnable() {

			@Override
			public void run() {
				if (!Bukkit.hasWhitelist()) channel_etat_serveur.sendMessageEmbeds(getOpenServerEmbed().build()).queue();
			}
		}.runTaskLater(main, 40);
	}
	
	public void onEvent(@NotNull GenericEvent event) {
		if (event instanceof ReadyEvent) Bukkit.getLogger().info("§cBot discord synchronisé avec minecraft prêt !");
		
	}

	public static EmbedBuilder getOpenServerEmbed() {
		return new EmbedBuilder()
				.setTitle("Serveur ouvert !")
				.setDescription("L'électricité a été rétablie !")
				.setColor(Color.GREEN);
	}

	public static EmbedBuilder getCloseServerEmbed() {
		return new EmbedBuilder()
				.setTitle("Serveur fermé !")
				.setDescription("Qui a éteint la lumière ?")
				.setColor(Color.RED);
	}

	public static TextChannel getChannelSurvie() {
		return channel_survie;
	}

	public static TextChannel getChannelNightclub() {
		return channel_nightclub;
	}

	public static TextChannel getChannelEtatServeur() {
		return channel_etat_serveur;
	}

	private static void addCommands() {
		jda.addEventListener(new DCommandMinecraft());
		jda.addEventListener(new TempDCommandShow());
		jda.addEventListener(new DCommandHelp());
	}

}
