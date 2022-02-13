package fr.nosto.commands;

import fr.nosto.Main;
import fr.nosto.Setup;
import fr.nosto.listeners.OnJoinListener;
import fr.nosto.menus.MainMenu;
import fr.nosto.menus.SanctionMenu;
import fr.nosto.menus.mainmenu.ShopMenu;
import fr.nosto.menus.mainmenu.TpMenu;
import fr.nosto.menus.mainmenu.TrailsMenu;
import fr.nosto.menus.mainmenu.tpmenu.MinijeuxMenu;
import fr.nosto.menus.mainmenu.tpmenu.MondeOuvertMenu;
import fr.nosto.menus.mainmenu.tpmenu.TrainingMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;

public class CommandNosto implements CommandExecutor {

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {

		String helpMessage = "\n§cUtilisation : /nosto < reloadconfig | normaljoin > | /nosto openmenu < nom >";

		if (args.length == 0) {
			sender.sendMessage(helpMessage);
		} else {
			switch (args[0].toLowerCase()) {
				
				case "reloadconfig" -> {
					Main.getInstance().reloadConfig();
					Setup.connexionMySQL(Main.getInstance());
					sender.sendMessage("\n§aConfig et MySQL reload !");
				}

				case "normaljoin" -> {
					if (sender instanceof Player player) {
						OnJoinListener.playerJoin(player);
					} else {
						sender.sendMessage("\n§cSeuls les joueurs peuvent faire cela !");
					}
				}

				case "openmenu" -> {
					if (sender instanceof Player player) {
						if (!(args.length >= 2)) {
							player.sendMessage(helpMessage);
							return false;
						}
						switch (args[1].toLowerCase()) {
							case "sanction" -> {
								try {
									SanctionMenu.openMenu(player, player);
								} catch (SQLException e) {
									e.printStackTrace();
								}
							}
							case "main" -> MainMenu.openMenu(player, true);
								case "tp" -> TpMenu.openMenu(player, true);
									case "minijeux" -> MinijeuxMenu.openMenu(player, true);
									case "mondeouvert" -> MondeOuvertMenu.openMenu(player, true);
									case "training" -> TrainingMenu.openMenu(player, true);
								case "trails" -> TrailsMenu.openMenu(player, true);
								case "shop" -> ShopMenu.openMenu(player, true);

							default -> player.sendMessage("\n§cAucun menu à ce nom");
						}
					} else {
						sender.sendMessage("\n§cSeuls les joueurs peuvent faire cela !");
					}
				}

				default -> sender.sendMessage(helpMessage);
			}
        }
		return true;
	}

}
