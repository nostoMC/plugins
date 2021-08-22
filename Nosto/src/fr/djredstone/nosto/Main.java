package fr.djredstone.nosto;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

import javax.security.auth.login.LoginException;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
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
import fr.djredstone.nosto.menus.TrailsMenu;
import fr.djredstone.nosto.particleEffects.PlayerTrailsStats;
import fr.djredstone.nosto.tasks.ParticleEffectTask;
import fr.djredstone.nosto.tasks.PluginListTask;
import fr.djredstone.nosto.tasks.RandomBroadcastTask;
import fr.djredstone.nosto.utils.afk.AFKListeners;
import fr.djredstone.nosto.utils.afk.CommandAFK;
import fr.djredstone.nosto.utils.sit.CommandSit;
import fr.djredstone.nosto.utils.sit.SitListeners;
import fr.djredstone.nosto.utils.vanish.CommandVanish;
import fr.djredstone.nosto.utils.vanish.VanishLoop;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

public class Main extends JavaPlugin implements Listener, EventListener, CommandExecutor {

	public static ArrayList<Player> frozen = new ArrayList<Player>();
	public static ArrayList<Player> menuPlayers = new ArrayList<Player>();
	public static ArrayList<Player> vanishList = new ArrayList<Player>();
	public static ArrayList<Player> afks = new ArrayList<Player>();
	static HashMap<Player, PlayerTrailsStats> playerTrails = new HashMap<Player, PlayerTrailsStats>();
	
	public static Main instance;
	
	public static JavaPlugin getInstance() {
		return instance;
	}
	
	public static String token;
	public static JDA jda;
	
	public boolean isAReload = false;
	
	@Override
	public void onEnable() {
		
		instance = this;
		super.onEnable();
		
		System.out.println("§b[Nosto] Plugin Custom Chargé !");
		
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
		getCommand("freeze").setExecutor(new CommandFreeze());
		getCommand("nosto").setExecutor(new CommandNosto());
		getCommand("heal").setExecutor(new CommandHeal());
		getCommand("feed").setExecutor(new CommandFeed());
		getCommand("lobby").setExecutor(new CommandLobby());
		getCommand("event").setExecutor(new CommandEvent());
		getCommand("event").setTabCompleter(new TabEvent());
		getCommand("trails").setExecutor(new CommandTrails());
		getCommand("menu").setExecutor(new CommandMenu());
		
		// Listeners
		new SitListeners(this);
		new AFKListeners(this);
		AFKListeners.onAFKLoop(this);
		new OnItemDropListener(this);
		new OnMoveItemInventoryListener(this);
		new OnInventoryClickListener(this);
		new OnLeaveListener(this);
		new OnJoinListener(this);
		new OnMoveListener(this);
		new OnInteractListener(this);
		new OnServerListPingListener(this);
		// Gui Listeners
		Bukkit.getPluginManager().registerEvents(new TrailsMenu(), this);
		
		// Tasks
		new VanishLoop(this);
		new PluginListTask(this);
		new RandomBroadcastTask(this);
		new ParticleEffectTask(this);
		
		if(!this.getConfig().contains("token")) {
			this.getConfig().set("token", "YOUR TOKEN HERE");
		}
		
		this.saveConfig();
		
		token = this.getConfig().getString("token");
		
		try {
			jda = JDABuilder.createDefault(token).build();
		} catch (LoginException e) {
			e.printStackTrace();
		}
		
		JDABuilder builder = JDABuilder.createDefault(token);
	    
	    builder.disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE);
	    try {
			builder.build();
		} catch (LoginException e) {
			e.printStackTrace();
		}
	    
	    try {
			jda.awaitReady();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    Bukkit.getPluginManager().registerEvents(this, this);
	    Bukkit.getPluginManager().registerEvents(new OnMessageListener(), this);
	    jda.addEventListener(new OnMessageListener());
		
	}

	@Override
	public void onDisable() {
		System.out.println("§b[Nosto] Plugin Custom Déchargé !");
		
		EmbedBuilder embed = new EmbedBuilder();
		if(isAReload) {
			embed.setTitle("Reload !");
			embed.setColor(Color.ORANGE);
		} else {
			embed.setTitle("Serveur déconecté !");
			embed.setColor(Color.RED);
		}
			
		Main.jda.getTextChannelById("875315182556053524").sendMessage(embed.build()).queue();
		
		jda.shutdown();
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(cmd.getName().equalsIgnoreCase("reload") || cmd.getName().equalsIgnoreCase("rl")) {
			
			isAReload = true;
			
		}
		
		return true;
	}
	
	public static void setPlayerTrailStats(Player player, PlayerTrailsStats stats) {
		playerTrails.put(player, stats);
	}

	public static HashMap<Player, PlayerTrailsStats> getPlayerTrailsMap() {
		return playerTrails;
	}

	@Override
	public void onEvent(GenericEvent event) {
		if (event instanceof ReadyEvent) System.out.println("§cBot discord synchronisé avec minecraft prêt !");
		
	}
}
