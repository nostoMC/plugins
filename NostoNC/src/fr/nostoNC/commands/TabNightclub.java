package fr.nostoNC.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class TabNightclub implements TabCompleter {

	List<String> arguments = new ArrayList<String>();
	List<String> arguments2 = new ArrayList<String>();
	
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		if(arguments.isEmpty()) {
			arguments.add("join");
			arguments.add("leave");
			arguments.add("menu");
		}
		if(arguments2.isEmpty()) {
			arguments2.add("dj");
			arguments2.add("vip");
		}
		
		List<String> result = new ArrayList<String>();
		if(args.length == 1) {
			for (String a : arguments) {
				if(a.toLowerCase().startsWith(args[0].toLowerCase())) {
					result.add(a);
				}
			}
			return result;
		} else if(args.length == 2) {
			if(args[0].equalsIgnoreCase("join") || args[0].equalsIgnoreCase("leave")) {
				for (String a : arguments2) {
					if(a.toLowerCase().startsWith(args[1].toLowerCase())) {
						result.add(a);
					}
				}
				return result;
			}
		}
	
	return null;
		
	}

}
