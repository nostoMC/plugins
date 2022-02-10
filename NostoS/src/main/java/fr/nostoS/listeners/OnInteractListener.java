package fr.nostoS.listeners;

import fr.nostoS.Utils;
import fr.nostoS.mysql.DbConnection;
import org.bukkit.Chunk;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OnInteractListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {

        Player player = event.getPlayer();

        if (Utils.getSurviesNames().contains(player.getWorld().getName())) {

            if (event.getClickedBlock() != null) {

                final DbConnection dbConnection = Utils.getDatabaseManager().getDbConnection();
                try {
                    Chunk chunk = event.getClickedBlock().getChunk();
                    String chunkID = chunk.getX() + "_" + chunk.getZ();

                    final Connection connection = dbConnection.getConnection();
                    final PreparedStatement preparedStatement = connection.prepareStatement("SELECT uuid, chunkID FROM survival_claim WHERE (uuid = ? AND chunkID = ?)");
                    preparedStatement.setString(1, player.getUniqueId().toString());
                    preparedStatement.setString(2, chunkID);
                    final ResultSet resultSet = preparedStatement.executeQuery();

                    if (!resultSet.next()) {
                        if (!player.isOp()) {
                            event.setCancelled(true);
                            player.sendMessage("\n§cCe chunk a été claim. Tu ne peut donc rien faire dans cette zone.");
                        }

                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }

    }

}
