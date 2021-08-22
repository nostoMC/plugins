package fr.djredstone.nosto.particleEffects;

import org.bukkit.entity.Player;

public abstract class SmallEffectRenderer {
	
	public Player player;
	
	public SmallEffectRenderer(Player player) {
		this.player = player;
	}

	public void run() {
		this.render();
	}

	public abstract void render();

}
