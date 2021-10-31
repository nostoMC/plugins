package fr.nosto.menus;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import fr.nosto.Utils;

public class EventMenu implements Listener {

	public static final String title = "§2§lMenu > TP > Events";

	public static void openMenu(Player player) {

		Inventory inv = Bukkit.createInventory(null, 27, title);
		
		inv.setItem(11, Utils.createItem(Material.MUSIC_DISC_BLOCKS , "§e§lNostoClub"));
		inv.setItem(13, Utils.createItem(Material.BARRIER , " "));
		inv.setItem(15, Utils.createItem(Material.BARRIER , " "));
		inv.setItem(22, Utils.createItem(Material.ARROW , "§6§lRetour"));
		
		Utils.fillEmptyItem(inv);
		
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
		
		switch(current.getType()) {

			case ARROW:
				TpMenu.openMenu(player);
				break;
	
			case MUSIC_DISC_BLOCKS:
				if(!player.hasPermission("server.nightclubAcces")) break;
				event.getView().close();
				Location nightClubLobby = new Location(Bukkit.getWorld("Nightclub"), 0.5, 64.0, 0.5, 0f, 0f);
				player.setGameMode(GameMode.ADVENTURE);
				player.teleport(nightClubLobby);
				break;

			default:
				break;
		}
	}

}