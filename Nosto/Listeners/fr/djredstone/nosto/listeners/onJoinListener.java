package fr.djredstone.nosto.listeners;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.djredstone.nosto.Main;

public class onJoinListener implements Listener {
	
	ArrayList<Player> menuPlayers = Main.getMenuPlayersList();

	public onJoinListener(Main main) {
		main.getServer().getPluginManager().registerEvents(this, main);
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onJoin(PlayerJoinEvent event){
		event.setJoinMessage("");
		Player player = event.getPlayer();
		
		File file = new File(Main.getInstance().getDataFolder(), "economy.yml");
		YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
		
		if(config.getConfigurationSection("money." + player.getUniqueId().toString()) == null) {
			config.set("money." + player.getUniqueId().toString(), 0);
			try {
				config.save(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(!Main.getInstance().getServer().getPluginManager().isPluginEnabled("pluginpv")) {
			player.setMaxHealth(20);
			player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 1, 200));
			Main.vanishPlayer(player);
			Location lobby = new Location(Bukkit.getWorld("world"), 0.5, 252, 0.5, 0f, 0f);
			player.teleport(lobby);
			player.getInventory().clear();
			player.sendMessage("");
			player.sendMessage("");
			player.sendMessage("	§eSalut §6§l" + player.getName() + " §e! Afin de garentir une sécurité maximale sur ton compte, nous te demandons de te connecter avec §6§l/login§e. Si c'est la première fois que tu te connecte, il faudra créer un mot de passe avec §6§l/register §epuis le validé avec §6§l/valide §e!");
			player.sendMessage("");
			player.sendMessage("");
			menuPlayers.add(player);
			player.setGameMode(GameMode.ADVENTURE);
			Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " parent remove administrateur");
			Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "deop " + player.getName());
			Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " parent remove builder");
			Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "deop " + player.getName());
			Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " parent remove dev");
			Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "deop " + player.getName());
			player.teleport(lobby);
			player.setGameMode(GameMode.ADVENTURE);
		} else {
			Location lg = new Location(Bukkit.getWorld("lg"), 2841, 73, 3539);
			player.teleport(lg);
			player.teleport(lg);
			player.setGameMode(GameMode.ADVENTURE);
		}
	}

}
