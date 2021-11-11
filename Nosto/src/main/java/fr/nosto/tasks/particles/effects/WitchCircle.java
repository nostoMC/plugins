package fr.nosto.tasks.particles.effects;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Player;

import fr.nosto.tasks.particles.CosmeticEffectRenderer;

public class WitchCircle extends CosmeticEffectRenderer {

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

		world.spawnParticle(Particle.SPELL_WITCH,
				loc.getX() + xOffset,  //posX
				loc.getY(),               //posY
				loc.getZ() + zOffset, //posZ
				1, //count
				0, 0, 0, //particle spread
				0); //speed

		world.spawnParticle(Particle.SPELL_WITCH,
				loc.getX() + xOffset * -1,  //posX
				loc.getY(),                    //posY
				loc.getZ() + zOffset * -1, //posZ
				1, //count
				0, 0, 0, //particle spread
				0); //speed
	}

}
