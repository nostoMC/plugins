package fr.nosto.listeners;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import fr.nosto.Main;

public class OnServerListPingListener implements Listener {
	
	@EventHandler
    public void onServerListPing(ServerListPingEvent event) {
		String motd = "                       §f§l§k|| §b§lNOSTO §f§l§k||;             §f§nhttps://discord.io/Nosto";
		motd = motd.replaceAll(";", System.getProperty("line.separator"));
		FileConfiguration cf = Main.getInstance().getConfig();
		if(cf.get("event") == null) {
			event.setMotd(motd);
		} else {
			if(cf.get("event").toString().equalsIgnoreCase("show")) {
				motd = "                       §f§l§k|| §b§lNOSTO §f§l§k|| §6§lEVENT EN COURS;             §f§nhttps://discord.io/Nosto";
				motd = motd.replaceAll(";", System.getProperty("line.separator"));
				event.setMotd(motd);
			}
		}
	}

}