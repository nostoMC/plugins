package fr.nosto.tasks;

import java.util.ArrayList;
import java.util.Random;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.scheduler.BukkitRunnable;

import fr.nosto.Main;
import fr.nosto.Utils;

public class RandomBroadcastTask {

	static Random random = new Random();

	public static void init(Main main) {

		ArrayList<TextComponent> messagesList = new ArrayList<>();

		messagesList.add(message1());
		messagesList.add(message2());

		new BukkitRunnable() {
			@Override
			public void run() {
				TextComponent textComponent = messagesList.get(random.nextInt(messagesList.size()));
				Utils.sendTextComponentToSurvival(textComponent);
			}
		}.runTaskTimer(main, 0, 3000);
	}

	private static TextComponent message1() {
		TextComponent finalTextComponent = new TextComponent("\n");
		finalTextComponent.addExtra("§e§l" + "Si vous rencontrez des bugs, veuillez nous les signaler sur le discord : ");
		TextComponent link = new TextComponent("§6§l§n" + "https://discord.io/Nosto");
		link.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_FILE, "https://discord.io/Nosto"));
		finalTextComponent.addExtra(link);
		return finalTextComponent;
	}

	private static TextComponent message2() {
		TextComponent finalTextComponent = new TextComponent("\n");
		finalTextComponent.addExtra("§c§l" + "Les machines à lag sont ");
		finalTextComponent.addExtra("§4§l" + "strictement interdites ");
		finalTextComponent.addExtra("§c§l" + "en tous mondes.");
		return finalTextComponent;
	}

}
