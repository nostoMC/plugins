package fr.nosto.listeners;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import fr.nosto.DiscordSetup;
import fr.nosto.Utils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class OnMessageListener extends ListenerAdapter implements Listener {
	
	@EventHandler
	public void onMessage(AsyncPlayerChatEvent event) {
		event.setCancelled(true);

		event.setMessage(ChatColor.translateAlternateColorCodes('&', event.getMessage()));

		Set<String> survival_worlds = Utils.getSurviesNames();
		Player player = event.getPlayer();

		// discord message
		String groupDiscord = Utils.getGroupDiscord(player);

		EmbedBuilder embed = new EmbedBuilder();
		embed.setAuthor(groupDiscord + player.getName(), null, "https://mc-heads.net/avatar/" + player.getName());
		embed.setDescription(event.getMessage());

		if(survival_worlds.contains(player.getWorld().getName())) {
			DiscordSetup.getChannelSurvie().sendMessageEmbeds(embed.build()).queue();
		} else if(player.getWorld().getName().equals("Nightclub")) {
			DiscordSetup.getChannelNightclub().sendMessageEmbeds(embed.build()).queue();
		}

		// minecraft message
		ChatColor gradeColor = Utils.getGradeColor(player);
		String format = gradeColor + "§l" + player.getName() + "§f • " + event.getMessage();

		if (survival_worlds.contains(player.getWorld().getName())) Utils.sendMessageToSurvival("\n" + format);
		else Utils.sendMessageToWorld(player.getWorld(), "\n" + format);
	}
	
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        
		if(event.getAuthor().isBot()) return;
		if(!event.getChannel().getId().equals("832554910301290506")
				&& !event.getChannel().getId().equals("877675571193200670")) return;

		Member member = event.getMember();
		if (member == null) return;

		List<Role> roles = member.getRoles();

		ChatColor color = ChatColor.GRAY;
		if(roles.contains(DiscordSetup.jda.getRoleById("782248738240069652"))) color = ChatColor.RED;
		else if(roles.contains(DiscordSetup.jda.getRoleById("861880443561312277"))) color = ChatColor.AQUA;
		else if(roles.contains(DiscordSetup.jda.getRoleById("855435199676547095"))) color = ChatColor.GREEN;

		String format = net.md_5.bungee.api.ChatColor.of("#5865F2") + "Discord §f| " + color + "§l" + event.getAuthor().getName() + "§f • " + event.getMessage().getContentDisplay();

		if (event.getChannel().getId().equals("832554910301290506")) Utils.sendMessageToSurvival("\n" + format);
		else Utils.sendMessageToWorld(Objects.requireNonNull(Bukkit.getWorld("Nightclub")), "\n" + format);
	}

}
