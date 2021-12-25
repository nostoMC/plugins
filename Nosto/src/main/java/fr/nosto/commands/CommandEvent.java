package fr.nosto.commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.nosto.Main;
import org.jetbrains.annotations.NotNull;

public class CommandEvent implements CommandExecutor {

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
		
		if(!sender.isOp()) return false;
		
		if(args[0].equalsIgnoreCase("add")) {
			if(Main.getInstance().getConfig().get("event") == null) {
				if(args[1].equalsIgnoreCase("show")) {
					Main.getInstance().getConfig().set("event", "show");
					Main.getInstance().saveConfig();
					Bukkit.broadcastMessage("");
					Bukkit.broadcastMessage("Un événement commence ! Utilisez /lobby pour vous téléporter !");
					if (sender instanceof Player player) {
						Location showLobby = new Location(Bukkit.getWorld("show"), 0.5, 65, 0.5, 0f, 0f);
						player.teleport(showLobby);
						player.teleport(showLobby);
						player.setGameMode(GameMode.ADVENTURE);
					}
				}
			} else {
				sender.sendMessage("");
				sender.sendMessage("Un événement est déjà en cours !");
			}
		} else if(args[0].equalsIgnoreCase("stop")) {
			if(Main.getInstance().getConfig().get("event") != null) {
				Main.getInstance().getConfig().set("event", null);
				Main.getInstance().saveConfig();
			} else {
				sender.sendMessage("");
				sender.sendMessage("Aucun événement en cours !");
			}
		} else {
			sender.sendMessage("");
			sender.sendMessage("Utilisation : /event add <nom de l'event ou /event stop");
		}
		
		return false;
	}

}
