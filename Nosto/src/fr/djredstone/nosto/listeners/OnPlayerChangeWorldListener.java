package fr.djredstone.nosto.listeners;

import java.awt.Color;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerChangedWorldEvent;

import fr.djredstone.nosto.Main;
import net.dv8tion.jda.api.EmbedBuilder;

public class OnPlayerChangeWorldListener {
	
	@EventHandler
	public void onWorldChange(PlayerChangedWorldEvent event){

		Player player = event.getPlayer();
			
		if((event.getFrom() != Bukkit.getWorld("survie_nether") || event.getFrom() != Bukkit.getWorld("survie_the_end") || event.getFrom() != Bukkit.getWorld("survie")) && (player.getWorld() == Bukkit.getWorld("survie")) || player.getWorld() == Bukkit.getWorld("survie_nether") || player.getWorld() == Bukkit.getWorld("survie_the_end")) {
			
			for(Player players : Bukkit.getOnlinePlayers()) {
				if(players.getWorld() == Bukkit.getWorld("survie") || players.getWorld() == Bukkit.getWorld("survie_nether") || players.getWorld() == Bukkit.getWorld("survie_the_end")) {
					String[] messages = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
					String message = "";
					players.sendMessage("");
					if(messages[new Random().nextInt(messages.length)] == "1") {
						message = "§eSalut §6§l" + player.getName() + "§e ! Bienvenue sur le survival !";
					} else if(messages[new Random().nextInt(messages.length)] == "2") {
						message = "§eBon retour parmi nous §6§l" + player.getName() + "§e !";
					} else if(messages[new Random().nextInt(messages.length)] == "3") {
						message = "§6§l" + player.getName() + "§e est de retour !";
					} else if(messages[new Random().nextInt(messages.length)] == "4") {
						message = "§6§l" + player.getName() + "§e nous a rejoint !";
					} else if(messages[new Random().nextInt(messages.length)] == "5") {
						message = "§eOh bas ! Qui vois là je ? C'est §6§l" + player.getName() + "�e !";
					} else if(messages[new Random().nextInt(messages.length)] == "6") {
						message = "§6§l" + player.getName() + "§e est là ! J'AIME BIEN !";
					} else if(messages[new Random().nextInt(messages.length)] == "7") {
						message = "§eOh.. It's you §6§l" + player.getName() + "§e .. It's been a looong time.. How have you been ?";
					} else if(messages[new Random().nextInt(messages.length)] == "8") {
						message = "§eAh ! Un nouveau joueur de connecter ! Et il s'agit de... §6§l" + player.getName() + "§e !";
					} else if(messages[new Random().nextInt(messages.length)] == "9") {
						message = "§eHello §6§l" + player.getName() + "§e ! Bon cube sur le survival !";
					}
					players.sendMessage(message);
					
					EmbedBuilder embed = new EmbedBuilder();
					embed.setTitle("[+] " + player.getName());
					embed.setImage("https://mc-heads.net/avatar/" + player.getName());
					embed.setColor(Color.GREEN);
					
					Main.jda.getTextChannelById("832554910301290506").sendMessage(embed.build()).queue();
					
				}
			}
			
		}
		if(event.getFrom() == Bukkit.getWorld("survie")) {
			
			if(player.getWorld() == Bukkit.getWorld("survie") || player.getWorld() == Bukkit.getWorld("survie_nether") || player.getWorld() == Bukkit.getWorld("survie_the_end")) {
				return;
			}
			
			for(Player players : Bukkit.getOnlinePlayers()) {
				if(players.getWorld() == Bukkit.getWorld("survie") || players.getWorld() == Bukkit.getWorld("survie_nether") || players.getWorld() == Bukkit.getWorld("survie_the_end")) {
					String[] messages = {"1", "2", "3", "4", "5", "6"};
					players.sendMessage("");
					if(messages[new Random().nextInt(messages.length)] == "1") {
						players.sendMessage("§6§l" + event.getPlayer().getName() + "§e est parti...");
					} else if(messages[new Random().nextInt(messages.length)] == "2") {
						players.sendMessage("§6§l" + event.getPlayer().getName() + "§e fait une pose.");
					} else if(messages[new Random().nextInt(messages.length)] == "3") {
						players.sendMessage("§6§l" + event.getPlayer().getName() + "§e est reparti !");
					} else if(messages[new Random().nextInt(messages.length)] == "4") {
						players.sendMessage("§eUne personne est parti, elle s'agit de §6§l" + event.getPlayer().getName() + "§e !");
					} else if(messages[new Random().nextInt(messages.length)] == "5") {
						players.sendMessage("§eBye bye §6§l" + event.getPlayer().getName() + "§e !");
					} else if(messages[new Random().nextInt(messages.length)] == "6") {
						players.sendMessage("§6§l" + event.getPlayer().getName() + "§e retourne au monde réél !");
					}
					
					EmbedBuilder embed = new EmbedBuilder();
					embed.setTitle("[-] " + player.getName());
					embed.setImage("https://mc-heads.net/avatar/" + player.getName());
					embed.setColor(Color.RED);
					
					Main.jda.getTextChannelById("832554910301290506").sendMessage(embed.build()).queue();
					
				}
			}
			
		}
		
	}

}
