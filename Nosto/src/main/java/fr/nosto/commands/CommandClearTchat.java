package fr.nosto.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import org.jetbrains.annotations.NotNull;

public class CommandClearTchat implements CommandExecutor {

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {

		int i = 0;
		while (i <= 100) {
			Bukkit.broadcastMessage("");
			i = i + 1;
		}
		
		Bukkit.broadcastMessage("§e----------------------------------------------");
		Bukkit.broadcastMessage("");
		Bukkit.broadcastMessage("§eTchat clear par §6§l" + sender.getName() + " §e!");
		Bukkit.broadcastMessage("");
		Bukkit.broadcastMessage("§e----------------------------------------------");
		
		return true;
	}

}
