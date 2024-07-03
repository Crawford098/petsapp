package org.santana.service.database;
import java.sql.Connection;
import java.sql.SQLException;

public interface IConnections {
    public Connection getConnection() throws SQLException ;
}