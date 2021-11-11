package fr.nosto.tasks.particles.effects;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Player;

import fr.nosto.tasks.particles.CosmeticEffectRenderer;

public class FireCrown extends CosmeticEffectRenderer {

    private int phase = 0;

    public FireCrown(Player player) {
        super(player);
    }

    @Override
    public void render(int loop) {
        loop %= 3;

        if (loop == 0) {

            phase++;
            if (phase >= 8) phase = 0;
            
            double angle = Math.PI * 2 * (phase / 8f);

            double xOffset = Math.cos(angle) / 3f;
            double zOffset = Math.sin(angle) / 3f;

            Location loc = player.getLocation();
            loc.add(0, 2.2, 0);
            World world = player.getWorld();

            world.spawnParticle(Particle.FLAME,
                    loc.getX() + xOffset,  //posX
                    loc.getY(),               //posY
                    loc.getZ() + zOffset, //posZ
                    1, //count
                    0, 0, 0, //particle spread
                    0); //speed
        }
    }

}
