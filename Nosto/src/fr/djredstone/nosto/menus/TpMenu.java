package fr.djredstone.nosto.menus;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.djredstone.nosto.Main;

public class TpMenu {

	public static void openMenu(Player player) {
		
		if(Main.menuPlayers.contains(player) && !player.getInventory().contains(Material.COMPASS)) return;

		Inventory inv = Bukkit.createInventory(null, 27, "§2§lMenu > TP");
		
		inv.setItem(10, getItem(Material.GRASS_BLOCK , "§2§lMonde ouvert"));
		inv.setItem(12, getItem(Material.IRON_SWORD , "§6§lMini jeux"));
		inv.setItem(14, getItem(Material.BOW , "§c§lTraning"));
		inv.setItem(16, getItem(Material.FIREWORK_ROCKET , "§e§lEvent"));
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
