package orm.archivarius.database.util;

import orm.archivarius.database.DBConfiguration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
    public PropertiesReader(String filename, DBConfiguration conf) {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(filename)) {
            Properties props = new Properties();
            props.load(input);

            conf.setUrl(props.getProperty("url"));
            conf.setUser(props.getProperty("user"));
            conf.setPassword(props.getProperty("password"));
            conf.setConnectionDriver(props.getProperty("driver"));
            conf.setDatabaseName(props.getProperty("db"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
