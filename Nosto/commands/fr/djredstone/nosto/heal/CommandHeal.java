package fr.djredstone.nosto.heal;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandHeal implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Player player = (Player) sender;

		if (args.length == 0) {
            player.setHealth(20);
            player.setFoodLevel(20);
            player.sendMessage("");
            player.sendMessage("§2Votre barre de vie a été restaurer !");
            return true;
        }
        Player target = Bukkit.getServer().getPlayer(args[0]);
        if (target == null) {
        	player.sendMessage("");
            player.sendMessage("§cJoueur innconu");
            return true;
        }
        target.setHealth(20);
        target.setFoodLevel(20);
        target.sendMessage("");
        target.sendMessage("§2Votre barre de vie a été restaurer !");
        player.sendMessage("");
        player.sendMessage("§2La barre de vie de §a§l" + target.getName() + " §2a été restaurer !!");
		
		return false;
	}

}
