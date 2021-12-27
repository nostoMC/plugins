package fr.nostoNC.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import fr.nostoNC.Main;

public class DamageListener implements Listener {

    @EventHandler
    public void onDamageByBlock(EntityDamageEvent event) {
        if (event.getEntity().getWorld().getName().equals(Main.defaultWorld.getName())) {
            event.setCancelled(true);
        }
    }
}
