package fr.djredstone.nostoW;

import org.bukkit.plugin.java.JavaPlugin;

import fr.djredstone.nostoW.commands.CommandWorld;

public class Main extends JavaPlugin {
	
	private static Main instance;
	
	public static Main getInstance() {
		return instance;
	}
	
	public void onEnable() {
		
		instance = this;
		
		getCommand("world").setExecutor(new CommandWorld());
		getCommand("world").setTabCompleter(new CommandWorld());
		
	}
	
	public void onDisable() {
		
	}

}
