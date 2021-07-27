package fr.djredstone.nosto;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import fr.djredstone.nosto.commands.annonce.CommandAnnonce;
import fr.djredstone.nosto.commands.claim.CommandClaim;
import fr.djredstone.nosto.commands.clearTchat.CommandClearTchat;
import fr.djredstone.nosto.commands.command.CommandNosto;
import fr.djredstone.nosto.commands.event.CommandEvent;
import fr.djredstone.nosto.commands.event.TabEvent;
import fr.djredstone.nosto.commands.feed.CommandFeed;
import fr.djredstone.nosto.commands.fly.CommandFly;
import fr.djredstone.nosto.commands.freeze.CommandFreeze;
import fr.djredstone.nosto.commands.godmode.CommandGodmode;
import fr.djredstone.nosto.commands.heal.CommandHeal;
import fr.djredstone.nosto.commands.home.CommandHome;
import fr.djredstone.nosto.commands.home.TabHome;
import fr.djredstone.nosto.commands.lobby.CommandLobby;
import fr.djredstone.nosto.commands.mdp.CommandMDP;
import fr.djredstone.nosto.commands.menu.CommandMenu;
import fr.djredstone.nosto.commands.msg.CommandMsg;
import fr.djredstone.nosto.commands.spawn.CommandSpawn;
import fr.djredstone.nosto.commands.speed.CommandSpeed;
import fr.djredstone.nosto.commands.speed.TabSpeed;
import fr.djredstone.nosto.commands.staffChat.CommandStaffChat;
import fr.djredstone.nosto.commands.trails.CommandTrails;
import fr.djredstone.nosto.listeners.OnInteractListener;
import fr.djredstone.nosto.listeners.OnInventoryClickListener;
import fr.djredstone.nosto.listeners.OnItemDropListener;
import fr.djredstone.nosto.listeners.OnJoinListener;
import fr.djredstone.nosto.listeners.OnLeaveListener;
import fr.djredstone.nosto.listeners.OnMessageListener;
import fr.djredstone.nosto.listeners.OnMoveItemInventoryListener;
import fr.djredstone.nosto.listeners.OnMoveListener;
import fr.djredstone.nosto.listeners.OnServerListPingListener;
import fr.djredstone.nosto.tasks.PluginListTask;
import fr.djredstone.nosto.tasks.RandomBroadcastTask;
import fr.djredstone.nosto.tasks.trails.FlameTrails;
import fr.djredstone.nosto.utils.afk.AFKListeners;
import fr.djredstone.nosto.utils.afk.CommandAFK;
import fr.djredstone.nosto.utils.sit.CommandSit;
import fr.djredstone.nosto.utils.sit.SitListeners;
import fr.djredstone.nosto.utils.vanish.CommandVanish;
import fr.djredstone.nosto.utils.vanish.VanishLoop;

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
		
		System.out.println("�b[Nosto] Plugin Custom Charg� !");
		
		getServer().getPluginManager().registerEvents(this, this);
		getCommand("annonce").setExecutor(new CommandAnnonce());
		getCommand("fly").setExecutor(new CommandFly());
		getCommand("speed").setExecutor(new CommandSpeed());
		getCommand("speed").setTabCompleter(new TabSpeed());
		getCommand("staffChat").setExecutor(new CommandStaffChat());
		getCommand("sc").setExecutor(new CommandStaffChat());
		getCommand("god").setExecutor(new CommandGodmode());
		getCommand("godmode").setExecutor(new CommandGodmode());
		getCommand("msg").setExecutor(new CommandMsg());
		getCommand("sethome").setExecutor(new CommandHome());
		getCommand("home").setExecutor(new CommandHome());
		getCommand("home").setTabCompleter(new TabHome());
		getCommand("delhome").setExecutor(new CommandHome());
		getCommand("spawn").setExecutor(new CommandSpawn());
		getCommand("clearTchat").setExecutor(new CommandClearTchat());
		getCommand("ct").setExecutor(new CommandClearTchat());
		getCommand("claim").setExecutor(new CommandClaim());
		getCommand("unclaim").setExecutor(new CommandClaim());
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
		getCommand("event").setTabCompleter(new TabEvent());
		getCommand("trails").setExecutor(new CommandTrails());
		getCommand("menu").setExecutor(new CommandMenu());
		
		new SitListeners(this);
		new AFKListeners(this);
		AFKListeners.onAFKLoop(this);
		new OnItemDropListener(this);
		new OnMoveItemInventoryListener(this);
		new OnInventoryClickListener(this);
		new OnMessageListener(this);
		new OnLeaveListener(this);
		new OnJoinListener(this);
		new OnMoveListener(this);
		new OnInteractListener(this);
		new OnServerListPingListener(this);
		
		new VanishLoop(this);
		
		new PluginListTask(this);
		
		new RandomBroadcastTask(this);
		
		new FlameTrails(this);
		
	}

	@Override
	public void onDisable() {
		System.out.println("�b[Nosto] Plugin Custom D�charg� !");
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
