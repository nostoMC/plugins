package fr.nosto.commands;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import org.jetbrains.annotations.NotNull;

public class CommandSit implements CommandExecutor {
	
	public static final ArrayList<Player> sitting = new ArrayList<>();

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
		
		Player player;
		
		if (sender instanceof Player) {
			player = (Player) sender;
		} else {
			return true;
		}
		
		if (sitting.contains(player)) {
			player.sendMessage("\n§cVous êtes déjà assis !");
			return true;
		}

		//noinspection deprecation
		if (!player.isOnGround()) {
    		player.sendMessage("\n§cVous ne pouvez pas vous assoir en l'air !");
    		return true;
		}
		
    	sitting.add(player);

		Location loc = player.getLocation().add(0, -.2, 0);

		ArmorStand chair = (ArmorStand) player.getWorld().spawnEntity(loc, EntityType.ARMOR_STAND);

		chair.addScoreboardTag("seat");
		chair.setVisible(false);
		chair.setMarker(true);
    	chair.addPassenger(player);
		
		return false;
	}
	
}
