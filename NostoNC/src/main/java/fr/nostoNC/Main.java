package fr.nostoNC;

import fr.nostoNC.commands.CommandFood;
import fr.nostoNC.commands.CommandNightclub;
import fr.nostoNC.commands.TabFood;
import fr.nostoNC.commands.TabNightclub;
import fr.nostoNC.customConsumables.ConsumeListener;
import fr.nostoNC.listeners.*;
import fr.nostoNC.menus.BarMenu;
import fr.nostoNC.menus.EffectsMenu;
import fr.nostoNC.tasks.effects.TopLaser;
import fr.nostoNC.tasks.effects.WallLaser;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.logging.Level;

public class Main extends JavaPlugin {

	public static Main instance;

	@Override
	public void onEnable() {
		
		instance = this;
		
		registerCommands();
		
		Bukkit.getPluginManager().registerEvents(new EffectsMenu(), this);
		Bukkit.getPluginManager().registerEvents(new BarMenu(), this);

		Bukkit.getPluginManager().registerEvents(new OnResourcepackStatusListener(), this);

		Bukkit.getPluginManager().registerEvents(new SitListener(), this);
		Bukkit.getPluginManager().registerEvents(new BarAccessListener(), this);
		Bukkit.getPluginManager().registerEvents(new ConsumeListener(), this);
		Bukkit.getPluginManager().registerEvents(new BarMenuListener(), this);

		Bukkit.getPluginManager().registerEvents(new InteractionListener(), this);
		Bukkit.getPluginManager().registerEvents(new DamageListener(), this);
		Bukkit.getPluginManager().registerEvents(new QuitDjListener(), this);
		Bukkit.getPluginManager().registerEvents(new HelmetRemoveListener(), this);

		Main main = this;
		new BukkitRunnable() {

			@Override
			public void run() {
				Utils.defaultWorld = Bukkit.getWorld("nostoclub");

				if (Utils.defaultWorld == null) {
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
		TopLaser.hideAll();
		WallLaser.hideAll();
	}
	
}
