package fr.nosto.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import org.jetbrains.annotations.NotNull;

public class CommandEvent implements CommandExecutor {

	private static Event event;

	public enum Event {
		NOSTOCLUB, MAINLOBBY
	}

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {

		final String helpMsg = """
															
				§cUtilisation :
				  /event add <nom de l'event>
				  /event stop
				""";

		if (sender instanceof Player player) {
			if (player.hasPermission("nosto.admin.event")) { // PARTIE ADMIN
				if (args.length == 0) {
					tpEvent(player);
					return true;
				}
				if (args[0].equalsIgnoreCase("add")) {
					if (event != null) player.sendMessage("\n§cUn event est déjà en cours !");
					else if (args.length != 2) sender.sendMessage(helpMsg);
					else {
						switch (args[1].toLowerCase()) {

							case "nostoclub" -> event = Event.NOSTOCLUB;
							case "mainlobby" -> event = Event.MAINLOBBY;

							default -> {
								player.sendMessage("\n§cAucun event n'a ce nom");
								return true;
							}
						}

						Bukkit.broadcastMessage("\n\n§b§lUn event vient de commencer ! Fais /event pour nous rejoindre !\n\n");
					}
				} else if (args[0].equalsIgnoreCase("stop")) {
					if (event == null) player.sendMessage("\n§cAucun event en cours !");
					else {
						event = null;
						player.sendMessage("\n§bL'event vient de se finir");
					}
				} else {
					sender.sendMessage(helpMsg);
				}
			} else { // PARTIE JOUEUR
				tpEvent(player);
			}
		}
		return true;
	}

	private static void tpEvent(Player player) {
		if (event == null) {
			player.sendMessage("\n§cAucun event en cours !");
		} else {
			switch (event) {

				case NOSTOCLUB -> {
					if (player.getWorld().getName().equalsIgnoreCase("nostoclub")) player.teleport(new Location(player.getWorld(), -2.0, 101, 168.5, 180, 0));
					else {
						Location location = new Location(Bukkit.getWorld("MainLobby"), 0.5, 99, -5.5, 180, 0);
						player.teleport(location);
						player.teleport(location);
					}
				}

				case MAINLOBBY -> {
					Location location = new Location(Bukkit.getWorld("MainLobby"), 0.5, 103, 0.5, 0, 0);
					player.teleport(location);
					player.teleport(location);
				}
			}
		}
	}

	public static Event getEvent() {
		return event;
	}

}
