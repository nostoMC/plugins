package fr.djredstone.nostoSRVSH.listeners;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import fr.djredstone.nostoSRVSH.Main;

public class onPlayerRespawn implements Listener {
	
	ArrayList<Player> speedRunner = Main.getSpeedRunnerList();
	ArrayList<Player> hunter = Main.getHunterList();
	Location deathLocation = onPlayerDeath.getDeathLocation();

	public onPlayerRespawn(Main main) {
		main.getServer().getPluginManager().registerEvents(this, main);
	}
	
	public void onPlayerRespawnEvent(PlayerRespawnEvent event) {
		Player player = event.getPlayer();
		if(hunter.contains(player)) {
			event.setRespawnLocation(new Location(Bukkit.getWorld("Hunt"), 0, 100, 0));
		}
	}

}
