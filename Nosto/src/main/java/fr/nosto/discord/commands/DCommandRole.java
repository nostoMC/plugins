package fr.nosto.discord.commands;

import java.util.Objects;

import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.component.SelectMenuInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.selections.SelectMenu;

import fr.nosto.Utils;
import fr.nosto.discord.DiscordSetup;

public class DCommandRole extends ListenerAdapter {

    private static final Guild guild = Objects.requireNonNull(DiscordSetup.jda.getGuildById("782248081381916696"));

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        if (!event.getMessage().getContentRaw().toLowerCase().startsWith(DiscordSetup.prefix + "role")) return;

        User user = event.getAuthor();

        SelectMenu menuPing = SelectMenu.create("menu.ping")
                .setPlaceholder("Pings Serveur")
                .setRequiredRange(0, 6)
                .addOption("Annonce Serveur", "ping.server", "Annonces concernant le serveur (nouveaux salon, nouveaux rôles, etc...)", Emoji.fromMarkdown("⚠"))
                .addOption("Annonce Event", "ping.event", "Annonces concernants les events officiels (minecraft, among us, etc...)", Emoji.fromMarkdown("\uD83C\uDF89"))
                .addOption("Annonce Autres", "ping.other", "Annonces concernants tout le reste (fêtes, prévenance, etc...)", Emoji.fromMarkdown("\uD83D\uDE2E"))
                .addOption("DjRedstone", "people.djredstone", "Un peu de tout mais sûrtout du montage dans tous les sens !", Emoji.fromMarkdown("⚙"))
                .addOption("Overlord", "people.overlord", "D O O R", Emoji.fromMarkdown("⌨"))
                .addOption("Bidoule", "people.bidoule", "Du CS:GO peut être un jour ? \uD83D\uDC40", Emoji.fromMarkdown("\uD83D\uDCB6"))
                .build();

        SelectMenu menuJeux = SelectMenu.create("menu.game")
                .setPlaceholder("Pings Jeux")
                .setRequiredRange(0, 17)
                .addOption("Minecraft", "game.minecraft", "Des block, de l'aventure, du build, bref le meilleur jeu de tout les temps !", Emoji.fromMarkdown("⛏"))
                .addOption("Rocket League", "game.rl", "Des voitures, une balle et deux buts, vous avez compris le principe ?", Emoji.fromMarkdown("⚽"))
                .addOption("Elite dangerous", "game.elite", "Parcourez la galaxie et prêtez allégeance à l'Empire, à la Fédération ou à l'Alliance !", Emoji.fromMarkdown("\uD83D\uDEF0"))
                .addOption("CS:GO", "game.csgo", "Terroriste ou Antiterroriste ? Placer la bombe ou désamorcer là ?", Emoji.fromMarkdown("\uD83D\uDC6E"))
                .addOption("Overwatch", "game.overwatch", "Rien de plus simple : Battez vous pour remporter la victoire !", Emoji.fromMarkdown("\uD83C\uDF87"))
                .addOption("Farming Simulator", "game.fs", "Développez votre ferme, achetez de meilleurs véhicule et agrandissez votre territoire !", Emoji.fromMarkdown("\uD83D\uDE9C"))
                .addOption("G Mod", "game.gmod", "Trop de chose à dire sur ce jeux enfin ces jeux donc amusez vous !", Emoji.fromMarkdown("\uD83C\uDFE2"))
                .addOption("GTA", "game.gta", "Los Santos ? Liberty City ? Quelle ville et personnages préférerez vous ?", Emoji.fromMarkdown("\uD83D\uDE94"))
                .addOption("Les Sims", "game.sims", "Vous trouvez votre vie ennuyeuse ? Créer votre vie parfaite ici !", Emoji.fromMarkdown("\uD83E\uDD30"))
                .addOption("Among us", "game.amongus", "Crewmate, réparez le vaisseau spatial pour gagner et trouver les imposteurs !", Emoji.fromMarkdown("\uD83D\uDC68\u200D\uD83D\uDE80"))
                .addOption("Watch Dogs", "game.watchdogs", "Le hacker de l’extrême c'est vous.", Emoji.fromMarkdown("\uD83D\uDD74"))
                .addOption("Fall Guys", "game.fallguys", "Top 1 ou top 2 à vous de voir !", Emoji.fromMarkdown("\uD83E\uDD34"))
                .addOption("Roblox", "game.roblox", "Venez découvrir tout les mini jeux disponible, attention il y en a beaucoup !", Emoji.fromMarkdown("♦"))
                .addOption("Portal", "game.portal", "Bienvenue à aperture ! L'entreprise géré par une IA démoniaque !", Emoji.fromMarkdown("\uD83E\uDD16"))
                .addOption("Call Of Duty", "game.cod", "Des armes, des explosions, tous ça vous attend dans Call of Duty !", Emoji.fromMarkdown("\uD83D\uDD2B"))
                .addOption("Apex Legend", "game.apex", "Du battle royal pur et dur en trio !", Emoji.fromMarkdown("3️⃣"))
                .addOption("Valorant", "game.valorent", "Des armes et des pouvoirs ! Le tout pour la victoire !", Emoji.fromMarkdown("\uD83E\uDE84"))
                .build();

        user.openPrivateChannel().queue((channel) -> {
                channel.sendMessage("\uD83D\uDD3D Choisis les rôles pings qui t'intéresse \uD83D\uDD3D")
                    .setActionRow(menuPing).queue();
                channel.sendMessage("\uD83D\uDD3D Choisis les rôles jeux qui t'intéresse \uD83D\uDD3D")
                        .setActionRow(menuJeux).queue();
        });

        Utils.DReply(event, "Un message vous a été envoyé en privé " + user.getAsMention() + " ! \uD83D\uDCE8");

    }

    @Override
    public void onSelectMenuInteraction(SelectMenuInteractionEvent event) {
        switch (event.getComponentId()) {
            case "menu.ping" -> {
                setRole("807275967155404800", "ping.server", event);
                setRole("807276127646253087", "ping.event", event);
                setRole("807276053680750632", "ping.other", event);
                setRole("821817572571217960", "people.djredstone", event);
                setRole("821835430818349128", "people.overlord", event);
                setRole("821835483524104272", "people.bidoule", event);
            }
            case "menu.game" -> {
                setRole("782408256005865502", "game.minecraft", event);
                setRole("782568059885191178", "game.rl", event);
                setRole("782408454593445910", "game.elite", event);
                setRole("782407719247151145", "game.amongus", event);
                setRole("782595185712300062", "game.roblox", event);
                setRole("782613923609837589", "game.csgo", event);
            }
        }
        event.reply("Les rôles ont été mis à jour ✅")
                .setEphemeral(true).queue();
    }

    private static void setRole(String roleID, String roleValue, SelectMenuInteractionEvent event) {
        String userID = event.getUser().getId();
        if (event.getValues().contains(roleValue)) guild.addRoleToMember(userID, Objects.requireNonNull(guild.getRoleById(roleID))).queue();
        else guild.removeRoleFromMember(userID, Objects.requireNonNull(guild.getRoleById(roleID))).queue();
    }

}
