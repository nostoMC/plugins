package fr.djredstone.nostoNC.commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.djredstone.nostoNC.Main;

public class CommandNightclub implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		Player player = (Player) sender;
		
		if(player.getWorld() != Bukkit.getWorld("Nightclub")) return true;
		
		if(cmd.getName().equalsIgnoreCase("nightclub")) {
			
			if(args.length == 1) {
				if(args[0].equalsIgnoreCase("menu")) {
					// if(dj.contains(player)) {
						Inventory inv = Bukkit.createInventory(null, 54, "§2§lGestioraire des effets");
						
						ItemStack it = new ItemStack(Material.STRING, 1);
						ItemMeta itM = it.getItemMeta();
						itM.setDisplayName("§7§lFloor Smoke");
						
						if(Main.floorSmoke == false ) {
							itM.setLore(Main.off);
						} else {
							itM.setLore(Main.on);
						}
						
						itM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
						it.setItemMeta(itM);
						inv.setItem(10, it);
						
						// ---------------------------------------------------------
						
						it = new ItemStack(Material.REDSTONE_LAMP, 1);
						itM = it.getItemMeta();
						itM.setDisplayName("§8§lStrobe");
						
						if(Main.strobe == false ) {
							itM.setLore(Main.off);
						} else {
							itM.setLore(Main.on);
						}
						
						itM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
						it.setItemMeta(itM);
						inv.setItem(19, it);
						
						// ---------------------------------------------------------
						
						it = new ItemStack(Material.FIREWORK_ROCKET, 1);
						itM = it.getItemMeta();
						itM.setDisplayName("§8§lFeux d'artifices");
						
						itM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
						it.setItemMeta(itM);
						inv.setItem(12, it);
						
						// ---------------------------------------------------------
						
						it = new ItemStack(Material.END_CRYSTAL, 1);
						itM = it.getItemMeta();
						itM.setDisplayName("§5§lLight Bottom");
						
						if(Main.lightBottom == false ) {
							itM.setLore(Main.off);
						} else {
							itM.setLore(Main.on);
						}
						
						itM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
						it.setItemMeta(itM);
						inv.setItem(32, it);
						
						// ---------------------------------------------------------
						
						it = new ItemStack(Material.END_CRYSTAL, 1);
						itM = it.getItemMeta();
						itM.setDisplayName("§5§lLight Top");
						
						if(Main.lightTop == false ) {
							itM.setLore(Main.off);
						} else {
							itM.setLore(Main.on);
						}
						
						itM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
						it.setItemMeta(itM);
						inv.setItem(14, it);
						
						// ---------------------------------------------------------
						
						it = new ItemStack(Material.CLOCK, 1);
						itM = it.getItemMeta();
						itM.setDisplayName("§6§lCadence");
						
						ArrayList<String> lore = new ArrayList<String>();
						lore.add("§eLa cadence est actuellement à §6§l" + Main.cadence + " §eticks !");
						lore.add("§cClick droit pour retirer du temps");
						lore.add("§aClick gauche pour ajouter du temps");
						itM.setLore(lore);
						
						itM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
						it.setItemMeta(itM);
						inv.setItem(41, it);
						
						// ---------------------------------------------------------
						
						it = new ItemStack(Material.BEACON, 1);
						itM = it.getItemMeta();
						itM.setDisplayName("§5§lRandom Beam");
						
						if(Main.randomBeacon == false ) {
							itM.setLore(Main.off);
						} else {
							itM.setLore(Main.on);
						}
						
						itM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
						it.setItemMeta(itM);
						inv.setItem(16, it);
						
						// ---------------------------------------------------------
						
						it = new ItemStack(Material.SEA_LANTERN, 1);
						itM = it.getItemMeta();
						itM.setDisplayName("§5§lSphere");
						
						if(Main.sphere == false ) {
							itM.setLore(Main.off);
						} else {
							itM.setLore(Main.on);
						}
						
						itM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
						it.setItemMeta(itM);
						inv.setItem(28, it);
						
						// ---------------------------------------------------------
						
						it = new ItemStack(Material.CRYING_OBSIDIAN, 1);
						itM = it.getItemMeta();
						itM.setDisplayName("§f§lWave");
						
						if(Main.wave == false ) {
							itM.setLore(Main.off);
						} else {
							itM.setLore(Main.on);
						}
						
						itM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
						it.setItemMeta(itM);
						inv.setItem(37, it);
						
						// ---------------------------------------------------------
						
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
					// }
				}
			} else if(args.length == 2) {
				if(args[1].equalsIgnoreCase("dj")) {
					if(args[0].equalsIgnoreCase("join")) {
						if(Main.dj.size() != 1) {
							Main.dj.add(player);
							player.teleport(new Location(Bukkit.getWorld("Nightclub"), 0.5, 67, 12.5, 180, 0));
							Bukkit.broadcastMessage("");
							Bukkit.broadcastMessage("§6§l" + player.getName() + " §eest notre nouveau DJ !");
						} else {
							if(Main.dj.contains(player)) {
								player.teleport(new Location(Bukkit.getWorld("Nightclub"), 0.5, 67, 12.5, 180, 0));
								player.sendMessage("");
								player.sendMessage("§eDe nouveau sur la scène !");
							} else {
								player.sendMessage("");
								player.sendMessage("§cUn DJ est déjà là !");
							}
						}
					} else if(args[0].equalsIgnoreCase("leave")) {
						if(Main.dj.contains(player)) {
							Main.dj.remove(player);
							player.teleport(new Location(Bukkit.getWorld("Nightclub"), 0.5, 64, 0.5, 0, 0));
							Bukkit.broadcastMessage("");
							Bukkit.broadcastMessage("§6§l" + player.getName() + " §en'est plus DJ !");
						}
					}
				}
			}
		}
		
		return false;
	}

}
