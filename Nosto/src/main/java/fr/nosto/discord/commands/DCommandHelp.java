package fr.nosto.discord.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import fr.nosto.Utils;
import fr.nosto.discord.DiscordSetup;

public class DCommandHelp extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        if (!event.getMessage().getContentRaw().toLowerCase().startsWith(DiscordSetup.prefix + "help") &&
                !event.getMessage().getContentRaw().toLowerCase().startsWith(DiscordSetup.prefix + "aide"))
            return;

        final EmbedBuilder embed = new EmbedBuilder()
                .setTitle("\uD83D\uDCD3 Liste des commandes \uD83D\uDCD3")
                .addField(DiscordSetup.prefix + "minecraft", "Affiche les informations en rapport au serveur minecraft", false)
                .addField(DiscordSetup.prefix + "aide OU " + DiscordSetup.prefix + "help", "Affiche les différentes commandes disponibles", false);

        Utils.DReply(event, embed);

    }

}
