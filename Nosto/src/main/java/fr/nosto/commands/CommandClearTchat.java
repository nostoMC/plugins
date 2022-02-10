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
		
		Bukkit.broadcastMessage("§3----------------------------------------------\n");
		Bukkit.broadcastMessage("§bLe Tchat a été clear par §b§l" + sender.getName() + " §b!");
		Bukkit.broadcastMessage("\n§3----------------------------------------------");
		
		return true;
	}

}
