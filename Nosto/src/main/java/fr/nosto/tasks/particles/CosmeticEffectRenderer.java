package fr.nosto.tasks.particles;

import org.bukkit.entity.Player;

public abstract class CosmeticEffectRenderer {

	public Player player;
	private int cooldown;

	public CosmeticEffectRenderer(Player player) {
		this.player = player;
	}

	public void run(int loop) {
		if (cooldown <= 0) {
			this.render(loop);
		} else {
			cooldown -= 1;
		}

	}

	public abstract void render(int loop);

	public void startCoolDown() {
		cooldown = 40;
	}
}
