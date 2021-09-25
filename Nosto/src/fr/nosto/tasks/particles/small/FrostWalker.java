package fr.nosto.tasks.particles.small;

import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

import fr.nosto.tasks.particles.SmallEffectRenderer;

public class FrostWalker extends SmallEffectRenderer {

	public FrostWalker(Player player) {
		super(player);
	}

	@Override
	public void render() {
		player.getWorld().spawnParticle(Particle.REDSTONE, player.getLocation(), 1, .2, 0, 0.2,
				new Particle.DustOptions(Color.fromRGB(178, 217, 255), 1));
	}

}
