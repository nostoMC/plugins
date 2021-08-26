package fr.djredstone.nostoNC;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import fr.djredstone.nostoNC.commands.CommandNightclub;
import fr.djredstone.nostoNC.commands.TabNightclub;
import fr.djredstone.nostoNC.listeners.OnInventoryClickListener;
import fr.djredstone.nostoNC.listeners.OnPlayerChangeWorldListener;
import fr.djredstone.nostoNC.listeners.OnPlayerDamageListener;
import fr.djredstone.nostoNC.listeners.OnResourcepackStatusListener;
import fr.djredstone.nostoNC.tasks.DjGlowing;
import fr.djredstone.nostoNC.tasks.FloorSmokeEffect;
import fr.djredstone.nostoNC.tasks.LightBottom;
import fr.djredstone.nostoNC.tasks.LightTop;
import fr.djredstone.nostoNC.tasks.RandomBeaconEffect;
import fr.djredstone.nostoNC.tasks.SphereEffect;
import fr.djredstone.nostoNC.tasks.StrobeEffect;
import fr.djredstone.nostoNC.tasks.WaveEffect;

public class Main extends JavaPlugin {
	
	public static ArrayList<String> on = new ArrayList<String>();
	public static ArrayList<String> off = new ArrayList<String>();
	public static ArrayList<Player> dj = new ArrayList<Player>();
	
	public static boolean floorSmoke = false;
	public static boolean strobe = false;
	public static boolean lightBottom = false;
	public static boolean lightTop = false;
	public static int cadence = 10;
	public static boolean randomBeacon = false;
	public static boolean sphere = false;
	public static boolean wave = false;
	public static boolean dragonDeathAnimation = false;
	
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
		
		new OnInventoryClickListener(this);
		new OnPlayerDamageListener(this);
		new OnPlayerChangeWorldListener(this);
		new OnResourcepackStatusListener(this);
		
		new FloorSmokeEffect(this);
		new StrobeEffect(this);
		new LightBottom(this);
		new LightTop(this);
		new RandomBeaconEffect(this);
		new SphereEffect(this);
		new WaveEffect(this);
		
		new DjGlowing(this);
		
		off.add("§c§loff");
		on.add("§a§lon");
		
		try {
			test = new Laser(new Location(Bukkit.getWorld("Nightclub"), 0, 80, -6), new Location(Bukkit.getWorld("Nightclub"), 0, 70, -6), 200, 100);
			test.start(this);
		} catch (ReflectiveOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void onDisable() {
		if(test.isStarted()) test.stop();
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
