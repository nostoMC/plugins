package fr.nostoNC.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.nostoNC.customDrinks.Drink;
import org.jetbrains.annotations.NotNull;

public class CommandDrink implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player player)) return true;

        if (args.length == 0) return false;

        Drink drink;

        try {
            drink = Drink.valueOf(args[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            player.sendMessage("§cAucune boisson ne correspond à §6" + args[0]);
            return true;
        }

        player.getInventory().addItem(drink.getPotion());
        
        return true;
    }
}
