package fr.nosto.tasks.particles;

import org.bukkit.entity.Player;

public abstract class SmallEffectRenderer {
	
	public Player player;
	
	public SmallEffectRenderer(Player player) {
		this.player = player;
	}

	public void run(int loop) {
		this.render(loop);
	}

	public abstract void render(int loop);

}
