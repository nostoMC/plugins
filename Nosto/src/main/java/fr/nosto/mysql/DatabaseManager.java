package fr.nosto.mysql;

import java.sql.SQLException;

public class DatabaseManager {
    private DbConnection dbConnection;

    public DatabaseManager() {
        this.dbConnection = new DbConnection(new DbCredentials("minecraft55.omgserv.com", "minecraft_330185", "<6Yw{+waKAXBm-qB", "minecraft_330185", 3306));
    }

    public void close() {
        try {
            this.dbConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
