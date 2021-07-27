package fr.djredstone.nosto.commands.clearTchat;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandClearTchat implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		int i = 0;
		while(i <= 100) {
			Bukkit.broadcastMessage("");
			i = i + 1;
		}
		
		Bukkit.broadcastMessage("�e----------------------------------------------");
		Bukkit.broadcastMessage("");
		Bukkit.broadcastMessage("�eTchat clear par �6�l" + sender.getName().toString() + " �e!");
		Bukkit.broadcastMessage("");
		Bukkit.broadcastMessage("�e----------------------------------------------");
		
		return false;
	}

}
