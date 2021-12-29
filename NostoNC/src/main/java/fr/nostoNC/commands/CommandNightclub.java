package fr.nostoNC.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.nostoNC.Utils;
import fr.nostoNC.menus.BottomLaserMenu;
import fr.nostoNC.menus.EffectsMenu;
import org.jetbrains.annotations.NotNull;

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
		}
		
		return false;
	}

}
