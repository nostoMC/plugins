package fr.nosto.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import org.jetbrains.annotations.NotNull;

public class TabSpeed implements TabCompleter {

	List<String> arguments = new ArrayList<>();

	public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
		if(arguments.isEmpty()) {
			arguments.add("1");
			arguments.add("2");
			arguments.add("5");
			arguments.add("10");
		}
		
		List<String> result = new ArrayList<>();
		if(args.length == 1) {
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
