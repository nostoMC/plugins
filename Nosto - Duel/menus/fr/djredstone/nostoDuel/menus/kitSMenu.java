package fr.djredstone.nostoDuel.menus;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class kitSMenu {

	public static void openMenu(Player player) {
		Inventory invEvent = Bukkit.createInventory(null, 54, "§9§lChoisissez votre kit !");

		ItemStack clearSlot = new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1);
		ItemMeta clearSlotMeta = clearSlot.getItemMeta();
		clearSlotMeta.setDisplayName(" ");
		clearSlot.setItemMeta(clearSlotMeta);
		invEvent.setItem(0, clearSlot);
		invEvent.setItem(1, clearSlot);
		invEvent.setItem(2, clearSlot);
		invEvent.setItem(3, clearSlot);
		invEvent.setItem(4, clearSlot);
		invEvent.setItem(5, clearSlot);
		invEvent.setItem(6, clearSlot);
		invEvent.setItem(7, clearSlot);
		invEvent.setItem(8, clearSlot);
		invEvent.setItem(9, clearSlot);
		invEvent.setItem(11, clearSlot);
		invEvent.setItem(13, clearSlot);
		invEvent.setItem(15, clearSlot);
		invEvent.setItem(17, clearSlot);
		invEvent.setItem(18, clearSlot);
		invEvent.setItem(19, clearSlot);
		invEvent.setItem(21, clearSlot);
		invEvent.setItem(23, clearSlot);
		invEvent.setItem(25, clearSlot);
		invEvent.setItem(26, clearSlot);
		invEvent.setItem(27, clearSlot);
		invEvent.setItem(28, clearSlot);
		invEvent.setItem(29, clearSlot);
		invEvent.setItem(30, clearSlot);
		invEvent.setItem(31, clearSlot);
		invEvent.setItem(32, clearSlot);
		invEvent.setItem(33, clearSlot);
		invEvent.setItem(34, clearSlot);
		invEvent.setItem(35, clearSlot);
		invEvent.setItem(36, clearSlot);
		invEvent.setItem(38, clearSlot);
		invEvent.setItem(39, clearSlot);
		invEvent.setItem(41, clearSlot);
		invEvent.setItem(42, clearSlot);
		invEvent.setItem(44, clearSlot);
		invEvent.setItem(45, clearSlot);
		invEvent.setItem(46, clearSlot);
		invEvent.setItem(47, clearSlot);
		invEvent.setItem(48, clearSlot);
		invEvent.setItem(49, clearSlot);
		invEvent.setItem(50, clearSlot);
		invEvent.setItem(51, clearSlot);
		invEvent.setItem(52, clearSlot);
		invEvent.setItem(53, clearSlot);
		
		invEvent.setItem(10, getItem(Material.IRON_LEGGINGS, "§7§lKit Normal"));
		invEvent.setItem(12, getItem(Material.GOLDEN_APPLE , "§6§lKit OP"));
		invEvent.setItem(14, getItem(Material.BOW , "§2§lKit Archer"));
		invEvent.setItem(16, getItem(Material.SPLASH_POTION , "§d§lKit Intense"));
		invEvent.setItem(20, getItem(Material.BARRIER , "§0§lSoon..."));
		invEvent.setItem(22, getItem(Material.BARRIER , "§0§lSoon..."));
		invEvent.setItem(24, getItem(Material.BARRIER , "§0§lSoon..."));
		
		invEvent.setItem(37, getItem(Material.ENDER_EYE , "§e§lProposer à tous les joueurs le duel"));
		invEvent.setItem(43, getItem(Material.ENDER_PEARL , "§5§lProposer à un joueurs le duel"));
		
		invEvent.setItem(40, getItem(Material.BARRIER , "§b§lKit sélectionné : §8§lAucun"));
		
		player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 100, 1);
		player.openInventory(invEvent);
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
