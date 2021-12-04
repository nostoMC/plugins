package fr.nostoS;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import fr.nostoS.afk.AFKListeners;
import fr.nostoS.afk.CommandAFK;
import fr.nostoS.tasks.clearLag;

public class Main extends JavaPlugin {
	
	@Override
	public void onEnable() {
		
		new clearLag(this);

		Bukkit.getPluginManager().registerEvents(new AFKListeners(), this);
		AFKListeners.initAFKLoop(this);

		registerCommands();
	}
	
	@Override
	public void onDisable() {
		
	}

	@SuppressWarnings("ConstantConditions")
	private void registerCommands() {

		getCommand("afk").setExecutor(new CommandAFK());
	}

}
