package orm.archivarius.database;


import orm.archivarius.logger.ProgramLogger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDao {
    private static DBConfiguration database;
    private static Connection connection;

    public ConnectionDao(DBConfiguration database) {
        ConnectionDao.database = database;
        connection = null;
    }

    public Connection getConnection() throws SQLException {
        try {
            Class.forName(database.getConnectionDriver());
            connection = DriverManager.getConnection(database.getUrl(), database.getUser(), database.getPassword());
            connection.setSchema("archivarius_test");
            ProgramLogger.getProgramLogger().addLogInfo("Successfully connected to => " + database.getDatabaseName());
        } catch (ClassNotFoundException e) {
            connection = null;
            ProgramLogger.getProgramLogger().addLogInfo("Failed to connect => " + database.getDatabaseName());
        }
        return connection;
    }
}
