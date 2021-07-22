package fr.djredstone.nosto.home;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import fr.djredstone.nosto.Main;

public class HomeTab implements TabCompleter {
	
	List<String> arguments = new ArrayList<String>();
	
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args){
		
		Player player = (Player) sender;
		
		if(arguments.isEmpty()) {
			List<Character> list = Main.getInstance().getConfig().getCharacterList("home." + player.getUniqueId());
			for(int i = 0; i < list.size(); i++) {
				arguments.add(list.get(i).toString());
			}
		}
		
		List<String> result = new ArrayList<String>();
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
