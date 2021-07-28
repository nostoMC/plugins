package fr.djredstone.nosto.utils.sit;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class CommandSit implements CommandExecutor {
	

	static ArrayList<Player> sitting = new ArrayList<Player>();

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Player player = null;
		
		if(sender instanceof Player) {
			player = (Player) sender;
		} else {
			return true;
		}
		
		if(!player.isOnGround()) {
    		player.sendMessage("");
    		player.sendMessage("§cVous ne pouvez pas vous assoir en l'air !");
    		return true;
    	}
    	
    	if(sitting.contains(player)) {
    		player.sendMessage("");
    		player.sendMessage("§cVous êtes déjà assis !");
    		return true;
    	}
    	
    	sitting.add(player);
    	
    	Location loc = player.getLocation();
    	
    	Location newloc = new Location(loc.getWorld(), loc.getX(), loc.getY() - 1.7, loc.getZ(), loc.getYaw(), loc.getPitch());
    	
    	World world = player.getWorld();
    	ArmorStand chair = (ArmorStand) world.spawnEntity(newloc, EntityType.ARMOR_STAND);
    	
    	chair.setGravity(false);
    	chair.setVisible(false);
    	chair.setInvulnerable(false);
    	chair.addPassenger(player);
		
		return false;
	}
	
	public static ArrayList<Player> getSitting() {
		return sitting;
	}

}
