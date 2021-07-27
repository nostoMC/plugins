package fr.djredstone.nostoDuel.commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import fr.djredstone.nostoDuel.Main;
import fr.djredstone.nostoDuel.listeners.onInventoryClick;

public class CommandDuel implements CommandExecutor {
	
	static Boolean duelStart = Main.getDuelStart();
	static ArrayList<Player> duel = Main.getDuelList();
	static BukkitTask demandeExpire = onInventoryClick.getDemandeExpire();
	static String demandeAccepted = "false";

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Player player = null;
		if(sender instanceof Player) {
			player = (Player) sender;
		}
		
		if(player.getWorld() == Bukkit.getWorld("duel")) {
			
			if(args.length == 1) {
				if(args[0].toString().equalsIgnoreCase("accept")) {
				
					if(!duel.isEmpty()) {
					
						if(duel.get(0) != player) {
						
							if(duelStart == false) {
							
								duel.add(player);
								demandeAccepted = "true";
							
							} else {
								player.sendMessage("");
								player.sendMessage("§cUne partie est déjà en cours");
							}
						
						} else {
							player.sendMessage("");
							player.sendMessage("§cTu ne peut pas accepter ton propre duel !");
						}
					
					} else {
						player.sendMessage("");
						player.sendMessage("§cAucune demande en cours");
					}
				
				} else {
					player.sendMessage("");
					player.sendMessage("§cUtilisation : /duel {accept}");
				}
			} else {
				player.sendMessage("");
				player.sendMessage("§cUtilisation : /duel {accept}");
			}
		
		} else {
			player.sendMessage("");
			player.sendMessage("§cCette commande est utilisable uniquement dans le monde duel !");
		}
		
		return false;
	}
	
	public static String getDemandeAccepted() {
		return demandeAccepted;
	}
	
	public static String resetDemandeAccepted() {
		demandeAccepted = "false";
		return null;
	}
}
