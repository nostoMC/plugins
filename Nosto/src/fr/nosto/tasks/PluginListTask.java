package fr.nosto.tasks;

import java.awt.Color;
import java.util.logging.Logger;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.scheduler.BukkitRunnable;

import fr.nosto.DiscordSetup;
import fr.nosto.Main;
import net.dv8tion.jda.api.EmbedBuilder;

public class PluginListTask {

	public PluginListTask(Main main) {
		
		new BukkitRunnable() {
			
			@Override
			public void run() {
				EmbedBuilder embed = new EmbedBuilder();
				embed.setTitle("Serveur actif !");
				embed.setColor(Color.GREEN);
					
				DiscordSetup.jda.getTextChannelById("875315182556053524").sendMessage(embed.build()).queue();
				
				PluginManager pm = Bukkit.getServer().getPluginManager();
				Logger logger = Bukkit.getLogger();
				logger.log(Level.WARNING, "");
				logger.log(Level.WARNING, "");
				logger.log(Level.WARNING, "§bListe des plugins Nosto disponibles : ");
				logger.log(Level.WARNING, "");
				
				if(pm.getPlugin("Nosto-Nightclub") != null) {
					logger.log(Level.WARNING, "§bNightclub - §a✔");
				} else {
					logger.log(Level.WARNING, "§bNightclub - §c✖");
				}
				logger.log(Level.WARNING, "");
				if(pm.getPlugin("Nosto-Survival") != null) {
					logger.log(Level.WARNING, "§bSurvival - §a✔");
				} else {
					logger.log(Level.WARNING, "§bSurvival - §c✖");
				}
				logger.log(Level.WARNING, "");
				if(pm.getPlugin("Nosto-World") != null) {
					logger.log(Level.WARNING, "§bWorld - §a✔");
				} else {
					logger.log(Level.WARNING, "§bWorld - §c✖");
				}
				
				logger.log(Level.WARNING, "");
				logger.log(Level.WARNING, "");
			}
		}.runTaskLater(main, 60);
		
	}

}
