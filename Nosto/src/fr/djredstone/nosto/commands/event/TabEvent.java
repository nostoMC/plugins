package fr.djredstone.nosto.commands.event;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class TabEvent implements TabCompleter {
	
	List<String> arguments = new ArrayList<String>();
	List<String> arguments2 = new ArrayList<String>();

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {

		if(arguments.isEmpty()) {
			arguments.add("add");
			arguments.add("stop");
		}
		if(arguments2.isEmpty()) {
			arguments2.add("sar");
			arguments2.add("lg");
			arguments2.add("show");
			arguments2.add("nightclub");
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
			if(args[0].equalsIgnoreCase("add")) {
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