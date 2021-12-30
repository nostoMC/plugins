package fr.nostoNC.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import fr.nostoNC.Utils;

public class InteractionListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if(Utils.isInClub(player)) {

            if(!player.hasPermission("nosto.nightclub.interact")) event.setCancelled(true);

            event.setUseItemInHand(Event.Result.DEFAULT);

            Block block = event.getClickedBlock();
            if (block != null) {
                Material type = block.getType();

                if (type == Material.OAK_WALL_SIGN || type == Material.SPRUCE_DOOR
                        || type == Material.STONE_BUTTON || type == Material.POLISHED_BLACKSTONE_BUTTON
                        || type == Material.POLISHED_BLACKSTONE_PRESSURE_PLATE) {
                    event.setCancelled(false);
                }
                if (player.hasPermission("nosto.nightclub.staff") && type == Material.DARK_OAK_DOOR) {
                    event.setCancelled(false);
                }
            }
        }
    }

    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        if (Utils.isInClub(player) && !player.hasPermission("nosto.nightclub.interact")) event.setCancelled(true);
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player player)) return;

        if (Utils.isInClub(player)) {
            if (!player.hasPermission("nosto.nightclub.interact")) event.setCancelled(true);
            else if (event.getEntity() instanceof ArmorStand) event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (Utils.isInClub(player) && !player.hasPermission("nosto.nightclub.interact")) event.setCancelled(true);
    }

    @EventHandler
    public void onArmorStandManipulate(PlayerArmorStandManipulateEvent event) {
        Player player = event.getPlayer();
        if (Utils.isInClub(player) && !player.hasPermission("nosto.nightclub.interact")) event.setCancelled(true);
    }

}
