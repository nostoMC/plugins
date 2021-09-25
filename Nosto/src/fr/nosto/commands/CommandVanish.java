package fr.nosto.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.nosto.Main;

public class CommandVanish implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		Player player = (Player) sender;
		
		if(args.length == 0) {
			if(Main.vanishList.contains(player)) {
				Main.vanishList.remove(player);
				player.sendMessage("");
				player.sendMessage("§7Vous êtes maintenant visible !");
			} else {
				Main.vanishList.add(player);
				player.sendMessage("");
				player.sendMessage("§7Vous êtes maintenant invisible !");
			}
		} else {
			Player target = Bukkit.getPlayer(args[0]);
			if(Main.vanishList.contains(target)) {
				Main.vanishList.remove(target);
				player.sendMessage("");
				player.sendMessage("§7Vous avez mis " + target.getName() + " visible !");
				target.sendMessage("");
				target.sendMessage("§7Vous êtes maintenant visible !");
			} else {
				Main.vanishList.add(target);
				player.sendMessage("");
				player.sendMessage("§7Vous avez mis " + target.getName() + " en invisible !");
				target.sendMessage("");
				target.sendMessage("§7Vous êtes maintenant invisible !");
			}
		}
		
		return false;
	}

}
