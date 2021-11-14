package fr.nosto;

import javax.security.auth.login.LoginException;
import java.awt.*;

import org.bukkit.scheduler.BukkitRunnable;

import fr.nosto.listeners.OnMessageListener;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import org.jetbrains.annotations.NotNull;

public class DiscordSetup implements EventListener {
	
	public static String token;
	public static JDA jda;

	static EmbedBuilder decoEmbed = new EmbedBuilder();

	private static TextChannel channel_survie;
	private static TextChannel channel_nightclub;
	private static TextChannel channel_etat_serveur;

	public DiscordSetup(Main main) {
		
		if(!main.getConfig().contains("token")) {
			main.getConfig().set("token", "YOUR TOKEN HERE");
		}
		
		main.saveConfig();
		
		token = main.getConfig().getString("token");

		try {
			jda = JDABuilder.createDefault(token).build();
		} catch (LoginException e) {
			e.printStackTrace();
		}
		
		JDABuilder builder = JDABuilder.createDefault(token);
	    
	    builder.disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE);
	    try {
			builder.build();
		} catch (LoginException e) {
			e.printStackTrace();
		}
	    
	    try {
			jda.awaitReady();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		jda.addEventListener(new OnMessageListener());
		jda.addEventListener(this);

		channel_survie = jda.getTextChannelById("832554910301290506");
		channel_nightclub = jda.getTextChannelById("877675571193200670");
		channel_etat_serveur = jda.getTextChannelById("875315182556053524");

		decoEmbed.setTitle("Serveur déconnecté !");
		decoEmbed.setColor(Color.RED);

		new BukkitRunnable() {

			@Override
			public void run() {
				EmbedBuilder embed = new EmbedBuilder();
				embed.setTitle("Serveur actif !");
				embed.setColor(Color.GREEN);
				channel_etat_serveur.sendMessageEmbeds(embed.build()).queue();
			}
		}.runTaskLater(main, 40);
	}
	
	public void onEvent(@NotNull GenericEvent event) {
		if (event instanceof ReadyEvent) System.out.println("§cBot discord synchronisé avec minecraft prêt !");
		
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

}
