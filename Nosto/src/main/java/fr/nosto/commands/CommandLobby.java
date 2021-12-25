package fr.nosto.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import org.jetbrains.annotations.NotNull;

public class CommandLobby implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player player)) return true;

        if (player.getWorld().getName().equals("MainLobby")) {
            player.sendMessage("\n§6Vous êtes déjà au lobby !");
        } else {
            Location loc = new Location(Bukkit.getWorld("MainLobby"), 0.5, 103, 0.5);
            player.teleport(loc);
            player.teleport(loc);
        }
        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 100, 2);

        return true;
    }
}
