package fr.nostoNC.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.nostoNC.DjManager;
import fr.nostoNC.Utils;
import fr.nostoNC.menus.EffectsMenu;
import org.jetbrains.annotations.NotNull;

public class CommandNightclub implements CommandExecutor {

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String commandLabel, String[] args) {
		
		Player player = (Player) sender;
		
		if (!Utils.isInClub(player)) return true;
		if (args.length != 1) return false;
		
		if (args[0].equalsIgnoreCase("menu")) EffectsMenu.openMenu(player);

		else if (args[0].equalsIgnoreCase("joindj")) {
			DjManager.tryJoinDj(player);
		}
		else if (args[0].equalsIgnoreCase("leavedj")) {
			if (DjManager.DjID == player.getUniqueId()) {
				DjManager.leaveDj();
			} else {
				player.sendMessage("§cVous n'êtes pas DJ !");
			}
		}
		
		return false;
	}

}
