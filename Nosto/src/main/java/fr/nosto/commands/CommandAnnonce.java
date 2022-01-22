package fr.nosto.commands;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import org.jetbrains.annotations.NotNull;

public class CommandAnnonce implements CommandExecutor {

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
		
		Player player = (Player)sender;
		
		if (args.length == 0) {
			player.sendMessage("§cVeuillez ajouter un message");
		} else {
			StringBuilder argsAnnonce = new StringBuilder();
			for (String part : args) {
				argsAnnonce.append(part).append(" ");
			}
			Bukkit.broadcastMessage("");
			Bukkit.broadcastMessage("");
			Bukkit.broadcastMessage("§6§l-- Annonce de " + player.getName() + " --");
			Bukkit.broadcastMessage("");
			Bukkit.broadcastMessage("§l" + argsAnnonce);
			Bukkit.broadcastMessage("");
			Bukkit.broadcastMessage("§6§l---------------------------");
			Bukkit.broadcastMessage("");
			Bukkit.broadcastMessage("");

			for (Player otherPlayer : Bukkit.getOnlinePlayers()) {
				otherPlayer.playSound(otherPlayer.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 100, 1);
			}
		}
		
		return false;
	}

}
