package fr.nostoNC;

import fr.nostoNC.tasks.effects.TopLaser;
import fr.nostoNC.tasks.effects.WallLaser;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	private static Main instance;

	public static Main getInstance() {
		return instance;
	}

	@Override
	public void onEnable() {
		instance = this;
		Startup.startup(instance);
	}

	@Override
	public void onDisable() {
		TopLaser.hideAll();
		WallLaser.hideAll();
	}
	
}
