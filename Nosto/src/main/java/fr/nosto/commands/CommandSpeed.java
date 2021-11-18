package fr.nosto.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import org.jetbrains.annotations.NotNull;

public class CommandSpeed implements CommandExecutor {

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] strings) {
		
		if (!(sender instanceof Player)) {
            sender.sendMessage("Player only command!");
            return false;
        }
        Player p = (Player) sender;
        if (!p.hasPermission("nosto.speed")) {
            p.sendMessage(Color("&cVous n'avez pas les permissions !"));
            return false;
        }
        if (strings.length == 0) {
        	p.sendMessage("");
            p.sendMessage(Color("&cVeuillez choisir une vitesse entre 1 et 10"));
            return false;
        }
        int speed;
        try {
            speed = Integer.parseInt(strings[0]);
        } catch (NumberFormatException e) {
        	p.sendMessage("");
            p.sendMessage(Color("&cVeuillez choisir une vitesse entre 1 et 10"));
            return false;
        }
        if (speed < 1 || speed > 10) {
        	p.sendMessage("");
            p.sendMessage(Color("&cVeuillez choisir une vitesse entre 1 et 10"));
            return false;
        }
        if (p.isFlying()) {
            p.setFlySpeed((float) speed / 10);
        } else {
            p.setWalkSpeed((float) speed/ 10);
        }
        p.sendMessage("");
        p.sendMessage(Color("&eLa vitesse a été mis à jour sur §6§l" + speed));
        return true;
	}
	
	private String Color(String s) {
        s = ChatColor.translateAlternateColorCodes('&', s);
        return s;
    }

}