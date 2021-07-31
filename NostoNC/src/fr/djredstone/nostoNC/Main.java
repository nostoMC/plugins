package fr.djredstone.nostoNC;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import fr.djredstone.nostoNC.commands.CommandNightclub;
import fr.djredstone.nostoNC.commands.TabNightclub;
import fr.djredstone.nostoNC.listeners.OnInventoryClickListener;
import fr.djredstone.nostoNC.listeners.OnPlayerDamageListener;
import fr.djredstone.nostoNC.tasks.DjGlowing;
import fr.djredstone.nostoNC.tasks.FloorSmokeEffect;
import fr.djredstone.nostoNC.tasks.LightBottom;
import fr.djredstone.nostoNC.tasks.RandomBeaconEffect;
import fr.djredstone.nostoNC.tasks.StrobeEffect;

public class Main extends JavaPlugin {
	
	static ArrayList<String> on = new ArrayList<String>();
	static ArrayList<String> off = new ArrayList<String>();
	static ArrayList<Player> dj = new ArrayList<Player>();
	
	static boolean floorSmoke = false;
	static boolean strobe = false;
	static boolean lightBottom = false;
	static boolean lightTop = false;
	static boolean randomBeacon = false;
	
	public static Main instance;
	
	public static JavaPlugin getInstance() {
		return instance;
	}

	@Override
	public void onEnable() {
		
		instance = this;
		super.onEnable();
		
		getCommand("nightclub").setExecutor(new CommandNightclub());
		getCommand("nightclub").setTabCompleter(new TabNightclub());
		
		new OnInventoryClickListener(this);
		new OnPlayerDamageListener(this);
		
		new FloorSmokeEffect(this);
		new StrobeEffect(this);
		new LightBottom(this);
		new RandomBeaconEffect(this);
		
		new DjGlowing(this);
		
		off.add("§c§loff");
		on.add("§a§lon");
		
	}
	
	@Override
	public void onDisable() {
		
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
	
	public static Boolean getfloorSmoke() {
		return floorSmoke;
	}
	
	public static Boolean setfloorSmoke(Boolean bool) {
		floorSmoke = bool;
		return null;
	}
	
	public static Boolean getStrobe() {
		return strobe;
	}
	
	public static Boolean setStrobe(Boolean bool) {
		strobe = bool;
		return null;
	}
	
	public static Boolean getLightBottom() {
		return lightBottom;
	}
	
	public static Boolean setlightBottom(Boolean bool) {
		lightBottom = bool;
		return null;
	}
	
	public static Boolean getLightTop() {
		return lightTop;
	}
	
	public static Boolean setlightTop(Boolean bool) {
		lightTop = bool;
		return null;
	}
	
	public static Boolean getRandomBeacon() {
		return randomBeacon;
	}
	
	public static Boolean setRandomBeacon(Boolean bool) {
		randomBeacon = bool;
		return null;
	}
	
	public static ArrayList<String> getOnLore() {
		return on;
	}
	
	public static ArrayList<String> getOffLore() {
		return off;
	}
	
	public static ArrayList<Player> getDjList() {
		return dj;
	}
	
	public static ArrayList<Player> setPlayerDjList(Player player) {
		dj.add(player);
		return null;
	}
	
}
