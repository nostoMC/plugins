package fr.djredstone.nosto.trails;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.djredstone.nosto.menus.trailsMenu;

public class CommandTrails implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if(sender instanceof Player) {
			Player player = (Player) sender;
			trailsMenu.openMenu(player);
		}
		
		return false;
	}

}
