package fr.djredstone.nostoNC.tasks;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.EntityType;
import org.bukkit.scheduler.BukkitRunnable;

import fr.djredstone.nostoNC.Main;

public class LightBottom {
	
	World w = Bukkit.getWorld("Nightclub");
	
	EnderCrystal crystal1 = null, crystal2 = null, crystal3 = null, crystal4 = null, crystal5 = null, crystal6 = null;
	EnderCrystal crystal21 = null, crystal22 = null, crystal23 = null, crystal24 = null;
	
	public LightBottom(Main main) {
		
		new BukkitRunnable() {
			
			int i = 0;
			Boolean start = true;
			
			@Override
			public void run() {
				
				if(crystal1 != null) crystal1.remove();
				if(crystal3 != null) crystal3.remove();
				if(crystal5 != null) crystal5.remove();
				if(crystal2 != null) crystal2.remove();
				if(crystal4 != null) crystal4.remove();
				if(crystal6 != null) crystal6.remove();
				
				if(crystal21 != null) crystal21.remove();
				if(crystal23 != null) crystal23.remove();
				if(crystal22 != null) crystal22.remove();
				if(crystal24 != null) crystal24.remove();
				
				if(Main.lightBottom == true) {
					if(start == true) {
						crystal1 = (EnderCrystal) w.spawnEntity(new Location(w, 11, 70, 4), EntityType.ENDER_CRYSTAL);
						crystal1.setBeamTarget(new Location(w, 9, 63, 11));
						crystal3 = (EnderCrystal) w.spawnEntity(new Location(w, 3, 70, 2), EntityType.ENDER_CRYSTAL);
						crystal3.setBeamTarget(new Location(w, 2, 63, 10));
						crystal5 = (EnderCrystal) w.spawnEntity(new Location(w, -7, 70, 3), EntityType.ENDER_CRYSTAL);
						crystal5.setBeamTarget(new Location(w, -5, 63, 11));
						crystal2 = (EnderCrystal) w.spawnEntity(new Location(w, 7, 70, 3), EntityType.ENDER_CRYSTAL);
						crystal2.setBeamTarget(new Location(w, 5, 63, 11));
						crystal4 = (EnderCrystal) w.spawnEntity(new Location(w, -11, 70, 4), EntityType.ENDER_CRYSTAL);
						crystal4.setBeamTarget(new Location(w, -9, 63, 11));
						crystal6 = (EnderCrystal) w.spawnEntity(new Location(w, -3, 70, 2), EntityType.ENDER_CRYSTAL);
						crystal6.setBeamTarget(new Location(w, -2, 63, 10));
					}
					if(i == 0) {
						i = 1;
						
						crystal21 = (EnderCrystal) w.spawnEntity(new Location(w, 6, 73, 8), EntityType.ENDER_CRYSTAL);
						crystal21.setBeamTarget(new Location(w, 2.0, 66.5, 16.5));
						crystal23 = (EnderCrystal) w.spawnEntity(new Location(w, -2, 73, 7), EntityType.ENDER_CRYSTAL);
						crystal23.setBeamTarget(new Location(w, 0.0, 66.5, 16.5));
						crystal22 = (EnderCrystal) w.spawnEntity(new Location(w, 2, 73, 7), EntityType.ENDER_CRYSTAL);
						crystal22.setBeamTarget(new Location(w, 1.0, 66.5, 16.5));
						crystal24 = (EnderCrystal) w.spawnEntity(new Location(w, -6, 73, 8), EntityType.ENDER_CRYSTAL);
						crystal24.setBeamTarget(new Location(w, -1.0, 66.5, 16.5));
					} else if(i == 1) {
						i = 0;
						crystal1 = (EnderCrystal) w.spawnEntity(new Location(w, 11, 70, 4), EntityType.ENDER_CRYSTAL);
						crystal1.setBeamTarget(new Location(w, 9, 63, 11));
						crystal3 = (EnderCrystal) w.spawnEntity(new Location(w, 3, 70, 2), EntityType.ENDER_CRYSTAL);
						crystal3.setBeamTarget(new Location(w, 2, 63, 10));
						crystal5 = (EnderCrystal) w.spawnEntity(new Location(w, -7, 70, 3), EntityType.ENDER_CRYSTAL);
						crystal5.setBeamTarget(new Location(w, -5, 63, 11));
						crystal2 = (EnderCrystal) w.spawnEntity(new Location(w, 7, 70, 3), EntityType.ENDER_CRYSTAL);
						crystal2.setBeamTarget(new Location(w, 5, 63, 11));
						crystal4 = (EnderCrystal) w.spawnEntity(new Location(w, -11, 70, 4), EntityType.ENDER_CRYSTAL);
						crystal4.setBeamTarget(new Location(w, -9, 63, 11));
						crystal6 = (EnderCrystal) w.spawnEntity(new Location(w, -3, 70, 2), EntityType.ENDER_CRYSTAL);
						crystal6.setBeamTarget(new Location(w, -2, 63, 10));
						
					}
					start = false;
				} else {
					start = true;
					
					if(crystal1 != null) crystal1.remove();
					if(crystal3 != null) crystal3.remove();
					if(crystal5 != null) crystal5.remove();
					if(crystal2 != null) crystal2.remove();
					if(crystal4 != null) crystal4.remove();
					if(crystal6 != null) crystal6.remove();
					
					if(crystal21 != null) crystal21.remove();
					if(crystal23 != null) crystal23.remove();
					if(crystal22 != null) crystal22.remove();
					if(crystal24 != null) crystal24.remove();
					
					if(w.getBlockAt(new Location(w, 0, 63, 13)) != null) w.getBlockAt(new Location(w, 0, 65, 13)).setType(Material.REDSTONE_BLOCK);
				}
				
			}
		}.runTaskTimer(main, 0, 10);
		
	}

}
