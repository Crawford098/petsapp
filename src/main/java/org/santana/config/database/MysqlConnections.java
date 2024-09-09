package org.santana.config.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlConnections {

    // WINDOW: 123456
    // MAC: root
    // private static final String URL = "jdbc:mysql://localhost:8889/pets_app";
    private static final String URL = "jdbc:mysql://localhost:3306/pets_app";
    private static final String USER = "root";
    // private static final String PASSWORD = "root";
    private static final String PASSWORD = "jonasde098";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}