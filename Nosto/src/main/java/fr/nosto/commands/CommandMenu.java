package fr.nosto.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.nosto.menus.MainMenu;
import org.jetbrains.annotations.NotNull;

public class CommandMenu implements CommandExecutor {

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {

		if(sender instanceof Player) {
			Player player = (Player) sender;
			MainMenu.openMenu(player);
		}
		
		return false;
	}

}
