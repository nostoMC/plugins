package fr.djredstone.nosto.tasks;

import java.awt.Color;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.scheduler.BukkitRunnable;

import fr.djredstone.nosto.Main;
import net.dv8tion.jda.api.EmbedBuilder;

public class PluginListTask {

	public PluginListTask(Main main) {
		
		new BukkitRunnable() {
			
			@Override
			public void run() {
				EmbedBuilder embed = new EmbedBuilder();
				embed.setTitle("Serveur actif !");
				embed.setColor(Color.GREEN);
					
				Main.jda.getTextChannelById("875315182556053524").sendMessage(embed.build()).queue();
				
				PluginManager pm = Bukkit.getServer().getPluginManager();
				System.out.println("");
				System.out.println("");
				System.out.println("§bListe des plugins Nosto disponibles : ");
				System.out.println("");
				if(pm.getPlugin("Nosto-Nightclub") != null) {
					System.out.println("§bNightclub - §a✔");
				} else {
					System.out.println("§bNightclub - §c✖");
				}
				System.out.println("");
				System.out.println("");
			}
		}.runTaskLater(main, 60);
		
	}

}
