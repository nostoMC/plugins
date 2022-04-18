package fr.nosto.commands;

import java.awt.*;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import net.dv8tion.jda.api.EmbedBuilder;

import fr.nosto.DiscordSetup;

import org.jetbrains.annotations.NotNull;

public class CommandWhitelist implements CommandExecutor {

    final String helpMsg = """
    
				§cUtilisation :
				  /whitelist off/on/reload/add/remove/list
				""";

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {

        if (args.length == 0) sender.sendMessage(helpMsg);
        else {

            switch (args[0]) {

                default -> sender.sendMessage(helpMsg);

                case "on" -> {
                    Bukkit.setWhitelist(true);
                    sender.sendMessage("\n§bLa whitelist est activée !");
                    EmbedBuilder builder = new EmbedBuilder()
                            .setTitle("Serveur fermé !")
                            .setDescription("Les techniciens doivent faire une maintenance !")
                            .setThumbnail("https://cdn.discordapp.com/attachments/555402600640413713/965697347139014757/maintenance_on.gif")
                            .setColor(Color.RED);
                    DiscordSetup.getChannelEtatServeur().sendMessageEmbeds(builder.build()).queue();
                }

                case "off" -> {
                    Bukkit.setWhitelist(false);
                    sender.sendMessage("\n§bLa whitelist est déactivée !");
                    EmbedBuilder builder = new EmbedBuilder()
                            .setTitle("Serveur ouvert !")
                            .setDescription("Les portails sont de nouveaux opérationnels !")
                            .setThumbnail("https://cdn.discordapp.com/attachments/555402600640413713/965696637072732190/maintenance_off.gif")
                            .setColor(Color.GREEN);
                    DiscordSetup.getChannelEtatServeur().sendMessageEmbeds(builder.build()).queue();
                }

                case "reload" -> {
                    Bukkit.reloadWhitelist();
                    sender.sendMessage("\n§bLa whitelist a été reload !");
                }

                case "add" -> {
                    if (args.length == 1) sender.sendMessage("\n§cVeuillez choisir un joueur");
                    else {
                        OfflinePlayer target = Bukkit.getPlayer(args[1]);
                        Bukkit.getWhitelistedPlayers().add(target);
                        sender.sendMessage("\n§3" + args[1] + "§b a été ajouté à la whitelist !");
                    }
                }

                case "remove" -> {
                    if (args.length == 1) sender.sendMessage("\n§cVeuillez choisir un joueur");
                    else {
                        OfflinePlayer target = Bukkit.getPlayer(args[1]);
                        Bukkit.getWhitelistedPlayers().remove(target);
                        sender.sendMessage("\n§3" + args[1] + "§b a été retiré de la whitelist !");
                    }
                }

                case "list" -> {
                    StringBuilder sb = new StringBuilder("\n§bIl y a §3" + Bukkit.getWhitelistedPlayers().size() + " joueurs§b dans la whitelist :§3");
                    for (OfflinePlayer player : Bukkit.getWhitelistedPlayers()) sb.append(" ").append(player.getName());
                    sender.sendMessage(sb.toString());
                }

            }

        }

        return true;
    }

}
