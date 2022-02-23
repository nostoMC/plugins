package fr.nostoS.tasks;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.scheduler.BukkitRunnable;

import fr.nostoS.Main;
import fr.nostoS.Utils;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class RandomBroadcastTask {

    static Random random = new Random();

    private static final ArrayList<TextComponent> messagesList = new ArrayList<>();
    static {
        TextComponent message1 = new TextComponent("\n");
        message1.addExtra("§e§l" + "Si vous rencontrez des bugs, veuillez nous les signaler sur le discord : ");
        TextComponent link = new TextComponent("§6§l§n" + "https://discord.io/Nosto");
        link.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_FILE, "https://discord.io/Nosto"));
        message1.addExtra(link);

        TextComponent message2 = new TextComponent("\n");
        message2.addExtra("§c§l" + "Les machines à lag sont ");
        message2.addExtra("§4§l" + "strictement interdites ");
        message2.addExtra("§c§l" + "en tous mondes.");

        messagesList.add(message1);
        messagesList.add(message2);
    }

    public static void init(Main main) {
        new BukkitRunnable() {
            @Override
            public void run() {
                TextComponent textComponent = messagesList.get(random.nextInt(messagesList.size()));
                Utils.sendTextComponentToSurvival(textComponent);
            }
        }.runTaskTimer(main, 0, 3600);
    }

}
