package fr.djredstone.nosto.spawn;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSpawn implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Player player = (Player) sender;
		
		Location SurvivalLobby = new Location(Bukkit.getWorld("survie"), -0.5, 63, 0.5, 180f, 0f);
		Location skyblockLobby = new Location(Bukkit.getWorld("skyworld"), 0.5, 150, 4.5, 0f, -10f);
		Location freebuildLobby = new Location(Bukkit.getWorld("Freebuild"), 107.5, 63, 0.5, 0f, 0f);
		Location huntLobby = new Location(Bukkit.getWorld("Hunt"), -127, 64, 160, 0f, 0f);
		Location pvpLobby = new Location(Bukkit.getWorld("event"), 136, 70, -231, 0f, 0f);
		Location lgLobby = new Location(Bukkit.getWorld("lg"), 2841, 72, 3536, 0f, 0f);
		Location roueLobby = new Location(Bukkit.getWorld("RoueDeLaChance"), 0, 229, 50, 0f, 0f);
		Location showLobby = new Location(Bukkit.getWorld("show"), 0.5, 65, 0.5, 0f, 0f);
		
		if(player.getWorld() == Bukkit.getWorld("survie")) {
			player.sendMessage("");
			player.sendMessage("§eTéléportation au spawn !");
			player.teleport(SurvivalLobby);
		} else if(player.getWorld() == Bukkit.getWorld("skyworld")) {
			player.sendMessage("");
			player.sendMessage("§eTéléportation au spawn !");
			player.teleport(skyblockLobby);
		} else if(player.getWorld() == Bukkit.getWorld("Freebuild")) {
			player.sendMessage("");
			player.sendMessage("§eTéléportation au spawn !");
			player.teleport(freebuildLobby);
		} else if(player.getWorld() == Bukkit.getWorld("Hunt")) {
			player.sendMessage("");
			player.sendMessage("§eTéléportation au spawn !");
			player.teleport(huntLobby);
		} else if(player.getWorld() == Bukkit.getWorld("event")) {
			player.sendMessage("");
			player.sendMessage("§eTéléportation au spawn !");
			player.teleport(pvpLobby);
		} else if(player.getWorld() == Bukkit.getWorld("lg")) {
			player.sendMessage("");
			player.sendMessage("§eTéléportation au spawn !");
			player.teleport(lgLobby);
		} else if(player.getWorld() == Bukkit.getWorld("RoueDeLaChance")) {
			player.sendMessage("");
			player.sendMessage("§eTéléportation au spawn !");
			player.teleport(roueLobby);
		} else if(player.getWorld() == Bukkit.getWorld("show")) {
			player.sendMessage("");
			player.sendMessage("§eTéléportation au spawn !");
			player.teleport(showLobby);
		} else {
			player.sendMessage("");
			player.sendMessage("§cAucun spawn n'existe pour ce monde !");
		}
		
		return false;
	}

}
