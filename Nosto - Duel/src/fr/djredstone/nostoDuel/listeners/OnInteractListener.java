package fr.djredstone.nostoDuel.listeners;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import fr.djredstone.nostoDuel.Main;
import fr.djredstone.nostoDuel.menus.KitSMenu;

public class OnInteractListener implements Listener {
	
	static ArrayList<Player> duelLobby = Main.getDuelLobbyList();
	static String demandeCancel = "false";

	public OnInteractListener(Main main) {
		main.getServer().getPluginManager().registerEvents(this, main);
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		
		if(event.getPlayer().getItemInHand().getType() == Material.BARRIER) {
			if(duelLobby.contains(event.getPlayer())) {
				demandeCancel = "true";
			}
		}
		
		if(event.getPlayer().getItemInHand().getType() == Material.DIAMOND_SWORD) {
			if(duelLobby.contains(event.getPlayer())) {
				KitSMenu.openMenu(event.getPlayer());
			}
		}
		
	}
	
	public static String getDemandeCancel() {
		return demandeCancel;
	}
	
	public static String reset() {
		demandeCancel = "false";
		return null;
	}
	
}
