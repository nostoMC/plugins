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
		arguments.add("menu");
		arguments.add("bottomlaser");
	}
	
	public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
		
		if(args.length == 1) {
			List<String> result = new ArrayList<>();

			for (String a : arguments) {
				if(a.toLowerCase().startsWith(args[0].toLowerCase())) {
					result.add(a);
				}
			}
			return result;
		}
	
		return null;
		
	}

}
