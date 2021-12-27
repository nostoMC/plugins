package fr.nosto.menus.mainmenu.tpmenu;

import java.util.Objects;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.nosto.Utils;
import fr.nosto.menus.mainmenu.TpMenu;

public class EventMenu implements Listener {

	public static final String title = "§2§lMenu > TP > Events";

	public static void openMenu(Player player) {

		Inventory inv = Bukkit.createInventory(null, 27, title);
		
		ItemStack disc = Utils.createItem(Material.MUSIC_DISC_BLOCKS, "§e§lNostoClub");
		ItemMeta discMeta = Objects.requireNonNull(disc.getItemMeta());
		discMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS); // somehow hides the base lore of the disc (author & name)
		disc.setItemMeta(discMeta);

		inv.setItem(11, disc);
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
		
		switch (current.getType()) {
			case ARROW -> TpMenu.openMenu(player);
			
			case MUSIC_DISC_BLOCKS -> {
				if (!player.hasPermission("server.nightclubAcces")) break;
				event.getView().close();
				Location nostoClubSpawn = new Location(Bukkit.getWorld("nostoclub"), -5.5, 101, 214.5, 180, 0);
				player.setGameMode(GameMode.ADVENTURE);
				player.teleport(nostoClubSpawn);
				player.teleport(nostoClubSpawn);
			}

			default -> {}
		}
	}

}