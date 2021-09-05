package fr.djredstone.nostoNC.tasks;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.scheduler.BukkitRunnable;

import fr.djredstone.nostoNC.Lasers;
import fr.djredstone.nostoNC.Main;

public class StartTask {

	public StartTask(Main main) {

		new BukkitRunnable() {
			
			@Override
			public void run() {
				
				Main.activeEffects.put("floorSmoke", false);
				Main.activeEffects.put("strobe", false);
				Main.activeEffects.put("lightBottom", false);
				Main.activeEffects.put("lightTop", false);
				Main.activeEffects.put("randomBeacon", false);
				Main.activeEffects.put("sphere", false);
				Main.activeEffects.put("wave", false);
				Main.activeEffects.put("djLaser", false);
				Main.activeEffects.put("goboLaser", false);
				Main.activeEffects.put("randomLaser", false);
				
				new FloorSmokeEffect(main);
				new StrobeEffect(main);
				new SphereEffect(main);
				new WaveEffect(main);
				
				new LightTop(main);
				new LightBottom(main);
				new RandomBeaconEffect(main);
				
				new DjLaserEffect(main);
				new GoboLaserEffect(main);
				new RandomLaserEffect(main);
				
				new DjGlowing(main);
				
				new VIPpass(main);

				new Location(Bukkit.getWorld("Nightclub"), 20, 64, 7).getBlock().setType(Material.REDSTONE_BLOCK);
				
				new Lasers();
				
			}
		}.runTaskLater(main, 1);
		
	}

}
