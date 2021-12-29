package fr.nostoNC.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import fr.nostoNC.Main;

public class DamageListener implements Listener {

    @EventHandler
    public void onDamageBySuffocation(EntityDamageEvent event) {
        if (event.getEntity().getWorld().getName().equals(Main.defaultWorld.getName())
                && event.getCause() == EntityDamageEvent.DamageCause.SUFFOCATION) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        if (event.getEntity().getWorld().getName().equals(Main.defaultWorld.getName())) {
            event.setCancelled(true);
        }
    }
}
