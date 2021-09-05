package fr.djredstone.nostoW.commands;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import fr.djredstone.nostoW.Main;

public class CommandWorld implements CommandExecutor, TabCompleter {
	
	private List<String> arguments = new ArrayList<String>();
	private List<String> tpArguments = new ArrayList<String>();
	private List<String> tpPlayerArguments = new ArrayList<String>();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		switch(args[0]) {
			
		case "tp":
			
			for(Player player : Bukkit.getOnlinePlayers()) {
				if(args[1].equalsIgnoreCase(player.getName())) {
					
					for(World w : Bukkit.getServer().getWorlds()) {
						if(args[2].equalsIgnoreCase(w.getName())) {
							
							File file = new File(Main.getInstance().getDataFolder(), "worldInfo.yml");
							YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
							
							ConfigurationSection configSection = config.getConfigurationSection(w.getName() + ".spawn");
							
							double x = configSection.getDouble("x");
							double y = configSection.getDouble("y");
							double z = configSection.getDouble("z");
							float yam = (float) configSection.getDouble("yam");
							float pitch = (float) configSection.getDouble("pitch");
							
							player.teleport(new Location(w, x, y, z, yam, pitch));
							
						}
					}
					
				}
			}
			
			break;
		
		}
		
		return false;
	}
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		
		arguments.clear();
		arguments.add("tp");
		
		tpArguments.clear();
		for(Player player : Bukkit.getOnlinePlayers()) {
			tpArguments.add(player.getName());
		}
		
		tpPlayerArguments.clear();
		for(World w : Bukkit.getServer().getWorlds()) {
			tpPlayerArguments.add(w.getName());
		}
		
		List<String> result = new ArrayList<String>();
		if(args.length == 1) {
			for (String a : arguments) {
				if(a.toLowerCase().startsWith(args[0].toLowerCase())) {
					result.add(a);
				}
			}
			return result;
		} else if(args[0].equalsIgnoreCase("tp")) {
			if(args.length == 2) {
				for (String a : tpArguments) {
					if(a.toLowerCase().startsWith(args[1].toLowerCase())) {
						result.add(a);
					}
				}
				return result;
			} else if(args.length == 3) {
				for (String a : tpPlayerArguments) {
					if(a.toLowerCase().startsWith(args[2].toLowerCase())) {
						result.add(a);
					}
				}
				return result;
			} else if(args.length > 3) {
				result.add("");
				return result;
			}
		}
	
		return null;
	}

}
