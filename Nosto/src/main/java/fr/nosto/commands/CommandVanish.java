package fr.nosto.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CommandVanish implements CommandExecutor {

	private static final ArrayList<Player> vanishList = new ArrayList<>();

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {

		Player player = (Player) sender;
		
		if (args.length == 0) {
			if (vanishList.contains(player)) {
				vanishList.remove(player);
				player.sendMessage("\n§7Vous êtes maintenant visible !");
			} else {
				vanishList.add(player);
				player.sendMessage("\n§7Vous êtes maintenant invisible !");
			}
		} else {
			Player target = Bukkit.getPlayer(args[0]);
			if (vanishList.contains(target)) {
				vanishList.remove(target);
				assert target != null;
				player.sendMessage("\n§7Vous avez mis " + target.getName() + " visible !");
				target.sendMessage("\n§7Vous êtes maintenant visible !");
			} else {
				vanishList.add(target);
				assert target != null;
				player.sendMessage("\n§7Vous avez mis " + target.getName() + " en invisible !");
				target.sendMessage("\n§7Vous êtes maintenant invisible !");
			}
		}
		
		return true;
	}

	public static ArrayList<Player> getVanishList() {
		return vanishList;
	}

}
