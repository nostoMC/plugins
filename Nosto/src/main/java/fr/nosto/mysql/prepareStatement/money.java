package fr.nosto.mysql.prepareStatement;

import fr.nosto.Main;
import fr.nosto.mysql.DbConnection;
import org.bukkit.entity.Player;

import java.sql.*;

public class money {

    private static Connection connection;

    public static void setup() throws SQLException {

        final DbConnection dbConnection = Main.getDatabaseManager().getDbConnection();
        connection = dbConnection.getConnection();

    }

    public static void setDefaultMoney(Player player) throws SQLException {

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
