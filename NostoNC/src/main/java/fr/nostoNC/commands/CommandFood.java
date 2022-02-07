package fr.nostoNC.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.nostoNC.customConsumables.Consumable;
import fr.nostoNC.customConsumables.Products;
import org.jetbrains.annotations.NotNull;

public class CommandFood implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player player)) return true;

        if (args.length == 0) return false;

        Consumable consumable = Products.products.get(args[0]);

        if (consumable == null) {
            player.sendMessage("\n§cAucun consommable ne correspond à §6" + args[0]);
            return true;
        }

        player.getInventory().addItem(consumable.getItem());
        
        return true;
    }
}
