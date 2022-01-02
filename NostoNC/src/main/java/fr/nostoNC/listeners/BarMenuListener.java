package fr.nostoNC.listeners;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import fr.nostoNC.Utils;
import fr.nostoNC.menus.BarMenu;

public class BarMenuListener implements Listener {

    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        if (!Utils.isInClub(player)) return;

        Entity entity = event.getRightClicked();
        if (entity.getType() != EntityType.WANDERING_TRADER) return;

        event.setCancelled(true);

        BarMenu.openMenu(player);
    }
    
}
