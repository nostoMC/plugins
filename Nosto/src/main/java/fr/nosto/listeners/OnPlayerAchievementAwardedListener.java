package fr.nosto.listeners;

import java.awt.*;
import java.util.Objects;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;

import net.dv8tion.jda.api.EmbedBuilder;

import fr.nosto.discord.DiscordSetup;
import fr.nosto.Utils;

import io.papermc.paper.advancement.AdvancementDisplay;

public class OnPlayerAchievementAwardedListener implements Listener {

    @EventHandler
    public void onPlayerAdvancementDoneEvent(PlayerAdvancementDoneEvent event) {

        Player player = event.getPlayer();
        AdvancementDisplay display = event.getAdvancement().getDisplay();
        assert display != null;

        TextColor color = TextColor.color(107, 145, 255);
        TextComponent component = Component.text("\n§3" + player.getName() + "§b a obtenu un succès : ")
                .append(display.title().hoverEvent(display.description().color(color)).color(color));

        if (Utils.getSurviesNames().contains(player.getWorld().getName())) {
            EmbedBuilder embed = new EmbedBuilder();
            embed.setAuthor(Utils.getGroupDiscord(player) + player.getName(), null, "https://mc-heads.net/avatar/" + player.getName());
            embed.setColor(Color.YELLOW);
            embed.setDescription("a obtenu un nouveau succès : " + event.getAdvancement().getKey().getKey());
            DiscordSetup.getChannelSurvie().sendMessageEmbeds(embed.build()).queue();
            for (String world : Utils.getSurviesNames())
                Objects.requireNonNull(Bukkit.getWorld(world)).sendMessage(component);
        }


    }

}
