package orm.archivarius.database;

public class DBConfiguration {
    private String url;
    private String user;
    private String password;
    private String connectionDriver;
    private String databaseName;

    public DBConfiguration() {}

    public String getUrl() { return url; }
    public String getUser() { return user; }
    public String getPassword() {
        return this.password;
    }
    public String getConnectionDriver() { return connectionDriver; }
    public String getDatabaseName() { return databaseName; }

    public void setUrl(String url) { this.url = url; }
    public void setUser(String user) {
        this.user = user;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setConnectionDriver(String connectionDriver) { this.connectionDriver = connectionDriver; }
    public void setDatabaseName(String databaseName) { this.databaseName = databaseName; }

    @Override
    public String toString() {
        return "DBConfiguration{" +
                "url='" + url + '\'' +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                ", connectionDriver='" + connectionDriver + '\'' +
                ", databaseName='" + databaseName + '\'' +
                '}';
    }
}
