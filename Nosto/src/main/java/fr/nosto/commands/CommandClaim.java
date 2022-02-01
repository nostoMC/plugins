package fr.nosto.commands;

import fr.nosto.mysql.DbConnection;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.nosto.Main;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.UUID;

public class CommandClaim implements CommandExecutor
{
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {

        if (sender instanceof Player player) {

			if (player.getWorld() == Bukkit.getWorld("survie")) {

				final DbConnection dbConnection = Main.databaseManager.getDbConnection();

				try {
					final Connection connection = dbConnection.getConnection();

					if (cmd.getName().equalsIgnoreCase("claim")) {

						final Chunk chunk = player.getLocation().getChunk();
						final String chunkID = chunk.getX() + "_" + chunk.getZ();

						final PreparedStatement preparedStatement = connection.prepareStatement("SELECT uuid, chunkID FROM survival_claim WHERE chunkID = ?");
						preparedStatement.setString(1, chunkID);
						final ResultSet resultSet = preparedStatement.executeQuery();

						if (resultSet.next()) {
							player.sendMessage("");
							player.sendMessage("§cCe chunk est déjà claim !");

						} else {
							addClaim(connection, player.getUniqueId(), chunkID);
							player.sendMessage("");
							player.sendMessage("§eVous avez claim ce chunk !");
						}

					}

				} catch (SQLException e) {
					e.printStackTrace();
				}
            	/*
            	if (cmd.getName().equalsIgnoreCase("unclaim")) {

            		Chunk chunk = player.getLocation().getChunk();

            		String chunkID = chunk.getX() + "_" + chunk.getZ();
            	
            		if (Main.getInstance().getConfig().getString("claim." + chunkID).equals(player.getUniqueId().toString())) {

            			if (Main.getInstance().getConfig().contains("claim." + chunkID)) {
            				Main.getInstance().getConfig().set("claim." + chunkID, null);
							Main.getInstance().saveConfig();
            				player.sendMessage("");
                			player.sendMessage("§eVous avez unclaim ce chunk !");
            			} else {
            				player.sendMessage("");
            				player.sendMessage("§cCe chunk n'est pas claim !");
            			}
            		} else {
            			player.sendMessage("");
            			player.sendMessage("§cVous ne possédez pas ce chunk !");
            		}
            	}

            	 */
            } else {
            	player.sendMessage("");
            	player.sendMessage("§cLes claim sont seulement autorisés dans les mondes : §6§lSurvie");
            }
        }
        return true;
    }

	private void addClaim(Connection connection, UUID uuid, String chunkID) {
		try {
			final PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO survival_claim VALUES(?, ?, ?, ?)");
			final Timestamp timestamp = new Timestamp(System.currentTimeMillis());

			preparedStatement.setString(1, uuid.toString());
			preparedStatement.setString(2, chunkID);
			preparedStatement.setTimestamp(3, timestamp);
			preparedStatement.setTimestamp(4, timestamp);

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
