package fr.nosto.listeners;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

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

import fr.nosto.Main;
import fr.nosto.mysql.DbConnection;
import fr.nosto.tasks.CosmeticEffectTask;
import fr.nosto.tasks.particles.PlayerTrailsStats;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;

public class OnJoinListener implements Listener {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		event.setJoinMessage("");
		Player player = event.getPlayer();

		try {
			setDefaultMoney(player);
		} catch (SQLException e) {
			player.kickPlayer("Une erreur est survenue. Si le problème persiste, essayez de contactez un administrateur sur notre discord: https://discord.io/Nosto");
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

	public void setDefaultMoney(Player player) throws SQLException {

		final DbConnection dbConnection = Main.databaseManager.getDbConnection();

		final Connection connection = dbConnection.getConnection();

		final PreparedStatement preparedStatement = connection.prepareStatement("SELECT uuid, money FROM player_money WHERE uuid = ?");
		preparedStatement.setString(1, player.getUniqueId().toString());
		final ResultSet resultSet = preparedStatement.executeQuery();

		if (!resultSet.next()) {
			final PreparedStatement preparedStatement1 = connection.prepareStatement("INSERT INTO player_money VALUES(?, ?, ?, ?)");
			final Timestamp timestamp = new Timestamp(System.currentTimeMillis());

			preparedStatement1.setString(1, player.getUniqueId().toString());
			preparedStatement1.setFloat(2, 0);
			preparedStatement1.setTimestamp(3, timestamp);
			preparedStatement1.setTimestamp(4, timestamp);

			preparedStatement1.executeUpdate();
		}
	}

}
