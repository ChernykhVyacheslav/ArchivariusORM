package orm.archivarius.database;

import orm.archivarius.database.util.PropertiesReader;

import java.util.Objects;

public class MySQL extends DBConfiguration{
    private static MySQL mysqlInstance;
    public PropertiesReader propertiesReader;

    public MySQL(){
        this.propertiesReader = new PropertiesReader("mysql.properties",this);
    }

    public static MySQL getInstance() {
        return Objects.isNull(mysqlInstance) ? mysqlInstance = new MySQL() : mysqlInstance;
    }
}
