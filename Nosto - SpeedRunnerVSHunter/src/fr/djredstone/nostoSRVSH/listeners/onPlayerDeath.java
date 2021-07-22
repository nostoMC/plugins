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

public class onPlayerDeath implements Listener {
	
	ArrayList<Player> speedRunner = Main.getSpeedRunnerList();
	ArrayList<Player> hunter = Main.getHunterList();
	static Location loc;

	public onPlayerDeath(Main main) {
		main.getServer().getPluginManager().registerEvents(this, main);
	}
	
	@EventHandler
	public void onPlayerDeathEvent(PlayerDeathEvent event) {
		if(event.getEntity() instanceof Player) {
			Player playerDeath = event.getEntity().getPlayer();
			Player killer = playerDeath.getKiller();
			
			if(speedRunner.contains(playerDeath)) {
				event.setDeathMessage("§eLe §b§lSpeedRunner §eest mort ! (§6§l" + killer.getName() + " §c☠ §4§l" + playerDeath.getName() + "§e)");
				loc = playerDeath.getLocation();
			} else if(hunter.contains(playerDeath)) {
				event.setDeathMessage("§eLe §b§lSpeedRunner §ea tuer un §c§lhunter §e! (§6§l" + killer.getName() + " §c☠ §4§l" + playerDeath.getName() + "§e)");
				playerDeath.setGameMode(GameMode.SPECTATOR);
				for(Player players : Bukkit.getOnlinePlayers()) {
					if(players.getWorld().getName().startsWith("Hunt")) {
						players.setGameMode(GameMode.SPECTATOR);
						players.sendTitle("§eLes §c§lHunters §e gagnent !", "", 10, 40, 20);
					}
				}
			}
		}
	}

	public static Location getDeathLocation() {
		return loc;
		
	}
}
