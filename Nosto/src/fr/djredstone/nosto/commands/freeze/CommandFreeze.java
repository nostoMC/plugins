package fr.djredstone.nosto.commands.freeze;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.djredstone.nosto.Main;

public class CommandFreeze implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Player player = (Player) sender;

		if(cmd.getName().equalsIgnoreCase("freeze")) {
        	if(args.length == 1){
        		Player target = Bukkit.getPlayer(args[0]);
        		if(!Main.frozen.contains(target)) {
        			Main.frozen.add(target);
        			player.sendMessage("");
        			player.sendMessage("§eVous avez freeze §6§l" + target.getPlayer());
        		} else {
        			Main.frozen.remove(target);
        			player.sendMessage("");
        			player.sendMessage("§eVous avez défreeze §6§l" + target.getPlayer());
            	}
			}
        }
		
		return false;
	}

}
