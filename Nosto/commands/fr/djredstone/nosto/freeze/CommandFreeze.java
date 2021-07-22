package fr.djredstone.nosto.freeze;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.djredstone.nosto.Main;

public class CommandFreeze implements CommandExecutor {
	
	ArrayList<Player> frozen = Main.getFreezList();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Player player = (Player) sender;

		if(cmd.getName().equalsIgnoreCase("freeze")) {
        	if(args.length == 1){
        		frozen = Main.getFreezList();
        		Player target = Bukkit.getPlayer(args[0]);
        		if(!frozen.contains(target)) {
        			frozen.add(target);
        			player.sendMessage("");
        			player.sendMessage("Vous avez freeze " + target.getPlayer());
        		} else {
        			frozen.remove(target);
        			player.sendMessage("");
        			player.sendMessage("Vous avez défreeze " + target.getPlayer());
            	}
			}
        }
		
		return false;
	}

}
