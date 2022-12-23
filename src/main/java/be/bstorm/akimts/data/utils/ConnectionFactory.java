package be.bstorm.akimts.data.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String URL = "jdbc:sqlserver://localhost:1433;" +
            "encrypt=true;" +
            "databaseName=cinema_manager;" +
            "integratedSecurity=true;" +
            "trustServerCertificate=true;";


    public static Connection createConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }

}
