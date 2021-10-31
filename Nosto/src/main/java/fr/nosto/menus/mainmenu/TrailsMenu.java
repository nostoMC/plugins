package fr.nosto.menus.mainmenu;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.nosto.Main;
import fr.nosto.Utils;
import fr.nosto.menus.MainMenu;
import fr.nosto.tasks.particles.BigEffect;
import fr.nosto.tasks.particles.PlayerTrailsStats;
import fr.nosto.tasks.particles.SmallEffect;
import net.md_5.bungee.api.ChatColor;

public class TrailsMenu implements Listener {
	
	private static final String noPermLore = "§cTu n'as pas acheté cette particule !";
	private static final String equipedLore = "§aÉquipé";
	private static final String availableLore = "§eClique pour équiper";

	private static final String title1 = "§2§lMenu > Particules | Page 1";
	private static final String title2 = "§2§lMenu > Particules | Page 2";

	public static void openMenu(Player player) {
		openPage1(player);
		player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 100, 1);
	}
	
	private static void makeInventoryBody(Inventory inv, PlayerTrailsStats stats) {

		ItemStack clearSlot = Utils.getClearSlot();
		for (int i = 0; i < 9; i++) {
			inv.setItem(i, clearSlot);
		}
		for (int i = 36; i < inv.getSize(); i++) {
			inv.setItem(i, clearSlot);
		}

		inv.setItem(53, Utils.createItem(Material.ARROW, "§6§lRetour"));

		ItemStack smallIt;
		if (stats.getEquipedSmall() == null) {
			smallIt = Utils.createItem(Material.BARRIER, "§fEffet ambient équipé:", "§7Aucun");
		} else {
			smallIt = getItemFromEffect(stats.getEquipedSmall());
			ItemMeta itM = Objects.requireNonNull(smallIt.getItemMeta());

			List<String> lore = itM.getLore();
			if (lore == null) lore = new ArrayList<>();

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
			bigIt = Utils.createItem(Material.BARRIER, "§bEffet spécial équipé:", "§7Aucun");
		} else {
			bigIt = getItemFromEffect(stats.getEquipedBig());
			ItemMeta itM = Objects.requireNonNull(bigIt.getItemMeta());

			List<String> lore = itM.getLore();
			if (lore == null) lore = new ArrayList<>();

			lore.add(0, itM.getDisplayName());
			lore.add("");
			lore.add("§eClique pour dé-équiper");
			itM.setLore(lore);

			itM.setDisplayName("§bEffet spécial équipé:");

			bigIt.setItemMeta(itM);
		}
		inv.setItem(50, bigIt);
	}

	private static void openPage1(Player player) {

		PlayerTrailsStats stats = Main.getPlayerTrailsMap().get(player);
		Inventory inv = Bukkit.createInventory(null, 54, title1);

		makeInventoryBody(inv, stats);

		inv.setItem(3, Utils.createItem(Material.IRON_INGOT, "§fEffets ambiants",
				"§7Des petits effets permanents",
				"§7qui te suivent partout!",
				"",
				"§aTu es déja sur cette page"));
		inv.setItem(5, Utils.createItem(Material.DIAMOND, "§bEffets spéciaux",
				"§7Des effets stylés qui ne s'activent",
				"§7que quand tu est immobile!",
				"",
				"§6Clique pour voir cette page"));

		for (SmallEffect effect : SmallEffect.values()) {
			ItemStack it = getItemFromEffect(effect);
			ItemMeta itM = Objects.requireNonNull(it.getItemMeta());

			List<String> lore = itM.getLore();
			if (lore == null) lore = new ArrayList<>();

			lore.add("");

			if (stats.getUnlockedSmall().contains(effect)) {
				if (stats.getEquipedSmall() == effect) lore.add(equipedLore);
				else lore.add(availableLore);
			} else lore.add(noPermLore);

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
		Inventory inv = Bukkit.createInventory(null, 54, title2);

		makeInventoryBody(inv, stats);

		inv.setItem(3, Utils.createItem(Material.IRON_INGOT, "§fEffets ambiants",
				"§7Des petits effets permanents",
				"§7qui te suivent partout!",
				"",
				"§6Clique pour voir cette page"));
		inv.setItem(5, Utils.createItem(Material.DIAMOND, "§bEffets spéciaux",
				"§7Des effets stylés qui ne s'activent",
				"§7que quand tu est immobile!",
				"",
				"§aTu es déja sur cette page"));

		for (BigEffect effect : BigEffect.values()) {
			ItemStack it = getItemFromEffect(effect);
			ItemMeta itM = Objects.requireNonNull(it.getItemMeta());

			List<String> lore = itM.getLore();
			if (lore == null) lore = new ArrayList<>();

			lore.add("");

			if (stats.getUnlockedBig().contains(effect)) {
				if (stats.getEquipedBig() == effect) lore.add(equipedLore);
				else lore.add(availableLore);
			} else lore.add(noPermLore);

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
				return Utils.createItem(Material.DIAMOND_BOOTS, ChatColor.of("#B1FFFC") + "Frost walker");
			case FLAMES:
				return Utils.createItem(Material.BLAZE_POWDER, "§6Flammes");
			case FIREWORKS:
				return Utils.createItem(Material.BONE_MEAL, "§fÉtincelles");
			default:
				return Utils.createItem(Material.BEDROCK, "null");
		}
	}

	private static ItemStack getItemFromEffect(BigEffect effect) {
		switch (effect) {
			case FIREWORK_CAPE:
				return Utils.createItem(Material.FIREWORK_ROCKET, "§fCape étincelante");
			case FIRE_CROWN:
				return Utils.createItem(Material.HONEYCOMB, "§6Couronne de feu");
			case WITCH_CIRCLE:
				return Utils.createItem(Material.END_CRYSTAL, "§5Cercle magique");
			default:
				return Utils.createItem(Material.BEDROCK, "null");
		}
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent event) {
		if (!event.getView().getTitle().equals(title1)
				&& !event.getView().getTitle().equals(title2))
			return;

		event.setCancelled(true);

		if (event.getCurrentItem() == null) return;
		
		Player player = (Player) event.getWhoClicked();
		PlayerTrailsStats stats = Main.getPlayerTrailsMap().get(player);
		final int slot = event.getSlot();

		// Flèche retour
		if (slot == 53)
			MainMenu.openMenu(player);
		// slot equiped small
		else if (slot == 48) {
			if (stats.getEquipedSmall() == null) return;
			stats.unequipSmall();
			player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
			updateMenu(event);
		}
		// slot equiped big
		else if (slot == 50) {
			if (stats.getEquipedBig() == null) return;
			stats.unequipBig();
			player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
			updateMenu(event);
		}
		// si menu page 1
		else if (event.getView().getTitle().equalsIgnoreCase(title1)) {

			// slot bouton page 2
			if (slot == 5) {
				openPage2(player);
				player.playSound(player.getLocation(), Sound.BLOCK_LEVER_CLICK, 1, 1);
			}
			// slots effets équipables
			else if (slot >= 9 && slot < 36) {
				SmallEffect effect = SmallEffect.values()[slot - 9];

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
			}

		}
		// si menu page 2
		else if (event.getView().getTitle().equalsIgnoreCase(title2)) {

			// slot bouton page 1
			if (slot == 3) {
				openPage1(player);
				player.playSound(player.getLocation(), Sound.BLOCK_LEVER_CLICK, 1, 1);
			}
			// slots effets équipables
			else if (slot >= 9 && slot < 36) {
				BigEffect effect = BigEffect.values()[slot - 9];

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
			}

		}
	}

	private static void updateMenu(InventoryClickEvent event) {
		final Player player = (Player) event.getWhoClicked();
		if (event.getView().getTitle().equalsIgnoreCase(title1))
			openPage1(player);
		if (event.getView().getTitle().equalsIgnoreCase(title2))
			openPage2(player);
	}

}
