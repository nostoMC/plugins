package fr.djredstone.nostoNC.menus;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
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

import fr.djredstone.nostoNC.Main;

public class EffectsMenu implements Listener {
	
	static final ArrayList<String> on = new ArrayList<String>();
	static final ArrayList<String> off = new ArrayList<String>();
	
	public EffectsMenu() {
		off.add("§c§loff");
		on.add("§a§lon");
	}

	public static void openMenu(Player player) {
		
		Inventory inv = Bukkit.createInventory(null, 54, "§2§lGestioraire des effets");
		
		createAndCheckActiveEffectItem(inv, Material.STRING, "§7§lFloor Smoke", "floorSmoke", 10);
		
		createAndCheckActiveEffectItem(inv, Material.REDSTONE_LAMP, "§8§lStrobe", "strobe", 19);
		
		createAndCheckActiveEffectItem(inv, Material.FIREWORK_ROCKET, "§8§lFeux d'artifices", null, 12);
		
		createAndCheckActiveEffectItem(inv, Material.END_CRYSTAL, "§5§lLight Bottom", "lightBottom", 32);
		
		createAndCheckActiveEffectItem(inv, Material.END_CRYSTAL, "§5§lLight Top", "lightTop", 14);
		
		createAndCheckActiveEffectItem(inv, Material.BEACON, "§5§lRandom Beam", "randomBeacon", 16);
		
		createAndCheckActiveEffectItem(inv, Material.SEA_LANTERN, "§5§lSphere", "sphere", 28);
		
		createAndCheckActiveEffectItem(inv, Material.CRYING_OBSIDIAN, "§f§lWave", "wave", 37);
		
		createAndCheckActiveEffectItem(inv, Material.PUMPKIN_SEEDS, "§f§lParticules aléatoires", null, 21);
		
		// ---------------------------------------------------------
		
		ItemStack it = new ItemStack(Material.CLOCK, 1);
		ItemMeta itM = it.getItemMeta();
		itM.setDisplayName("");
				
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("§eLa cadence est actuellement à §6§l" + Main.activeEffects.get("cadence") + " §eticks !");
		lore.add("§cClick droit pour retirer du temps");
		lore.add("§aClick gauche pour ajouter du temps");
		itM.setLore(lore);
				
		itM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		it.setItemMeta(itM);
		inv.setItem(41, it);
				
		// ---------------------------------------------------------
		
		fillEmptyItem(inv);
		
		player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 100, 1);
		player.openInventory(inv);
	}
	
	private static void createAndCheckActiveEffectItem(Inventory inv, Material material, String itName, String var, int slot) {
		ItemStack it = new ItemStack(material, 1);
		ItemMeta itM = it.getItemMeta();
		itM.setDisplayName(itName);
		
		if(var != null) {
			if(Main.activeEffects.get(var) == false ) {
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
	
	@EventHandler
	public void onClick(InventoryClickEvent event) {
		
		ItemStack current = event.getCurrentItem();
		Player player = (Player) event.getWhoClicked();
		
		if(current == null) return;
		
		if(event.getView().getTitle().equalsIgnoreCase("§2§lGestioraire des effets")) {
			event.setCancelled(true);
			
			ItemStack it;
			ItemMeta itM;
			
			switch(current.getType()) {
			
			case STRING:
				it = current;
				itM = it.getItemMeta();
				if(Main.floorSmoke == false ) {
					player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, 100, 2);
					itM.setLore(on);
					it.setItemMeta(itM);
					Main.floorSmoke = true;
				} else {
					player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, 100, 0);
					itM.setLore(off);
					it.setItemMeta(itM);
					Main.floorSmoke = false;
				}
				break;
				
			case REDSTONE_LAMP:
				it = current;
				itM = it.getItemMeta();
				if(Main.strobe == false ) {
					player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, 100, 2);
					itM.setLore(on);
					it.setItemMeta(itM);
					Main.strobe = true;
				} else {
					player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, 100, 0);
					itM.setLore(off);
					it.setItemMeta(itM);
					Main.strobe = false;
				}
				break;
				
			case FIREWORK_ROCKET:
				
				Set<Firework> setOfFW = new HashSet<Firework>();
				
				Firework fw1 = (Firework) Bukkit.getWorld("Nightclub").spawnEntity(new Location(Bukkit.getWorld("Nightclub"), 11.5, 65.7, 13.5), EntityType.FIREWORK);
				setOfFW.add(fw1);
				Firework fw2 = (Firework) Bukkit.getWorld("Nightclub").spawnEntity(new Location(Bukkit.getWorld("Nightclub"), 7.5, 65.7, 14.5), EntityType.FIREWORK);
				setOfFW.add(fw2);
				Firework fw3 = (Firework) Bukkit.getWorld("Nightclub").spawnEntity(new Location(Bukkit.getWorld("Nightclub"), 3.5, 65.7, 15.5), EntityType.FIREWORK);
				setOfFW.add(fw3);
				Firework fw4 = (Firework) Bukkit.getWorld("Nightclub").spawnEntity(new Location(Bukkit.getWorld("Nightclub"), -2.5, 65.7, 15.5), EntityType.FIREWORK);
				setOfFW.add(fw4);
				Firework fw5 = (Firework) Bukkit.getWorld("Nightclub").spawnEntity(new Location(Bukkit.getWorld("Nightclub"), -6.5, 65.7, 14.5), EntityType.FIREWORK);
				setOfFW.add(fw5);
				Firework fw6 = (Firework) Bukkit.getWorld("Nightclub").spawnEntity(new Location(Bukkit.getWorld("Nightclub"), -10.5, 65.7, 13.5), EntityType.FIREWORK);
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
				new Location(Bukkit.getWorld("Nightclub"), -12, 62, 11).getBlock().setType(Material.REDSTONE_BLOCK);
				break;
				
			case END_CRYSTAL:
				if(current.getItemMeta().getDisplayName().equalsIgnoreCase("§5§lLight Bottom")) {
					it = current;
					itM = it.getItemMeta();
					if(Main.lightBottom == false ) {
						player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, 100, 2);
						itM.setLore(on);
						it.setItemMeta(itM);
						Main.lightBottom = true;
					} else {
						player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, 100, 0);
						itM.setLore(off);
						it.setItemMeta(itM);
						Main.lightBottom = false;
					}
				} else if(current.getItemMeta().getDisplayName().equalsIgnoreCase("§5§lLight Top")) {
					it = current;
					itM = it.getItemMeta();
					if(Main.lightTop == false ) {
						player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, 100, 2);
						itM.setLore(on);
						it.setItemMeta(itM);
						Main.lightTop = true;
					} else {
						player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, 100, 0);
						itM.setLore(off);
						it.setItemMeta(itM);
						Main.lightTop = false;
					}
				}
				break;
				
			case CLOCK:
				it = current;
				itM = it.getItemMeta();
				if(event.getAction() == InventoryAction.PICKUP_HALF) {
					player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, 100, 1);
					if(Main.cadence != 2) Main.cadence = Main.cadence - 1;
				} else if(event.getAction() == InventoryAction.PICKUP_ALL) {
					player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, 100, 2);
					Main.cadence = Main.cadence + 1;
				}
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("§eLa cadence est actuellement à §6§l" + Main.cadence + " §eticks !");
				lore.add("§cClick droit pour retirer du temps");
				lore.add("§aClick gauche pour ajouter du temps");
				itM.setLore(lore);
				it.setItemMeta(itM);
				break;
				
			case BEACON:
				it = current;
				itM = it.getItemMeta();
				if(Main.randomBeacon == false ) {
					player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, 100, 2);
					itM.setLore(on);
					it.setItemMeta(itM);
					Main.randomBeacon = true;
				} else {
					player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, 100, 0);
					itM.setLore(off);
					it.setItemMeta(itM);
					Main.randomBeacon = false;
				}
				break;
				
			case SEA_LANTERN:
				it = current;
				itM = it.getItemMeta();
				if(Main.sphere == false ) {
					player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, 100, 2);
					itM.setLore(on);
					it.setItemMeta(itM);
					Main.sphere = true;
				} else {
					player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, 100, 0);
					itM.setLore(off);
					it.setItemMeta(itM);
					Main.sphere = false;
				}
				break;
				
			case CRYING_OBSIDIAN:
				it = current;
				itM = it.getItemMeta();
				if(Main.wave == false ) {
					player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, 100, 2);
					itM.setLore(on);
					it.setItemMeta(itM);
					Main.wave = true;
				} else {
					player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, 100, 0);
					itM.setLore(off);
					it.setItemMeta(itM);
					Main.wave = false;
				}
				break;
			
			default:
				break;
			
			}
		}
	}

}
