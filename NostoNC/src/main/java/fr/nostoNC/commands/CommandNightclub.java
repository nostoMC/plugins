package fr.nostoNC.commands;

import fr.nostoNC.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.nostoNC.Utils;
import fr.nostoNC.menus.BottomLaserMenu;
import fr.nostoNC.menus.EffectsMenu;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class CommandNightclub implements CommandExecutor {

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String commandLabel, String[] args) {
		
		Player player = (Player) sender;
		
		if (!Utils.isInClub(player)) return true;
		
		if (args.length == 1) {
			if (args[0].equalsIgnoreCase("menu")) {
				EffectsMenu.openMenu(player);
			} else if (args[0].equalsIgnoreCase("bottomlaser")) {
				BottomLaserMenu.openMenu(player);
			}
		} else if (args.length == 2) {
			if (args[0].equalsIgnoreCase("dj")) {
				if (args[1].equalsIgnoreCase("join")) {
					Location loc = new Location(Main.defaultWorld, -2.0, 103.0, 146.99, 0, 0);
					if (Main.DjID == null) {
						Main.DjID = player.getUniqueId();
						for (Player players : Bukkit.getOnlinePlayers()) {
							players.sendMessage("");
							players.sendMessage("§6" + player.getName() + "§e est notre nouveau DJ !");
						}
						player.teleport(loc);
					} else if (Main.DjID == player.getUniqueId()) {
						Objects.requireNonNull(Bukkit.getPlayer(Main.DjID)).teleport(loc);
					}
				} else if (args[1].equalsIgnoreCase("leave")) {
					Location loc = new Location(Main.defaultWorld, -2.0, 101.0, 152.99, 180, 0);
					if (Main.DjID == player.getUniqueId()) {
						Main.DjID = null;
						for (Player players : Bukkit.getOnlinePlayers()) {
							players.sendMessage("");
							players.sendMessage("§6" + player.getName() + "§e quitte son poste de DJ !");
						}
						player.teleport(loc);
					}
				}
			}
		}
		
		return false;
	}

}
