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

public class TpMenu implements Listener {

	public static final String title = "§2§lMenu > TP";

	public static void openMenu(Player player) {

		Inventory inv = Bukkit.createInventory(null, 27, title);
		
		inv.setItem(10, Main.createItem(Material.GRASS_BLOCK , "§2§lMonde ouvert"));
		inv.setItem(12, Main.createItem(Material.WOODEN_SWORD , "§c§lTraining"));
		inv.setItem(14, Main.createItem(Material.FIREWORK_ROCKET , "§e§lEvents"));
		inv.setItem(16, Main.createItem(Material.FISHING_ROD , "§6§lMini jeux"));
		inv.setItem(22, Main.createItem(Material.ARROW , "§6§lRetour"));
		
		Main.fillEmplyItem(inv);
	
		player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 100, 1);
		player.openInventory(inv);
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent event) {
		if (!event.getView().getTitle().equals(title)) return;
		event.setCancelled(true);

		Player player = (Player) event.getWhoClicked();
		ItemStack current = event.getCurrentItem();
		if (current == null) return;
				
		switch(current.getType()){

			case ARROW:
				MainMenu.openMenu(player);
				break;

			case GRASS_BLOCK:
				MondeOuvertMenu.openMenu(player);
				break;
				
			case FISHING_ROD:
				MinijeuxMenu.openMenu(player);
				break;

			case WOODEN_SWORD:
				TrainingMenu.openMenu(player);
				break;

			case FIREWORK_ROCKET:
				EventMenu.openMenu(player);
				break;

			default:
				break;
		}
	}
	
}
