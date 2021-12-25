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

import fr.nosto.Utils;
import fr.nosto.menus.MainMenu;
import fr.nosto.tasks.CosmeticEffectTask;
import fr.nosto.tasks.particles.CosmeticEffect;
import fr.nosto.tasks.particles.PlayerTrailsStats;

public class TrailsMenu implements Listener {
	
	private static final String noPermLore = "§cTu n'as pas acheté cette particule !";
	private static final String equipedLore = "§aÉquipé";
	private static final String availableLore = "§eClique pour équiper";

	private static final String title = "§2§lMenu > Particules";

	public static void openMenu(Player player) {
		open(player);
		player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 100, 1);
	}

	private static void open(Player player) {

		PlayerTrailsStats stats = CosmeticEffectTask.playerTrails.get(player.getUniqueId());
		Inventory inv = Bukkit.createInventory(null, 54, title);

		ItemStack clearSlot = Utils.getClearSlot();
		for (int i = 0; i < 9; i++) {
			inv.setItem(i, clearSlot);
		}
		for (int i = 36; i < inv.getSize(); i++) {
			inv.setItem(i, clearSlot);
		}

		inv.setItem(53, Utils.createItem(Material.ARROW, "§6§lRetour"));

		ItemStack equiped;
		if (stats.getEquiped() == null) {
			equiped = Utils.createItem(Material.BARRIER, "§bEffet équipé:", "§7Aucun");
		} else {
			equiped = getItemFromEffect(stats.getEquiped());
			ItemMeta equipedMeta = Objects.requireNonNull(equiped.getItemMeta());

			List<String> lore = equipedMeta.getLore();
			if (lore == null) lore = new ArrayList<>();

			lore.add(0, equipedMeta.getDisplayName());
			lore.add("");
			lore.add("§eClique pour dé-équiper");
			equipedMeta.setLore(lore);

			equipedMeta.setDisplayName("§bEffet équipé:");

			equiped.setItemMeta(equipedMeta);
		}
		inv.setItem(49, equiped);

		for (CosmeticEffect effect : CosmeticEffect.values()) {
			ItemStack it = getItemFromEffect(effect);
			ItemMeta itM = Objects.requireNonNull(it.getItemMeta());

			List<String> lore = itM.getLore();
			if (lore == null) lore = new ArrayList<>();

			lore.add("");

			if (stats.getUnlocked().contains(effect)) {
				if (stats.getEquiped() == effect) lore.add(equipedLore);
				else lore.add(availableLore);
			} else lore.add(noPermLore);

			itM.setLore(lore);
			it.setItemMeta(itM);

			for (int i = 9; i < 27; i++) { // parcourt l'inventaire et place l'item dans le premier slot vide
				if (inv.getItem(i) == null) {
					inv.setItem(i, it);
					break;
				}
			}
		}

		player.openInventory(inv);
	}

	private static ItemStack getItemFromEffect(CosmeticEffect effect) {
		return switch (effect) {
			case FIREWORK_CAPE -> Utils.createItem(Material.FIREWORK_ROCKET, "§fCape étincelante");
			case FIRE_CROWN -> Utils.createItem(Material.HONEYCOMB, "§6Couronne de feu");
			case WITCH_CIRCLE -> Utils.createItem(Material.END_CRYSTAL, "§5Cercle magique");
		};
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent event) {
		if (!event.getView().getTitle().equals(title)) return;

		event.setCancelled(true);

		if (event.getCurrentItem() == null) return;
		
		Player player = (Player) event.getWhoClicked();
		PlayerTrailsStats stats = CosmeticEffectTask.playerTrails.get(player.getUniqueId());
		final int slot = event.getSlot();

		// Flèche retour
		if (slot == 53)
			MainMenu.openMenu(player);
		// slot equiped
		else if (slot == 49) {
			if (stats.getEquiped() == null) return;
			stats.unequip();
			player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
			open(player);
		}
		// slots effets équipables
		if (slot >= 9 && slot < 36) {
			CosmeticEffect effect = CosmeticEffect.values()[slot - 9];

			if (stats.getUnlocked().contains(effect)) {
				if (stats.getEquiped() != effect) {
					stats.equip(effect);
					player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
					open(player);
				}
			} else {
				player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1, 1);
				stats.unlock(effect);
			}
		}

	}

}
