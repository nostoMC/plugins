package fr.djredstone.nosto.tasks;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.scheduler.BukkitRunnable;

import fr.djredstone.nosto.Main;
import fr.djredstone.nosto.particleEffects.BigEffectRenderer;
import fr.djredstone.nosto.particleEffects.SmallEffectRenderer;

public class ParticleEffectTask {

	public static Map<UUID, SmallEffectRenderer> smallEffects = new HashMap<UUID, SmallEffectRenderer>();
	public static Map<UUID, BigEffectRenderer> bigEffects = new HashMap<UUID, BigEffectRenderer>();
	private static int loop;
	private static boolean inited = false;

	public ParticleEffectTask(Main main) {

		if (inited) return;
		inited = true;

		new BukkitRunnable() {

			@Override
			public void run() {

				loop++;
				if (loop == Integer.MAX_VALUE || loop < 0) loop = 0;

				for (SmallEffectRenderer renderer : smallEffects.values()) {
					renderer.run();
				}
				for (BigEffectRenderer renderer : bigEffects.values()) {
					renderer.run(loop);
				}

			}

		}.runTaskTimer(main, 0, 1);

	}
}
