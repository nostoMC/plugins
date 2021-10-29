package fr.nosto.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import org.jetbrains.annotations.NotNull;

public class CommandFeed implements CommandExecutor {

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
		
		Player player = (Player) sender;

		if (args.length == 0) {
            player.setFoodLevel(20);
            player.sendMessage("");
            player.sendMessage("§2Votre barre de nourriture a été restaurer !");
            return true;
        }
        Player target = Bukkit.getServer().getPlayer(args[0]);
        if (target == null) {
        	player.sendMessage("");
            player.sendMessage("§cJoueur innconu");
            return true;
        }
        target.setFoodLevel(20);
        target.sendMessage("");
        target.sendMessage("§2Votre barre de nourriture a été restaurer !");
        player.sendMessage("");
        player.sendMessage("§2La barre de nourriture de §a§l" + target.getName() + " §2a été restaurer !");
		
		return false;
	}

}
