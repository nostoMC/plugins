package fr.djredstone.nostoDuel;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import fr.djredstone.nostoDuel.Main;
import fr.djredstone.nostoDuel.commands.CommandDuel;
import fr.djredstone.nostoDuel.commands.duelTab;
import fr.djredstone.nostoDuel.listeners.onDisconnect;
import fr.djredstone.nostoDuel.listeners.onInteractListener;
import fr.djredstone.nostoDuel.listeners.onInventoryClick;
import fr.djredstone.nostoDuel.listeners.onItemDropListener;
import fr.djredstone.nostoDuel.listeners.onMoveItemInventoryListener;
import fr.djredstone.nostoDuel.listeners.onPlayerDamageListener;
import fr.djredstone.nostoDuel.listeners.onRespawn;
import fr.djredstone.nostoDuel.listeners.onplayerDeathListener;

public class Main extends JavaPlugin implements Listener {
	
	static Boolean duelStart = false;
	static ArrayList<Player> duel = new ArrayList<Player>();
	static ArrayList<Player> duelLobby = new ArrayList<Player>();
	
	public static Main instance;
	
	public static JavaPlugin getInstance() {
		return instance;
	}
	
	@Override
	public void onEnable() {
		getCommand("duel").setExecutor(new CommandDuel());
		getCommand("duel").setTabCompleter(new duelTab());
		new onInventoryClick(this);
		new onItemDropListener(this);
		new onMoveItemInventoryListener(this);
		new onPlayerDamageListener(this);
		new onInteractListener(this);
		new onplayerDeathListener(this);
		new onRespawn(this);
		new onDisconnect(this);
		instance = this;
		super.onEnable();
		
		new BukkitRunnable() {
			
			@Override
			public void run() {
				for(Player players : Bukkit.getOnlinePlayers()) {
					if(players.getWorld() == Bukkit.getWorld("duel")) {
						if(!players.isOp()) {
							players.setAllowFlight(false);
						}
						players.setFoodLevel(20);
					}
				}
				
			}
		}.runTaskTimer(this, 0, 40);
		
		new BukkitRunnable() {
			
			@Override
			public void run() {
				for(Player players : Bukkit.getOnlinePlayers()) {
					if(players.getWorld() != Bukkit.getWorld("duel")) {
						if(duelLobby.contains(players)) {
							duelLobby.remove(players);
						}
					}
				}
				
			}
		}.runTaskTimer(this, 0, 0);
	}
	
	@Override
	public void onDisable() {
		
	}
	
	public static ArrayList<Player> getDuelList() {
		return duel;
	}
	
	public static Boolean getDuelStart() {
		return duelStart;
	}
	
	public static ArrayList<Player> getDuelLobbyList() {
		return duelLobby;
	}
}
