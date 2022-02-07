package fr.nosto.commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import org.jetbrains.annotations.NotNull;

public class CommandFly implements CommandExecutor {
	
	private ArrayList<Player> list = new ArrayList<>();

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
		
		if (sender instanceof Player player) {
			if (args.length == 0) {
				if (list.contains(player) || player.getAllowFlight()) {
					list.remove(player);
					player.setAllowFlight(false);
					player.sendMessage("\n§cVous ne pouvez plus voler !");
				} else if (!list.contains(player)) {
					list.add(player);
					player.setAllowFlight(true);
					player.sendMessage("\n§aVous pouvez voler !");
				}
			} else {
				player = Bukkit.getPlayer(args[0]);
				if (list.contains(player) || player.getAllowFlight()) {
					list.remove(player);
					player.setAllowFlight(false);
					player.sendMessage("\n§c" + player.getName() + " ne peut plus voler !");
				} else if (!list.contains(player)) {
					list.add(player);
					player.setAllowFlight(true);
					player.sendMessage("\n§a" + player.getName() + " peut voler !");
				}
			}
		}
		
		return false;
	}

}
