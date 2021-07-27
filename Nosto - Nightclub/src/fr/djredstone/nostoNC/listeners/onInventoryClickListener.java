package fr.djredstone.nostoNC.listeners;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import fr.djredstone.nostoNC.Main;

public class onInventoryClickListener implements Listener {
	
	static Boolean floorSmoke = Main.getfloorSmoke();
	static Boolean strobe = Main.getStrobe();
	static Boolean lightBottom = Main.getLightBottom();
	static Boolean randomBeacon = Main.getRandomBeacon();
	static ArrayList<String> on = Main.getOnLore();
	static ArrayList<String> off = Main.getOffLore();

	public onInventoryClickListener(Main main) {
		main.getServer().getPluginManager().registerEvents(this, main);
	}

	@EventHandler
	public void onClick(InventoryClickEvent event) {
		
		ItemStack current = event.getCurrentItem();
		
		if(current == null) return;
		
		if(event.getView().getTitle().equalsIgnoreCase("§2§lGestioraire des effets")) {
			event.setCancelled(true);
			
			if(current.getType() == Material.STRING) {
				ItemStack it = current;
				ItemMeta itM = it.getItemMeta();
				floorSmoke = Main.getfloorSmoke();
				if(floorSmoke == false ) {
					itM.setLore(on);
					it.setItemMeta(itM);
					Main.setfloorSmoke(true);
				} else {
					itM.setLore(off);
					it.setItemMeta(itM);
					Main.setfloorSmoke(false);
				}
			} else if(current.getType() == Material.REDSTONE_LAMP) {
				ItemStack it = current;
				ItemMeta itM = it.getItemMeta();
				strobe = Main.getStrobe();
				if(strobe == false ) {
					itM.setLore(on);
					it.setItemMeta(itM);
					Main.setStrobe(true);
				} else {
					itM.setLore(off);
					it.setItemMeta(itM);
					Main.setStrobe(false);
				}
			} else if(current.getType() == Material.FIREWORK_ROCKET) {
				Firework fw1 = (Firework) Bukkit.getWorld("Nightclub").spawnEntity(new Location(Bukkit.getWorld("Nightclub"), 9.5, 65.7, 11.5), EntityType.FIREWORK);
				Firework fw2 = (Firework) Bukkit.getWorld("Nightclub").spawnEntity(new Location(Bukkit.getWorld("Nightclub"), 5.5, 65.7, 11.5), EntityType.FIREWORK);
				Firework fw3 = (Firework) Bukkit.getWorld("Nightclub").spawnEntity(new Location(Bukkit.getWorld("Nightclub"), 2.5, 65.7, 10.5), EntityType.FIREWORK);
				Firework fw4 = (Firework) Bukkit.getWorld("Nightclub").spawnEntity(new Location(Bukkit.getWorld("Nightclub"), -1.5, 65.7, 10.5), EntityType.FIREWORK);
				Firework fw5 = (Firework) Bukkit.getWorld("Nightclub").spawnEntity(new Location(Bukkit.getWorld("Nightclub"), -4.5, 65.7, 11.5), EntityType.FIREWORK);
				Firework fw6 = (Firework) Bukkit.getWorld("Nightclub").spawnEntity(new Location(Bukkit.getWorld("Nightclub"), -8.5, 65.7, 11.5), EntityType.FIREWORK);
	            FireworkMeta fwm = fw1.getFireworkMeta();
	           
	            fwm.setPower(2);
	            fwm.addEffect(FireworkEffect.builder().withColor(Color.WHITE).with(Type.BURST).flicker(true).trail(true).build());
	            fw1.setVelocity(new Vector(0, 1, 0));
	            fw2.setVelocity(new Vector(0, 1, 0));
	            fw3.setVelocity(new Vector(0, 1, 0));
	            fw4.setVelocity(new Vector(0, 1, 0));
	            fw5.setVelocity(new Vector(0, 1, 0));
	            fw6.setVelocity(new Vector(0, 1, 0));
	           
	            fw1.setFireworkMeta(fwm);
	            fw2.setFireworkMeta(fwm);
	            fw3.setFireworkMeta(fwm);
	            fw4.setFireworkMeta(fwm);
	            fw5.setFireworkMeta(fwm);
	            fw6.setFireworkMeta(fwm);
	            fw1.detonate();
	            fw2.detonate();
	            fw3.detonate();
	            fw4.detonate();
	            fw5.detonate();
	            fw6.detonate();
			} else if(current.getType() == Material.END_CRYSTAL) {
				ItemStack it = current;
				ItemMeta itM = it.getItemMeta();
				lightBottom = Main.getLightBottom();
				if(lightBottom == false ) {
					itM.setLore(on);
					it.setItemMeta(itM);
					Main.setlightBottom(true);
				} else {
					itM.setLore(off);
					it.setItemMeta(itM);
					Main.setlightBottom(false);
				}
			} else if(current.getType() == Material.BEACON) {
				ItemStack it = current;
				ItemMeta itM = it.getItemMeta();
				randomBeacon = Main.getRandomBeacon();
				if(randomBeacon == false ) {
					itM.setLore(on);
					it.setItemMeta(itM);
					Main.setRandomBeacon(true);
				} else {
					itM.setLore(off);
					it.setItemMeta(itM);
					Main.setRandomBeacon(false);
				}
			}
		}
	}
	
}
