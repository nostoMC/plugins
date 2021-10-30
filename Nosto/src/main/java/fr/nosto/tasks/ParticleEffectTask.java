package fr.nosto.tasks;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.scheduler.BukkitRunnable;

import fr.nosto.Main;
import fr.nosto.tasks.particles.BigEffectRenderer;
import fr.nosto.tasks.particles.SmallEffectRenderer;

public class ParticleEffectTask {

	public static Map<UUID, SmallEffectRenderer> smallEffects = new HashMap<>();
	public static Map<UUID, BigEffectRenderer> bigEffects = new HashMap<>();
	private static int loop = 0;
	private static boolean inited = false;

	public ParticleEffectTask(Main main) {

		if (inited) return;
		inited = true;

		new BukkitRunnable() {

			@Override
			public void run() {

				loop++;

				for (SmallEffectRenderer renderer : smallEffects.values()) {
					renderer.run(loop);
				}
				for (BigEffectRenderer renderer : bigEffects.values()) {
					renderer.run(loop);
				}

			}

		}.runTaskTimer(main, 0, 1);

	}
}
