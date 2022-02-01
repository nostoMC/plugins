package fr.nosto.mysql;

import java.sql.SQLException;

public class DatabaseManager {
    private DbConnection dbConnection;

    public DatabaseManager(String host, String user, String pass, String dbName) {
        this.dbConnection = new DbConnection(new DbCredentials(host, user, pass, dbName, 3306));
    }

    public void close() {
        try {
            this.dbConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
