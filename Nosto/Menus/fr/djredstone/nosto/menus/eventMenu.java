package fr.djredstone.nosto.menus;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class eventMenu {
	
	public static void openMenu(Player player) {
		Inventory inv = Bukkit.createInventory(null, 36, "§2§lMenu > TP > Events");
		
		inv.setItem(10, getItem(Material.FLINT_AND_STEEL , "§8§lEvent Hunt"));
		inv.setItem(12, getItem(Material.SHIELD , "§c§lEvent PvP"));
		inv.setItem(14, getItem(Material.PLAYER_HEAD , "§4§lEvent Loup Garou"));
		inv.setItem(16, getItem(Material.DIAMOND , "§b§lEvent Roue de la Chance"));
		inv.setItem(20, getItem(Material.FIREWORK_ROCKET , "§6§lEvent Show"));
		inv.setItem(24, getItem(Material.MUSIC_DISC_BLOCKS , "§e§lEvent NightClub"));
		inv.setItem(22, getItem(Material.ARROW , "§6§lRetour"));
		
		ItemStack clearSlot = new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1);
		ItemMeta clearSlotMeta = clearSlot.getItemMeta();
		clearSlotMeta.setDisplayName(" ");
		clearSlot.setItemMeta(clearSlotMeta);
		
		for(int i = 0; i < inv.getSize(); i++) {
			if(inv.getItem(i) == null) {
				inv.setItem(i, clearSlot);
			}
		}
		
		player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 100, 1);
		player.openInventory(inv);
	}
	
	public static ItemStack getItem(Material material, String customName) {
		ItemStack it = new ItemStack(material, 1);
		ItemMeta itM = it.getItemMeta();
		if(customName != null) itM.setDisplayName(customName);
		itM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		it.setItemMeta(itM);
		return it;
	}

}