package fr.nosto;

import java.awt.Color;

import javax.security.auth.login.LoginException;

import fr.nosto.listeners.OnMessageListener;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

public class DiscordSetup {
	
	public static String token;
	public static JDA jda;

	static EmbedBuilder decoEmbed = new EmbedBuilder();

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
	    
	    decoEmbed.setTitle("Serveur déconecté !");
		decoEmbed.setColor(Color.RED);
		
		jda.addEventListener(new OnMessageListener());
	    jda.addEventListener(this);
		
	}
	
	public void onEvent(GenericEvent event) {
		if (event instanceof ReadyEvent) System.out.println("§cBot discord synchronisé avec minecraft prêt !");
		
	}

}
