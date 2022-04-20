package fr.nosto.listeners;

import fr.nosto.discord.DiscordSetup;
import fr.nosto.Main;
import fr.nosto.Utils;
import fr.nosto.menus.SanctionMenu;
import fr.nosto.mysql.prepareStatement.mute;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.internal.utils.tuple.Pair;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scheduler.BukkitRunnable;

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

		Player player = event.getPlayer();

		try {

			if (SanctionMenu.getAskData().containsKey(player) && SanctionMenu.getData().containsKey(player)) {

				final Pair<String, Boolean> askInfo = SanctionMenu.getAskData().get(player);
				final Pair<OfflinePlayer, String> info = SanctionMenu.getData().get(player);
				if (askInfo.getLeft().equalsIgnoreCase("mute")) {
					if (!askInfo.getRight()) {
						SanctionMenu.getData().put(player, Pair.of(info.getLeft(), event.getMessage()));
						SanctionMenu.getAskData().put(player, Pair.of("mute", true));
						player.sendMessage("\n§bEntrez le temps de mute");
					} else {
						OfflinePlayer target = info.getLeft();
						Timestamp timestamp = stringToTimestamp(event.getMessage());
						mute.add(target, player, info.getRight(), timestamp);
						Bukkit.broadcastMessage("\n§4" + target.getName() +
								"§c a été mute par §4" + player.getName() +
								"§c jusqu'au §4" + timestamp +
								"§c pour la raison suivante :\n§4" + info.getRight());
						SanctionMenu.getAskData().remove(player);
						SanctionMenu.getData().remove(player);
					}
					return;
				} else if (askInfo.getLeft().equalsIgnoreCase("kick")) {
					Player target = info.getLeft().getPlayer();
					assert target != null;
					new BukkitRunnable() {
						@Override
						public void run() {
							target.kickPlayer("\n§cVous avais été kick par §4" + player.getName() +
									"§c pour la raison suivante :" +
									"\n§4" + event.getMessage());
						}
					}.runTask(Main.getInstance());
					Bukkit.broadcastMessage("\n§4" + target.getName() + "§c a été kick par §4" + player.getName() +
							"§c pour la raison suivante :" +
							"\n§4" + event.getMessage());
					SanctionMenu.getAskData().remove(player);
					SanctionMenu.getData().remove(player);
					return;
				} else if (askInfo.getLeft().equalsIgnoreCase("ban")) {
					if (!askInfo.getRight()) {
						SanctionMenu.getData().put(player, Pair.of(info.getLeft(), event.getMessage()));
						SanctionMenu.getAskData().put(player, Pair.of("ban", true));
						player.sendMessage("\n§bEntrez le temps de ban");
					} else {
						OfflinePlayer target = info.getLeft();
						Timestamp timestamp = stringToTimestamp(event.getMessage());
						target.banPlayer(info.getRight(), timestamp, player.getName(), false);
						if (target.isOnline()) {
							new BukkitRunnable() {
								@Override
								public void run() {
									Objects.requireNonNull(target.getPlayer()).kickPlayer("\n§cVous avais été banni par §4" + player.getName() +
											"§c jusqu'au §4" + timestamp +
											"§c pour la raison suivante :" +
											"\n§4" + info.getRight());
								}
							}.runTask(Main.getInstance());
						}
						Bukkit.broadcastMessage("\n§4" + target.getName() +
								"§c a été banni par §4" + player.getName() +
								"§c jusqu'au §4" + timestamp +
								"§c pour la raison suivante :" +
								"\n§4" + info.getRight());
						SanctionMenu.getAskData().remove(player);
						SanctionMenu.getData().remove(player);
					}
					return;
				}

			}

			OfflinePlayer target = Bukkit.getOfflinePlayer(player.getName());
			ResultSet resultSet = mute.check(target);
			while (resultSet.next()) {
				if (resultSet.getTimestamp("end_date").before(new Timestamp(System.currentTimeMillis()))) {
					mute.remove(target, resultSet.getTimestamp("end_date"));
				}
			}
			if (mute.check(player).next()) {
				player.sendMessage("\n§cVous êtes actuellement mute !");
				return;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		event.setMessage(ChatColor.translateAlternateColorCodes('&', event.getMessage()));

		Set<String> survival_worlds = Utils.getSurviesNames();

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

	private static Timestamp stringToTimestamp(String message) {
		int i = 0;
		for (String arg : message.toLowerCase().split(" ")) {
			if (arg.endsWith("s")) {
				i += tryAddTime(arg, 1);
			} else if (arg.endsWith("m")) {
				i += tryAddTime(arg, 60);
			} else if (arg.endsWith("h")) {
				i += tryAddTime(arg, 3600);
			} else if (arg.endsWith("j")) {
				i += tryAddTime(arg, 3600*24);
			} else if (arg.endsWith("w")) {
				i += tryAddTime(arg, 3600*24*7);
			} else if (arg.endsWith("a")) {
				i += tryAddTime(arg, 3600*24*365);
			}
		}
		return new Timestamp(System.currentTimeMillis() + i);
	}

	private static int tryAddTime(String arg, int time) {
		int result = 0;
		try {
			StringBuilder number = new StringBuilder(arg);
			number.deleteCharAt(arg.length()-1);
			int i = Integer.parseInt(number.toString());
			result += i * time * 1000;
		} catch (NumberFormatException ignored) {
		}
		return result;
	}
}
