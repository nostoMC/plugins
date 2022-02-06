package fr.nosto.listeners;

import fr.nosto.Main;
import fr.nosto.mysql.DbConnection;
import fr.nosto.tasks.CosmeticEffectTask;
import fr.nosto.tasks.particles.PlayerTrailsStats;
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
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.sql.*;

public class OnJoinListener implements Listener {
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onJoin(PlayerJoinEvent event){
		event.setJoinMessage("");
		Player player = event.getPlayer();

		try {
			setDefaultMoney(player);
		} catch (SQLException e) {
			player.kickPlayer("Une erreur est survenu. Veuyez contacter un administrateur.");
			e.printStackTrace();
		}

		player.performCommand("jukebox");

		if (Main.getInstance().getServer().getPluginManager().isPluginEnabled("pluginpv")) {
			Location lg = new Location(Bukkit.getWorld("lg"), 2841, 73, 3539);
			player.teleport(lg);
			player.teleport(lg);
			player.setGameMode(GameMode.ADVENTURE);
		} else {
			Location lobby = new Location(Bukkit.getWorld("MainLobby"), 0.5, 103.5, 0.5, 0f, 0f);
			player.teleport(lobby);
			player.teleport(lobby);
			player.setMaxHealth(20);
			player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 1, 200));
			player.getInventory().clear();
			if (player.hasPermission("*")) {
				player.setGameMode(GameMode.CREATIVE);
			} else {
				player.setGameMode(GameMode.ADVENTURE);
			}
			player.sendMessage("");
			player.sendTitle("§l§3≪ §l§bNosto §l§3≫", "§f§k§l|| §l§7Bienvenue " + player.getName() +  " §f§k§l||", 0, 100, 5);
			ItemStack compassLobby = new ItemStack(Material.COMPASS, 1);
			ItemMeta compassLobbyMeta = compassLobby.getItemMeta();
			assert compassLobbyMeta != null;
			compassLobbyMeta.setDisplayName("§b§lClick pour ouvrir le menu de téléportation");
			compassLobbyMeta.addEnchant(Enchantment.DAMAGE_ALL, 200, true);
			compassLobbyMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			compassLobby.setItemMeta(compassLobbyMeta);
			player.getInventory().setItem(4, compassLobby);
			player.updateInventory();
		}

		PlayerTrailsStats stats = new PlayerTrailsStats(player); // futur: chercher les stats dans un fichier yml
		CosmeticEffectTask.playerTrails.put(player.getUniqueId(), stats);
		stats.equip(stats.getEquiped()); // re-updating value

		// ADMIN MESSAGE
		Bukkit.broadcast("\n§5[LOG] §d" + player.getName() + "§5 joined the server", "nosto.logmessages.joinleave");
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
