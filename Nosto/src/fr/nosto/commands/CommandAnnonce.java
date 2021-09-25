package fr.nosto.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandAnnonce implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		
		Player player = (Player)sender;
		
		if(args.length == 0) {
			player.sendMessage("§cVeuillez ajouter un message");
		} else if(args.length >= 1) {
			StringBuilder argsAnnonce = new StringBuilder();
			for(String part : args) {
				argsAnnonce.append(part + " ");
			}
			Bukkit.broadcastMessage("");
			Bukkit.broadcastMessage("");
			Bukkit.broadcastMessage("§6§l-- Annonce de " + player.getName() + " --");
			Bukkit.broadcastMessage("");
			Bukkit.broadcastMessage("§l" + argsAnnonce.toString());
			Bukkit.broadcastMessage("");
			Bukkit.broadcastMessage("§6§l---------------------------");
			Bukkit.broadcastMessage("");
			Bukkit.broadcastMessage("");
		}
		
		return false;
	}

}
