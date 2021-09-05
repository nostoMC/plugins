package fr.djredstone.nostoNC.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class OnPlayerInteractListener implements Listener {
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		if(event.getPlayer().getWorld() == Bukkit.getWorld("Nightclub")) {
			if(!event.getPlayer().hasPermission("nosto.nightclub.interact")) event.setCancelled(true);
			if(event.getClickedBlock().getType() == Material.OAK_WALL_SIGN) event.setCancelled(false);
			if(event.getClickedBlock().getType() == Material.DARK_OAK_DOOR) event.setCancelled(false);
		}
	}
	
	@EventHandler
	public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
		if(event.getPlayer().getWorld() == Bukkit.getWorld("Nightclub")) {
			if(!event.getPlayer().hasPermission("nosto.nightclub.interact")) event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
		if(event.getDamager().getWorld() == Bukkit.getWorld("Nightclub")) {
			if(!event.getDamager().hasPermission("nosto.nightclub.interact")) event.setCancelled(true);
			if(event.getEntity() instanceof ArmorStand) event.setCancelled(true);
		}
	}
	
	@EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
		if(event.getPlayer().getWorld() == Bukkit.getWorld("Nightclub")) {
			if(!event.getPlayer().hasPermission("nosto.nightclub.interact")) event.setCancelled(true);
		}
    }

}
