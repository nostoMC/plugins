package fr.djredstone.nostoNC.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.djredstone.nostoNC.Main;
import fr.djredstone.nostoNC.menus.EffectsMenu;

public class CommandNightclub implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		Player player = (Player) sender;
		
		if(player.getWorld() != Bukkit.getWorld("Nightclub")) return true;
		
		if(cmd.getName().equalsIgnoreCase("nightclub")) {
			
			if(args.length == 1) {
				if(args[0].equalsIgnoreCase("menu")) {
					// if(dj.contains(player)) {
						EffectsMenu.openMenu(player);
					// }
				}
			} else if(args.length == 2) {
				if(args[1].equalsIgnoreCase("dj")) {
					if(args[0].equalsIgnoreCase("join")) {
						if(Main.dj.size() != 1) {
							if(!player.hasPermission("nosto.nightclub.dj")) return true;
							Main.dj.add(player);
							player.teleport(new Location(Bukkit.getWorld("Nightclub"), -12.5, 65, 13.5, -90, 0));
							Bukkit.broadcastMessage("");
							Bukkit.broadcastMessage("§6§l" + player.getName() + " §eest notre nouveau DJ !");
						} else {
							if(Main.dj.contains(player)) {
								player.teleport(new Location(Bukkit.getWorld("Nightclub"), -12.5, 65, 13.5, -90, 0));
								player.sendMessage("");
								player.sendMessage("§eDe nouveau sur la scène !");
							} else {
								player.sendMessage("");
								player.sendMessage("§cUn DJ est déjà là !");
							}
						}
					} else if(args[0].equalsIgnoreCase("leave")) {
						if(Main.dj.contains(player)) {
							Main.dj.remove(player);
							player.teleport(new Location(Bukkit.getWorld("Nightclub"), -14.5, 65, 13.5, 90, 0));
							Bukkit.broadcastMessage("");
							Bukkit.broadcastMessage("§6§l" + player.getName() + " §en'est plus DJ !");
						}
					}
				} else if(args[1].equalsIgnoreCase("vip")) {
					if(args[0].equalsIgnoreCase("join")) {
						if(!player.hasPermission("nosto.nightclub.vip")) return true;
						if(Main.vip.contains(player)) {
							player.teleport(new Location(Bukkit.getWorld("Nightclub"), -9.0, 64, -45.7, 0, 0));
							player.sendMessage("");
							player.sendMessage("§eDe nouveau dans la zone VIP !");
						} else {
							Main.vip.add(player);
							player.teleport(new Location(Bukkit.getWorld("Nightclub"), -9.0, 64, -45.7, 0, 0));
							player.sendMessage("");
							player.sendMessage("§eBienvenue dans la zone VIP !");
						}
						
					} else if(args[0].equalsIgnoreCase("leave")) {
						if(Main.vip.contains(player)) {
							Main.vip.remove(player);
							player.teleport(new Location(Bukkit.getWorld("Nightclub"), -8.9, 64, -47.3, 180, 0));
							player.sendMessage("");
							player.sendMessage("§eA bientôt dans la zone VIP !");
						}
					}
				}
			}
		}
		
		return false;
	}

}
