package fr.nosto.commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import org.jetbrains.annotations.NotNull;

public class CommandFreeze implements CommandExecutor {

	public static ArrayList<Player> frozen = new ArrayList<>();

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {

		if (args.length == 1) {

			Player target = Bukkit.getPlayer(args[0]);

			if (target == null) {
				sender.sendMessage("\n§cImpossible de trouver un joueur avec le nom §6" + args[0]);
				return false;
			}

			if (!CommandFreeze.frozen.contains(target)) {
				CommandFreeze.frozen.add(target);
				sender.sendMessage("\n§bVous avez freeze §6§l" + target.getName());
				target.sendMessage("\n§bVous avez été freeze par un modérateur");
			} else {
				CommandFreeze.frozen.remove(target);
				sender.sendMessage("\n§bVous avez défreeze §6§l" + target.getName());
				target.sendMessage("\n§bVous avez été défreeze");
			}

		} else {
			sender.sendMessage("\n§cSyntaxe: /freeze <player>");
		}

		return false;
	}

}
