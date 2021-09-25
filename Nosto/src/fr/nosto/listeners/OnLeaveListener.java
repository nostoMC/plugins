package fr.nosto.listeners;

import java.awt.Color;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.nosto.DiscordSetup;
import net.dv8tion.jda.api.EmbedBuilder;

public class OnLeaveListener implements Listener {
	
	String[] messages = {"1", "2", "3", "4", "5", "6"};
	
	@EventHandler
	public void onLeave(PlayerQuitEvent event) {
		event.setQuitMessage("");
		
		Player player = event.getPlayer();
		
		Set<String> survies_names = new HashSet<String>();
		survies_names.add("survie");
		survies_names.add("survie_the_end");
		survies_names.add("survie_nether");
		
		if(survies_names.contains(player.getWorld().getName())) {
			
			String[] messages = {"1", "2", "3", "4", "5", "6"};
			
			String message = "";
			
			if(messages[new Random().nextInt(messages.length)] == "1") {
				message = ("§6§l" + event.getPlayer().getName() + "§e est parti...");
			} else if(messages[new Random().nextInt(messages.length)] == "2") {
				message = ("§6§l" + event.getPlayer().getName() + "§e fait une pose.");
			} else if(messages[new Random().nextInt(messages.length)] == "3") {
				message = ("§6§l" + event.getPlayer().getName() + "§e est reparti !");
			} else if(messages[new Random().nextInt(messages.length)] == "4") {
				message = ("§eUne personne est parti, elle s'agit de §6§l" + event.getPlayer().getName() + "§e !");
			} else if(messages[new Random().nextInt(messages.length)] == "5") {
				message = ("§eBye bye §6§l" + event.getPlayer().getName() + "§e !");
			} else if(messages[new Random().nextInt(messages.length)] == "6") {
				message = ("§6§l" + event.getPlayer().getName() + "§e retourne au monde réél !");
			}
			
			if(!Bukkit.getOnlinePlayers().isEmpty()) {
				for(Player players : Bukkit.getOnlinePlayers()) {
					if(survies_names.contains(player.getWorld().getName())) {
						
						players.sendMessage("");
						players.sendMessage(message);
						
					}
				}
			}
			
			EmbedBuilder embed = new EmbedBuilder();
			embed.setAuthor("[-] " + player.getName(), null, "https://mc-heads.net/avatar/" + player.getName());
			embed.setColor(Color.RED);
			
			DiscordSetup.jda.getTextChannelById("832554910301290506").sendMessage(embed.build()).queue();
			
		}
		
		// ADMIN MESSAGE
		for(Player players : Bukkit.getOnlinePlayers()) {
			if(players.isOp()) {
				players.sendMessage("");
				players.sendMessage("§5[LOG] §d" + player.getName() + "§5 left the server");
			}
		}
		
	}

}
