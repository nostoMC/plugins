package fr.djredstone.nostoSRVSH;

import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import fr.djredstone.nostoSRVSH.listeners.OnInventoryClickListener;
import fr.djredstone.nostoSRVSH.listeners.OnPlayerChangeWorld;
import fr.djredstone.nostoSRVSH.listeners.OnPlayerDeath;
import fr.djredstone.nostoSRVSH.listeners.OnPlayerRespawn;

public class Main extends JavaPlugin {
	
	static ArrayList<Player> speedRunner = new ArrayList<Player>();
	static ArrayList<Player> hunter = new ArrayList<Player>();
	
	@Override
	public void onEnable() {
		new OnPlayerDeath(this);
		new OnPlayerRespawn(this);
		new OnInventoryClickListener(this);
		new OnPlayerChangeWorld(this);
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
