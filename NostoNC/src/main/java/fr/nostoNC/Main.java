package fr.nostoNC;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import fr.nostoNC.commands.CommandNightclub;
import fr.nostoNC.commands.TabNightclub;
import fr.nostoNC.listeners.OnPlayerChangeWorldListener;
import fr.nostoNC.listeners.OnResourcepackStatusListener;
import fr.nostoNC.listeners.SitListener;
import fr.nostoNC.menus.BottomLaserMenu;
import fr.nostoNC.menus.EffectsMenu;

public class Main extends JavaPlugin {
	
	public static ArrayList<Player> dj = new ArrayList<Player>();
	public static ArrayList<Player> vip = new ArrayList<Player>();
	
	public static HashMap<String, Boolean> activeEffects = new HashMap<String, Boolean>();
	
	public static int cadence = 10;
	
	public static World defaultWorld = Bukkit.getWorld("build");
	
	public static Main instance;
	
	public static JavaPlugin getInstance() {
		return instance;
	}

	@Override
	public void onEnable() {
		
		instance = this;
//		super.onEnable();
		
		getCommand("nightclub").setExecutor(new CommandNightclub());
		getCommand("nightclub").setTabCompleter(new TabNightclub());
		
		Bukkit.getPluginManager().registerEvents(new EffectsMenu(), this);
		Bukkit.getPluginManager().registerEvents(new BottomLaserMenu(), this);
		
		Bukkit.getPluginManager().registerEvents(new OnPlayerChangeWorldListener(), this);
		Bukkit.getPluginManager().registerEvents(new OnResourcepackStatusListener(), this);

		Bukkit.getPluginManager().registerEvents(new SitListener(), this);

		new Startup(this);
		
		//fix
		/*
        WorldData worldData = ((CraftWorld) w).getHandle().getWorldData();
        Reflection.getField(worldData.getClass(), long.class, 0).set(worldData, 0);
        */
		
	}
	
	@Override
	public void onDisable() {
		for(Entity e : Bukkit.getWorld("Nightclub").getEntities()) {
			if(e instanceof ArmorStand) {
				if(e.getScoreboardTags().contains("spot")) {
					e.remove();
				}
			}
		}
	}
	
	public static void smoothMove(Entity entity, Location toLoc, Integer time) {
	    Location loc = toLoc.subtract(entity.getLocation());
	    Location locPartial = new Location(loc.getWorld(), loc.getX()/time, loc.getY()/time, loc.getZ()/time);

	    for (int i = 0; i < time; i++) {
	        Bukkit.getServer().getScheduler().runTaskLater(instance, new Runnable() {
	            public void run() {
	                entity.teleport(entity.getLocation().add(locPartial));
	            }
	        }, 1L*i);
	    }
	}
	
}
