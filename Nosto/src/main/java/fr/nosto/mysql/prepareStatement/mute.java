package fr.nosto.mysql.prepareStatement;

import fr.nosto.Main;
import fr.nosto.mysql.DbConnection;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.sql.*;

public class mute {

    private static Connection connection;

    public static void setup() throws SQLException {

        final DbConnection dbConnection = Main.getDatabaseManager().getDbConnection();
        connection = dbConnection.getConnection();
    }

    public static void add(OfflinePlayer target, Player master, String raison, Timestamp date) throws SQLException {

        final PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO player_mute VALUES(?, ?, ?, ?)");

        preparedStatement.setString(1, target.getUniqueId().toString());
        preparedStatement.setString(2, master.getUniqueId().toString());
        preparedStatement.setString(3, raison);
        preparedStatement.setTimestamp(4, date);

        preparedStatement.executeUpdate();

    }

    public static ResultSet check(OfflinePlayer target) throws SQLException {

        final PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM player_mute WHERE (uuid = ? AND end_date > ?)");

        preparedStatement.setString(1, target.getUniqueId().toString());
        preparedStatement.setTimestamp(2, new Timestamp(System.currentTimeMillis()));

        return preparedStatement.executeQuery();
    }

    public static void remove(OfflinePlayer target, Timestamp date) throws SQLException {

        final PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM player_mute WHERE (uuid = ? AND end_date = ?)");

        preparedStatement.setString(1, target.getUniqueId().toString());
        preparedStatement.setTimestamp(2, date);

        preparedStatement.executeUpdate();
    }

}
