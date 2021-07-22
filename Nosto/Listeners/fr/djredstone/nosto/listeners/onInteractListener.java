package fr.djredstone.nosto.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import fr.djredstone.nosto.Main;
import fr.djredstone.nosto.menus.tpMenu;

public class onInteractListener implements Listener {

	public onInteractListener(Main main) {
		main.getServer().getPluginManager().registerEvents(this, main);
	}

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
	                		player.sendMessage("�cCe chunk a �t� claim. Tu ne peut donc rien faire dans cette zone.");
	            		}
	        		}
	        	}
				
			}
		}
        
        if(player.getWorld() == Bukkit.getWorld("world")) {
        
        		if(player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equalsIgnoreCase("�b�lClick pour ouvrire le menu de t�l�portation")) {
        			tpMenu.openMenu(player);
        		}
        		
        }
	}

}
