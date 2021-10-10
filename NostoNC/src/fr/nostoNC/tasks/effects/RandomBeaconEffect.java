package fr.nostoNC.tasks.effects;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.scheduler.BukkitRunnable;

import fr.nostoNC.Main;

public class RandomBeaconEffect {
	
	Random r = new Random();

	public RandomBeaconEffect(Main main) {
		
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:nightclub run fill -16 62 -26 16 63 8 minecraft:hopper");
		
		new BukkitRunnable() {
			
			int color = 0;
			
			@Override
			public void run() {
				
				if(Main.activeEffects.get("randomBeacon")) {
					color = r.nextInt(5);
					
					if(color == 0) {
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:nightclub run fill -16 62 -26 16 62 8 minecraft:red_stained_glass");
					} else if(color == 0) {
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:nightclub run fill -16 62 -26 16 62 8 minecraft:orange_stained_glass");
					} else if(color == 1) {
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:nightclub run fill -16 62 -26 16 62 8 minecraft:yellow_stained_glass");
					} else if(color == 2) {
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:nightclub run fill -16 62 -26 16 62 8 minecraft:green_stained_glass");
					} else if(color == 3) {
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:nightclub run fill -16 62 -26 16 62 8 minecraft:blue_stained_glass");
					} else if(color == 4) {
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:nightclub run fill -16 62 -26 16 62 8 minecraft:purple_stained_glass");
					} else {
						color = 0;
					}
				}
				
			}
		}.runTaskTimer(main, 0, 14);
		
		new BukkitRunnable() {
			
			@Override
			public void run() {
				
				new BukkitRunnable() {
					
					int stade = 0;
					
					@Override
					public void run() {
						
						if(Main.activeEffects.get("randomBeacon") == true) {
							if(stade == 0) {
								for(int i = 0; i < 50; i++) {
									Bukkit.getWorld("Nightclub").getBlockAt(new Location(null, r.nextInt(16 + 16) - 16, 63, r.nextInt(8 + 26) - 26)).setType(Material.HOPPER);
								}
								stade = 1;
							} else {
								Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:nightclub run fill -16 63 -26 16 63 8 minecraft:gray_concrete");
								stade = 0;
							}
						} else {
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "execute in minecraft:nightclub run fill -16 63 -26 16 63 8 minecraft:gray_concrete");
						}
					}
				}.runTaskTimer(main, 0, 7);
				
			}
		}.runTaskLater(main, 200);
		
	}

}