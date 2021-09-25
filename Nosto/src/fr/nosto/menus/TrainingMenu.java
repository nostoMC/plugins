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

public class TrainingMenu implements Listener {

	public static void openMenu(Player player) {
		Inventory inv = Bukkit.createInventory(null, 27, "§2§lMenu > TP > Training");
		
		inv.setItem(11, Main.createItem(Material.SANDSTONE , "§e§l§kBridge"));
		inv.setItem(15, Main.createItem(Material.DIAMOND_SWORD , "§b§l§kPVP"));
		inv.setItem(22, Main.createItem(Material.ARROW , "§6§lRetour"));
		
		Main.fillEmplyItem(inv);
		
		player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 100, 1);
		player.openInventory(inv);
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent event) {
		
		Player player = (Player) event.getWhoClicked();
		ItemStack current = event.getCurrentItem();
		
		if(current.getType() == null) {
			return;
		}
		
		if(event.getView().getTitle().equalsIgnoreCase("§2§lMenu > TP > Training")) {
			event.setCancelled(true);

			switch(current.getType()) {
			
			case ARROW:
				TpMenu.openMenu(player);
				break;
			
			default:
				break;
			
			}

		}
		
	}

}