package fr.nosto.discord.commands;

import java.util.Objects;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.AudioChannel;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.AudioManager;

import fr.nosto.Utils;
import fr.nosto.discord.music.GuildMusicManager;
import fr.nosto.discord.music.PlayerManager;

public class TempDCommandShow extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        String message = event.getMessage().getContentRaw();

        if (!message.toLowerCase().startsWith(";event") || !Objects.requireNonNull(event.getMember()).hasPermission(Permission.ADMINISTRATOR)) return;

        if (message.toLowerCase().contains("stop")) {
            final GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(event.getGuild());
            musicManager.scheduler.player.stopTrack();
            musicManager.scheduler.queue.clear();
            event.getGuild().getAudioManager().closeAudioConnection();
            Utils.DReply(event, "Musique stopée");
            return;
        }

        final GuildVoiceState memberVoiceState = Objects.requireNonNull(event.getMember()).getVoiceState();

        assert memberVoiceState != null;
        if (!memberVoiceState.inAudioChannel()) {
            Utils.DReply(event, "Tu dois être dans un channel vocal pour utiliser cette commande");
            return;
        }

        final AudioManager audioManager = event.getGuild().getAudioManager();
        final AudioChannel memberChannel = memberVoiceState.getChannel();

        audioManager.openAudioConnection(memberChannel);
        PlayerManager.getInstance().loadAndPlay(event.getTextChannel(), message.split("\\s+")[1]);
        Utils.DReply(event, "Musique ajoutée");

    }

}
