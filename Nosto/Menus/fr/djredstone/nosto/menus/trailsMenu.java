package fr.djredstone.nosto.menus;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.djredstone.nosto.Main;

public class trailsMenu {
	
	static ArrayList<Player> menuPlayers = Main.getMenuPlayersList();
	static ArrayList<String> noPermLore = new ArrayList<String>();

	public static void openMenu(Player player) {
		
		noPermLore.add("§c§lVous n'avez pas acheter cette particule !");
		
		if(menuPlayers.contains(player) && !player.getInventory().contains(Material.COMPASS)) return;

		Inventory inv = Bukkit.createInventory(null, 45, "§2§lMenu > Particules");
		
		inv.setItem(10, getItem(Material.BLAZE_POWDER , "§c§lFlame"));
		if(!player.hasPermission("nosto.trails.flame")) {
			inv.getItem(10).getItemMeta().setLore(noPermLore);
		}
		ItemStack it = inv.getItem(10);
		ItemMeta itM = it.getItemMeta();
		if(Main.getPlayerFlameTrails().get(player) != null && Main.getPlayerFlameTrails().get(player) == true) {
			itM.addEnchant(Enchantment.DAMAGE_ALL, 200, true);
			it.setItemMeta(itM);
			inv.setItem(10, it);
		} else {
			itM.removeEnchant(Enchantment.DAMAGE_ALL);
			it.setItemMeta(itM);
			inv.setItem(10, it);
		}
		
		inv.setItem(40, getItem(Material.ARROW , "§6§lRetour"));
		
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
		itM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		it.setItemMeta(itM);
		return it;
	}

}
