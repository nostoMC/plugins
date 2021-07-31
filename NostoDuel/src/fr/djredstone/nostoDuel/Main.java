package fr.djredstone.nostoDuel;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import fr.djredstone.nostoDuel.Main;
import fr.djredstone.nostoDuel.commands.CommandDuel;
import fr.djredstone.nostoDuel.commands.TabDuel;
import fr.djredstone.nostoDuel.listeners.OnDisconnect;
import fr.djredstone.nostoDuel.listeners.OnInteractListener;
import fr.djredstone.nostoDuel.listeners.OnInventoryClick;
import fr.djredstone.nostoDuel.listeners.OnItemDropListener;
import fr.djredstone.nostoDuel.listeners.OnMoveItemInventoryListener;
import fr.djredstone.nostoDuel.listeners.OnPlayerDamageListener;
import fr.djredstone.nostoDuel.listeners.OnRespawn;
import fr.djredstone.nostoDuel.listeners.OnplayerDeathListener;

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
		getCommand("duel").setTabCompleter(new TabDuel());
		new OnInventoryClick(this);
		new OnItemDropListener(this);
		new OnMoveItemInventoryListener(this);
		new OnPlayerDamageListener(this);
		new OnInteractListener(this);
		new OnplayerDeathListener(this);
		new OnRespawn(this);
		new OnDisconnect(this);
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
