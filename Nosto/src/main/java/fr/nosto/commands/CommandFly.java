package fr.nosto.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import org.jetbrains.annotations.NotNull;

public class CommandFly implements CommandExecutor {
	
	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
		
		if (sender instanceof Player player) {
			if (args.length == 0) {
				if (player.getAllowFlight()) {
					player.setAllowFlight(false);
					player.sendMessage("\n§cVous ne pouvez plus voler !");
				} else {
					player.setAllowFlight(true);
					player.sendMessage("\n§aVous pouvez voler !");
				}
			} else {
				Player target = Bukkit.getPlayer(args[0]);
				if (target == null) {
					player.sendMessage("\n§cImpossible de trouver un joueur avec le nom §6" + args[0]);
					return true;
				}

				if (target.getAllowFlight()) {
					target.setAllowFlight(false);
					target.sendMessage("\n§c" + target.getName() + " ne peut plus voler !");
				} else {
					target.setAllowFlight(true);
					target.sendMessage("\n§a" + target.getName() + " peut voler !");
				}
			}
		}
		
		return true;
	}

}
