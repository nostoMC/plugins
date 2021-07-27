package fr.djredstone.nosto.commands.home;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.djredstone.nosto.Main;

public class CommandHome implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Player p = (Player) sender;
		
		if(p.getWorld() == Bukkit.getWorld("survie") || p.getWorld() == Bukkit.getWorld("skyworld")) {		
		if(cmd.getName().equalsIgnoreCase("sethome")) {
			if(sender instanceof Player) {
				
				if(args.length == 0) {
					p.sendMessage("");
					p.sendMessage("�cUtilisation : /sethome <nom>");
					return false;
				}
				
				if(args.length == 1) {
					Main.getInstance().getConfig().set("home." + p.getUniqueId().toString() + "." + args[0] + ".world", p.getLocation().getWorld().getName());
					Main.getInstance().getConfig().set("home." + p.getUniqueId().toString() + "." + args[0] + ".x", p.getLocation().getX());
					Main.getInstance().getConfig().set("home." + p.getUniqueId().toString() + "." + args[0] + ".y", p.getLocation().getY());
					Main.getInstance().getConfig().set("home." + p.getUniqueId().toString() + "." + args[0] + ".z", p.getLocation().getZ());
					Main.getInstance().getConfig().set("home." + p.getUniqueId().toString() + "." + args[0] + ".pitch", p.getEyeLocation().getPitch());
					Main.getInstance().getConfig().set("home." + p.getUniqueId().toString() + "." + args[0] + ".yaw", p.getEyeLocation().getYaw());
					Main.getInstance().saveConfig();
					p.sendMessage("");
					p.sendMessage("�eLe home �6�l" + args[0] + " �eest maintenant mis en place en �a�l" + p.getLocation().getBlockX() + " �a�l" + p.getLocation().getBlockY() + " �a�l" + p.getLocation().getBlockZ() + " �e!");
					return false;
				}
				
				if(args.length >= 2) {
					p.sendMessage("");
					p.sendMessage("�cUtilisation : /sethome <nom>");
					return false;
				}
				
			}
		}
			
			if(cmd.getName().equalsIgnoreCase("home")) {
				if(sender instanceof Player) {
				
				if(args.length == 0) {
					p.sendMessage("");
					p.sendMessage("�cUtilisation : /home <nom>");
					return false;
				}
				
				if(args.length == 1) {
					if(Main.getInstance().getConfig().contains("home." + p.getUniqueId() + "." + args[0])) {
						World w = Bukkit.getServer().getWorld(Main.getInstance().getConfig().getString("home." + p.getUniqueId().toString() + "." + args[0] + ".world"));
						double x = Main.getInstance().getConfig().getDouble("home." + p.getUniqueId().toString() + "." + args[0] + ".x");
						double y = Main.getInstance().getConfig().getDouble("home." + p.getUniqueId().toString() + "." + args[0] + ".y");
						double z = Main.getInstance().getConfig().getDouble("home." + p.getUniqueId().toString() + "." + args[0] + ".z");
						double pitch = Main.getInstance().getConfig().getDouble("home." + p.getUniqueId().toString() + "." + args[0] + ".pitch");
						double yaw = Main.getInstance().getConfig().getDouble("home." + p.getUniqueId().toString() + "." + args[0] + ".yaw");
						p.teleport(new Location(w, x, y, z, (float) yaw, (float) pitch));
						p.sendMessage("");
						p.sendMessage("�eT�l�portation vers �6�l" + args[0] + " �e!");
						return false;
					} else {
						p.sendMessage("");
						p.sendMessage("�cLe home n'exste pas !");
						return false;
					}
				}
				
				if(args.length >= 2) {
					p.sendMessage("");
					p.sendMessage("�cUtilisation : /home <nom>");
					return false;
				}
				
			}
		} 
			if(cmd.getName().equalsIgnoreCase("delhome")) {	
			if(sender instanceof Player) {

				if(args.length == 0) {
					p.sendMessage("");
					p.sendMessage("�cUtilisation : /delhome <nom>");
					return false;
				}
			
				if(args.length == 1) {
					if(Main.getInstance().getConfig().contains("home." + p.getUniqueId().toString() + "." + args[0])) {
						Main.getInstance().getConfig().set("home." + p.getUniqueId().toString() + "." + args[0], null);
						Main.getInstance().saveConfig();
						p.sendMessage("");
						p.sendMessage("�eHome supprim� !");
						return false;
					}
				}
			
				if(args.length >= 2) {
					p.sendMessage("");
					p.sendMessage("�cUtilisation : /delhome <nom>");
					return false;
				}
			}
		}
			
			if(cmd.getName().equalsIgnoreCase("homelist")) {
				if(sender instanceof Player) {
					p.sendMessage("");
					p.sendMessage("Liste de vos homes : ");
				}
			}
		} else {
			p.sendMessage("");
			p.sendMessage("�cLes homes sont seulement autoris�s dans les mondes : �6�lSurvie �r| �6�lSkyblock");
		}
		
		return false;
	}

}
