package fr.nosto.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import org.jetbrains.annotations.NotNull;

public class TabEvent implements TabCompleter {
	
	private static final List<String> arguments = new ArrayList<>();
	static {
		arguments.add("add");
		arguments.add("stop");
	}
	private static final List<String> arguments2 = new ArrayList<>();
	static {
		arguments2.add("nostoclub");
		arguments2.add("mainlobby");
	}

	@Override
	public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
		
		List<String> result = new ArrayList<>();

		if (args.length == 1) {
			for (String a : arguments) {
				if (a.toLowerCase().startsWith(args[0].toLowerCase())) {
					result.add(a);
				}
			}
		} else if (args.length == 2) {
			if (args[0].equalsIgnoreCase("add")) {
				for (String a : arguments2) {
					if (a.toLowerCase().startsWith(args[1].toLowerCase())) {
						result.add(a);
					}
				}
			}
		}
		
		return result;
	}

}
