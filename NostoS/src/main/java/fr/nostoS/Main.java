package fr.nostoS;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	@Override
	public void onEnable() {
		new Setup(this);
	}
	
	@Override
	public void onDisable() {
		Utils.getDatabaseManager().close();
	}

}
