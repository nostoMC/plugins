package fr.nosto.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import org.jetbrains.annotations.NotNull;

public class CommandHeal implements CommandExecutor {

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
		
		Player player = (Player) sender;

		if (args.length == 0) {
            player.setHealth(20);
            player.setFoodLevel(20);
            player.sendMessage("\n§2Votre barre de vie a été restaurée !");
            return true;
        }
        Player target = Bukkit.getServer().getPlayer(args[0]);
        if (target == null) {
            player.sendMessage("\n§cJoueur innconu");
            return true;
        }
        target.setHealth(20);
        target.setFoodLevel(20);
        target.sendMessage("\n§2Votre barre de vie a été restaurée !");
        player.sendMessage("\n§2La barre de vie de §a§l" + target.getName() + " §2a été restaurée !");
		
		return false;
	}

}
