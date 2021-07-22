package fr.djredstone.nostoMCNF.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import fr.djredstone.nostoMCNF.musics.tutorial;

public class CommandMCNF implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(args[0].equalsIgnoreCase("start") || args[1].equalsIgnoreCase("tutorial") || args[2].equalsIgnoreCase("normal")) {
			tutorial.start("normal", new Location(Bukkit.getWorld("McNightFunkin"), 5.5, 63.0, 267.5, 90, 0), new Location(Bukkit.getWorld("McNightFunkin"), 5.5, 63.0, 268.5, 90, 0), new Location(Bukkit.getWorld("McNightFunkin"), 5.5, 63.0, 269.5, 90, 0), new Location(Bukkit.getWorld("McNightFunkin"), 5.5, 63.0, 270.5, 90, 0),new Location(Bukkit.getWorld("McNightFunkin"), 5.5, 63.0, 273.5, 90, 0), new Location(Bukkit.getWorld("McNightFunkin"), 5.5, 63.0, 274.5, 90, 0),new Location(Bukkit.getWorld("McNightFunkin"), 5.5, 63.0, 275.5, 90, 0), new Location(Bukkit.getWorld("McNightFunkin"), 5.5, 63.0, 276.5, 90, 0));
		}
		
		return false;
	}

}
