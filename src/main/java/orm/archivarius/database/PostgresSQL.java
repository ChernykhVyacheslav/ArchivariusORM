package orm.archivarius.database;

import orm.archivarius.database.util.PropertiesReader;

import java.util.Objects;

public class PostgresSQL extends DBConfiguration{
    private static PostgresSQL postgresInstance;
    public PropertiesReader propertiesReader;

    public PostgresSQL(){
        this.propertiesReader = new PropertiesReader("postgres.properties",this);
    }

    public static PostgresSQL getInstance() {
        return Objects.isNull(postgresInstance) ? postgresInstance = new PostgresSQL() : postgresInstance;
    }
}
