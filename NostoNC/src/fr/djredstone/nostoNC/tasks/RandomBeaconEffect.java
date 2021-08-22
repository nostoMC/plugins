package fr.djredstone.nostoNC.tasks;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.scheduler.BukkitRunnable;

import fr.djredstone.nostoNC.Main;

public class RandomBeaconEffect {
	
	Random r = new Random();

	public RandomBeaconEffect(Main main) {
		
		Bukkit.getWorld("Nightclub").getBlockAt(new Location(Bukkit.getWorld("Nightclub"), 20, 64, 14)).setType(Material.REDSTONE_BLOCK);
		
		new BukkitRunnable() {
			
			int color = 0;
			
			@Override
			public void run() {
				
				color = r.nextInt(5);
				
				if(color == 0) {
					Bukkit.getWorld("Nightclub").getBlockAt(new Location(Bukkit.getWorld("Nightclub"), 33, 64, -16)).setType(Material.REDSTONE_BLOCK);
				} else if(color == 1) {
					Bukkit.getWorld("Nightclub").getBlockAt(new Location(Bukkit.getWorld("Nightclub"), 33, 64, -14)).setType(Material.REDSTONE_BLOCK);
				} else if(color == 2) {
					Bukkit.getWorld("Nightclub").getBlockAt(new Location(Bukkit.getWorld("Nightclub"), 33, 64, -12)).setType(Material.REDSTONE_BLOCK);
				} else if(color == 3) {
					Bukkit.getWorld("Nightclub").getBlockAt(new Location(Bukkit.getWorld("Nightclub"), 33, 64, -10)).setType(Material.REDSTONE_BLOCK);
				} else if(color == 4) {
					Bukkit.getWorld("Nightclub").getBlockAt(new Location(Bukkit.getWorld("Nightclub"), 33, 64, -8)).setType(Material.REDSTONE_BLOCK);
				} else if(color == 5) {
					Bukkit.getWorld("Nightclub").getBlockAt(new Location(Bukkit.getWorld("Nightclub"), 33, 64, -6)).setType(Material.REDSTONE_BLOCK);
				} else {
					color = 0;
				}
				
			}
		}.runTaskTimer(main, 0, 14);
		
		new BukkitRunnable() {
			
			@Override
			public void run() {
				
				Bukkit.getWorld("Nightclub").getBlockAt(new Location(Bukkit.getWorld("Nightclub"), 18, 64, 14)).setType(Material.REDSTONE_BLOCK);
				
				new BukkitRunnable() {
					
					int stade = 0;
					
					@Override
					public void run() {
						
						if(Main.randomBeacon == true) {
							if(stade == 0) {
								for(int i = 0; i < 50; i++) {
									Bukkit.getWorld("Nightclub").getBlockAt(new Location(null, r.nextInt(16 + 16) - 16, 63, r.nextInt(8 + 26) - 26)).setType(Material.GLASS);
								}
								stade = 1;
							} else {
								Bukkit.getWorld("Nightclub").getBlockAt(new Location(null, 22, 64, 14)).setType(Material.REDSTONE_BLOCK);
								stade = 0;
							}
						} else {
							Bukkit.getWorld("Nightclub").getBlockAt(new Location(null, 22, 64, 14)).setType(Material.REDSTONE_BLOCK);
						}
					}
				}.runTaskTimer(main, 0, 7);
				
			}
		}.runTaskLater(main, 200);
		
	}

}
