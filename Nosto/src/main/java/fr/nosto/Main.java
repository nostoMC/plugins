package fr.nosto;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import fr.nosto.mysql.DatabaseManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

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
		Utils.databaseManager.close();
		Bukkit.getLogger().info("§b[Nosto] Plugin Custom Déchargé !");
		
	}

	private void createMessageConfig() {
		File customConfigFile = new File(getDataFolder(), "messages.yml");
		if (!customConfigFile.exists()) {
			//noinspection ResultOfMethodCallIgnored
			customConfigFile.getParentFile().mkdirs();
			saveResource("messages.yml", false);
		}

		Utils.messageConfig = new YamlConfiguration();
		try {
			Utils.messageConfig.load(customConfigFile);
		} catch (IOException | InvalidConfigurationException e) {
			e.printStackTrace();
		}
	}
	
}
