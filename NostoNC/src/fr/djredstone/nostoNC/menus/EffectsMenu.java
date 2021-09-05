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
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import fr.djredstone.nostoNC.Main;
import fr.djredstone.nostoNC.tasks.DjLaserEffect;

public class EffectsMenu implements Listener {
	
	static final ArrayList<String> on = new ArrayList<String>();
	static final ArrayList<String> off = new ArrayList<String>();
	
	public EffectsMenu() {
		off.add("§c§loff");
		on.add("§a§lon");
	}

	public static void openMenu(Player player) {
		
		Inventory inv = Bukkit.createInventory(null, 54, "§2§lGestioraire des effets");
		
		// ----------------------------------------------------------------------------------------------------------------------
		
		createAndCheckActiveEffectItem(inv, Material.STRING, "§7§lFloor Smoke", "floorSmoke", 10);
		
		createAndCheckActiveEffectItem(inv, Material.REDSTONE_LAMP, "§8§lStrobe", "strobe", 19);

		createAndCheckActiveEffectItem(inv, Material.SEA_LANTERN, "§5§lSphere", "sphere", 28);
		
		createAndCheckActiveEffectItem(inv, Material.CRYING_OBSIDIAN, "§f§lWave", "wave", 37);
		
		// ----------------------------------------------------------------------------------------------------------------------
		
		createAndCheckActiveEffectItem(inv, Material.FIREWORK_ROCKET, "§8§lFeux d'artifices", null, 12);
		
		createAndCheckActiveEffectItem(inv, Material.PUMPKIN_SEEDS, "§f§lParticules aléatoires", null, 21);
		
		// ----------------------------------------------------------------------------------------------------------------------
		
		createAndCheckActiveEffectItem(inv, Material.END_CRYSTAL, "§5§lLight Top", "lightTop", 14);
		
		createAndCheckActiveEffectItem(inv, Material.END_CRYSTAL, "§5§lLight Bottom", "lightBottom", 23);
		
		createAndCheckActiveEffectItem(inv, Material.BEACON, "§5§lRandom Beam", "randomBeacon", 32);
		
		// ----------------------------------------------------------------------------------------------------------------------
		
		createAndCheckActiveEffectItem(inv, Material.LEVER, "§a§lLaser UP/DOWN", "djLaser", 16);
		
		createAndCheckActiveEffectItem(inv, Material.LEVER, "§a§lLaser gobo", "goboLaser", 25);
		
		createAndCheckActiveEffectItem(inv, Material.LEVER, "§a§lLaser random", "randomLaser", 34);
		
		// ----------------------------------------------------------------------------------------------------------------------
		
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
				
			case REDSTONE_LAMP:
				checkActiveEffectItem(current, player, "strobe");
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
					checkActiveEffectItem(current, player, "lightBottom");
				} else if(current.getItemMeta().getDisplayName().equalsIgnoreCase("§5§lLight Top")) {
					checkActiveEffectItem(current, player, "lightTop");
				}
				break;
				
			case BEACON:
				checkActiveEffectItem(current, player, "randomBeacon");
				break;
				
			case SEA_LANTERN:
				checkActiveEffectItem(current, player, "sphere");
				break;
				
			case CRYING_OBSIDIAN:
				checkActiveEffectItem(current, player, "wave");
				break;
				
			case LEVER:
				if(current.getItemMeta().getDisplayName().equalsIgnoreCase("§a§lLaser UP/DOWN")) {
					if(!Main.activeEffects.get("djLaser")) {
						DjLaserEffect.light1.start(Main.getInstance());
						DjLaserEffect.light2.start(Main.getInstance());
						DjLaserEffect.light3.start(Main.getInstance());
						DjLaserEffect.light4.start(Main.getInstance());
						DjLaserEffect.light5.start(Main.getInstance());
					} else {
						if(DjLaserEffect.light1.isStarted()) DjLaserEffect.light1.stop();
						if(DjLaserEffect.light2.isStarted()) DjLaserEffect.light2.stop();
						if(DjLaserEffect.light3.isStarted()) DjLaserEffect.light3.stop();
						if(DjLaserEffect.light4.isStarted()) DjLaserEffect.light4.stop();
						if(DjLaserEffect.light5.isStarted()) DjLaserEffect.light5.stop();
					}
					checkActiveEffectItem(current, player, "djLaser");
				} else if(current.getItemMeta().getDisplayName().equalsIgnoreCase("§a§lLaser gobo")) {
					checkActiveEffectItem(current, player, "goboLaser");
				} else if(current.getItemMeta().getDisplayName().equalsIgnoreCase("§a§lLaser random")) {
					checkActiveEffectItem(current, player, "randomLaser");
				}
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
	
	private static void checkActiveEffectItem(ItemStack current, Player player, String var) {
		ItemMeta itM = current.getItemMeta();
		if(Main.activeEffects.get(var) == false ) {
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
