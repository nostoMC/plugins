package fr.nostoNC.menus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.nostoNC.Utils;
import fr.nostoNC.tasks.BottomLaser;

public class BottomLaserMenu implements Listener {
	
	public static void openMenu(Player player) {
		
		Inventory inv = Bukkit.createInventory(null, 45, "§2§lGestioraire des laser bottom");
		
		inv.setItem(10, createItem(Material.REDSTONE_LAMP, "§e§lAffichage"));
		inv.setItem(11, createItem(Material.STONE, "§e§lAll OFF"));
		inv.setItem(12, createItem(Material.GOLD_BLOCK, "§e§lAll ON"));
		inv.setItem(13, createItem(Material.SPONGE, "§e§lStrobe"));
		inv.setItem(14, createItem(Material.WET_SPONGE, "§e§lStrobe mélangé"));
		inv.setItem(15, createItem(Material.WARPED_HYPHAE, "§e§lAlternance"));
		inv.setItem(16, createItem(Material.CLOCK, "§c§lTimer", "§e§lActuellement à " + BottomLaser.affichageTimer + " ticks"));
		
		inv.setItem(28, createItem(Material.RAIL, "§e§lMouvement"));
		inv.setItem(29, createItem(Material.STONE, "§e§lDown"));
		inv.setItem(30, createItem(Material.GOLD_BLOCK, "§e§lUp"));
		inv.setItem(31, createItem(Material.SPONGE, "§e§l???"));
		inv.setItem(32, createItem(Material.WET_SPONGE, "§e§lEdge"));
		inv.setItem(33, createItem(Material.WARPED_HYPHAE, "§e§lUp/Down"));
		inv.setItem(34, createItem(Material.GOLD_ORE, "§c§lTimer", "§e§lRandom Up/Down"));

		Utils.fillEmptyItem(inv);
		
		player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 100, 1);
		player.openInventory(inv);
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent event) {
		
		ItemStack current = event.getCurrentItem();
		
		if(current == null) return;
		
		if(event.getView().getTitle().equalsIgnoreCase("§2§lGestioraire des laser bottom")) {
			event.setCancelled(true);
			
			switch(current.getType()) {
				
			case STONE:
				if(event.getSlot() < 20) {
					BottomLaser.alternance = false;
					BottomLaser.strobe = false;
					BottomLaser.hideAll();
				} else {
					BottomLaser.moveToDown();
				}
				break;
				
			case GOLD_BLOCK:
				if(event.getSlot() < 20) {
					BottomLaser.alternance = false;
					BottomLaser.strobe = false;
					BottomLaser.showAll();
				} else {
					BottomLaser.moveToUp();
				}
				break;
			
			case SPONGE:
				BottomLaser.alternance = false;
				BottomLaser.strobe = true;
				break;
				
			case WARPED_HYPHAE:
				BottomLaser.strobe = false;
				BottomLaser.alternance = true;
				break;
				
			case CLOCK:
				if(event.getAction() == InventoryAction.PICKUP_ALL) {
					BottomLaser.affichageTimer++;
				} else {
					BottomLaser.affichageTimer--;
				}
				if(BottomLaser.affichageTimer < 1) BottomLaser.affichageTimer = 1;
				openMenu((Player) event.getWhoClicked());
				break;
			
			default:
				break;
			
			}
		}
	}
	
	private static ItemStack createItem(Material material, String customName, String... lore) {
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

}
