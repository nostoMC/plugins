package fr.nosto.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import org.jetbrains.annotations.NotNull;

public class CommandClearTchat implements CommandExecutor {

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {

		Bukkit.broadcastMessage("\n".repeat(100));
		
		Bukkit.broadcastMessage("§e----------------------------------------------\n");
		Bukkit.broadcastMessage("§eTchat clear par §6§l" + sender.getName() + " §e!");
		Bukkit.broadcastMessage("\n§e----------------------------------------------");
		
		return true;
	}

}
