package fr.nosto.commands;

import fr.nosto.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.nosto.Main;
import org.jetbrains.annotations.NotNull;

public class CommandVanish implements CommandExecutor {

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {

		Player player = (Player) sender;
		
		if (args.length == 0) {
			if (Utils.vanishList.contains(player)) {
				Utils.vanishList.remove(player);
				player.sendMessage("\n§7Vous êtes maintenant visible !");
			} else {
				Utils.vanishList.add(player);
				player.sendMessage("\n§7Vous êtes maintenant invisible !");
			}
		} else {
			Player target = Bukkit.getPlayer(args[0]);
			if (Utils.vanishList.contains(target)) {
				Utils.vanishList.remove(target);
				player.sendMessage("\n§7Vous avez mis " + target.getName() + " visible !");
				target.sendMessage("\n§7Vous êtes maintenant visible !");
			} else {
				Utils.vanishList.add(target);
				player.sendMessage("\n§7Vous avez mis " + target.getName() + " en invisible !");
				target.sendMessage("\n§7Vous êtes maintenant invisible !");
			}
		}
		
		return false;
	}

}
