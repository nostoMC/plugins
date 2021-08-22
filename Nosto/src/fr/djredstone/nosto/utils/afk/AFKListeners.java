package fr.djredstone.nosto.utils.afk;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

import fr.djredstone.nosto.Main;
import net.dv8tion.jda.api.EmbedBuilder;

public class AFKListeners implements Listener {
	
	static HashMap<Player, Integer> time = new HashMap<Player, Integer>();
	static ArrayList<Player> afks = CommandAFK.getAFKS();
	
	public AFKListeners(Main main) {
		main.getServer().getPluginManager().registerEvents(this, main);
	}
	
	public static void onAFKLoop(Main main) {
		
		for(Player players : Bukkit.getOnlinePlayers()) {
			time.put(players, 0);
		}
		
		new BukkitRunnable() {

			@Override
			public void run() {
				
				for(Player players : Bukkit.getOnlinePlayers()) {
					if(players.getWorld() != Bukkit.getWorld("world")) {
						time.put(players, time.get(players) + 1);
					}
					if(time.get(players) == 60) {
						afks.add(players);
						players.setCustomName(players.getName() + " §7§l(AFK)");
						players.setCustomNameVisible(true);
						Bukkit.broadcastMessage("");
						Bukkit.broadcastMessage("§8§l" + players.getName() + " §8est AFK");
						EmbedBuilder embed = new EmbedBuilder();
						String groupDiscord = "";
						if(players.hasPermission("group.dev")) {
							groupDiscord = "Developper ";
						}
						if(players.hasPermission("group.buildeur")) {
							groupDiscord = "Buildeur ";
						}
						if(players.hasPermission("group.administrateur")) {
							groupDiscord = "Administrateur ";
						}
						embed.setAuthor(groupDiscord + "| " + players.getName(), null, "https://mc-heads.net/avatar/" + players.getName());
						embed.setColor(Color.GRAY);
						embed.addField("est AFK", "", false);
						Main.jda.getTextChannelById("832554910301290506").sendMessage(embed.build()).queue();
					}
				}
				
			}
			
		}.runTaskTimer(main, 0, 20);
	}

	@EventHandler
	public void onMove(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		EmbedBuilder embed = new EmbedBuilder();
		String groupDiscord = "";
		if(player.hasPermission("group.dev")) {
			groupDiscord = "Developper ";
		}
		if(player.hasPermission("group.buildeur")) {
			groupDiscord = "Buildeur ";
		}
		if(player.hasPermission("group.administrateur")) {
			groupDiscord = "Administrateur ";
		}
		embed.setAuthor(groupDiscord + "| " + player.getName(), null, "https://mc-heads.net/avatar/" + player.getName());
		time.put(player, 0);
		if(afks.contains(player)) {
			afks.remove(player);
			player.setCustomName(player.getName());
			player.setCustomNameVisible(true);
			Bukkit.broadcastMessage("");
			Bukkit.broadcastMessage("§7§l" + player.getName() + " §7n'est plus AFK");
			player.setCustomName(player.getName());
			embed.setColor(Color.LIGHT_GRAY);
			embed.addField("n'est plus AFK", "", false);
			Main.jda.getTextChannelById("832554910301290506").sendMessage(embed.build()).queue();
		}
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		
		Player player = event.getPlayer();
		time.put(player, 0);
		
	}
	
}
