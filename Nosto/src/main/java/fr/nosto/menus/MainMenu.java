package fr.nosto.menus;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import fr.nosto.Main;
import fr.nosto.Utils;
import fr.nosto.menus.mainmenu.ShopMenu;
import fr.nosto.menus.mainmenu.TpMenu;
import fr.nosto.menus.mainmenu.TrailsMenu;

public class MainMenu implements Listener {

	public static final String title = "§2§lMenu";

	public static void openMenu(Player player) {
		
		File file = new File(Main.getInstance().getDataFolder(), "economy.yml");
		YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
		
		ConfigurationSection configSection = config.getConfigurationSection("money.");
		
		double playerMoney = configSection.getDouble(player.getUniqueId().toString());

		Inventory inv = Bukkit.createInventory(null, 54, title);
	
		ItemStack skull = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) skull.getItemMeta();
		meta.setOwningPlayer(player);
		meta.setDisplayName("§6§l" + player.getName());
		
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§eMoney : §6§l" + playerMoney);
		
		meta.setLore(lore);
		
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		skull.setItemMeta(meta);
		
		inv.setItem(13, skull);

		ItemStack particleItem;
		if (player.getWorld().getName().endsWith("Lobby")) {
			particleItem = Utils.createItem(Material.BLAZE_POWDER, "§6§lParticules");
		} else {
			particleItem = Utils.createItem(Material.BLAZE_POWDER, "§8§lParticules",
					"§cCe menu n'est accessible que dans les lobbys !");
		}
		inv.setItem(37, particleItem);
		inv.setItem(31, Utils.createItem(Material.COMPASS , "§2§lTP"));
		inv.setItem(43, Utils.createItem(Material.GOLD_INGOT , "§e§lBoutique"));
		
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

			case COMPASS:
				TpMenu.openMenu(player);
				break;

			case BLAZE_POWDER:
				if (player.getWorld().getName().endsWith("Lobby"))
					TrailsMenu.openMenu(player);
				else player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 100, 1);
				break;

			case GOLD_INGOT:
				ShopMenu.openMenu(player);
				break;

			default:
				break;
		}
	}

}