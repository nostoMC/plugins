package fr.nostoNC;

import java.util.HashMap;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import fr.nostoNC.commands.CommandFood;
import fr.nostoNC.commands.CommandNightclub;
import fr.nostoNC.commands.TabFood;
import fr.nostoNC.commands.TabNightclub;
import fr.nostoNC.customConsumables.ConsumeListener;
import fr.nostoNC.listeners.BarAccessListener;
import fr.nostoNC.listeners.BarMenuListener;
import fr.nostoNC.listeners.DamageListener;
import fr.nostoNC.listeners.InteractionListener;
import fr.nostoNC.listeners.OnPlayerChangeWorldListener;
import fr.nostoNC.listeners.OnResourcepackStatusListener;
import fr.nostoNC.listeners.QuitDjListener;
import fr.nostoNC.listeners.SitListener;
import fr.nostoNC.menus.BarMenu;
import fr.nostoNC.menus.BottomLaserMenu;
import fr.nostoNC.menus.EffectsMenu;

public class Main extends JavaPlugin {
	
	public static final HashMap<String, Boolean> activeEffects = new HashMap<>();

	public static World defaultWorld;

	public static Main instance;

	@Override
	public void onEnable() {
		
		instance = this;
		
		registerCommands();
		
		Bukkit.getPluginManager().registerEvents(new EffectsMenu(), this);
		Bukkit.getPluginManager().registerEvents(new BottomLaserMenu(), this);
		Bukkit.getPluginManager().registerEvents(new BarMenu(), this);
		
		Bukkit.getPluginManager().registerEvents(new OnPlayerChangeWorldListener(), this);
		Bukkit.getPluginManager().registerEvents(new OnResourcepackStatusListener(), this);

		Bukkit.getPluginManager().registerEvents(new SitListener(), this);
		Bukkit.getPluginManager().registerEvents(new BarAccessListener(), this);
		Bukkit.getPluginManager().registerEvents(new ConsumeListener(), this);
		Bukkit.getPluginManager().registerEvents(new BarMenuListener(), this);

		Bukkit.getPluginManager().registerEvents(new InteractionListener(), this);
		Bukkit.getPluginManager().registerEvents(new DamageListener(), this);
		Bukkit.getPluginManager().registerEvents(new QuitDjListener(), this);

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
		
	}
	
	@SuppressWarnings("ConstantConditions")
	private void registerCommands() {
		getCommand("nightclub").setExecutor(new CommandNightclub());
			getCommand("nightclub").setTabCompleter(new TabNightclub());
		getCommand("getfood").setExecutor(new CommandFood());
			getCommand("getfood").setTabCompleter(new TabFood());
	}

	@Override
	public void onDisable() {
	}
	
}
