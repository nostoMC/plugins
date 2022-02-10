package fr.nosto.commands;

import java.io.File;
import java.io.IOException;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import fr.nosto.Main;
import org.jetbrains.annotations.NotNull;

public class CommandSpawn implements CommandExecutor {

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {

		if (!(sender instanceof Player player)) return true;

		File file = new File(Main.getInstance().getDataFolder()+"/spawn.yml");
		FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);

		fileConfiguration.set("coo.EXEMPLE.x", 0.0);
		fileConfiguration.set("coo.EXEMPLE.y", 0.0);
		fileConfiguration.set("coo.EXEMPLE.z", 0.0);
		fileConfiguration.set("coo.EXEMPLE.yaw", 0.0);
		fileConfiguration.set("coo.EXEMPLE.pitch", 0.0);
		try {
			fileConfiguration.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}

		String worldName = player.getWorld().getName();

		if (fileConfiguration.contains("coo." + worldName)) {
			double x = fileConfiguration.getDouble("coo." + worldName + ".x");
			double y = fileConfiguration.getDouble("coo." + worldName + ".y");
			double z = fileConfiguration.getDouble("coo." + worldName + ".z");
			float yaw = (float) fileConfiguration.getDouble("coo." + worldName + ".yaw");
			float pitch = (float) fileConfiguration.getDouble("coo." + worldName + ".pitch");
			player.sendMessage("\n§7§oTéléportation au spawn du monde...");
			player.teleport(new Location(player.getWorld(), x, y, z, yaw, pitch));
		} else {
			player.sendMessage("\n§cAucun spawn n'existe pour ce monde !");
		}

		return true;
	}

}
