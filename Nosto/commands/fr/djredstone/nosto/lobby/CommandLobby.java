package fr.djredstone.nosto.lobby;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.djredstone.nosto.menus.tpMenu;

public class CommandLobby implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Player player = (Player) sender;

		if(!player.isOp()) {
    		player.sendMessage("Cette commande est en train d'être repensé");
    		return true;
    	} else {
    		tpMenu.openMenu(player);
    	}
		
		return false;
	}

}
