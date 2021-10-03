package fr.nosto.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitRunnable;

import fr.nosto.Main;

public class CommandNosto implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if(cmd.getName().equalsIgnoreCase("nosto")) {
        	if(args.length >= 3) {
        		sender.sendMessage("");
				sender.sendMessage("Utilisation : /nosto reload <server | config>");
			} else {
				if(args[0].equalsIgnoreCase("reload")) {
					if(args[1].equalsIgnoreCase("server")) {
						Bukkit.broadcastMessage("");
						Bukkit.broadcastMessage("§4Reload dans §4§l10 §4secondes !");
						new BukkitRunnable() {
							int timer = 11;
							@Override
							public void run() {
								if(timer <= 5) {
									Bukkit.broadcastMessage("");
									Bukkit.broadcastMessage("§4Reload dans §4§l" + timer + "§4s !");
								}
								if(timer == 0) {
									Bukkit.broadcastMessage("");
									Bukkit.broadcastMessage("§4§lReload !");
									Bukkit.reload();
									this.cancel();
								}
								timer = timer - 1;
							}
						}.runTaskTimer(Main.instance, 0, 20);
					} else if(args[1].equalsIgnoreCase("config")) {
						Main.getInstance().reloadConfig();
						sender.sendMessage("Config reload !");
					} else {
						sender.sendMessage("");
						sender.sendMessage("Erreur (Utilisation : /reload <server | config>)");
					}
				} else {
					sender.sendMessage("");
					sender.sendMessage("Utilisation : /nosto reload <server | config>");
				}
			}
        }
		
		return false;
	}

}
