package fr.nostoNC.menus;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.bukkit.*;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import fr.nostoNC.Main;
import fr.nostoNC.Utils;
import fr.nostoNC.tasks.effects.RandomParticleEffect;
import fr.nostoNC.tasks.effects.StrobeEffect;

public class EffectsMenu implements Listener {

	static final ArrayList<String> on = new ArrayList<>();
	static final ArrayList<String> off = new ArrayList<>();

	public EffectsMenu() {
		off.add("§c§loff");
		on.add("§a§lon");
	}

	public static void openMenu(Player player) {

		Inventory inv = Bukkit.createInventory(null, 54, "§2§lGestioraire des effets");

		// ----------------------------------------------------------------------------------------------------------------------

		createAndCheckActiveEffectItem(inv, Material.STRING, "§7§lFloor Smoke", "floorSmoke", 10);

		inv.setItem(18, Utils.createItem(Material.CLOCK, "§e§lTiming", "§e§lLa vitesse est actuellement à §6§l" + StrobeEffect.timing));
		if (Main.activeEffects.get("strobe") != null) if (Main.activeEffects.get("strobe")) {
			inv.setItem(19, Utils.createItem(Material.REDSTONE_LAMP, "§8§lStrobe", "§a§lon"));
		} else {
			inv.setItem(19, Utils.createItem(Material.REDSTONE_LAMP, "§8§lStrobe", "§c§loff"));
		}

		createAndCheckActiveEffectItem(inv, Material.FIREWORK_ROCKET, "§8§lFeux d'artifices", null, 12);

		createAndCheckActiveEffectItem(inv, Material.PUMPKIN_SEEDS, "§f§lParticules aléatoires", null, 21);

		fillEmptyItem(inv);

		player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 100, 1);
		player.openInventory(inv);
	}

	@EventHandler
	public void onClick(InventoryClickEvent event) {

		ItemStack current = event.getCurrentItem();
		Player player = (Player) event.getWhoClicked();

		if(current == null) return;

		if(event.getView().getTitle().equalsIgnoreCase("§2§lGestioraire des effets")) {
			event.setCancelled(true);

			switch(current.getType()) {

			case STRING:
				checkActiveEffectItem(current, player, "floorSmoke");
				break;

			case CLOCK:
				if (event.getAction() == InventoryAction.PICKUP_ALL)
					StrobeEffect.timing++;
				if (event.getAction() == InventoryAction.PICKUP_HALF)
					StrobeEffect.timing--;
				openMenu(player);
				break;

			case REDSTONE_LAMP:
				checkActiveEffectItem(current, player, "strobe");
				break;

			case FIREWORK_ROCKET:

				Set<Firework> setOfFW = new HashSet<>();

				Firework fw1 = (Firework) Main.defaultWorld.spawnEntity(new Location(Main.defaultWorld, 11.5, 65.7, 13.5), EntityType.FIREWORK);
				setOfFW.add(fw1);
				Firework fw2 = (Firework) Main.defaultWorld.spawnEntity(new Location(Main.defaultWorld, 7.5, 65.7, 14.5), EntityType.FIREWORK);
				setOfFW.add(fw2);
				Firework fw3 = (Firework) Main.defaultWorld.spawnEntity(new Location(Main.defaultWorld, 3.5, 65.7, 15.5), EntityType.FIREWORK);
				setOfFW.add(fw3);
				Firework fw4 = (Firework) Main.defaultWorld.spawnEntity(new Location(Main.defaultWorld, -2.5, 65.7, 15.5), EntityType.FIREWORK);
				setOfFW.add(fw4);
				Firework fw5 = (Firework) Main.defaultWorld.spawnEntity(new Location(Main.defaultWorld, -6.5, 65.7, 14.5), EntityType.FIREWORK);
				setOfFW.add(fw5);
				Firework fw6 = (Firework) Main.defaultWorld.spawnEntity(new Location(Main.defaultWorld, -10.5, 65.7, 13.5), EntityType.FIREWORK);
				setOfFW.add(fw6);
	            FireworkMeta fwm = fw1.getFireworkMeta();

	            fwm.setPower(2);
	            fwm.addEffect(FireworkEffect.builder().withColor(Color.WHITE).with(Type.BURST).flicker(true).trail(true).build());

	            for(Firework fw : setOfFW) {
	            	fw.setVelocity(new Vector(0, 1, -0.2));
	            	fw.setFireworkMeta(fwm);
	            	fw.detonate();
	            }

				break;

			case PUMPKIN_SEEDS:
				new RandomParticleEffect(Main.instance);
				break;

			default:
				break;

			}
		}
	}

	private static void createAndCheckActiveEffectItem(Inventory inv, Material material, String itName, String var, int slot) {
		ItemStack it = new ItemStack(material, 1);
		ItemMeta itM = it.getItemMeta();
		itM.setDisplayName(itName);

		if(var != null) {
			if(!Main.activeEffects.get(var)) {
				itM.setLore(off);
			} else {
				itM.setLore(on);
				itM.addEnchant(Enchantment.LUCK, 1, false);
			}
		}

		itM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		itM.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		it.setItemMeta(itM);
		inv.setItem(slot, it);
	}

	private static void checkActiveEffectItem(ItemStack current, Player player, String var) {
		ItemMeta itM = current.getItemMeta();
		if(!Main.activeEffects.get(var)) {
			player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, 100, 2);
			itM.setLore(on);
			current.setItemMeta(itM);
			Main.activeEffects.put(var, true);
		} else {
			player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, 100, 0);
			itM.setLore(off);
			current.setItemMeta(itM);
			Main.activeEffects.put(var, false);
		}
	}

	private static void fillEmptyItem(Inventory inv) {

		ItemStack clearSlot = new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1);
		ItemMeta clearSlotMeta = clearSlot.getItemMeta();
		clearSlotMeta.setDisplayName(" ");
		clearSlot.setItemMeta(clearSlotMeta);

		for(int i = 0; i < inv.getSize(); i++) {
			if(inv.getItem(i) == null) {
				inv.setItem(i, clearSlot);
			}
		}
	}

}
