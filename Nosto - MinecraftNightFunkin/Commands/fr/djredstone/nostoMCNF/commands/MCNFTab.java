package fr.djredstone.nostoMCNF.commands;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import fr.djredstone.nostoMCNF.Main;

public class MCNFTab implements TabCompleter {
	
	List<String> arguments = new ArrayList<String>();
	List<String> arguments2 = new ArrayList<String>();
	List<String> arguments3 = new ArrayList<String>();

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		
		for (File file : new File(Main.getInstance().getDataFolder().getAbsolutePath() + File.separator + "FOLDER NAME").listFiles()) {
			
		}

		if(arguments.isEmpty()) {
			arguments.add("start");
		}
		if(arguments2.isEmpty()) {
			arguments2.add("tutorial");
		}
		if(arguments3.isEmpty()) {
			arguments3.add("normal");
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
			for (String a : arguments2) {
				if(a.toLowerCase().startsWith(args[1].toLowerCase())) {
					result.add(a);
				}
			}
			return result;
		} else if(args.length == 3) {
			for (String a : arguments3) {
				if(a.toLowerCase().startsWith(args[2].toLowerCase())) {
					result.add(a);
				}
			}
			return result;
		}
	
	return null;
	}

}
