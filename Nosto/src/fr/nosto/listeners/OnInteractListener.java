package fr.nosto.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import fr.nosto.Main;
import fr.nosto.menus.TpMenu;

public class OnInteractListener implements Listener {

	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		
		Player player = event.getPlayer();
		
		if(player.getWorld() == Bukkit.getWorld("survie")) {
			
			if(event.getClickedBlock() != null) {
				
				Chunk chunk = event.getClickedBlock().getChunk();
	        	String chunkID = chunk.getX() + "_" + chunk.getZ();
	        	
	        	if(Main.getInstance().getConfig().contains("claim." + chunkID)) {
	        
	        		if (!Main.getInstance().getConfig().getString("claim." + chunkID).equalsIgnoreCase(player.getUniqueId().toString()))
	        		{
	            		if (!player.isOp())
	            		{
	                		event.setCancelled(true);
	                		player.sendMessage("");
	                		player.sendMessage("§cCe chunk a été claim. Tu ne peut donc rien faire dans cette zone.");
	            		}
	        		}
	        	}
				
			}
		}
        
        if(player.getWorld().getName().endsWith("Lobby")) {
        
        	if(player.getInventory().getItemInMainHand().getItemMeta().getDisplayName() != null && player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equalsIgnoreCase("§b§lClick pour ouvrire le menu de téléportation")) {
        		TpMenu.openMenu(player);
        	}
        	
        	if(event.getPlayer().getWorld().getName().endsWith("Lobby")) {
        		if(!event.getPlayer().hasPermission("nosto.lobby.interact")) event.setCancelled(true);
        		if(event.getClickedBlock().getType() != null) {
        			if(event.getClickedBlock().getType() == Material.OAK_WALL_SIGN) event.setCancelled(false);
            		if(event.getClickedBlock().getType() == Material.DARK_OAK_DOOR) event.setCancelled(false);
        		}
        		
        	}
        		
        }
	}
	
	@EventHandler
	public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
		if(event.getPlayer().getWorld().getName().endsWith("Lobby")) {
			if(!event.getPlayer().hasPermission("nosto.lobby.interact")) event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
		if(event.getDamager().getWorld().getName().endsWith("Lobby")) {
			if(!event.getDamager().hasPermission("nosto.lobby.interact")) event.setCancelled(true);
			if(event.getEntity() instanceof ArmorStand) event.setCancelled(true);
		}
	}
	
	@EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
		if(event.getPlayer().getWorld().getName().endsWith("Lobby")) {
			if(!event.getPlayer().hasPermission("nosto.lobby.interact")) event.setCancelled(true);
		}
    }
	
	@EventHandler
	public void onArmorStandInteract(PlayerArmorStandManipulateEvent event) {
		if(event.getPlayer().getWorld().getName().endsWith("Lobby")) {
			if(!event.getPlayer().hasPermission("nosto.lobby.interact")) event.setCancelled(true);
		}
	}

}
