package fr.nosto.commands;

import fr.nosto.menus.SanctionMenu;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;

public class CommandSanction implements CommandExecutor {

    @SuppressWarnings("deprecation")
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {

        if (sender instanceof Player player) {
            if (args.length == 0) {
                player.sendMessage("\n§cVeuillez entrer une cible (/sanction <joueur>");
            } else {
                try {
                    SanctionMenu.openMenu(player, Bukkit.getOfflinePlayer(args[0]));
                } catch (NullPointerException | SQLException e) {
                    player.sendMessage("\n§cVeuillez entrer une cible valide (/sanction <joueur>");
                }
            }
        }

        return true;
    }

}
