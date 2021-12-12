package fr.nosto;

import org.bukkit.Bukkit;

import fr.nosto.commands.*;
import fr.nosto.listeners.*;
import fr.nosto.menus.MainMenu;
import fr.nosto.menus.mainmenu.ShopMenu;
import fr.nosto.menus.mainmenu.TpMenu;
import fr.nosto.menus.mainmenu.TrailsMenu;
import fr.nosto.menus.mainmenu.tpmenu.EventMenu;
import fr.nosto.menus.mainmenu.tpmenu.MinijeuxMenu;
import fr.nosto.menus.mainmenu.tpmenu.MondeOuvertMenu;
import fr.nosto.menus.mainmenu.tpmenu.TrainingMenu;
import fr.nosto.tasks.*;

public class Setup {

	public Setup(Main main) {

		// Message manager
		MessageManager.init(main);

		// Commands
		registerCommands(main);

		// Listeners
		Bukkit.getPluginManager().registerEvents(new SitListeners(), main);
		Bukkit.getPluginManager().registerEvents(new OnItemDropListener(), main);
		Bukkit.getPluginManager().registerEvents(new OnLeaveListener(), main);
		Bukkit.getPluginManager().registerEvents(new OnJoinListener(), main);
		Bukkit.getPluginManager().registerEvents(new OnMoveListener(), main);
		Bukkit.getPluginManager().registerEvents(new OnInteractListener(), main);
		Bukkit.getPluginManager().registerEvents(new OnServerListPingListener(), main);
		Bukkit.getPluginManager().registerEvents(new OnMessageListener(), main);
		Bukkit.getPluginManager().registerEvents(new OnPlayerChangeWorldListener(), main);
		Bukkit.getPluginManager().registerEvents(new OnPlayerDamageListener(), main);

		// Menu Listeners
		Bukkit.getPluginManager().registerEvents(new MainMenu(), main);
			Bukkit.getPluginManager().registerEvents(new TrailsMenu(), main);
			Bukkit.getPluginManager().registerEvents(new ShopMenu(), main);
			Bukkit.getPluginManager().registerEvents(new TpMenu(), main);
				Bukkit.getPluginManager().registerEvents(new MondeOuvertMenu(), main);
				Bukkit.getPluginManager().registerEvents(new TrainingMenu(), main);
				Bukkit.getPluginManager().registerEvents(new EventMenu(), main);
				Bukkit.getPluginManager().registerEvents(new MinijeuxMenu(), main);

		// Tasks
		PluginListTask.list(main);

		VanishLoop.init(main);
		RandomBroadcastTask.init(main);
		CosmeticEffectTask.init(main);
		MainLobbyParticles.init(main);
		MainLobbyJumpPads.init(main);

		System.out.println("§b[Nosto] Plugin Custom Chargé !");
	}

	@SuppressWarnings("ConstantConditions")
	private void registerCommands(Main main) {
		
		main.getCommand("annonce").setExecutor(new CommandAnnonce());
		main.getCommand("fly").setExecutor(new CommandFly());
		main.getCommand("speed").setExecutor(new CommandSpeed());
			main.getCommand("speed").setTabCompleter(new TabSpeed());
		main.getCommand("staffChat").setExecutor(new CommandStaffChat());
		main.getCommand("msg").setExecutor(new CommandMsg());
		main.getCommand("sethome").setExecutor(new CommandHome());
		main.getCommand("home").setExecutor(new CommandHome());
			main.getCommand("home").setTabCompleter(new TabHome());
		main.getCommand("delhome").setExecutor(new CommandHome());
		main.getCommand("spawn").setExecutor(new CommandSpawn());
		main.getCommand("cleartchat").setExecutor(new CommandClearTchat());
		main.getCommand("claim").setExecutor(new CommandClaim());
		main.getCommand("unclaim").setExecutor(new CommandClaim());
		main.getCommand("sit").setExecutor(new CommandSit());
		main.getCommand("vanish").setExecutor(new CommandVanish());
		main.getCommand("freeze").setExecutor(new CommandFreeze());
		main.getCommand("nosto").setExecutor(new CommandNosto());
			main.getCommand("nosto").setTabCompleter(new TabNosto());
		main.getCommand("heal").setExecutor(new CommandHeal());
		main.getCommand("feed").setExecutor(new CommandFeed());
		main.getCommand("event").setExecutor(new CommandEvent());
			main.getCommand("event").setTabCompleter(new TabEvent());
		main.getCommand("menu").setExecutor(new CommandMenu());
		main.getCommand("lobby").setExecutor(new CommandLobby());
	}

}
