package fr.nosto.listeners;

import fr.nosto.menus.mainmenu.TpMenu;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class OnInteractListener implements Listener {

	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		
		Player player = event.getPlayer();
        
        if (player.getWorld().getName().endsWith("Lobby")) {

			if (player.getOpenInventory().getTitle().equals("Crafting")
					&& event.getMaterial() == Material.COMPASS) {
				TpMenu.openMenu(player, false);
			}

			if (denyInteract(player)) event.setCancelled(true);

			Block block = event.getClickedBlock();
			if (block != null) {
				if (block.getType() == Material.OAK_WALL_SIGN || block.getType() == Material.DARK_OAK_DOOR) event.setCancelled(false);
			}
        }
	}
	
	@EventHandler
	public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
		if (event.getPlayer().getWorld().getName().endsWith("Lobby")) {
			if (denyInteract(event.getPlayer())) event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
		if (event.getDamager().getWorld().getName().endsWith("Lobby")) {
			if (denyInteract((Player) event.getDamager())) event.setCancelled(true);
			if (event.getEntity() instanceof ArmorStand) event.setCancelled(true);
		}
	}
	
	@EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
		if (event.getPlayer().getWorld().getName().endsWith("Lobby")) {
			if (denyInteract(event.getPlayer())) event.setCancelled(true);
		}
    }
	
	@EventHandler
	public void onArmorStandInteract(PlayerArmorStandManipulateEvent event) {
		if (event.getPlayer().getWorld().getName().endsWith("Lobby")) {
			if (denyInteract(event.getPlayer())) event.setCancelled(true);
		}
	}

	private boolean denyInteract(Player player) {
		return player.getGameMode() != GameMode.CREATIVE || !player.hasPermission("*");
	}

}
