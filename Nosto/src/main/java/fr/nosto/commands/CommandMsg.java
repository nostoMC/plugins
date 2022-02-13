package fr.nosto.commands;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import org.jetbrains.annotations.NotNull;

public class CommandMsg implements CommandExecutor {

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
		
		if (sender instanceof Player player) {

			if (args.length == 0 || Bukkit.getPlayer(args[0]) == null) {
				player.sendMessage("\n§cUtilisation : /msg <joueur> <message>");
				return true;
			}

			Player target = Bukkit.getPlayer(args[0]);
			assert target != null;

			if (args.length == 1) {
				player.sendMessage("\n§bVous avez poke §b§l" + target.getName() + " §b!");
				target.sendMessage("\n§b§l" + player.getName() + " §bvous à poke !");
				target.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 100, 2);
			} else {
				StringBuilder messageBuilder = new StringBuilder();
				for (int i = 1; i < args.length; i++) {
					messageBuilder.append(args[i]).append(" ");
				}
				String message = messageBuilder.toString();
				player.sendMessage("\n§bVous avez envoyer : §a" + message + "§bà §b§l" + target.getName());
				target.sendMessage("\n§bVous avez reçu un message de §b§l" + player.getName() + "§b : §a" + message);
				target.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 100, 1);
			}
		}
		return true;
	}

}
