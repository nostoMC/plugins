package fr.djredstone.nosto.utils.vanish;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.djredstone.nosto.Main;

public class CommandVanish implements CommandExecutor {
	
	ArrayList<Player> vanishList = Main.getVanishList();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		Player player = (Player) sender;
		
		if(args.length == 0) {
			vanishList = Main.getVanishList();
			if(vanishList.contains(player)) {
				Main.unVanishPlayer(player);
				player.sendMessage("");
				player.sendMessage("�7Vous �tes maintenant visible !");
			} else {
				Main.vanishPlayer(player);
				player.sendMessage("");
				player.sendMessage("�7Vous �tes maintenant invisible !");
			}
		} else {
			Player target = Bukkit.getPlayer(args[0]);
			if(vanishList.contains(target)) {
				Main.unVanishPlayer(target);
				player.sendMessage("");
				player.sendMessage("�7Vous avez mis " + target.getName() + " visible !");
				target.sendMessage("");
				target.sendMessage("�7Vous �tes maintenant visible !");
			} else {
				Main.vanishPlayer(target);
				player.sendMessage("");
				player.sendMessage("�7Vous avez mis " + target.getName() + " en invisible !");
				target.sendMessage("");
				target.sendMessage("�7Vous �tes maintenant invisible !");
			}
		}
		
		return false;
	}

}
