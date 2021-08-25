package fr.djredstone.nosto.menus;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import fr.djredstone.nosto.Main;

public class MondeOuvertMenu implements Listener {

	public static void openMenu(Player player) {
		Inventory inv = Bukkit.createInventory(null, 27, "§2§lMenu > TP > Monde Ouvert");
		
		inv.setItem(11, Main.createItem(Material.COOKED_BEEF , "§c§lSurvival"));
		inv.setItem(15, Main.createItem(Material.WHITE_WOOL , "§b§lSkyblock"));
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
		
		if(event.getView().getTitle().equalsIgnoreCase("§2§lMenu > TP > Monde Ouvert")) {
			event.setCancelled(true);
			
			switch(current.getType()) {
			
			case ARROW:
				TpMenu.openMenu(player);
				break;
				
			case COOKED_BEEF:
				if(!player.hasPermission("server.survivalAcces")) break;
				event.getView().close();
				Main.menuPlayers.remove(player);
				Main.vanishList.remove(player);
				Location SurvivalLobby = new Location(Bukkit.getWorld("survie"), -0.5, 63, 0.5, 180f, 0f);
				player.teleport(SurvivalLobby);
				player.setGameMode(GameMode.SURVIVAL);
				break;
				
			case WHITE_WOOL:
				if(!player.hasPermission("server.skyblockAcces")) break;
				event.getView().close();
				Main.menuPlayers.remove(player);
				Main.vanishList.remove(player);
				Location skyblockLobby = new Location(Bukkit.getWorld("skyworld"), 0.5, 150, 4.5, 0f, -10f);
				player.teleport(skyblockLobby);
				player.setGameMode(GameMode.SURVIVAL);
				break;
			
			default:
				break;
			
			}

		}
	}

}
