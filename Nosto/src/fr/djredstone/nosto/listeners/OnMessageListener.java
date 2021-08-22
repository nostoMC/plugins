package fr.djredstone.nosto.listeners;

import java.awt.Color;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import fr.djredstone.nosto.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class OnMessageListener extends ListenerAdapter implements Listener {
	
	@EventHandler
	public void onMessage(AsyncPlayerChatEvent event) {
		
		Player player = event.getPlayer();
		
		if(Main.menuPlayers.contains(player)) {
			event.setCancelled(true);
			return;
		}
		
		String group = "";
		String groupDiscord = "";
		
		if(player.hasPermission("group.dev")) {
			group = "§bDevelopper ";
			groupDiscord = "Developper ";
		}
		if(player.hasPermission("group.buildeur")) {
			group = "§aBuildeur ";
			groupDiscord = "Buildeur ";
		}
		if(player.hasPermission("group.administrateur")) {
			group = "§cAdministrateur ";
			groupDiscord = "Administrateur ";
		}
		
		String format = "§f<" + group + "§f" + player.getName() + "> " + event.getMessage();
		Bukkit.broadcastMessage("");
		event.setFormat(format);
		event.setMessage(event.getMessage().replaceAll("&", "§"));
		
		EmbedBuilder embed = new EmbedBuilder();
		embed.setAuthor(groupDiscord + "| " + player.getName(), null, "https://mc-heads.net/avatar/" + player.getName());
		embed.addField(event.getMessage(), "", false);
		embed.setColor(Color.GREEN);
		
		System.out.println("Test");
		
		if(player.getWorld() == Bukkit.getWorld("survie") || player.getWorld() == Bukkit.getWorld("survie_nether") || player.getWorld() == Bukkit.getWorld("survie_the_end")) {
			
			Main.jda.getTextChannelById("832554910301290506").sendMessage(embed.build()).queue();
			
		} else if(player.getWorld() == Bukkit.getWorld("Nightclub")) {
			
			Main.jda.getTextChannelById("877675571193200670").sendMessage(embed.build()).queue();
			
		}
	}
	
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        
		if(event.getAuthor().isBot()) {
			return;
		}
		
		String group = "";
		
		Member member = event.getGuild().getMember(event.getAuthor());
		if(event.getGuild().getMembersWithRoles(Main.jda.getRoleById("855435199676547095")).contains(member)) { // BUILDEUR
			group = "§aBuildeur";
		}
		if(event.getGuild().getMembersWithRoles(Main.jda.getRoleById("861880443561312277")).contains(member)) { // DEV
			group = "§bDéveloppeur";
		}
		if(event.getGuild().getMembersWithRoles(Main.jda.getRoleById("782248738240069652")).contains(member)) { // ADMIN
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
