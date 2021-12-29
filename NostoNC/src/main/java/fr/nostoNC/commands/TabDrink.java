package fr.nostoNC.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import fr.nostoNC.customDrinks.Drink;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TabDrink implements TabCompleter {

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player player)) return null;

        if (!player.hasPermission("nosto.nightclub.getdrink")) return new ArrayList<>();

        if (args.length != 1) return new ArrayList<>();

        List<String> drinknames = new ArrayList<>();
        for (Drink drink : Drink.values()) {
            drinknames.add(drink.name().toLowerCase());
        }

        return drinknames;
    }
}
