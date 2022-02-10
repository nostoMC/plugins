package fr.nostoS.commands;

import fr.nostoS.listeners.AFKListeners;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.nostoS.Utils;
import org.jetbrains.annotations.NotNull;

public class CommandAFK implements CommandExecutor {

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {

		if (!(sender instanceof Player)) return true;
		Player player = (Player) sender;

		if (!Utils.getSurviesNames().contains(player.getWorld().getName())) {
			player.sendMessage("\n§cCette commande ne peut être utilisée qu'en monde survie!");
			return true;
		}

		if (AFKListeners.afks.contains(player.getUniqueId())) AFKListeners.removeAFK(player);
		else AFKListeners.setAFK(player);
		
		return true;
	}
	
}
