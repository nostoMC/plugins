package fr.nosto.listeners;

import java.awt.Color;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

import fr.nosto.DiscordSetup;
import net.dv8tion.jda.api.EmbedBuilder;

public class OnPlayerChangeWorldListener implements Listener {
	
	@EventHandler
	public void onWorldChange(PlayerChangedWorldEvent event){

		Player player = event.getPlayer();
		
		Set<String> survies_names = new HashSet<>();
		survies_names.add("survie");
		survies_names.add("survie_the_end");
		survies_names.add("survie_nether");
			
		if (!survies_names.contains(event.getFrom().getName()) && survies_names.contains(player.getWorld().getName())) {
			
			String[] messages = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
			String message = "";
			
			if(messages[new Random().nextInt(messages.length)].equals("1")) {
				message = "§eSalut §6§l" + player.getName() + "§e ! Bienvenue sur le survival !";
			} else if(messages[new Random().nextInt(messages.length)].equals("2")) {
				message = "§eBon retour parmi nous §6§l" + player.getName() + "§e !";
			} else if(messages[new Random().nextInt(messages.length)].equals("3")) {
				message = "§6§l" + player.getName() + "§e est de retour !";
			} else if(messages[new Random().nextInt(messages.length)].equals("4")) {
				message = "§6§l" + player.getName() + "§e nous a rejoint !";
			} else if(messages[new Random().nextInt(messages.length)].equals("5")) {
				message = "§eOh bas ! Qui vois là je ? C'est §6§l" + player.getName() + "�e !";
			} else if(messages[new Random().nextInt(messages.length)].equals("6")) {
				message = "§6§l" + player.getName() + "§e est là ! J'AIME BIEN !";
			} else if(messages[new Random().nextInt(messages.length)].equals("7")) {
				message = "§eOh.. It's you §6§l" + player.getName() + "§e .. It's been a looong time.. How have you been ?";
			} else if(messages[new Random().nextInt(messages.length)].equals("8")) {
				message = "§eAh ! Un nouveau joueur de connecter ! Et il s'agit de... §6§l" + player.getName() + "§e !";
			} else if(messages[new Random().nextInt(messages.length)].equals("9")) {
				message = "§eHello §6§l" + player.getName() + "§e ! Bon cube sur le survival !";
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
			embed.setAuthor("[+] " + player.getName(), null, "https://mc-heads.net/avatar/" + player.getName());
			embed.setColor(Color.GREEN);
			
			DiscordSetup.jda.getTextChannelById("832554910301290506").sendMessage(embed.build()).queue();
			
		}
		if(survies_names.contains(event.getFrom().getName()) && !survies_names.contains(player.getWorld().getName())) {
			
			String[] messages = {"1", "2", "3", "4", "5", "6"};
			
			String message = "";
			
			if(messages[new Random().nextInt(messages.length)].equals("1")) {
				message = ("§6§l" + event.getPlayer().getName() + "§e est parti...");
			} else if(messages[new Random().nextInt(messages.length)].equals("2")) {
				message = ("§6§l" + event.getPlayer().getName() + "§e fait une pose.");
			} else if(messages[new Random().nextInt(messages.length)].equals("3")) {
				message = ("§6§l" + event.getPlayer().getName() + "§e est reparti !");
			} else if(messages[new Random().nextInt(messages.length)].equals("4")) {
				message = ("§eUne personne est parti, elle s'agit de §6§l" + event.getPlayer().getName() + "§e !");
			} else if(messages[new Random().nextInt(messages.length)].equals("5")) {
				message = ("§eBye bye §6§l" + event.getPlayer().getName() + "§e !");
			} else if(messages[new Random().nextInt(messages.length)].equals("6")) {
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
				players.sendMessage("§5[LOG] §d" + player.getName() + "§5 travels from §d" + event.getFrom().getName() + "§5 to §d" + player.getWorld().getName());
			}
		}
		
	}

}
