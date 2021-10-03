package fr.nosto.listeners;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import fr.nosto.DiscordSetup;
import fr.nosto.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class OnMessageListener extends ListenerAdapter implements Listener {
	
	@EventHandler
	public void onMessage(AsyncPlayerChatEvent event) {
		
		event.setCancelled(true);
		
		event.setMessage(ChatColor.translateAlternateColorCodes('&', event.getMessage()));
		
		Player player = event.getPlayer();
		
		if(Main.menuPlayers.contains(player)) {
			return;
		}
		
		String group = "§7";
		String groupDiscord = "";
		
		if(player.hasPermission("group.dev")) {
			group = "§b";
			groupDiscord = "Developper ";
		}
		if(player.hasPermission("group.buildeur")) {
			group = "§a";
			groupDiscord = "Buildeur ";
		}
		if(player.hasPermission("group.administrateur")) {
			group = "§c";
			groupDiscord = "Administrateur ";
		}
		
		String format = group + "§l" + player.getName() + "§f • " + event.getMessage();
		event.setFormat("");
		event.setMessage(event.getMessage().replaceAll("&", "§"));
		
		EmbedBuilder embed = new EmbedBuilder();
		embed.setAuthor(groupDiscord + "| " + player.getName(), null, "https://mc-heads.net/avatar/" + player.getName());
		embed.setDescription(event.getMessage());
		
		Set<String> survival_worlds = new HashSet<String>();
		survival_worlds.add("survie");
		survival_worlds.add("survie_nether");
		survival_worlds.add("survie_the_end");
		
		if(player.getWorld() == Bukkit.getWorld("survie") || player.getWorld() == Bukkit.getWorld("survie_nether") || player.getWorld() == Bukkit.getWorld("survie_the_end")) {
			
			DiscordSetup.jda.getTextChannelById("832554910301290506").sendMessage(embed.build()).queue();
			
		} else if(player.getWorld() == Bukkit.getWorld("Nightclub")) {
			
			DiscordSetup.jda.getTextChannelById("877675571193200670").sendMessage(embed.build()).queue();
			
		}
		
		for(Player players : Bukkit.getOnlinePlayers()) {
			
			if(survival_worlds.contains(player.getWorld().getName())) {
				
				if(survival_worlds.contains(players.getWorld().getName())) {
					players.sendMessage("");
					players.sendMessage(format);
				}
				
			} else if(player.getWorld() == Bukkit.getWorld("Nightclub")) {
				
				if(players.getWorld() == Bukkit.getWorld("Nightclub")) {
					players.sendMessage("");
					players.sendMessage(format);
				}
				
			} else if(player.getWorld() == Bukkit.getWorld("MainLobby")) {
				
				if(players.getWorld() == Bukkit.getWorld("MainLobby")) {
					players.sendMessage("");
					players.sendMessage(format);
				}
				
			}
			
		}
		
	}
	
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        
		if(event.getAuthor().isBot()) {
			return;
		}
		
		String group = "";
		
		Member member = event.getGuild().getMember(event.getAuthor());
		if(event.getGuild().getMembersWithRoles(DiscordSetup.jda.getRoleById("855435199676547095")).contains(member)) { // BUILDEUR
			group = "§aBuildeur";
		}
		if(event.getGuild().getMembersWithRoles(DiscordSetup.jda.getRoleById("861880443561312277")).contains(member)) { // DEV
			group = "§bDéveloppeur";
		}
		if(event.getGuild().getMembersWithRoles(DiscordSetup.jda.getRoleById("782248738240069652")).contains(member)) { // ADMIN
			group = "§cAdministrateur";
		}
		
        String format = "§f[§5Discord§f] <" + group + " §f" + event.getAuthor().getName() + "> " + event.getMessage().getContentDisplay();
		
		if(!event.getChannel().getId().equalsIgnoreCase("832554910301290506")) {
			return;
		}
        
        if(!Bukkit.getOnlinePlayers().isEmpty()) {
        	for(Player player : Bukkit.getOnlinePlayers()) {
        		
        		if(event.getChannel().getId().equalsIgnoreCase("832554910301290506")) {
        			
        			if(player.getWorld() == Bukkit.getWorld("survie") || player.getWorld() == Bukkit.getWorld("survie_nether") || player.getWorld() == Bukkit.getWorld("survie_the_end")) {
        				player.sendMessage("");
        				player.sendMessage(format);
        			}
        			
        		} else if(event.getChannel().getId().equalsIgnoreCase("877675571193200670")) {
        			
        			if(player.getWorld() == Bukkit.getWorld("Nightclub")) {
        				player.sendMessage("");
        				player.sendMessage(format);
        			}
        			
        		}
        		
    			
    		}
        }
        
	}

}
