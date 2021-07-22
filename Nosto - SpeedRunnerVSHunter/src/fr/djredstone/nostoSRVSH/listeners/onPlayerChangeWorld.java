package fr.djredstone.nostoSRVSH.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

import fr.djredstone.nostoSRVSH.Main;

public class onPlayerChangeWorld implements Listener {
	
	public onPlayerChangeWorld(Main main) {
		main.getServer().getPluginManager().registerEvents(this, main);
	}

	@EventHandler
	public void onWorldChange(PlayerChangedWorldEvent event) {
		
	}

}
