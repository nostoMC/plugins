package fr.nosto.listeners;

import fr.nosto.commands.CommandEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class OnServerListPingListener implements Listener {
	
	@EventHandler
    public void onServerListPing(ServerListPingEvent event) {
		if (CommandEvent.getEvent() == null) {
			event.setMotd(createMOTDSeparator("                       §f§l§k|| §b§lNOSTO §f§l§k||;             §f§nhttps://discord.io/Nosto"));
		} else {
			event.setMotd(createMOTDSeparator("                       §f§l§k|| §b§lNOSTO §f§l§k|| §6§lEVENT EN COURS;             §f§nhttps://discord.io/Nosto"));
		}
	}

	/**
	 * Method - Separate line with ',' for MOTD
	 * @param motd - MOTD with ',' as '\n'
	 * @return String - The final MOTD
	 **/
	private static String createMOTDSeparator(String motd) {
		motd = motd.replaceAll(";", System.getProperty("line.separator"));
		return motd;
	}

}
