package fr.djredstone.nosto.menu;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.djredstone.nosto.menus.mainMenu;

public class CommandMenu implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if(sender instanceof Player) {
			Player player = (Player) sender;
			mainMenu.openMenu(player);
		}
		
		return false;
	}

}
