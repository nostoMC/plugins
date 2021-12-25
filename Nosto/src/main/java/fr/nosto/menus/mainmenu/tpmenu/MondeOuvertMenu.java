package fr.nosto.menus.mainmenu.tpmenu;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import fr.nosto.Utils;
import fr.nosto.menus.mainmenu.TpMenu;

public class MondeOuvertMenu implements Listener {

	public static final String title = "§2§lMenu > TP > Monde Ouvert";

	public static void openMenu(Player player) {
		Inventory inv = Bukkit.createInventory(null, 27, title);
		
		inv.setItem(11, Utils.createItem(Material.COOKED_BEEF , "§c§lSurvival"));
		inv.setItem(15, Utils.createItem(Material.WHITE_WOOL , "§b§lSkyblock"));
		inv.setItem(22, Utils.createItem(Material.ARROW , "§6§lRetour"));
		
		Utils.fillEmptyItem(inv);
		
		player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 100, 1);
		player.openInventory(inv);
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent event) {
		if(!event.getView().getTitle().equals(title)) return;
		event.setCancelled(true);

		Player player = (Player) event.getWhoClicked();
		ItemStack current = event.getCurrentItem();
		if (current == null) return;

		switch (current.getType()) {
			case ARROW -> TpMenu.openMenu(player);

			case COOKED_BEEF -> {
				if (!player.hasPermission("server.survivalAcces")) break;
				event.getView().close();
				Location SurvivalLobby = new Location(Bukkit.getWorld("survie"), -0.5, 63, 0.5, 180f, 0f);
				player.teleport(SurvivalLobby);
				player.setGameMode(GameMode.SURVIVAL);
			}

			case WHITE_WOOL -> {
				if (!player.hasPermission("server.skyblockAcces")) break;
				event.getView().close();
				Location skyblockLobby = new Location(Bukkit.getWorld("skyworld"), 0.5, 150, 4.5, 0f, -10f);
				player.teleport(skyblockLobby);
				player.setGameMode(GameMode.SURVIVAL);
			}

			default -> {}
		}
	}

}
