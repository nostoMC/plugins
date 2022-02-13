package fr.nosto.listeners;

import fr.nosto.DiscordSetup;
import fr.nosto.Utils;
import fr.nosto.mysql.prepareStatement.mute;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class OnMessageListener extends ListenerAdapter implements Listener {

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onMessage(AsyncPlayerChatEvent event) {
		event.setCancelled(true);

		try {
			OfflinePlayer target = Bukkit.getOfflinePlayer(event.getPlayer().getName());
			ResultSet resultSet = mute.check(target);
			while (resultSet.next()) {
				if (resultSet.getTimestamp("end_date").before(new Timestamp(System.currentTimeMillis()))) {
					mute.remove(target, resultSet.getTimestamp("end_date"));
				}
			}
			if (mute.check(event.getPlayer()).next()) {
				event.getPlayer().sendMessage("\n§cVous êtes actuellement mute !");
				return;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		event.setMessage(ChatColor.translateAlternateColorCodes('&', event.getMessage()));

		Set<String> survival_worlds = Utils.getSurviesNames();
		Player player = event.getPlayer();

		// discord message
		String groupDiscord = Utils.getGroupDiscord(player);

		EmbedBuilder embed = new EmbedBuilder();
		embed.setAuthor(groupDiscord + player.getName(), null, "https://mc-heads.net/avatar/" + player.getName());
		embed.setDescription(event.getMessage());

		if (survival_worlds.contains(player.getWorld().getName())) {
			DiscordSetup.getChannelSurvie().sendMessageEmbeds(embed.build()).queue();
		} else if (player.getWorld().getName().equals("nostoclub")) {
			DiscordSetup.getChannelNightclub().sendMessageEmbeds(embed.build()).queue();
		}

		// minecraft message
		ChatColor gradeColor = Utils.getGradeColor(player);
		String format = gradeColor + "§l" + player.getName() + "§f • " + event.getMessage();

		if (survival_worlds.contains(player.getWorld().getName())) Utils.sendMessageToSurvival("\n" + format);
		else Utils.sendMessageToWorld(player.getWorld(), "\n" + format);
	}

	public void onMessageReceived(MessageReceivedEvent event) {
        
		if (event.getAuthor().isBot()) return;
		if (!event.getChannel().getId().equals("832554910301290506")
				&& !event.getChannel().getId().equals("877675571193200670")) return;

		Member member = event.getMember();
		if (member == null) return;

		List<Role> roles = member.getRoles();

		ChatColor color = ChatColor.GRAY;
		if (roles.contains(DiscordSetup.jda.getRoleById("782248738240069652"))) color = ChatColor.RED;
		else if (roles.contains(DiscordSetup.jda.getRoleById("861880443561312277"))) color = ChatColor.AQUA;
		else if (roles.contains(DiscordSetup.jda.getRoleById("855435199676547095"))) color = ChatColor.GREEN;

		String format = net.md_5.bungee.api.ChatColor.of("#5865F2") + "Discord §f| " + color + "§l" + event.getAuthor().getName() + "§f • " + event.getMessage().getContentDisplay();

		if (event.getChannel().getId().equals("832554910301290506")) Utils.sendMessageToSurvival("\n" + format);
		else Utils.sendMessageToWorld(Objects.requireNonNull(Bukkit.getWorld("nostoclub")), "\n" + format);
	}

}
