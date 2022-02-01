package fr.nosto.commands;

import fr.nosto.Main;
import fr.nosto.Utils;
import fr.nosto.mysql.DbConnection;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.UUID;

public class CommandHome implements CommandExecutor {

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
		if (!(sender instanceof Player player)) return true;
		
		if (Utils.getSurviesNames().contains(player.getWorld().getName())) {

			final DbConnection dbConnection = Main.databaseManager.getDbConnection();

			try {
				final Connection connection = dbConnection.getConnection();

				if (cmd.getName().equalsIgnoreCase("sethome")) {

					if (args.length == 0) {
						player.sendMessage("");
						player.sendMessage("§cUtilisation : /sethome <nom>");
						return false;
					}

					if (args.length == 1) {
						setHome(connection, player.getUniqueId(), args[0], player.getWorld().getName(), (float) player.getLocation().getX(), (float) player.getLocation().getY(), (float) player.getLocation().getZ(), player.getEyeLocation().getPitch(), player.getEyeLocation().getYaw());
						player.sendMessage("");
						player.sendMessage("§eLe home §6§l" + args[0] + " §eest maintenant mis en place en §a§l" + player.getLocation().getBlockX() + " §a§l" + player.getLocation().getBlockY() + " §a§l" + player.getLocation().getBlockZ() + " §e!");
						return false;
					}

					player.sendMessage("");
					player.sendMessage("§cUtilisation : /sethome <nom>");
					return false;

				} else if (cmd.getName().equalsIgnoreCase("delhome")) {

					if (args.length == 0) {
						player.sendMessage("");
						player.sendMessage("§cUtilisation : /delhome <nom>");
						return false;
					}

					if (args.length == 1) {
						final PreparedStatement preparedStatement = connection.prepareStatement("SELECT uuid, name FROM survival_home WHERE (uuid = ? AND name = ?)");
						preparedStatement.setString(1, player.getUniqueId().toString());
						preparedStatement.setString(2, args[0]);
						final ResultSet resultSet = preparedStatement.executeQuery();

						if (resultSet.next()) {
							deleteHome(connection, player.getUniqueId(), args[0]);
							player.sendMessage("");
							player.sendMessage("§eHome supprimé !");
							return false;
						} else {
							player.sendMessage("");
							player.sendMessage("§cVous n'avez aucun home à ce nom");
						}
					}

					player.sendMessage("");
					player.sendMessage("§cUtilisation : /delhome <nom>");
					return false;
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
			/*
			if (cmd.getName().equalsIgnoreCase("home")) {

				if (args.length == 0) {
					p.sendMessage("");
					p.sendMessage("§cUtilisation : /home <nom>");
					return false;
				}

				if (args.length == 1) {
					if (Main.getInstance().getConfig().contains("home." + p.getUniqueId() + "." + args[0])) {
						World w = Bukkit.getServer().getWorld(Main.getInstance().getConfig().getString("home." + p.getUniqueId() + "." + args[0] + ".world"));
						double x = Main.getInstance().getConfig().getDouble("home." + p.getUniqueId() + "." + args[0] + ".x");
						double y = Main.getInstance().getConfig().getDouble("home." + p.getUniqueId() + "." + args[0] + ".y");
						double z = Main.getInstance().getConfig().getDouble("home." + p.getUniqueId() + "." + args[0] + ".z");
						double pitch = Main.getInstance().getConfig().getDouble("home." + p.getUniqueId() + "." + args[0] + ".pitch");
						double yaw = Main.getInstance().getConfig().getDouble("home." + p.getUniqueId() + "." + args[0] + ".yaw");
						p.teleport(new Location(w, x, y, z, (float) yaw, (float) pitch));
						p.sendMessage("");
						p.sendMessage("§eTéléportation vers §6§l" + args[0] + " §e!");
					} else {
						p.sendMessage("");
						p.sendMessage("§cLe home n'exste pas !");
					}
					return false;
				}

				if (args.length >= 2) {
					p.sendMessage("");
					p.sendMessage("§cUtilisation : /home <nom>");
					return false;
				}

			}
			*/
		} else {
			player.sendMessage("");
			player.sendMessage("§cLes homes sont seulement autorisés dans les mondes : §6§lSurvie");
		}
		
		return false;
	}

	private void setHome(Connection connection, UUID uuid, String name, String world, float x, float y, float z, float pitch, float yaw) {
		try {
			final PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO survival_home VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			final Timestamp timestamp = new Timestamp(System.currentTimeMillis());

			preparedStatement.setString(1, uuid.toString());
			preparedStatement.setString(2, name);
			preparedStatement.setString(3, world);
			preparedStatement.setFloat(4, x);
			preparedStatement.setFloat(5, y);
			preparedStatement.setFloat(6, z);
			preparedStatement.setFloat(7, pitch);
			preparedStatement.setFloat(8, yaw);
			preparedStatement.setTimestamp(9, timestamp);
			preparedStatement.setTimestamp(10, timestamp);

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void deleteHome(Connection connection, UUID uuid, String name) {
		try {
			final PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM survival_home WHERE (uuid = ? AND name = ?)");

			preparedStatement.setString(1, uuid.toString());
			preparedStatement.setString(2, name);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
