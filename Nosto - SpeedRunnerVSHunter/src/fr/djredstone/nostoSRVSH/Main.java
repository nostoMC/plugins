package fr.djredstone.nostoSRVSH;

import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import fr.djredstone.nostoSRVSH.listeners.onInventoryClickListener;
import fr.djredstone.nostoSRVSH.listeners.onPlayerChangeWorld;
import fr.djredstone.nostoSRVSH.listeners.onPlayerDeath;
import fr.djredstone.nostoSRVSH.listeners.onPlayerRespawn;

public class Main extends JavaPlugin {
	
	static ArrayList<Player> speedRunner = new ArrayList<Player>();
	static ArrayList<Player> hunter = new ArrayList<Player>();
	
	@Override
	public void onEnable() {
		new onPlayerDeath(this);
		new onPlayerRespawn(this);
		new onInventoryClickListener(this);
		new onPlayerChangeWorld(this);
	}
	
	@Override
	public void onDisable() {
		
	}
	
	public static ArrayList<Player> getSpeedRunnerList() {
		return speedRunner;
	}
	
	public static ArrayList<Player> getHunterList() {
		return hunter;
	}

}
