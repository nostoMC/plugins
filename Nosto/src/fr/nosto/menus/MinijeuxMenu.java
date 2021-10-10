package fr.nosto.menus;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import fr.nosto.Main;

public class MinijeuxMenu implements Listener {

	public static void openMenu(Player player) {
		Inventory inv = Bukkit.createInventory(null, 27, "§2§lMenu > TP > Mini jeux");
		
		inv.setItem(11, Main.createItem(Material.SPRUCE_PLANKS , "§6§l§kMoyen âge"));
		inv.setItem(13, Main.createItem(Material.BRICKS , "§5§l§kSteampunk"));
		inv.setItem(15, Main.createItem(Material.SMOOTH_STONE , "§7§l§kModerne"));
		inv.setItem(22, Main.createItem(Material.ARROW , "§6§lRetour"));
		
		Main.fillEmplyItem(inv);
		
		player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 100, 1);
		player.openInventory(inv);
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent event) {
		
		Player player = (Player) event.getWhoClicked();
		ItemStack current = event.getCurrentItem();
		
		try {
			
			if(event.getView().getTitle().equalsIgnoreCase("§2§lMenu > TP > Mini jeux")) {
				event.setCancelled(true);

				switch(current.getType()) {
				
				case ARROW:
					TpMenu.openMenu(player);
					break;
				
				default:
					break;
				
				}

			}
			
		} catch (NullPointerException e) {
			return;
		}
		
	}
}