package fr.djredstone.nostoSRVSH.listeners;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import fr.djredstone.nostoSRVSH.Main;

public class OnPlayerDeath implements Listener {
	
	ArrayList<Player> speedRunner = Main.getSpeedRunnerList();
	ArrayList<Player> hunter = Main.getHunterList();
	static Location loc;

	public OnPlayerDeath(Main main) {
		main.getServer().getPluginManager().registerEvents(this, main);
	}
	
	@EventHandler
	public void onPlayerDeathEvent(PlayerDeathEvent event) {
		if(event.getEntity() instanceof Player) {
			Player playerDeath = event.getEntity().getPlayer();
			Player killer = playerDeath.getKiller();
			
			if(speedRunner.contains(playerDeath)) {
				event.setDeathMessage("Â§eLe Â§bÂ§lSpeedRunner Â§eest mort ! (Â§6Â§l" + killer.getName() + " Â§câ  Â§4Â§l" + playerDeath.getName() + "Â§e)");
				loc = playerDeath.getLocation();
			} else if(hunter.contains(playerDeath)) {
				event.setDeathMessage("Â§eLe Â§bÂ§lSpeedRunner Â§ea tuer un Â§cÂ§lhunter Â§e! (Â§6Â§l" + killer.getName() + " Â§câ  Â§4Â§l" + playerDeath.getName() + "Â§e)");
				playerDeath.setGameMode(GameMode.SPECTATOR);
				for(Player players : Bukkit.getOnlinePlayers()) {
					if(players.getWorld().getName().startsWith("Hunt")) {
						players.setGameMode(GameMode.SPECTATOR);
						players.sendTitle("Â§eLes Â§cÂ§lHunters Â§e gagnent !", "", 10, 40, 20);
					}
				}
			}
		}
	}

	public static Location getDeathLocation() {
		return loc;
		
	}
}
