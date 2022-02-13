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

        if (args.length < 1) {
            sender.sendMessage("\n§cLe message ne peut pas être vide !");
            return true;
        }

        StringBuilder builder = new StringBuilder("\n§3[StaffChat] §b");
        
        if (sender instanceof Player player) {
            builder.append(player.getDisplayName());
        } else {
            builder.append("[Console]");
        }
        
        builder.append("§7:§r");

        for (String s : args) {
            builder.append(" ").append(s);
        }

        String msg = Color(builder.toString());

        Bukkit.getLogger().warning(msg);
        Bukkit.broadcast(msg, "nosto.admin.chat");
		
		return true;
	}
	
	private String Color(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

}
