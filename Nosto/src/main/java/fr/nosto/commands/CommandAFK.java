package fr.nosto.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.nosto.listeners.AFKListeners;
import org.jetbrains.annotations.NotNull;

public class CommandAFK implements CommandExecutor {

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {

		if (!(sender instanceof Player)) return false;
		Player player = (Player) sender;

		if(AFKListeners.afks.contains(player.getUniqueId())) AFKListeners.removeAFK(player);
		else AFKListeners.setAFK(player);
		
		return false;
	}
	
}
