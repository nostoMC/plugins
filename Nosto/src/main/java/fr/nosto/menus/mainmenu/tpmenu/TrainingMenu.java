package fr.nosto.menus.mainmenu.tpmenu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import fr.nosto.Utils;
import fr.nosto.menus.mainmenu.TpMenu;

public class TrainingMenu implements Listener {

	private static final String title = "§2§lMenu > TP > Training";

	public static void openMenu(Player player, boolean override) {

		if (!player.getWorld().getName().endsWith("Lobby") && !override) return;

		Inventory inv = Bukkit.createInventory(null, 27, title);
		
		inv.setItem(11, Utils.createItem(Material.SANDSTONE , "§e§l§kBridge"));
		inv.setItem(15, Utils.createItem(Material.DIAMOND_SWORD , "§b§l§kPVP"));
		inv.setItem(22, Utils.createItem(Material.ARROW , "§6§lRetour"));
		
		Utils.fillEmptyItem(inv);
		
		player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 100, 1);
		player.openInventory(inv);
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent event) {
		if (!event.getView().getTitle().equals(title)) return;
		event.setCancelled(true);
		
		ItemStack current = event.getCurrentItem();
		if (current == null) return;
		Player player = (Player) event.getWhoClicked();

		switch (current.getType()) {
			case ARROW -> TpMenu.openMenu(player, false);
			default -> {}
		}
	}

}