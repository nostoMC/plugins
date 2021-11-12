package fr.nosto.tasks;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import fr.nosto.Main;
import fr.nosto.tasks.particles.CosmeticEffectRenderer;
import fr.nosto.tasks.particles.PlayerTrailsStats;

public class CosmeticEffectTask {

	public static final HashMap<UUID, PlayerTrailsStats> playerTrails = new HashMap<>();
	public static Map<UUID, CosmeticEffectRenderer> effectRenderers = new HashMap<>();
	private static int loop = 0;
	private static boolean inited = false;

	public static void init(Main main) {

		if (inited) {
			Bukkit.getLogger().warning("ParticleEffectTask.init() ran twice!");
			return;
		}
		inited = true;

		new BukkitRunnable() {

			@Override
			public void run() {

				loop++;

				for (CosmeticEffectRenderer renderer : effectRenderers.values()) {
					if (renderer.player.getWorld().getName().endsWith("Lobby")) {
						renderer.run(loop);
					}
				}

			}

		}.runTaskTimer(main, 0, 1);

	}
}
