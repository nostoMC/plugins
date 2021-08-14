package fr.djredstone.nosto.particleEffects.bigEffects;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Player;

import fr.djredstone.nosto.particleEffects.BigEffectRenderer;

public class WitchCircle extends BigEffectRenderer {

	public WitchCircle(Player player) {
		super(player);
	}

	@Override
	public void render(int loop) {
		loop %= 100;
		
		double angle = Math.PI * 2 * (loop / 100.0);
		
		double xOffset = Math.cos(angle);
		double zOffset = Math.sin(angle);

		Location loc = player.getLocation();
		World world = player.getWorld();

		world.spawnParticle(Particle.SPELL_WITCH, loc.getX() + xOffset, loc.getY(), loc.getZ() + zOffset, 1, 0, 0, 0,
				0);
		world.spawnParticle(Particle.SPELL_WITCH, loc.getX() + xOffset * -1, loc.getY(), loc.getZ() + zOffset * -1, 1,
				0, 0, 0, 0);
	}

}
