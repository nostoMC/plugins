package fr.djredstone.nostoSRVSH.listeners;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import fr.djredstone.nostoSRVSH.Main;
import fr.djredstone.nostoSRVSH.menus.welcomeMenu;


public class onInventoryClickListener implements Listener {
	
	ArrayList<Player> speedRunner = Main.getSpeedRunnerList();
	ArrayList<Player> hunter = Main.getHunterList();
	
	public onInventoryClickListener(Main main) {
		main.getServer().getPluginManager().registerEvents(this, main);
	}

	@EventHandler
	public void onClick(InventoryClickEvent event) {
		
		Player player = (Player) event.getWhoClicked();
		ItemStack current = event.getCurrentItem();
		
		if(event.getView().getTitle().equalsIgnoreCase("§2§lTP > Events")) {
			event.setCancelled(true);
			
			if(current.getType() == Material.FLINT_AND_STEEL) {
				if(!player.hasPermission("server.huntAcces")) return;
				welcomeMenu.openMenu(player);
			}
		} else if(event.getView().getTitle().equalsIgnoreCase("§2§lTP > Events > Hunt")) {
			event.setCancelled(true);
			
			switch(current.getType()) {
			
			case LEATHER_BOOTS: // SPEEDRUNNER
				if(speedRunner.isEmpty()) {
					speedRunner.add(player);
					Location huntLobby = new Location(Bukkit.getWorld("Hunt"), 0, 100, 0, 0f, 0f);
					player.teleport(huntLobby);
					player.setGameMode(GameMode.SURVIVAL);
					player.sendMessage("§eVous êtes un §b§lSpeedRunner §e!");
				} else {
					hunter.add(player);
					Location huntLobby = new Location(Bukkit.getWorld("Hunt"), 0, 100, 0, 0f, 0f);
					player.teleport(huntLobby);
					player.setGameMode(GameMode.SURVIVAL);
					player.sendMessage("§cUn §b§lSpeedRunner §ca déjà pris la place ! §eVous êtes donc un §4§lhunter §e!");
				}
				break;
				
			case IRON_SWORD:
				hunter.add(player);
				Location huntLobby = new Location(Bukkit.getWorld("Hunt"), 0, 100, 0, 0f, 0f);
				player.teleport(huntLobby);
				player.setGameMode(GameMode.SURVIVAL);
				player.sendMessage("§eVous êtes un §4§lhunter §e!");
				break;
			
			default:
				break;
			
			}
		}
	}

}
