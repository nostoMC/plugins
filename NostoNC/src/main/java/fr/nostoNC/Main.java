package fr.nostoNC;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import fr.nostoNC.commands.CommandNightclub;
import fr.nostoNC.commands.TabNightclub;
import fr.nostoNC.listeners.DamageListener;
import fr.nostoNC.listeners.InteractionListener;
import fr.nostoNC.listeners.OnPlayerChangeWorldListener;
import fr.nostoNC.listeners.OnResourcepackStatusListener;
import fr.nostoNC.listeners.SitListener;
import fr.nostoNC.menus.BottomLaserMenu;
import fr.nostoNC.menus.EffectsMenu;

public class Main extends JavaPlugin {
	
	public static ArrayList<Player> dj = new ArrayList<>();

	public static HashMap<String, Boolean> activeEffects = new HashMap<>();

	public static World defaultWorld;
	
	public static Main instance;

	@Override
	public void onEnable() {
		
		instance = this;
		
		Objects.requireNonNull(getCommand("nightclub")).setExecutor(new CommandNightclub());
		Objects.requireNonNull(getCommand("nightclub")).setTabCompleter(new TabNightclub());
		
		Bukkit.getPluginManager().registerEvents(new EffectsMenu(), this);
		Bukkit.getPluginManager().registerEvents(new BottomLaserMenu(), this);
		
		Bukkit.getPluginManager().registerEvents(new OnPlayerChangeWorldListener(), this);
		Bukkit.getPluginManager().registerEvents(new OnResourcepackStatusListener(), this);

		Bukkit.getPluginManager().registerEvents(new SitListener(), this);

		Bukkit.getPluginManager().registerEvents(new InteractionListener(), this);
		Bukkit.getPluginManager().registerEvents(new DamageListener(), this);

		Main main = this;
		new BukkitRunnable() {

			@Override
			public void run() {
				defaultWorld = Bukkit.getWorld("nostoclub");

				if (defaultWorld == null) {
					Bukkit.getLogger().log(Level.SEVERE, "Unable to get world \"nostoclub\"");
					return;
				}
				
				Startup.startup(main);
			}
		}.runTaskLater(this, 1);
		
		//fix
		/*
        WorldData worldData = ((CraftWorld) w).getHandle().getWorldData();
        Reflection.getField(worldData.getClass(), long.class, 0).set(worldData, 0);
        */
		
	}
	
	@Override
	public void onDisable() {
	}
	
}
