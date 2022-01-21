package fr.nosto.commands;

import fr.nosto.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Set;

public class CommandMe implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {

        Set<String> survival_worlds = Utils.getSurviesNames();

        if (sender instanceof Player player) {

            String message = String.join(" ", args);

            if(survival_worlds.contains(player.getWorld().getName())) {

                Utils.sendMessageToSurvival("");
                Utils.sendMessageToSurvival("§7§o" + player.getName() + " " + message);

            } else {

                Utils.sendMessageToWorld(player.getWorld(),"");
                Utils.sendMessageToWorld(player.getWorld(),"§7§o" + player.getName() + " " + message);

            }

        }

        return false;
    }

}
