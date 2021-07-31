package fr.djredstone.nostoMCNF;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import fr.djredstone.nostoMCNF.Main;
import fr.djredstone.nostoMCNF.commands.CommandMCNF;
import fr.djredstone.nostoMCNF.commands.TabMCNF;

public class Main extends JavaPlugin {
	
	public static Main instance;
	
	public static JavaPlugin getInstance() {
		return instance;
	}
	
	boolean NoteBlockAPI = true;
	
	@Override
	public void onEnable() {
		instance = this;
		super.onEnable();
		
		getCommand("mcnf").setExecutor(new CommandMCNF());
		getCommand("mcnf").setTabCompleter(new TabMCNF());
		
		getConfig().set("test", true);
		
		if (!Bukkit.getPluginManager().isPluginEnabled("NoteBlockAPI")){
		    getLogger().severe("*** NoteBlockAPI is not installed or not enabled. ***");
		    NoteBlockAPI = false;
		    return;
		}
		
	}
	
	@Override
	public void onDisable() {
		
	}

}
