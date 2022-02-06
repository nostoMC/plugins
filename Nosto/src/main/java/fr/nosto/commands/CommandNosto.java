package fr.nosto.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.nosto.Main;
import fr.nosto.listeners.OnJoinListener;
import org.jetbrains.annotations.NotNull;

public class CommandNosto implements CommandExecutor {

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {

		String helpMessage = "\nUtilisation : /nosto < reloadconfig | normaljoin >";

		if (args.length != 1) {
			sender.sendMessage(helpMessage);

		} else {
			switch (args[0].toLowerCase()) {
				
				case "reloadconfig" -> {
					Main.getInstance().reloadConfig();
					sender.sendMessage("§aConfig reload !");
				}

				case "normaljoin" -> {
					if (sender instanceof Player player) {
						OnJoinListener.playerJoin(player);
					} else {
						sender.sendMessage("Seuls les joueurs peuvent faire ca !");
					}
				}

				default -> sender.sendMessage(helpMessage);
			}
        }
		
		return true;
	}

}
