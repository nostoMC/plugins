package fr.nostoNC.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import org.jetbrains.annotations.NotNull;

public class TabNightclub implements TabCompleter {

	private static final List<String> arguments = new ArrayList<>();
	static {
		arguments.add("join");
		arguments.add("leave");
		arguments.add("menu");
		arguments.add("bottomlaser");
	}
	private static final List<String> arguments2 = new ArrayList<>();
	static {
		arguments2.add("dj");
		arguments2.add("vip");
	}
	
	public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
		
		List<String> result = new ArrayList<>();
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
