package fr.nosto;

import java.util.Date;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import org.bukkit.Bukkit;

import fr.nosto.commands.CommandAFK;
import fr.nosto.commands.CommandAnnonce;
import fr.nosto.commands.CommandClaim;
import fr.nosto.commands.CommandClearTchat;
import fr.nosto.commands.CommandEvent;
import fr.nosto.commands.CommandFeed;
import fr.nosto.commands.CommandFly;
import fr.nosto.commands.CommandFreeze;
import fr.nosto.commands.CommandGodmode;
import fr.nosto.commands.CommandHeal;
import fr.nosto.commands.CommandHome;
import fr.nosto.commands.CommandMenu;
import fr.nosto.commands.CommandMsg;
import fr.nosto.commands.CommandNosto;
import fr.nosto.commands.CommandSit;
import fr.nosto.commands.CommandSpawn;
import fr.nosto.commands.CommandSpeed;
import fr.nosto.commands.CommandStaffChat;
import fr.nosto.commands.CommandTrails;
import fr.nosto.commands.CommandVanish;
import fr.nosto.commands.TabEvent;
import fr.nosto.commands.TabHome;
import fr.nosto.commands.TabNosto;
import fr.nosto.commands.TabSpeed;
import fr.nosto.listeners.AFKListeners;
import fr.nosto.listeners.OnInteractListener;
import fr.nosto.listeners.OnItemDropListener;
import fr.nosto.listeners.OnJoinListener;
import fr.nosto.listeners.OnLeaveListener;
import fr.nosto.listeners.OnMessageListener;
import fr.nosto.listeners.OnMoveListener;
import fr.nosto.listeners.OnPlayerChangeWorldListener;
import fr.nosto.listeners.OnPlayerDamageListener;
import fr.nosto.listeners.OnServerListPingListener;
import fr.nosto.listeners.SitListeners;
import fr.nosto.menus.EventMenu;
import fr.nosto.menus.MainMenu;
import fr.nosto.menus.MinijeuxMenu;
import fr.nosto.menus.MondeOuvertMenu;
import fr.nosto.menus.ShopMenu;
import fr.nosto.menus.TpMenu;
import fr.nosto.menus.TrailsMenu;
import fr.nosto.menus.TrainingMenu;
import fr.nosto.tasks.MainLobbyParticles;
import fr.nosto.tasks.MainLobbyUtils;
import fr.nosto.tasks.ParticleEffectTask;
import fr.nosto.tasks.PluginListTask;
import fr.nosto.tasks.RandomBroadcastTask;
import fr.nosto.tasks.TabManager;
import fr.nosto.tasks.VanishLoop;

public class Setup {
	
	private TabManager tab;

	public Setup(Main main) {

		System.out.println("§b[Nosto] Plugin Custom Chargé !");
		
		// Commands
		main.getCommand("annonce").setExecutor(new CommandAnnonce());
		main.getCommand("fly").setExecutor(new CommandFly());
		main.getCommand("speed").setExecutor(new CommandSpeed());
			main.getCommand("speed").setTabCompleter(new TabSpeed());
		main.getCommand("staffChat").setExecutor(new CommandStaffChat());
		main.getCommand("sc").setExecutor(new CommandStaffChat());
		main.getCommand("god").setExecutor(new CommandGodmode());
		main.getCommand("godmode").setExecutor(new CommandGodmode());
		main.getCommand("msg").setExecutor(new CommandMsg());
		main.getCommand("sethome").setExecutor(new CommandHome());
		main.getCommand("home").setExecutor(new CommandHome());
			main.getCommand("home").setTabCompleter(new TabHome());
		main.getCommand("delhome").setExecutor(new CommandHome());
		main.getCommand("spawn").setExecutor(new CommandSpawn());
		main.getCommand("clearTchat").setExecutor(new CommandClearTchat());
		main.getCommand("ct").setExecutor(new CommandClearTchat());
		main.getCommand("claim").setExecutor(new CommandClaim());
		main.getCommand("unclaim").setExecutor(new CommandClaim());
		main.getCommand("sit").setExecutor(new CommandSit());
		main.getCommand("afk").setExecutor(new CommandAFK());
		main.getCommand("vanish").setExecutor(new CommandVanish());
		main.getCommand("freeze").setExecutor(new CommandFreeze());
		main.getCommand("nosto").setExecutor(new CommandNosto());
			main.getCommand("nosto").setTabCompleter(new TabNosto());
		main.getCommand("heal").setExecutor(new CommandHeal());
		main.getCommand("feed").setExecutor(new CommandFeed());
		main.getCommand("event").setExecutor(new CommandEvent());
			main.getCommand("event").setTabCompleter(new TabEvent());
		main.getCommand("trails").setExecutor(new CommandTrails());
		main.getCommand("menu").setExecutor(new CommandMenu());
		
		// Listeners
		Bukkit.getPluginManager().registerEvents(new SitListeners(), main);
		Bukkit.getPluginManager().registerEvents(new AFKListeners(), main);
		AFKListeners.onAFKLoop(main);
		Bukkit.getPluginManager().registerEvents(new OnItemDropListener(), main);
		Bukkit.getPluginManager().registerEvents(new OnLeaveListener(), main);
		Bukkit.getPluginManager().registerEvents(new OnJoinListener(), main);
		Bukkit.getPluginManager().registerEvents(new OnMoveListener(), main);
		Bukkit.getPluginManager().registerEvents(new OnInteractListener(), main);
		Bukkit.getPluginManager().registerEvents(new OnServerListPingListener(), main);
		Bukkit.getPluginManager().registerEvents(new OnMessageListener(), main);
		Bukkit.getPluginManager().registerEvents(new OnPlayerChangeWorldListener(), main);
		Bukkit.getPluginManager().registerEvents(new OnPlayerDamageListener(), main);
		// Gui Listeners
		Bukkit.getPluginManager().registerEvents(new MainMenu(), main);
			Bukkit.getPluginManager().registerEvents(new TrailsMenu(), main);
			Bukkit.getPluginManager().registerEvents(new ShopMenu(), main);
			Bukkit.getPluginManager().registerEvents(new TpMenu(), main);
				Bukkit.getPluginManager().registerEvents(new MondeOuvertMenu(), main);
				Bukkit.getPluginManager().registerEvents(new TrainingMenu(), main);
				Bukkit.getPluginManager().registerEvents(new EventMenu(), main);
				Bukkit.getPluginManager().registerEvents(new MinijeuxMenu(), main);
				Bukkit.getPluginManager().registerEvents(new TrailsMenu(), main);
		
		// Tasks
		new VanishLoop(main);
		new PluginListTask(main);
		new RandomBroadcastTask(main);
		new ParticleEffectTask(main);
		new MainLobbyParticles(main);
		new MainLobbyUtils(main);
		
		// Logger
		Bukkit.getServer().getLogger().addHandler(new Handler() {
            @Override
            public void close() throws SecurityException {/*Ignore this method*/}
 
            @Override
            public void flush() {/*Ignore this method*/}
 
            @Override
            public void publish(LogRecord log) {
				DiscordSetup.jda.getTextChannelById("827280062311038986").sendMessage("**[" + new Date(log.getMillis()*1000) + "] [" + log.getLevel().toString() + "]** `" + log.getMessage() + "`").queue();
            }
        });
		
		// Tab
		tab = new TabManager(main);
	    tab.addHeader("\n§8⋙ &b&lNosto §8⋘\n");
	    tab.addFooter("\n&9&lDiscord : discord.io/nosto\n                                             ");
	    
	    tab.showTab();
		
	}

}
