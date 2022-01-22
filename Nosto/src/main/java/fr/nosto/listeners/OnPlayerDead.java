package fr.nosto.listeners;

import fr.nosto.DiscordSetup;
import fr.nosto.Utils;
import net.dv8tion.jda.api.EmbedBuilder;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.awt.*;
import java.util.Set;

public class OnPlayerDead implements Listener {

    @EventHandler
    public void OnPlayerDeath(PlayerDeathEvent event) {

        Set<String> survival_worlds = Utils.getSurviesNames();
        Player player = event.getEntity();

        // discord message
        String groupDiscord = Utils.getGroupDiscord(player);

        EmbedBuilder embed = new EmbedBuilder();
        embed.setAuthor(groupDiscord + player.getName(), null, "https://mc-heads.net/avatar/" + player.getName());
        embed.setColor(Color.ORANGE);
        embed.setDescription(event.getDeathMessage());

        if (survival_worlds.contains(player.getWorld().getName())) {
            DiscordSetup.getChannelSurvie().sendMessageEmbeds(embed.build()).queue();
        }

        // minecraft message
        String format = "§6§l" + event.getDeathMessage();

        if (survival_worlds.contains(player.getWorld().getName())) Utils.sendMessageToSurvival("\n" + format);
        else Utils.sendMessageToWorld(player.getWorld(), "\n" + format);

    }

}
