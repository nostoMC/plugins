package fr.djredstone.nosto.msg;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandMsg implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(sender instanceof Player) {
			Player player = (Player) sender;
			Player target;
			if(args.length == 0) {
				player.sendMessage("");
				player.sendMessage("§cUtilisation : /msg <joueur> <message>");
			} else if(args.length == 1 && Bukkit.getPlayer(args[0]) instanceof Player) {
				target = Bukkit.getPlayer(args[0]);
				player.sendMessage("");
				player.sendMessage("§eVous avez poke §6§l" + target.getName() + " §e!");
				target.sendMessage("");
				target.sendMessage("§6§l" + player.getName() + " §evous à poke !");
				target.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 100, 2);
			} else if(args.length >= 2) {
				target = Bukkit.getPlayer(args[0]);
				StringBuilder argsMessage = new StringBuilder();
				for(String part : args) {
					argsMessage.append(part + " ");
				}
				int max = player.getName().length();
				max = max + 1;
				argsMessage.delete(0, max);
				int maxMessage = argsMessage.length();
				argsMessage.delete(maxMessage - 1, maxMessage);
				player.sendMessage("");
				player.sendMessage("§eVous avez envoyer : §a" + argsMessage.toString() + " §eà §6§l" + target.getName());
				target.sendMessage("");
				target.sendMessage("§eVous avez reçu un message de §6§l" + player.getName() + "§e : §a" + argsMessage.toString());
				target.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 100, 1);
			}
		}
		
		return false;
	}

}
