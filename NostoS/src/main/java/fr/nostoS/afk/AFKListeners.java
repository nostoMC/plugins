package fr.nostoS.afk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import fr.nostoS.Main;
import fr.nostoS.Utils;

public class AFKListeners implements Listener {

	public static HashMap<UUID, Integer> time = new HashMap<>();
	public static ArrayList<UUID> afks = new ArrayList<>();

	private static boolean inited = false;

	public static void initAFKLoop(Main main) {

		if (inited) return;
		inited = true;

		for (Player player : Bukkit.getOnlinePlayers()) {
			time.put(player.getUniqueId(), 0);
		}

		new BukkitRunnable() {

			@Override
			public void run() {
				for (World world : Utils.getSurviesWorlds()) {

					for(Player player : world.getPlayers()) {
						final UUID uuid = player.getUniqueId();

						time.put(uuid, time.get(uuid) + 1);

						if(time.get(uuid) == 300 // 5 MIN
								&& !afks.contains(uuid)) {
							setAFK(player);
						}
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

	@EventHandler
	public void onLeave(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		UUID uuid = player.getUniqueId();

		if (afks.contains(uuid)) {
			afks.remove(uuid);

			player.setCustomName(player.getName());
			player.setCustomNameVisible(true);
		}
	}

	public static void setAFK(Player player) {
		UUID uuid = player.getUniqueId();

		afks.add(uuid);

		player.setCustomName(player.getName() + " §7§l(AFK)");
		player.setCustomNameVisible(true);

		Utils.sendMessageToSurvival("\n§8§l" + player.getName() + " §8est AFK");
	}

	public static void removeAFK(Player player) {
		UUID uuid = player.getUniqueId();

		afks.remove(uuid);

		player.setCustomName(player.getName());
		player.setCustomNameVisible(true);

		Utils.sendMessageToSurvival("\n§7§l" + player.getName() + " §7n'est plus AFK");
	}

}
