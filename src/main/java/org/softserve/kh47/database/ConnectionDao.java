package org.softserve.kh47.database;

import org.softserve.kh47.logger.ProgramLogger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDao {
    private static DBConfiguration database;
    private static Connection connection;

    public ConnectionDao(DBConfiguration database){
        ConnectionDao.database = database;
        connection = null;
    }

    public Connection getConnection() throws SQLException {
        try{
            Class.forName(database.getConnectionDriver());
            connection =  DriverManager.getConnection(database.getUrl(), database.getUser(), database.getPassword());
            ProgramLogger.getProgramLogger().addLogInfo("Successfully connected to => " + database.getDatabaseName());
        } catch (ClassNotFoundException e){
            connection = null;
            ProgramLogger.getProgramLogger().addLogInfo("Failed to connect => " + database.getDatabaseName());
        }
        return connection;
    }
}
