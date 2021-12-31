package fr.nostoNC.listeners;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import fr.nostoNC.Utils;

public class DamageListener implements Listener {

    @EventHandler
    public void onDamageBySuffocation(EntityDamageEvent event) {
        if (Utils.isInClub(event.getEntity()) && event.getCause() == EntityDamageEvent.DamageCause.SUFFOCATION) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onDamageByEntity(EntityDamageByEntityEvent event) {
        if (Utils.isInClub(event.getEntity())) {

            EntityType damagerType = event.getDamager().getType();
            if (damagerType == EntityType.FIREWORK || damagerType == EntityType.PLAYER) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        if (Utils.isInClub(event.getEntity())) {
            event.setCancelled(true);
        }
    }
}
