package fr.nostoNC.tasks;

import fr.nostoNC.Main;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.Vector;

public class StaffPass {

    private static final BoundingBox box = BoundingBox.of(new Vector(-21f, 112f, 173f), new Vector(-29f, 109f, 170f));
    private static int soundCooldown = 0;

    public StaffPass(Main main) {

        new BukkitRunnable() {

            @Override
            public void run() {

                for(Player player : Main.defaultWorld.getPlayers()) {

                    if(!player.hasPermission("nosto.nightclub.staff")) {

                        Location loc = player.getLocation();
                        if (box.contains(loc.toVector())) {
                            if (soundCooldown == 0) {
                                Main.defaultWorld.playSound(loc, Sound.ENTITY_IRON_GOLEM_HURT, 1, 1);
                                soundCooldown = 2;
                            }
                            player.setVelocity(new Vector(.8, .5, .3));
                        }

                    }

                }
                if (soundCooldown > 0) soundCooldown--;

            }
        }.runTaskTimer(main, 5, 1);

    }

}
