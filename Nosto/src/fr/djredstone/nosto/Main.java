package fr.djredstone.nosto;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import fr.djredstone.nosto.afk.AFKListeners;
import fr.djredstone.nosto.afk.CommandAFK;
import fr.djredstone.nosto.annonce.CommandAnnonce;
import fr.djredstone.nosto.claim.ClaimCommand;
import fr.djredstone.nosto.clearTchat.CommandClearTchat;
import fr.djredstone.nosto.command.CommandNosto;
import fr.djredstone.nosto.event.CommandEvent;
import fr.djredstone.nosto.event.tabEvent;
import fr.djredstone.nosto.feed.CommandFeed;
import fr.djredstone.nosto.fly.CommandFly;
import fr.djredstone.nosto.freeze.CommandFreeze;
import fr.djredstone.nosto.godmode.CommandGodmode;
import fr.djredstone.nosto.heal.CommandHeal;
import fr.djredstone.nosto.home.Home;
import fr.djredstone.nosto.home.HomeTab;
import fr.djredstone.nosto.listeners.onInteractListener;
import fr.djredstone.nosto.listeners.onInventoryClickListener;
import fr.djredstone.nosto.listeners.onItemDropListener;
import fr.djredstone.nosto.listeners.onJoinListener;
import fr.djredstone.nosto.listeners.onLeaveListener;
import fr.djredstone.nosto.listeners.onMessageListener;
import fr.djredstone.nosto.listeners.onMoveItemInventoryListener;
import fr.djredstone.nosto.listeners.onMoveListener;
import fr.djredstone.nosto.listeners.onServerListPingListener;
import fr.djredstone.nosto.lobby.CommandLobby;
import fr.djredstone.nosto.mdp.CommandMDP;
import fr.djredstone.nosto.menu.CommandMenu;
import fr.djredstone.nosto.msg.CommandMsg;
import fr.djredstone.nosto.sit.CommandSit;
import fr.djredstone.nosto.sit.SitListeners;
import fr.djredstone.nosto.spawn.CommandSpawn;
import fr.djredstone.nosto.speed.CommandSpeed;
import fr.djredstone.nosto.speed.speedTab;
import fr.djredstone.nosto.staffChat.CommandStaffChat;
import fr.djredstone.nosto.tasks.pluginListTask;
import fr.djredstone.nosto.tasks.randomBroadcastTask;
import fr.djredstone.nosto.tasks.trails.flameTrails;
import fr.djredstone.nosto.trails.CommandTrails;
import fr.djredstone.nosto.vanish.CommandVanish;
import fr.djredstone.nosto.vanish.vanishLoop;

public class Main extends JavaPlugin implements Listener {

	static ArrayList<Player> frozen = new ArrayList<Player>();
	static ArrayList<Player> menuPlayers = new ArrayList<Player>();
	static ArrayList<Player> vanishList = new ArrayList<Player>();
	static ArrayList<Player> afks = new ArrayList<Player>();
	static HashMap<Player, Boolean> trailsFlame = new HashMap<Player, Boolean>();
	
	public static Main instance;
	
	public static JavaPlugin getInstance() {
		return instance;
	}
	
	@Override
	public void onEnable() {
		
		instance = this;
		super.onEnable();
		
		System.out.println("§b[Nosto] Plugin Custom Chargé !");
		
		getServer().getPluginManager().registerEvents(this, this);
		getCommand("annonce").setExecutor(new CommandAnnonce());
		getCommand("fly").setExecutor(new CommandFly());
		getCommand("speed").setExecutor(new CommandSpeed());
		getCommand("speed").setTabCompleter(new speedTab());
		getCommand("staffChat").setExecutor(new CommandStaffChat());
		getCommand("sc").setExecutor(new CommandStaffChat());
		getCommand("god").setExecutor(new CommandGodmode());
		getCommand("godmode").setExecutor(new CommandGodmode());
		getCommand("msg").setExecutor(new CommandMsg());
		getCommand("sethome").setExecutor(new Home());
		getCommand("home").setExecutor(new Home());
		getCommand("home").setTabCompleter(new HomeTab());
		getCommand("delhome").setExecutor(new Home());
		getCommand("spawn").setExecutor(new CommandSpawn());
		getCommand("clearTchat").setExecutor(new CommandClearTchat());
		getCommand("ct").setExecutor(new CommandClearTchat());
		getCommand("claim").setExecutor(new ClaimCommand());
		getCommand("unclaim").setExecutor(new ClaimCommand());
		getCommand("sit").setExecutor(new CommandSit());
		getCommand("afk").setExecutor(new CommandAFK());
		getCommand("vanish").setExecutor(new CommandVanish());
		getCommand("mdp").setExecutor(new CommandMDP());
		getCommand("login").setExecutor(new CommandMDP());
		getCommand("register").setExecutor(new CommandMDP());
		getCommand("valide").setExecutor(new CommandMDP());
		getCommand("freeze").setExecutor(new CommandFreeze());
		getCommand("nosto").setExecutor(new CommandNosto());
		getCommand("heal").setExecutor(new CommandHeal());
		getCommand("feed").setExecutor(new CommandFeed());
		getCommand("lobby").setExecutor(new CommandLobby());
		getCommand("event").setExecutor(new CommandEvent());
		getCommand("event").setTabCompleter(new tabEvent());
		getCommand("trails").setExecutor(new CommandTrails());
		getCommand("menu").setExecutor(new CommandMenu());
		
		new SitListeners(this);
		new AFKListeners(this);
		AFKListeners.onAFKLoop(this);
		new onItemDropListener(this);
		new onMoveItemInventoryListener(this);
		new onInventoryClickListener(this);
		new onMessageListener(this);
		new onLeaveListener(this);
		new onJoinListener(this);
		new onMoveListener(this);
		new onInteractListener(this);
		new onServerListPingListener(this);
		
		new vanishLoop(this);
		
		new pluginListTask(this);
		
		new randomBroadcastTask(this);
		
		new flameTrails(this);
		
	}

	@Override
	public void onDisable() {
		System.out.println("§b[Nosto] Plugin Custom Déchargé !");
	}
	
	public static void vanishPlayer(Player player) {
		vanishList.add(player);
	}
	
	public static void unVanishPlayer(Player player) {
		vanishList.remove(player);
	}
	
	public static ArrayList<Player> getVanishList() {
		return vanishList;
	}

	public static ArrayList<Player> getMenuPlayersList() {
		return menuPlayers;
	}
	
	public static ArrayList<Player> getFreezList() {
		return frozen;
	}
	
	public static ArrayList<Player> getAfksList() {
		return afks;
	}
	
	public static void setPlayerFlameTrails(Player player, Boolean bool) {
		trailsFlame.put(player, bool);
	}
	
	public static HashMap<Player, Boolean> getPlayerFlameTrails() {
		return trailsFlame;
	}
}
