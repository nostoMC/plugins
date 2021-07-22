package fr.djredstone.nosto.staffChat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandStaffChat implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
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
        String mess = "§c[StaffChat]§5 " + p.getDisplayName() + ": ";
        for (String s : args) {
            mess = mess + s + " ";
        }

        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.hasPermission("nosto.admin.chat")) {
                player.sendMessage(Color(mess));
            }
        }
		
		return false;
	}
	
	private String Color(String s) {
	        s = ChatColor.translateAlternateColorCodes('&', s);
	        return s;
	    }

}
