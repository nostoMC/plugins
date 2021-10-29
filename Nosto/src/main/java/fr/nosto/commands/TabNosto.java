package fr.nosto.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import org.jetbrains.annotations.NotNull;

public class TabNosto implements TabCompleter {
	
	List<String> arguments = new ArrayList<>();
	List<String> arguments2 = new ArrayList<>();

	@Override
	public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {

		if(arguments.isEmpty()) {
			arguments.add("reload");
		}
		if(arguments2.isEmpty()) {
			arguments2.add("server");
			arguments2.add("config");
		}
		
		List<String> result = new ArrayList<>();
		if(args.length == 1) {
			for (String a : arguments) {
				if(a.toLowerCase().startsWith(args[0].toLowerCase())) {
					result.add(a);
				}
			}
			return result;
		} else if(args.length == 2) {
			if(args[0].equalsIgnoreCase("reload")) {
				for (String a : arguments2) {
					if(a.toLowerCase().startsWith(args[1].toLowerCase())) {
						result.add(a);
					}
				}
				return result;
			}
		} else {
			result.add("");
			return result;
		}
		
		return null;
	}

}
