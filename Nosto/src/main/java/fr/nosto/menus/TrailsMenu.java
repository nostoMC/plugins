package fr.nosto.menus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.nosto.Main;
import fr.nosto.tasks.particles.BigEffect;
import fr.nosto.tasks.particles.PlayerTrailsStats;
import fr.nosto.tasks.particles.SmallEffect;
import net.md_5.bungee.api.ChatColor;

public class TrailsMenu implements Listener {
	
	private static String noPermLore = "§cTu n'as pas acheté cette particule !";
	private static String equipedLore = "§aÉquipé";
	private static String availableLore = "§eClique pour équiper";

	public static void openMenu(Player player) {

		openPage1(player);
		player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 100, 1);
	}
	
	private static Inventory makeInventoryBody(Inventory inv, PlayerTrailsStats stats) {

		ItemStack clearSlot = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
		ItemMeta clearSlotMeta = clearSlot.getItemMeta();
		clearSlotMeta.setDisplayName(" ");
		clearSlot.setItemMeta(clearSlotMeta);

		for (int i = 0; i < 9; i++) {
			inv.setItem(i, clearSlot);
		}
		for (int i = 36; i < inv.getSize(); i++) {
			inv.setItem(i, clearSlot);
		}

		inv.setItem(45, createItem(Material.ARROW, "§6§lRetour"));

		ItemStack smallIt;
		if (stats.getEquipedSmall() == null) {
			smallIt = createItem(Material.BARRIER, "§fEffet ambient équipé:", "§7Aucun");
		} else {
			smallIt = getItemFromEffect(stats.getEquipedSmall());
			ItemMeta itM = smallIt.getItemMeta();

			List<String> lore;
			if (itM.hasLore())
				lore = itM.getLore();
			else
				lore = new ArrayList<>();

			lore.add(0, itM.getDisplayName());
			lore.add("");
			lore.add("§eClique pour dé-équiper");
			itM.setLore(lore);

			itM.setDisplayName("§fEffet ambient équipé:");

			smallIt.setItemMeta(itM);
		}
		inv.setItem(48, smallIt);

		ItemStack bigIt;
		if (stats.getEquipedBig() == null) {
			bigIt = createItem(Material.BARRIER, "§bEffet spécial équipé:", "§7Aucun");
		} else {
			bigIt = getItemFromEffect(stats.getEquipedBig());
			ItemMeta itM = bigIt.getItemMeta();

			List<String> lore;
			if (itM.hasLore())
				lore = itM.getLore();
			else
				lore = new ArrayList<>();

			lore.add(0, itM.getDisplayName());
			lore.add("");
			lore.add("§eClique pour dé-équiper");
			itM.setLore(lore);

			itM.setDisplayName("§bEffet spécial équipé:");

			bigIt.setItemMeta(itM);
		}
		inv.setItem(50, bigIt);

		return inv;
	}

	private static void openPage1(Player player) {

		PlayerTrailsStats stats = Main.getPlayerTrailsMap().get(player);
		Inventory inv = Bukkit.createInventory(null, 54, "§2§lMenu > Particules | Page 1");

		makeInventoryBody(inv, stats);

		inv.setItem(3, createItem(Material.IRON_INGOT, "§fEffets ambiants", "§7Des petits effets permanents",
				"§7qui te suivent partout!", "", "§aTu es déja sur cette page"));
		inv.setItem(5, createItem(Material.DIAMOND, "§bEffets spéciaux", "§7Des effets stylés qui ne s'activent",
				"§7que quand tu est immobile!", "", "§6Clique pour voir cette page"));

		for (SmallEffect effect : SmallEffect.values()) {
			ItemStack it = getItemFromEffect(effect);
			ItemMeta itM = it.getItemMeta();

			List<String> lore;
			if (itM.hasLore())
				lore = itM.getLore();
			else
				lore = new ArrayList<>();

			lore.add("");

			if (stats.getUnlockedSmall().contains(effect)) {
				if (stats.getEquipedSmall() == effect) {
					lore.add(equipedLore);
				} else {
					lore.add(availableLore);
				}
			} else {
				lore.add(noPermLore);
			}

			itM.setLore(lore);
			it.setItemMeta(itM);

			for (int i = 9; i < 27; i++) {
				if (inv.getItem(i) == null) {
					inv.setItem(i, it);
					break;
				}
			}
		}

		player.openInventory(inv);
	}

	private static void openPage2(Player player) {

		PlayerTrailsStats stats = Main.getPlayerTrailsMap().get(player);

		Inventory inv = Bukkit.createInventory(null, 54, "§2§lMenu > Particules | Page 2");

		makeInventoryBody(inv, stats);

		inv.setItem(3, createItem(Material.IRON_INGOT, "§fEffets ambiants", "§7Des petits effets permanents",
				"§7qui te suivent partout!", "", "§6Clique pour voir cette page"));
		inv.setItem(5, createItem(Material.DIAMOND, "§bEffets spéciaux", "§7Des effets stylés qui ne s'activent",
				"§7que quand tu est immobile!", "", "§aTu es déja sur cette page"));

		for (BigEffect effect : BigEffect.values()) {
			ItemStack it = getItemFromEffect(effect);
			ItemMeta itM = it.getItemMeta();

			List<String> lore;
			if (itM.hasLore())
				lore = itM.getLore();
			else
				lore = new ArrayList<>();

			lore.add("");

			if (stats.getUnlockedBig().contains(effect)) {
				if (stats.getEquipedBig() == effect) {
					lore.add(equipedLore);
				} else {
					lore.add(availableLore);
				}
			} else {
				lore.add(noPermLore);
			}

			itM.setLore(lore);
			it.setItemMeta(itM);

			for (int i = 9; i < 27; i++) {
				if (inv.getItem(i) == null) {
					inv.setItem(i, it);
					break;
				}
			}
		}

		player.openInventory(inv);
	}

	private static ItemStack getItemFromEffect(SmallEffect effect) {
		switch (effect) {
		case FROST_WALKER:
			return createItem(Material.DIAMOND_BOOTS, ChatColor.of("#B1FFFC") + "Frost walker");
		case FLAMES:
			return createItem(Material.BLAZE_POWDER, "§6Flammes");
		case FIREWORKS:
			return createItem(Material.BONE_MEAL, "§fÉtincelles");
		default:
			return createItem(Material.BEDROCK, "null");
		}
	}

	private static ItemStack getItemFromEffect(BigEffect effect) {
		switch (effect) {
		case FIREWORK_CAPE:
			return createItem(Material.FIREWORK_ROCKET, "§fCape étincelante");
		case FIRE_CROWN:
			return createItem(Material.HONEYCOMB, "§6Couronne de feu");
		case WITCH_CIRCLE:
			return createItem(Material.END_CRYSTAL, "§5Cercle magique");
		default:
			return createItem(Material.BEDROCK, "null");
		}
	}

	public static ItemStack createItem(Material material, String customName, String... lore) {
		ItemStack it = new ItemStack(material);
		ItemMeta itM = it.getItemMeta();

		if(customName != null) itM.setDisplayName(customName);

		List<String> itemLore = new ArrayList<>();
		Collections.addAll(itemLore, lore);
		itM.setLore(itemLore);

		itM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		itM.addItemFlags(ItemFlag.HIDE_ENCHANTS);

		it.setItemMeta(itM);
		return it;
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent event) {
		if (!event.getView().getTitle().equalsIgnoreCase("§2§lMenu > Particules | Page 1")
				&& !event.getView().getTitle().equalsIgnoreCase("§2§lMenu > Particules | Page 2"))
			return;

		event.setCancelled(true);

		Player player = (Player) event.getWhoClicked();
		PlayerTrailsStats stats = Main.getPlayerTrailsMap().get(player);

		if (event.getSlot() == 45) {
			MainMenu.openMenu(player);
			return;
		}

		if (event.getSlot() == 48 && stats.getEquipedSmall() != null) {
			stats.unequipSmall();
			player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
			updateMenu(event);
			return;
		}

		if (event.getSlot() == 50 && stats.getEquipedBig() != null) {
			stats.unequipBig();
			player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
			updateMenu(event);
			return;
		}

		if (event.getView().getTitle().equalsIgnoreCase("§2§lMenu > Particules | Page 1")) {

			if (event.getSlot() == 5) {
				openPage2(player);
				player.playSound(player.getLocation(), Sound.BLOCK_LEVER_CLICK, 1, 1);
				return;
			}
			
			if (event.getSlot() >= 9 && event.getSlot() < 36 && event.getCurrentItem() != null) {
				SmallEffect effect = SmallEffect.values()[event.getSlot() - 9];

				if (stats.getUnlockedSmall().contains(effect)) {
					if (stats.getEquipedSmall() != effect) {
						stats.equip(effect);
						player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
						openPage1(player);
					}
				} else {
					player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
					stats.unlock(effect);
				}
				return;
			}

		}

		if (event.getView().getTitle().equalsIgnoreCase("§2§lMenu > Particules | Page 2")) {

			if (event.getSlot() == 3) {
				openPage1(player);
				player.playSound(player.getLocation(), Sound.BLOCK_LEVER_CLICK, 1, 1);
				return;
			}

			if (event.getSlot() >= 9 && event.getSlot() < 36 && event.getCurrentItem() != null) {
				BigEffect effect = BigEffect.values()[event.getSlot() - 9];

				if (stats.getUnlockedBig().contains(effect)) {
					if (stats.getEquipedBig() != effect) {
						stats.equip(effect);
						player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
						openPage2(player);
					}
				} else {
					player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
					stats.unlock(effect);
				}
				return;
			}

		}
	}

	private static void updateMenu(InventoryClickEvent event) {
		if (event.getView().getTitle().equalsIgnoreCase("§2§lMenu > Particules | Page 1"))
			openPage1((Player) event.getWhoClicked());
		if (event.getView().getTitle().equalsIgnoreCase("§2§lMenu > Particules | Page 2"))
			openPage2((Player) event.getWhoClicked());
	}

}
