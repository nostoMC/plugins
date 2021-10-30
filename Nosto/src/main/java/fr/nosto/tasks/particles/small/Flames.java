package fr.nosto.tasks.particles.small;

import org.bukkit.Particle;
import org.bukkit.entity.Player;

import fr.nosto.tasks.particles.SmallEffectRenderer;

public class Flames extends SmallEffectRenderer {
    
    public Flames(Player player) {
        super(player);
    }

    @Override
    public void render(int loop) {
        loop %= 10;
        if (loop == 0) player.getWorld().spawnParticle(Particle.FLAME, player.getLocation().add(0, 1, 0), 1, .2, .5, 0.2, 0.02);
    }
}
