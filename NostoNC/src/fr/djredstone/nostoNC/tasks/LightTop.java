package fr.djredstone.nostoNC.tasks;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.scheduler.BukkitRunnable;

import fr.djredstone.nostoNC.Main;

public class LightTop {
	
	World w = Bukkit.getWorld("Nightclub");
	
	EnderCrystal crystal1 = null, crystal2 = null, crystal3 = null, crystal4 = null;
	EnderCrystal crystal21 = null, crystal22 = null, crystal23 = null, crystal24 = null;

	public LightTop(Main main) {

		new BukkitRunnable() {
			
			int i = 0;
			Boolean start = true;
			
			@Override
			public void run() {
				
				List<Entity> listOfEntity = Bukkit.getWorld("Nightclub").getEntities();
				for(Entity e : listOfEntity) {
					if(e instanceof EnderCrystal) {
						e.remove();
					}
				}
				
				if(Main.activeEffects.get("lightTop") == true) {
					if(start == true) {
						crystal1 = (EnderCrystal) w.spawnEntity(new Location(w, 12, 62, 6), EntityType.ENDER_CRYSTAL);
						crystal1.setBeamTarget(new Location(w, 9, 75,14));
						crystal3 = (EnderCrystal) w.spawnEntity(new Location(w, 3, 62, 6), EntityType.ENDER_CRYSTAL);
						crystal3.setBeamTarget(new Location(w, 6,75,14));
						crystal2 = (EnderCrystal) w.spawnEntity(new Location(w, -12, 62, 6), EntityType.ENDER_CRYSTAL);
						crystal2.setBeamTarget(new Location(w, -9, 75, 14));
						crystal4 = (EnderCrystal) w.spawnEntity(new Location(w, -3, 62, 6), EntityType.ENDER_CRYSTAL);
						crystal4.setBeamTarget(new Location(w, -6, 75, 14));
					}
					if(i == 0) {
						i = 1;
						
						crystal21 = (EnderCrystal) w.spawnEntity(new Location(w, 10, 70, 0), EntityType.ENDER_CRYSTAL);
						crystal21.setBeamTarget(new Location(w, 6, 75, 10));
						crystal23 = (EnderCrystal) w.spawnEntity(new Location(w, 5, 70, 0), EntityType.ENDER_CRYSTAL);
						crystal23.setBeamTarget(new Location(w, 2, 75, 9));
						crystal22 = (EnderCrystal) w.spawnEntity(new Location(w, -10, 70, 0), EntityType.ENDER_CRYSTAL);
						crystal22.setBeamTarget(new Location(w, -6, 75, 10));
						crystal24 = (EnderCrystal) w.spawnEntity(new Location(w, -5, 70, 0), EntityType.ENDER_CRYSTAL);
						crystal24.setBeamTarget(new Location(w, -2, 75, 9));
					} else if(i == 1) {
						i = 0;
						crystal1 = (EnderCrystal) w.spawnEntity(new Location(w, 12, 62, 6), EntityType.ENDER_CRYSTAL);
						crystal1.setBeamTarget(new Location(w, 9, 75,14));
						crystal3 = (EnderCrystal) w.spawnEntity(new Location(w, 3, 62, 6), EntityType.ENDER_CRYSTAL);
						crystal3.setBeamTarget(new Location(w, 6,75,14));
						crystal2 = (EnderCrystal) w.spawnEntity(new Location(w, -12, 62, 6), EntityType.ENDER_CRYSTAL);
						crystal2.setBeamTarget(new Location(w, -9, 75, 14));
						crystal4 = (EnderCrystal) w.spawnEntity(new Location(w, -3, 62, 6), EntityType.ENDER_CRYSTAL);
						crystal4.setBeamTarget(new Location(w, -6, 75, 14));
						
					}
					start = false;
				} else {
					start = true;
					
				}
				
			}
		}.runTaskTimer(main, 0, 10);
		
	}

}
