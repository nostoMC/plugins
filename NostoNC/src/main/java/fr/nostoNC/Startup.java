package fr.nostoNC;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import fr.nostoNC.commands.CommandFood;
import fr.nostoNC.commands.CommandNightclub;
import fr.nostoNC.commands.TabFood;
import fr.nostoNC.commands.TabNightclub;
import fr.nostoNC.customConsumables.ConsumeListener;
import fr.nostoNC.listeners.BarAccessListener;
import fr.nostoNC.listeners.BarMenuListener;
import fr.nostoNC.listeners.DamageListener;
import fr.nostoNC.listeners.HelmetRemoveListener;
import fr.nostoNC.listeners.InteractionListener;
import fr.nostoNC.listeners.OnResourcepackStatusListener;
import fr.nostoNC.listeners.QuitDjListener;
import fr.nostoNC.listeners.SitListener;
import fr.nostoNC.menus.BarMenu;
import fr.nostoNC.menus.EffectsMenu;
import fr.nostoNC.tasks.ElevatorTask;
import fr.nostoNC.tasks.GolemPass;
import fr.nostoNC.tasks.effects.FloorSmokeEffect;
import fr.nostoNC.tasks.effects.GlowingLamp;
import fr.nostoNC.tasks.effects.StrobeEffect;
import fr.nostoNC.tasks.effects.TopLaser;
import fr.nostoNC.tasks.effects.WallLaser;

public class Startup {

	public static void startup(Main main) {
		
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

		new BukkitRunnable() {

			@Override
			public void run() {
				Utils.setDefaultWorld(Bukkit.getWorld("nostoclub"));

				if (Utils.getDefaultWorld() == null) {
					Bukkit.getLogger().log(Level.SEVERE, "Unable to get world \"nostoclub\"");
				} else {
					loadAfterWorld(main);
				}
			}
		}.runTaskLater(main, 1);
	}

	private static void loadAfterWorld(Main main) {
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
