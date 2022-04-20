package fr.nosto;

import fr.nosto.discord.DiscordSetup;
import fr.nosto.discord.DiscordShutdown;
import fr.nosto.mysql.DatabaseManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class Main extends JavaPlugin {

	private static FileConfiguration messageConfig;

	private static DatabaseManager databaseManager;

	private static Main instance;

	public static Main getInstance() {
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

		for (Player player : Bukkit.getOnlinePlayers()) {
			player.kickPlayer("§cLe serveur est en train de s'éteindre.");
		}

		new DiscordShutdown(this);
		databaseManager.close();
		Bukkit.getLogger().info("§b[Nosto] Plugin Custom Déchargé !");
		
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

	public static FileConfiguration getMessageConfig() {
		return messageConfig;
	}

	public static DatabaseManager getDatabaseManager() {
		return databaseManager;
	}

	public static void setDatabaseManager(DatabaseManager databaseManager) {
		Main.databaseManager = databaseManager;
	}
	
}
