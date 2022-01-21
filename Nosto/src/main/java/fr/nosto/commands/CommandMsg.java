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
		
		if(sender instanceof Player player) {

			if (args.length == 0 || Bukkit.getPlayer(args[0]) == null) {
				player.sendMessage("");
				player.sendMessage("§cUtilisation : /msg <joueur> <message>");
				return false;
			}

			Player target = Bukkit.getPlayer(args[0]);
			assert target != null;

			if (args.length == 1) {
				player.sendMessage("");
				player.sendMessage("§eVous avez poke §6§l" + target.getName() + " §e!");
				target.sendMessage("");
				target.sendMessage("§6§l" + player.getName() + " §evous à poke !");
				target.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 100, 2);
			} else {
				StringBuilder messageBuilder = new StringBuilder();
				for (int i = 1; i < args.length; i++) {
					messageBuilder.append(args[i]).append(" ");
				}
				String message = messageBuilder.toString();
				player.sendMessage("");
				player.sendMessage("§eVous avez envoyer : §a" + message + "§eà §6§l" + target.getName());
				target.sendMessage("");
				target.sendMessage("§eVous avez reçu un message de §6§l" + player.getName() + "§e : §a" + message);
				target.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 100, 1);
			}
		}
		return false;
	}

}
