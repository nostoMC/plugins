package fr.djredstone.nosto.utils.afk;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandAFK implements CommandExecutor {
	
	static ArrayList<Player> afks = new ArrayList<Player>();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Player player = (Player) sender;
		
		if(afks.contains(player)) {
			player.setCustomName(player.getName());
			player.setCustomNameVisible(true);
			Bukkit.broadcastMessage("");
			Bukkit.broadcastMessage("�7�l" + player.getName() + " �7n'est plus AFK");
			afks.remove(player);
			player.setCustomName(player.getName());
		} else {
			player.setCustomName(player.getName() + " �7�l(AFK)");
			player.setCustomNameVisible(true);
			Bukkit.broadcastMessage("");
			Bukkit.broadcastMessage("�8�l" + player.getName() + " �8est AFK");
			afks.add(player);
			String playerName = player.getName();
			player.setCustomName(playerName + " �7�l(AFK)");
		}
		return false;
	}
	
	public static ArrayList<Player> getAFKS() {
		return afks;
	}
	
}
