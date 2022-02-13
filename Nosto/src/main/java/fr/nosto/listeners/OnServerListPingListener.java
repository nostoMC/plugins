package fr.nosto.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;
import net.kyori.adventure.text.Component;

import fr.nosto.commands.CommandEvent;

public class OnServerListPingListener implements Listener {
	
	@EventHandler
    public void onServerListPing(ServerListPingEvent event) {
		if (CommandEvent.getEvent() == null) {
			event.motd(createMOTD("                       §f§l§k|| §b§lNOSTO §f§l§k||", "             §f§nhttps://discord.io/Nosto"));
		} else {
			event.motd(createMOTD("                       §f§l§k|| §b§lNOSTO §f§l§k|| §6§lEVENT EN COURS", "             §f§nhttps://discord.io/Nosto"));
		}
	}

	/**
	 * Creates a MOTD from 2 lines
	 *
	 * @param line1 the first line of the MOTD
	 * @param line2 the second line of the MOTD
	 * @return a {@link Component}, the final MOTD
	 **/
	@SuppressWarnings("SameParameterValue")
	private static Component createMOTD(String line1, String line2) {
		return Component.text(line1 + System.getProperty("line.separator") + line2);
	}

}
