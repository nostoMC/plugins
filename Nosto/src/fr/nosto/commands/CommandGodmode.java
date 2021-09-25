package fr.nosto.commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;

public class CommandGodmode implements CommandExecutor {
	
	private ArrayList<Player> list = new ArrayList<>();

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(sender instanceof Player) {
			Player player = (Player) sender;
			if(args.length == 0) {
				if(list.contains(player)) {
					list.remove(player);
					player.sendMessage("");
					player.sendMessage("§cVous n'êtes plus un dieu !");
				} else if(!list.contains(player)) {
					list.add(player);
					player.setAllowFlight(true);
					player.sendMessage("");
					player.sendMessage("§2Vous êtes un dieu !");
				}
			} else if(args.length >= 1) {
				player = Bukkit.getPlayer(args[0]);
				if(list.contains(player)) {
					list.remove(player);
					player.setAllowFlight(false);
					player.sendMessage("");
					player.sendMessage("§c" + player.getName() + " n'est plus un dieu !");
				} else if(!list.contains(player)) {
					list.add(player);
					player.setAllowFlight(true);
					player.sendMessage("");
					player.sendMessage("§2" + player.getName() + " est un dieu !");
				}
			}
		}
		
		return false;
	}
	
	@EventHandler
	public void onPlayerDamage(EntityDamageEvent event) {
	    if(list.contains(event.getEntity())) {
	        event.setCancelled(true);
	    }
	}
}
