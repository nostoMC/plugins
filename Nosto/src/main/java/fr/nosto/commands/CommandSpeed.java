package fr.nosto.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import org.jetbrains.annotations.NotNull;

public class CommandSpeed implements CommandExecutor {

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
		
		if (!(sender instanceof Player)) {
            sender.sendMessage("Player only command!");
            return true;
        }

        Player p = (Player) sender;

        if (!p.hasPermission("nosto.speed")) {
            p.sendMessage("§cVous n'avez pas les permissions !");
            return true;
        }

        String invalidArgumentMessage = "\n§cVeuillez choisir une vitesse entre 1 et 10, ou \"default\"";

        if (args.length == 0) {
            p.sendMessage(invalidArgumentMessage);
            return true;
        }

        int speed;

        if (args[0].equals("default")) {
            if (p.isFlying()) speed = 1;
            else speed = 2;

        } else {
            try {
                speed = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                p.sendMessage(invalidArgumentMessage);
                return true;
            }
            if (speed < 1 || speed > 10) {
                p.sendMessage(invalidArgumentMessage);
                return true;
            }
        }

        if (p.isFlying()) {
            p.setFlySpeed(speed / 10f);
            p.sendMessage("\n§eVotre vitesse de vol est maintenant §6§l" + speed);
        } else {
            p.setWalkSpeed(speed / 10f);
            p.sendMessage("\n§eVotre vitesse de marche est maintenant §6§l" + speed);
        }
        return true;
	}

}
