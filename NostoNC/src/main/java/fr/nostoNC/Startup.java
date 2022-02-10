package fr.nostoNC;

import fr.nostoNC.commands.CommandFood;
import fr.nostoNC.commands.CommandNightclub;
import fr.nostoNC.commands.TabFood;
import fr.nostoNC.commands.TabNightclub;
import fr.nostoNC.customConsumables.ConsumeListener;
import fr.nostoNC.listeners.*;
import fr.nostoNC.menus.BarMenu;
import fr.nostoNC.menus.EffectsMenu;
import fr.nostoNC.tasks.*;
import fr.nostoNC.tasks.effects.*;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.logging.Level;

public class Startup {

	public static void startup(Main main) {

		new BukkitRunnable() {

			@Override
			public void run() {
				Utils.setDefaultWorld(Bukkit.getWorld("nostoclub"));

				if (Utils.getDefaultWorld() == null) {
					Bukkit.getLogger().log(Level.SEVERE, "Unable to get world \"nostoclub\"");
				}
			}
		}.runTaskLater(main, 1);

		registerCommands(main);

		Bukkit.getPluginManager().registerEvents(new EffectsMenu(), main);
		Bukkit.getPluginManager().registerEvents(new BarMenu(), main);

		Bukkit.getPluginManager().registerEvents(new OnResourcepackStatusListener(), main);

		Bukkit.getPluginManager().registerEvents(new SitListener(), main);
		Bukkit.getPluginManager().registerEvents(new BarAccessListener(), main);
		Bukkit.getPluginManager().registerEvents(new ConsumeListener(), main);
		Bukkit.getPluginManager().registerEvents(new BarMenuListener(), main);

		Bukkit.getPluginManager().registerEvents(new InteractionListener(), main);
		Bukkit.getPluginManager().registerEvents(new DamageListener(), main);
		Bukkit.getPluginManager().registerEvents(new QuitDjListener(), main);
		Bukkit.getPluginManager().registerEvents(new HelmetRemoveListener(), main);

		Utils.putActiveEffects("floorSmoke", false);
		Utils.putActiveEffects("strobe", false);

		FloorSmokeEffect.init(main);
		StrobeEffect.init(main);

		GolemPass.init(main);
		ElevatorTask.init(main);

		TopLaser.setup();
		WallLaser.setup();
		GlowingLamp.setup();
		
	}

	@SuppressWarnings("ConstantConditions")
	private static void registerCommands(Main main) {
		main.getCommand("nightclub").setExecutor(new CommandNightclub());
			main.getCommand("nightclub").setTabCompleter(new TabNightclub());
		main.getCommand("getfood").setExecutor(new CommandFood());
			main.getCommand("getfood").setTabCompleter(new TabFood());
	}

}
