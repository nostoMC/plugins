package fr.nosto;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import fr.nosto.tasks.particles.PlayerTrailsStats;

public class Main extends JavaPlugin {

	public static ArrayList<Player> frozen = new ArrayList<>();
	public static ArrayList<Player> vanishList = new ArrayList<>();
	public static ArrayList<Player> afks = new ArrayList<>();
	static HashMap<Player, PlayerTrailsStats> playerTrails = new HashMap<>();
	
	public static Main instance;
	
	public static JavaPlugin getInstance() {
		return instance;
	}

	@Override
	public void onEnable() {
		
		instance = this;
		
		new Setup(this);
		new DiscordSetup(this);
		
	}

	@Override
	public void onDisable() {
		
		new DiscordShutdown(this);
		System.out.println("§b[Nosto] Plugin Custom Déchargé !");
		
	}
	
	public static void setPlayerTrailStats(Player player, PlayerTrailsStats stats) {
		playerTrails.put(player, stats);
	}

	public static HashMap<Player, PlayerTrailsStats> getPlayerTrailsMap() {
		return playerTrails;
	}
	
}
