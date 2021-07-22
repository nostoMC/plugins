package fr.djredstone.nosto.fly;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandFly implements CommandExecutor {
	
	private ArrayList<Player> list = new ArrayList<>();

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(sender instanceof Player) {
			Player player = (Player) sender;
			if(args.length == 0) {
				if(list.contains(player) || player.getAllowFlight() == true) {
					list.remove(player);
					player.setAllowFlight(false);
					player.sendMessage("");
					player.sendMessage("§cVous ne pouvez plus voler !");
				} else if(!list.contains(player)) {
					list.add(player);
					player.setAllowFlight(true);
					player.sendMessage("");
					player.sendMessage("§aVous pouvez voler !");
				}
			} else if(args.length >= 1) {
				player = Bukkit.getPlayer(args[0]);
				if(list.contains(player) || player.getAllowFlight() == true) {
					list.remove(player);
					player.setAllowFlight(false);
					player.sendMessage("");
					player.sendMessage("§c" + player.getName() + " ne peut plus voler !");
				} else if(!list.contains(player)) {
					list.add(player);
					player.setAllowFlight(true);
					player.sendMessage("");
					player.sendMessage("§a" + player.getName() + " peut voler !");
				}
			}
		}
		
		return false;
	}

}
