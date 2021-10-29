package fr.nosto.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import org.jetbrains.annotations.NotNull;

public class CommandStaffChat implements CommandExecutor {

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
		
		if (!(sender instanceof Player)) {
            sender.sendMessage("Only for players");
            return false;
        }
        Player p = (Player) sender;
        if (!(p.hasPermission("nosto.admin.chat"))) {
        	p.sendMessage("");
            p.sendMessage(Color("&cVous n'avez pas les permissions !"));
            return false;
        }

        if (args.length < 1) {
        	p.sendMessage("");
            p.sendMessage(Color("&cLe message ne peut pas être vide !"));
            return false;
        }
        
        p.sendMessage("");
        StringBuilder mess = new StringBuilder("§c[StaffChat]§5 " + p.getDisplayName() + ": ");
        for (String s : args) {
            mess.append(s).append(" ");
        }

        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.hasPermission("nosto.admin.chat")) {
                player.sendMessage(Color(mess.toString()));
            }
        }
		
		return false;
	}
	
	private String Color(String s) {
	        s = ChatColor.translateAlternateColorCodes('&', s);
	        return s;
	    }

}
