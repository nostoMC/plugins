package fr.nosto;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import fr.nosto.tasks.particles.PlayerTrailsStats;

public class Main extends JavaPlugin {

	public static ArrayList<Player> frozen = new ArrayList<>();
	public static ArrayList<Player> vanishList = new ArrayList<>();
	public static ArrayList<Player> afks = new ArrayList<>();
	static HashMap<Player, PlayerTrailsStats> playerTrails = new HashMap<>();

	private FileConfiguration messageConfig;

	public static Main instance;

	public static JavaPlugin getInstance() {
		return instance;
	}

	@Override
	public void onEnable() {
		
		instance = this;

		createMessageConfig();
		
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

	public FileConfiguration getMessageConfig() {
		return messageConfig;
	}

	private void createMessageConfig() {
		File customConfigFile = new File(getDataFolder(), "messages.yml");
		if (!customConfigFile.exists()) {
			//noinspection ResultOfMethodCallIgnored
			customConfigFile.getParentFile().mkdirs();
			saveResource("messages.yml", false);
		}

		messageConfig = new YamlConfiguration();
		try {
			messageConfig.load(customConfigFile);
		} catch (IOException | InvalidConfigurationException e) {
			e.printStackTrace();
		}
	}
	
}
