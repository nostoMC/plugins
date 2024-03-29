package fr.nosto.listeners;

import fr.nosto.Main;
import fr.nosto.Setup;
import fr.nosto.commands.CommandEvent;
import fr.nosto.mysql.prepareStatement.money;
import fr.nosto.tasks.CosmeticEffectTask;
import fr.nosto.tasks.particles.PlayerTrailsStats;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.sql.SQLException;

public class OnJoinListener implements Listener {

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		event.setJoinMessage("");
		Player player = event.getPlayer();

		try {
			money.setDefaultMoney(player);
		} catch (SQLException e) {
			player.kickPlayer("§cUne erreur est survenue, essayez de vous reconnecter.\nSi le problème persiste, essayez de contactez un administrateur sur notre discord: https://discord.io/Nosto");
			Setup.connexionMySQL(Main.getInstance());
			e.printStackTrace();
		}

		// Trails
		PlayerTrailsStats stats = new PlayerTrailsStats(player); // futur: chercher les stats dans un fichier yml
		CosmeticEffectTask.playerTrails.put(player.getUniqueId(), stats);
		stats.equip(stats.getEquiped()); // re-updating value

		// Message admin
		Bukkit.broadcast("\n§5[LOG] §d" + player.getName() + "§5 joined the server", "nosto.logmessages.joinleave");

		// Génération du lien McJukebox
		player.performCommand("jukebox");

		// Séquence de join normal si le joueur n'est pas admin
		if (player.hasPermission("nosto.admin.joinpersistence")) {

			Component hoverText = Component.text("§6Rejoindre comme un user normal");

			Component component = Component.text("§6Click ici pour rejoindre le serveur comme un user")
					.clickEvent(ClickEvent.runCommand("/nosto normaljoin"))
					.hoverEvent(HoverEvent.showText(hoverText));

			player.sendMessage(component);

		} else {
			playerJoin(player);
		}

	}

	@SuppressWarnings("deprecation")
	public static void playerJoin(Player player) {
		Location lobby = new Location(Bukkit.getWorld("MainLobby"), 0.5, 103.0, 0.5, 0f, 0f);
		player.teleport(lobby);
		player.teleport(lobby);

		//noinspection deprecation
		player.setMaxHealth(20);
		player.setHealth(20);
		player.setFoodLevel(20);

		player.setGameMode(GameMode.ADVENTURE);

		player.sendTitle("§l§3« §l§bNosto §l§3»",
				"§f§k§l|| §l§7Bienvenue " + player.getName() +  " §f§k§l||",
				0, 100, 5);

		if (CommandEvent.getEvent() != null) {
			player.sendMessage("\n\n§b§lUn event est en cours ! Fait /event pour nous rejoindre !\n\n");
		}

		ItemStack compassLobby = new ItemStack(Material.COMPASS);
		ItemMeta compassLobbyMeta = compassLobby.getItemMeta();
		assert compassLobbyMeta != null;

		compassLobbyMeta.setDisplayName("§b§lClick pour ouvrir le menu de téléportation");
		compassLobbyMeta.addEnchant(Enchantment.LUCK, 1, true);
		compassLobbyMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

		compassLobby.setItemMeta(compassLobbyMeta);

		player.getInventory().clear();
		player.getInventory().setItem(4, compassLobby);
		player.updateInventory();
	}



}
