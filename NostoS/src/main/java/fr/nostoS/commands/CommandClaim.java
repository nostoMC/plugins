package fr.nostoS.commands;

import fr.nostoS.Utils;
import fr.nostoS.mysql.DbConnection;
import org.bukkit.Chunk;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.UUID;

public class CommandClaim implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {

        if (sender instanceof Player) {

            Player player = (Player) sender;

            if (Utils.getSurviesNames().contains(player.getWorld().getName())) {

                final DbConnection dbConnection = Utils.getDatabaseManager().getDbConnection();

                try {
                    final Connection connection = dbConnection.getConnection();

                    final Chunk chunk = player.getLocation().getChunk();
                    final String chunkID = chunk.getX() + "_" + chunk.getZ();

                    if (cmd.getName().equalsIgnoreCase("claim")) {

                        final PreparedStatement preparedStatement = connection.prepareStatement("SELECT uuid, chunkID FROM survival_claim WHERE chunkID = ?");
                        preparedStatement.setString(1, chunkID);
                        final ResultSet resultSet = preparedStatement.executeQuery();

                        if (resultSet.next()) {
                            player.sendMessage("\n§cCe chunk est déjà claim !");

                        } else {
                            addClaim(connection, player.getUniqueId(), chunkID);
                            player.sendMessage("\n§bVous avez claim ce chunk !");
                        }

                    } else if (cmd.getName().equalsIgnoreCase("unclaim")) {

                        final PreparedStatement preparedStatement = connection.prepareStatement("SELECT uuid, chunkID FROM survival_claim WHERE (uuid = ? AND chunkID = ?)");
                        preparedStatement.setString(1, player.getUniqueId().toString());
                        preparedStatement.setString(2, chunkID);
                        final ResultSet resultSet = preparedStatement.executeQuery();

                        if (resultSet.next()) {
                            deleteClaim(connection, player.getUniqueId(), chunkID);
                            player.sendMessage("\n§bVous avez unclaim ce chunk !");

                        } else {
                            player.sendMessage("\n§cVous ne pouvez pas unclaim ce chunk !");
                        }
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } else {
                player.sendMessage("\n§cLes claim sont seulement autorisés dans les mondes : §6§lSurvie");
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

    private void deleteClaim(Connection connection, UUID uuid, String chunkID) {
        try {
            final PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM survival_claim WHERE (uuid = ? AND chunkID = ?)");

            preparedStatement.setString(1, uuid.toString());
            preparedStatement.setString(2, chunkID);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
