package fr.nosto.tasks.particles.small;

import org.bukkit.Particle;
import org.bukkit.entity.Player;

import fr.nosto.tasks.particles.SmallEffectRenderer;

public class Fireworks extends SmallEffectRenderer {

    public Fireworks(Player player) {
        super(player);
    }

    @Override
    public void render(int loop) {
        loop %= 10;
        if (loop == 0) player.getWorld().spawnParticle(Particle.FIREWORKS_SPARK, player.getLocation().add(0, 1.5, 0), 1, .2, .5, 0.2, 0);
    }
}
