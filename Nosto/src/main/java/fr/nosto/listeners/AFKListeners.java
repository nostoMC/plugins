package fr.nosto.listeners;

import java.awt.*;
import java.util.*;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

import fr.nosto.DiscordSetup;
import fr.nosto.Main;
import fr.nosto.Utils;
import net.dv8tion.jda.api.EmbedBuilder;
import org.jetbrains.annotations.NotNull;

public class AFKListeners implements Listener {

	public static HashMap<UUID, Integer> time = new HashMap<>();
	public static ArrayList<UUID> afks = new ArrayList<>();

	private static boolean inited = false;

	public static void initAFKLoop(Main main) {

		if (inited) return;
		inited = true;

		Set<String> survies_names = Utils.getSurviesNames();

		new BukkitRunnable() {

			@Override
			public void run() {

				for(Player player : Bukkit.getOnlinePlayers()) {
					final UUID uuid = player.getUniqueId();

					if(survies_names.contains(player.getWorld().getName())) {
						time.put(uuid, time.get(uuid) + 1);
					}

					if(time.get(uuid) == 300 // 5 MIN
							&& !afks.contains(uuid)) {
						setAFK(player);
					}
				}

			}

		}.runTaskTimerAsynchronously(main, 20, 20);
	}

	@EventHandler
	public void onMove(PlayerMoveEvent event) {
		final Player player = event.getPlayer();
		final UUID uuid = player.getUniqueId();

		time.put(uuid, 0);
		if(afks.contains(uuid)) removeAFK(player);
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		time.put(event.getPlayer().getUniqueId(), 0);
	}

	public static void setAFK(Player player) {
		UUID uuid = player.getUniqueId();

		afks.add(uuid);

		player.setCustomName(player.getName() + " §7§l(AFK)");
		player.setCustomNameVisible(true);

		Bukkit.broadcastMessage("");
		Bukkit.broadcastMessage("§8§l" + player.getName() + " §8est AFK");

		EmbedBuilder embed = new EmbedBuilder();

		String groupDiscord = getGroupDiscord(player);

		embed.setAuthor(groupDiscord + "| " + player.getName(), null, "https://mc-heads.net/avatar/" + player.getName());
		embed.setColor(Color.GRAY);
		embed.addField("est AFK", "", false);

		DiscordSetup.getChannelSurvie().sendMessageEmbeds(embed.build()).queue();
	}

	public static void removeAFK(Player player) {
		UUID uuid = player.getUniqueId();

		afks.remove(uuid);

		player.setCustomName(player.getName());
		player.setCustomNameVisible(true);

		Bukkit.broadcastMessage("");
		Bukkit.broadcastMessage("§7§l" + player.getName() + " §7n'est plus AFK");

		EmbedBuilder embed = new EmbedBuilder();

		String groupDiscord = getGroupDiscord(player);

		embed.setAuthor(groupDiscord + "| " + player.getName(), null, "https://mc-heads.net/avatar/" + player.getName());
		embed.setColor(Color.LIGHT_GRAY);
		embed.addField("n'est plus AFK", "", false);

		DiscordSetup.getChannelSurvie().sendMessageEmbeds(embed.build()).queue();
	}

	@NotNull
	private static String getGroupDiscord(Player player) {
		String groupDiscord = "";
		if (player.hasPermission("group.dev")) groupDiscord = "Developpeur ";
		if (player.hasPermission("group.buildeur")) groupDiscord = "Buildeur ";
		if (player.hasPermission("group.administrateur")) groupDiscord = "Administrateur ";
		return groupDiscord;
	}

}
