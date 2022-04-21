package fr.nosto.discord.commands;

import java.awt.*;

import org.bukkit.Bukkit;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import fr.nosto.Utils;
import fr.nosto.discord.DiscordSetup;

public class DCommandMinecraft extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        if (!event.getMessage().getContentRaw().toLowerCase().startsWith(DiscordSetup.prefix + "minecraft")) return;

        String tps = Math.round(Bukkit.getTPS()[0]) +
                " - " +
                Math.round(Bukkit.getTPS()[1]) +
                " - " +
                Math.round(Bukkit.getTPS()[2]);

        final EmbedBuilder embed = new EmbedBuilder()
                .setTitle("Serveur Minecraft")
                .setThumbnail("https://cdn.discordapp.com/attachments/782250117792268329/966420064377122826/favicon.png")
                .setColor(Color.CYAN)
                .addField("Etat actuel", Bukkit.hasWhitelist() ? "En maintenance \uD83C\uDFD7" : "Actif ✅", true)
                .addField("IP", "`nosto.mine.gg`", true)
                .addField("Nombre de joueurs", Bukkit.getOnlinePlayers().size() + (Bukkit.getOnlinePlayers().size() == 0 ? " *:(*" : ""), true)
                .addField("TPS", tps, true);

        Utils.DReply(event, embed);

    }

}
