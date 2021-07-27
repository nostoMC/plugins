package fr.djredstone.nosto.menus;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import fr.djredstone.nosto.Main;

public class MainMenu {
	
	static ArrayList<Player> menuPlayers = Main.getMenuPlayersList();

	public static void openMenu(Player player) {
		
		if(menuPlayers.contains(player) && !player.getInventory().contains(Material.COMPASS)) return;
		
		File file = new File(Main.getInstance().getDataFolder(), "economy.yml");
		YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
		
		ConfigurationSection configSection = config.getConfigurationSection("money.");
		
		double playerMoney = configSection.getDouble(player.getUniqueId().toString());

		Inventory inv = Bukkit.createInventory(null, 54, "�2�lMenu");
	
		ItemStack skull = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) skull.getItemMeta();
		meta.setOwningPlayer(player);
		meta.setDisplayName("�6�l" + player.getName());
		
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("�eMoney : �6�l" + playerMoney);
		
		meta.setLore(lore);
		
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		skull.setItemMeta(meta);
		
		inv.setItem(13, skull);
		
		inv.setItem(37, getItem(Material.BLAZE_POWDER , "�6�lParticules"));
		inv.setItem(31, getItem(Material.COMPASS , "�2�lTP"));
		inv.setItem(43, getItem(Material.GOLD_INGOT , "�e�lBoutique"));
		
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
