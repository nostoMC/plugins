package fr.djredstone.nostoNC;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import fr.djredstone.nostoNC.commands.CommandNightclub;
import fr.djredstone.nostoNC.commands.TabNightclub;
import fr.djredstone.nostoNC.listeners.OnPlayerChangeWorldListener;
import fr.djredstone.nostoNC.listeners.OnPlayerDamageListener;
import fr.djredstone.nostoNC.listeners.OnPlayerInteractListener;
import fr.djredstone.nostoNC.listeners.OnResourcepackStatusListener;
import fr.djredstone.nostoNC.menus.EffectsMenu;
import fr.djredstone.nostoNC.tasks.DjGlowing;
import fr.djredstone.nostoNC.tasks.FloorSmokeEffect;
import fr.djredstone.nostoNC.tasks.LightBottom;
import fr.djredstone.nostoNC.tasks.LightTop;
import fr.djredstone.nostoNC.tasks.RandomBeaconEffect;
import fr.djredstone.nostoNC.tasks.SphereEffect;
import fr.djredstone.nostoNC.tasks.StrobeEffect;
import fr.djredstone.nostoNC.tasks.WaveEffect;

public class Main extends JavaPlugin {
	
	public static ArrayList<Player> dj = new ArrayList<Player>();
	public static ArrayList<Player> vip = new ArrayList<Player>();
	
	public static HashMap<String, Boolean> activeEffects = new HashMap<String, Boolean>();
	
	public static int cadence = 10;
	
	public static Main instance;
	
	public static Laser test;
	
	public static JavaPlugin getInstance() {
		return instance;
	}

	@Override
	public void onEnable() {
		
		instance = this;
		super.onEnable();
		
		getCommand("nightclub").setExecutor(new CommandNightclub());
		getCommand("nightclub").setTabCompleter(new TabNightclub());
		
		Bukkit.getPluginManager().registerEvents(new EffectsMenu(), this);
		
		Bukkit.getPluginManager().registerEvents(new OnPlayerDamageListener(), this);
		Bukkit.getPluginManager().registerEvents(new OnPlayerChangeWorldListener(), this);
		Bukkit.getPluginManager().registerEvents(new OnResourcepackStatusListener(), this);
		Bukkit.getPluginManager().registerEvents(new OnPlayerInteractListener(), this);
		
		new FloorSmokeEffect(this);
		new StrobeEffect(this);
		new LightBottom(this);
		new LightTop(this);
		new RandomBeaconEffect(this);
		new SphereEffect(this);
		new WaveEffect(this);
		
		new DjGlowing(this);
		
		try {
			test = new Laser(new Location(Bukkit.getWorld("Nightclub"), 0, 80, -6), new Location(Bukkit.getWorld("Nightclub"), 0, 70, -6), 200, 100);
			test.start(this);
		} catch (ReflectiveOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		new Location(Bukkit.getWorld("Nightclub"), 20, 64, 7).getBlock().setType(Material.REDSTONE_BLOCK);
		
		activeEffects.put("floorSmoke", false);
		activeEffects.put("strobe", false);
		activeEffects.put("lightBottom", false);
		activeEffects.put("lightTop", false);
		activeEffects.put("randomBeacon", false);
		activeEffects.put("sphere", false);
		activeEffects.put("wave", false);
		
	}
	
	@Override
	public void onDisable() {
		if(test.isStarted()) test.stop();
		for(Entity e : Bukkit.getWorld("Nightclub").getEntities()) {
			if(e instanceof ArmorStand) {
				if(e.getScoreboardTags().contains("spot")) {
					e.remove();
				}
			}
		}
	}
	
	public void smoothMove(Entity entity, Location toLoc, Integer time) {
	    Location loc = toLoc.subtract(entity.getLocation());
	    Location locPartial = new Location(loc.getWorld(), loc.getX()/time, loc.getY()/time, loc.getZ()/time);

	    for (int i = 0; i < time; i++) {
	        getServer().getScheduler().runTaskLater(this, new Runnable() {
	            public void run() {
	                entity.teleport(entity.getLocation().add(locPartial));
	            }
	        }, 1L*i);
	    }
	}
	
}
