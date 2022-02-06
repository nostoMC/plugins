package fr.nosto.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import org.jetbrains.annotations.NotNull;

public class TabNosto implements TabCompleter {
	
	private static final List<String> arguments = new ArrayList<>();
	static {
		arguments.add("reloadconfig");
		arguments.add("normaljoin");
	}

	@Override
	public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
		
		List<String> result = new ArrayList<>();

		if (args.length == 1) {
			for (String a : arguments) {
				if (a.toLowerCase().contains(args[0].toLowerCase())) {
					result.add(a);
				}
			}
		}

		return result;
	}

}
