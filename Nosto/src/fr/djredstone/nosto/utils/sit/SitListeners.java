package fr.djredstone.nosto.utils.sit;

import java.util.ArrayList;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.spigotmc.event.entity.EntityDismountEvent;

import fr.djredstone.nosto.Main;

public class SitListeners implements Listener {
	
	ArrayList<Player> sitting = CommandSit.getSitting();
	
	public SitListeners(Main main) {
		main.getServer().getPluginManager().registerEvents(this, main);
	}

	@EventHandler
	public void onDismount(EntityDismountEvent event) {
		
		if(!(event.getEntity() instanceof Player)) return;
		
		Player player = (Player) event.getEntity();
		
		if(!(event.getDismounted() instanceof ArmorStand)) return;
		
		ArmorStand as = (ArmorStand) event.getDismounted();
		
		sitting.remove(player);
		
		as.remove();
	}

}
