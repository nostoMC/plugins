package fr.djredstone.nosto.utils.afk;

import java.awt.Color;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.djredstone.nosto.Main;
import net.dv8tion.jda.api.EmbedBuilder;

public class CommandAFK implements CommandExecutor {
	
	static ArrayList<Player> afks = new ArrayList<Player>();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Player player = (Player) sender;
		
		EmbedBuilder embed = new EmbedBuilder();
		String groupDiscord = "";
		if(player.hasPermission("group.dev")) {
			groupDiscord = "Developper ";
		}
		if(player.hasPermission("group.buildeur")) {
			groupDiscord = "Buildeur ";
		}
		if(player.hasPermission("group.administrateur")) {
			groupDiscord = "Administrateur ";
		}
		embed.setAuthor(groupDiscord + "| " + player.getName(), null, "https://mc-heads.net/avatar/" + player.getName());
		
		if(afks.contains(player)) {
			player.setCustomName(player.getName());
			player.setCustomNameVisible(true);
			Bukkit.broadcastMessage("");
			Bukkit.broadcastMessage("§7§l" + player.getName() + " §7n'est plus AFK");
			afks.remove(player);
			player.setCustomName(player.getName());
			
			embed.setColor(Color.LIGHT_GRAY);
			embed.addField("n'est plus AFK", "", false);
			Main.jda.getTextChannelById("832554910301290506").sendMessage(embed.build()).queue();
		} else {
			player.setCustomName(player.getName() + " §7§l(AFK)");
			player.setCustomNameVisible(true);
			Bukkit.broadcastMessage("");
			Bukkit.broadcastMessage("§8§l" + player.getName() + " §8est AFK");
			afks.add(player);
			String playerName = player.getName();
			player.setCustomName(playerName + " §7§l(AFK)");

			embed.setColor(Color.GRAY);
			embed.addField("est AFK", "", false);
			Main.jda.getTextChannelById("832554910301290506").sendMessage(embed.build()).queue();
		}
		return false;
	}
	
	public static ArrayList<Player> getAFKS() {
		return afks;
	}
	
}
